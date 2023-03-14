import numpy as np
import pandas as pd 
import random
import os

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, MaxPooling2D, Dropout, Flatten, Dense
from tensorflow.keras.callbacks import EarlyStopping
from tensorflow.keras.utils import to_categorical
from tensorflow.keras.preprocessing.image import ImageDataGenerator, load_img

from sklearn.model_selection import train_test_split


# 1. Data
IMAGE_WIDTH=128
IMAGE_HEIGHT=128
IMAGE_SIZE=(IMAGE_WIDTH, IMAGE_HEIGHT)
IMAGE_CHANNELS=3

filenames = os.listdir("D:/_data/dogs-vs-cats/train/")
categories = []
for filename in filenames:
    category = filename.split('.')[0]
    if category == 'dog':
        categories.append(1)
    else:
        categories.append(0)

df = pd.DataFrame({
    'filename': filenames,
    'category': categories
})

df["category"] = df["category"].replace({0: 'cat', 1: 'dog'}) 

train_df, validate_df = train_test_split(df, test_size=0.20, random_state=42)
train_df = train_df.reset_index(drop=True)
validate_df = validate_df.reset_index(drop=True)

total_train = train_df.shape[0]
total_validate = validate_df.shape[0]
batch_size=64

train_datagen = ImageDataGenerator(
    rotation_range=15,
    rescale=1./255,
    shear_range=0.1,
    zoom_range=0.2,
    horizontal_flip=True,
    width_shift_range=0.1,
    height_shift_range=0.1
)

train_generator = train_datagen.flow_from_dataframe(
    train_df, 
    "D:/_data/dogs-vs-cats/train/", 
    x_col='filename',
    y_col='category',
    target_size=IMAGE_SIZE,
    class_mode='categorical',
    batch_size=batch_size
)

x_train = np.load('D:/_data/dogs-vs-cats/numpy/cat_dog_x_train.npy')
y_train = np.load('D:/_data/dogs-vs-cats/numpy/cat_dog_y_train.npy')

x_val = np.load('D:/_data/dogs-vs-cats/numpy/cat_dog_x_val.npy')
y_val = np.load('D:/_data/dogs-vs-cats/numpy/cat_dog_y_val.npy')

print(x_train.shape, x_val.shape) # (20000, 128, 128, 3) (5000, 128, 128, 3)
print(y_train.shape, y_val.shape) # (20000, 2) (5000, 2)


# 2. Model
model = Sequential()
model.add(Conv2D(64, (3, 3), activation='relu', input_shape=(IMAGE_WIDTH, IMAGE_HEIGHT, IMAGE_CHANNELS)))
model.add(MaxPooling2D(pool_size=(2, 2)))
model.add(Conv2D(32, (3, 3), activation='relu'))
model.add(MaxPooling2D(pool_size=(2, 2)))
model.add(Conv2D(16, (3, 3), activation='relu'))
model.add(MaxPooling2D(pool_size=(2, 2)))
model.add(Dropout(0.25))
model.add(Flatten())
model.add(Dense(2, activation='softmax'))
model.summary()


# 3. Compile and Train
model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['acc'])

earlystop = EarlyStopping(monitor='val_acc', mode='max', patience=64,
                              restore_best_weights=True,
                              verbose=1)

hist = model.fit(x_train, y_train,
                    epochs=2,
                    batch_size=64,
                    validation_data=(x_val, y_val),
                    callbacks=[earlystop],
                    verbose=1)


# 4. evaluate and predict
accuracy = hist.history['acc']
val_acc = hist.history['val_acc']
print("Accuracy: ", accuracy[-1])
print("Val_acc: ", val_acc[-1])

loss = hist.history['loss']
val_loss = hist.history['val_loss']
print("Loss: ", loss[-1])
print("Val_Loss: ", val_loss[-1])

'''
Result
Accuracy:  0.9463000297546387
Val_acc:  0.8547999858856201

Loss:  0.13410215079784393
Val_Loss:  0.5559490323066711

'''


# 5. Submission
test_filenames = os.listdir("D:/_data/dogs-vs-cats/test1")
test_df = pd.DataFrame({
    'filename': test_filenames
})
nb_samples = test_df.shape[0]
'''
print(test_df)

        filename
0          1.jpg
1         10.jpg
2        100.jpg
3       1000.jpg
4      10000.jpg

print(test_df.shape[0]) # 12500
print(test_df.shape[1]) # 1

'''

test_gen = ImageDataGenerator(rescale=1./255)

test_generator = test_gen.flow_from_dataframe(
    test_df, 
    "D:/_data/dogs-vs-cats/test1/", 
    x_col='filename',
    y_col=None,
    class_mode=None,
    target_size=IMAGE_SIZE,
    batch_size=batch_size,
    shuffle=False
)
# generator 거쳐서 image shape이 변화 (128,128,)


# for submission
predict = model.predict_generator(test_generator, steps=np.ceil(nb_samples/batch_size))

test_df['category'] = np.argmax(predict, axis=-1)
# axis=-1: 2차원일 때는 y축, 3차원일 때는 z축

label_map = dict((v,k) for k,v in train_generator.class_indices.items()) # train_generator의 class 확인
test_df['category'] = test_df['category'].replace(label_map)
test_df['category'] = test_df['category'].replace({ 'dog': 1, 'cat': 0 })

'''
print(test_df)

        filename  category
0          1.jpg         1
1         10.jpg         0
2        100.jpg         1
3       1000.jpg         1
4      10000.jpg         1
'''

submission_df = test_df.copy()
submission_df['id'] = submission_df['filename'].str.split('.').str[0]
submission_df['label'] = submission_df['category']

'''
print(submission_df)

        filename  category     id  label
0          1.jpg         1      1      1
1         10.jpg         0     10      0
2        100.jpg         1    100      1
3       1000.jpg         1   1000      1
4      10000.jpg         1  10000      1
...          ...       ...    ...    ...
12495   9995.jpg         1   9995      1
12496   9996.jpg         1   9996      1
12497   9997.jpg         1   9997      1
12498   9998.jpg         0   9998      0
12499   9999.jpg         1   9999      1
'''

submission_df.drop(['filename', 'category'], axis=1, inplace=True)
# drop: axis=1 -> col, inplace=True (변경된 값을 해당 df에 저장)

'''
print(submission_df)

          id  label
0          1      1
1         10      0
2        100      1
3       1000      1
4      10000      1
...      ...    ...
12495   9995      1
12496   9996      1
12497   9997      1
12498   9998      0
12499   9999      1
'''

submission_df.to_csv('submission.csv', index=False)
# df -> csv, idx=false -> idx를 내보내지 않음
