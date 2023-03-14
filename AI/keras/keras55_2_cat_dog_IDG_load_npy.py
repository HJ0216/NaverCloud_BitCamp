import numpy as np

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, MaxPooling2D, Dropout, Flatten, Dense
from tensorflow.keras.callbacks import EarlyStopping


# 1. Data
x_train = np.load('D:/_data/dogs-vs-cats/numpy/cat_dog_x_train.npy')
y_train = np.load('D:/_data/dogs-vs-cats/numpy/cat_dog_y_train.npy')

x_val = np.load('D:/_data/dogs-vs-cats/numpy/cat_dog_x_val.npy')
y_val = np.load('D:/_data/dogs-vs-cats/numpy/cat_dog_y_val.npy')

print(x_train.shape, x_val.shape) # (20000, 128, 128, 3) (5000, 128, 128, 3)
print(y_train.shape, y_val.shape) # (20000, 2) (5000, 2)


# 2. Model
IMAGE_WIDTH=128
IMAGE_HEIGHT=128
IMAGE_SIZE=(IMAGE_WIDTH, IMAGE_HEIGHT)
IMAGE_CHANNELS=3

model = Sequential()
model.add(Conv2D(32, 2, activation='relu', input_shape=(IMAGE_WIDTH, IMAGE_HEIGHT, IMAGE_CHANNELS)))
model.add(MaxPooling2D())
model.add(Conv2D(32, 2, activation='relu'))
model.add(MaxPooling2D())
model.add(Conv2D(32, 2, activation='relu'))
model.add(MaxPooling2D())
model.add(Conv2D(32, 2, activation='relu'))
model.add(Flatten())
model.add(Dense(2, activation='softmax')) # 2 because we have cat and dog classes
model.summary()
'''
Model Construction 시 유의 사항
Total params: 32,516,226 -> model parameter가 너무 커지지 않도록 유의
Flatten(): 차원을 줄이기 위해 row*col*channel -> Conv2D, MaxPooling size 줄이기

'''


# 3. Compile and Train
model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['acc'])

earlystop = EarlyStopping(monitor='val_acc', mode='max', patience=64,
                              restore_best_weights=True,
                              verbose=1)

hist = model.fit(x_train, y_train,
                    epochs=256,
                    batch_size=64,
                    validation_data=(x_val, y_val),
                    callbacks=[earlystop],
                    verbose=1)


# 4. evaluate and predict
accuracy = hist.history['acc']
val_acc = hist.history['val_acc']

loss = hist.history['loss']
val_loss = hist.history['val_loss']

print("Loss: ", loss[-1]) # 마지막 훈련의 loss값 추출
print("Val_Loss: ", val_loss[-1])
print("Accuracy: ", accuracy[-1])
print("Val_acc: ", val_acc[-1])



'''
Result
Loss:  0.025188125669956207
Val_Loss:  2.459749460220337
Accuracy:  0.9906499981880188
Val_acc:  0.7961999773979187

'''