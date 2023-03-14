import numpy as np
import pandas as pd 
import os

from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, MaxPooling2D, Dropout, Flatten, Dense
from tensorflow.keras.callbacks import EarlyStopping


# 1. Data
IMAGE_WIDTH=300
IMAGE_HEIGHT=300
IMAGE_SIZE=(IMAGE_WIDTH, IMAGE_HEIGHT)
IMAGE_CHANNELS=3

# print(os.listdir("D:/_data/rps"))
# ImageDataGenerator에서 listdir 순으로 번호 부여, ['paper: 0', 'rock: 1', 'scissors: 2']

train_datagen = ImageDataGenerator(
    rescale=1./255,
    horizontal_flip=True,
    vertical_flip=True,
    width_shift_range=0.1,
    height_shift_range=0.1,
    rotation_range=15,
    zoom_range=0.2,
    shear_range=0.1,
    fill_mode='nearest'
)
# Found 2520 images belonging to 3 classes.

xy_train = train_datagen.flow_from_directory(
    'D:/_data/rps',
    target_size=IMAGE_SIZE,
    batch_size=2520,
    class_mode='categorical',
    color_mode='rgb',
    shuffle='True',
    )
# Found 2520 images belonging to 3 classes.

print(xy_train[0][0].shape) # x: (2520, 300, 300, 3)
print(xy_train[0][1].shape) # y: (2520, 3)


# train dataset의 과적합 문제를 해결하기 위해 train data -> train,test split 진행
from sklearn.model_selection import train_test_split

x_data = xy_train[0][0]
y_data = xy_train[0][1]

x_data = x_data.reshape(2520, 270000)
# split을 위한 reshape
# xy_train[0][0] 직접 reshape 시, tuple로 인식되므로 변수로 받아서 reshape

x_train, x_test, y_train, y_test = train_test_split(
    x_data, y_data, train_size=0.7, random_state=444
)

x_train = x_train.reshape(1764, 300 ,300, 3)
x_test = x_test.reshape(756, 300 ,300, 3)
# 기존 shape 되돌리기


# 2. Model
model = Sequential()
model.add(Conv2D(32, (5, 5), activation='relu', input_shape=(IMAGE_WIDTH, IMAGE_HEIGHT, IMAGE_CHANNELS)))
model.add(MaxPooling2D(pool_size=(3, 3)))

model.add(Conv2D(16, (5, 5), activation='relu'))
model.add(MaxPooling2D(pool_size=(3, 3)))

model.add(Conv2D(8, (3, 3), activation='relu'))
model.add(MaxPooling2D(pool_size=(3, 3)))
model.add(MaxPooling2D(pool_size=(2, 2)))

model.add(Flatten())
model.add(Dense(8, activation='relu'))
model.add(Dense(3, activation='softmax'))
model.summary()
# Total params: 32,851


# 3. Compile and Train
model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['acc'])

earlystop = EarlyStopping(monitor='val_acc', mode='max', patience=16,
                              restore_best_weights=True,
                              verbose=1)

hist = model.fit(x_train, y_train,
                    epochs=64,
                    batch_size=32,
                    validation_split=0.3,
                    callbacks=[earlystop],
                    verbose=1)


# 4. evaluate and predict
# fit result
val_acc = hist.history['val_acc']
val_loss = hist.history['val_loss']

print("Val_acc: ", val_acc[-1])
print("Val_Loss: ", val_loss[-1])
'''
Result
Val_acc:  0.9444444179534912
Val_Loss:  0.5943151116371155
'''

# evaluate
loss, accuracy = model.evaluate(x_test, y_test)


# 5. Submission
test_filenames = os.listdir("D:/_data/rps_test")
test_df = pd.DataFrame({
    'filename': test_filenames
})

'''
print(test_df.head())

  filename
0    a.jpg
1    b.jpg
2    c.jpg
3    d.jpg
4    e.jpg

print(test_df.shape[0]) # 26
print(test_df.shape[1]) # 1
'''

test_gen = ImageDataGenerator(rescale=1./255)

test_generator = test_gen.flow_from_dataframe(
    test_df, 
    "D:/_data/rps_test/", 
    x_col='filename',
    y_col=None,
    class_mode=None,
    # no labels are returned, which is useful to use in model.predict_generator()
    # train_generator와 달리 test_generator는 y label를 붙이는 것이 아닌 predict를 통해서 y label을 붙이는 것이므로 class_mode=None
    target_size=IMAGE_SIZE,
    batch_size=26,
    shuffle=False
)
# Found 26 validated image filenames.

# for submission
predict = model.predict_generator(test_generator, steps=1)
# steps(1) * test_generator_batch_size(26) = test_df_number(26)
'''
print(predict[:3])
[[0.14669487 0.69777083 0.15553434]
 [0.17402807 0.51887447 0.3070975 ]
 [0.15882452 0.534536   0.30663946]]
'''

test_df['category'] = np.argmax(predict, axis=-1)
# 모델이 다중분류 처리되어 predict를 return 할 떄, one hot encoding 상태로 반환 -> argmax를 통한 predicted class return
# axis=-1: 2차원일 때는 y축, 3차원일 때는 z축

label_map = dict((v,k) for k,v in xy_train.class_indices.items())
# print(xy_train.class_indices.items())
# ('paper': 0, 'rock': 1, 'scissors': 2)
# print(label_map)
# (0: 'paper', 1: 'rock', 2: 'scissors')
test_df['category'] = test_df['category'].replace(label_map)
# replace(label_map): return 0, 1, 2 -> return 'paper', 'rock', 'scissors'

submission_df = test_df.copy()
submission_df['id'] = submission_df['filename'].str.split('.').str[0]
submission_df['label'] = submission_df['category']
# filename col, categpry col을 각각 새로이 id col과 label col에 담아줌
'''
print(test_df.head())
  filename category id  label
0    a.jpg    paper  a  paper
1    b.jpg    paper  b  paper
2    c.jpg     rock  c   rock
3    d.jpg    paper  d  paper
4    e.jpg     rock  e   rock
'''

submission_df.drop(['filename', 'category'], axis=1, inplace=True)
# id col과 label col과 중복되는 filename col, categpry col 삭제
# drop: axis=1 -> col, inplace=True (변경된 값을 해당 df에 저장)


print(submission_df.head())
'''
print(submission_df.head())

  id label
0  a  rock
1  b  rock
2  c  rock
3  d  rock
4  e  rock
'''

submission_df.to_csv('submission3.csv', index=False)
# df -> csv, idx=false -> idx를 내보내지 않음
