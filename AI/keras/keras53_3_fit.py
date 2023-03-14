# 1. Data
# image training을 위한 이미지 변환

from tensorflow.keras.preprocessing.image import ImageDataGenerator
train_datagen = ImageDataGenerator(
    rescale=1./255.,
    horizontal_flip=True,
    vertical_flip=True,
    width_shift_range=0.1,
    height_shift_range=0.1,
    rotation_range=5,
    zoom_range=1.2,
    shear_range=0.7,
    fill_mode='nearest'
)

test_datagen = ImageDataGenerator(
    rescale=1./255.
)

# directory로부터 data training 전 preprocessing
xy_train = train_datagen.flow_from_directory(
    './_data/brain/train',
    target_size=(100, 100),
    batch_size=1000,
    class_mode='binary',
    color_mode='grayscale',
    shuffle='True', # shuffle: ad, normal
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
# x: image data의 수치화
# y: class(0 1)이 각각 80개씩

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
model.add(Dense(1, activation='sigmoid')) # binary classification
# model.add(Dense(2, activation='softmax')) # binary classification
# model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['acc'])


# 3. Compile and Train
from tensorflow.keras.callbacks import EarlyStopping

model.compile(loss='binary_crossentropy', optimizer='adam', metrics=['acc'])
# hist = model.fit_generator(xy_train, steps_per_epoch=16, epochs=10,
#                     validation_data=xy_test,
#                     validation_steps=4)

earlystop = EarlyStopping(monitor='val_acc', mode='max', patience=64,
                              restore_best_weights=True,
                              verbose=1)

hist = model.fit(xy_train[0][0], xy_train[0][1],
                    batch_size=4,
                    # iteration=10, fit_generator: steps_per_epoch
                    epochs=256,
                    validation_data=(xy_test[0][0], xy_test[0][1]),
                    callbacks=[earlystop],
                    )
# batch_size를 키우고 0번째 행에 모든 데이터가 들어갈 수 있도록 한 후,
# model.fit에서 전체 image data로 훈련

accuracy = hist.history['acc'] # hist의 history의 acc col 내용을 accuracy로 받음
val_acc = hist.history['val_acc']
loss = hist.history['loss']
val_loss = hist.history['val_loss']

print("Loss: ", loss[-1])
print("Val_Loss: ", val_loss[-1])
print("Accuracy: ", accuracy[-1])
print("Val_acc: ", val_acc[-1])


'''
Result
Loss:  2.6830884962691925e-05
Val_Loss:  4.97659158706665
Accuracy:  1.0
Val_acc:  0.5416666865348816

'''