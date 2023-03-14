# 1. Data
from tensorflow.keras.preprocessing.image import ImageDataGenerator

train_datagen = ImageDataGenerator(
    rescale=1./255.,
    horizontal_flip=True, # 수평
    vertical_flip=True, # 수직
    width_shift_range=0.1, # 가로 이동
    height_shift_range=0.1, # 세로 이동
    rotation_range=5,# 훈련 시, 과적합 문제를 해결하기 위해 shift, ratatoin 시행
    zoom_range=1.2, # 20% 확대
    shear_range=0.7, # 절삭
    fill_mode='nearest' # 이동 시, 발생하는 빈 칸을 어떻게 채울 것인가
)

test_datagen = ImageDataGenerator(
    rescale=1./255.
)

xy_train = train_datagen.flow_from_directory(
    './_data/brain/train',
    target_size=(100, 100),
    batch_size=1000, # len(data_variable)
    class_mode='binary',
    color_mode='grayscale',
    shuffle='True', # ad, normal data shuffle
    )
# Found 160 images belonging to 2 classes.

xy_test = train_datagen.flow_from_directory(
    './_data/brain/test',
    target_size=(100, 100),
    batch_size=1000,
    class_mode='binary',
    color_mode='grayscale',
    shuffle='True',
    )
# Found 120 images belonging to 2 classes.
# x,y가 dic 형태로 들어가 있음

# y: 0 1이 각각 80개씩

print(xy_train)
# <keras.preprocessing.image.DirectoryIterator object at 0x000002134BCFCA60>


# 2. Model
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, Conv2D, Flatten

model = Sequential()
model.add(Conv2D(64,(2,2), input_shape=(100,100,1)))
model.add(Conv2D(64, (3,3), activation='relu'))
model.add(Conv2D(64, (3,3), activation='relu'))
model.add(Flatten())
model.add(Dense(16, activation='relu'))
model.add(Dense(16, activation='relu'))
model.add(Dense(1, activation='sigmoid'))


# 3. Compile and Train
from tensorflow.keras.callbacks import EarlyStopping

model.compile(loss='binary_crossentropy', optimizer='adam',
              metrics=['acc'])

earlystop = EarlyStopping(monitor='val_acc', mode='max', patience=32,
                              restore_best_weights=True,
                              verbose=1)

hist = model.fit(xy_train, # x,y를 하나의 변수에 담아 fit 가능
                    # xy_train[0][0], xy_train[0][1],
                    batch_size=4,
                    epochs=128,
                    validation_data=(xy_test[0][0], xy_test[0][1]),
                    # validation_split=0.2,
                    )

accuracy = hist.history['acc']
val_acc = hist.history['val_acc']

loss = hist.history['loss']
val_loss = hist.history['val_loss']

print("Loss: ", loss[-1])
print("Val_Loss: ", val_loss[-1])
print("Accuracy: ", accuracy[-1])
print("Val_acc: ", val_acc[-1])


'''
Result
Loss:  0.39585646986961365
Val_Loss:  0.33949244022369385
Accuracy:  0.875
Val_acc:  0.8500000238418579

'''