import numpy as np
import datetime

from tensorflow.keras.datasets import mnist, cifar10, cifar100, fashion_mnist
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, Flatten, Dense, Dropout, MaxPooling2D # 2D: 이미지
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint

path = './_save/'
filepath = './_save/MCP/'
filename = '{epoch:04d}-{val_loss:.4f}.hdf5'


# 1. data
(x_train, y_train), (x_test, y_test) = mnist.load_data()
'''
x_train.shape: (60000, 28, 28), x_train.shape: (60000,)
x_test.shape: (10000, 28, 28), x_test.shape: (10000,)
'''

x_train=x_train/255.
x_test=x_test/255.


# 2. Model
model = Sequential()
model.add(Dense(128, activation='relu', input_shape=(28,28)))
# model.add(Dropout(0.3))
model.add(Dense(64, activation='relu'))
# model.add(Dropout(0.3))
model.add(Dense(32, activation='linear'))
model.add(Flatten())
model.add(Dense(10, activation='softmax'))
model.summary()
'''
# Dense Model은 2차원 이상을 input으로 받는 것이 가능
Model: "sequential"
_________________________________________________________________
 Layer (type)                Output Shape              Param #
=================================================================
 dense (Dense)               (None, 28, 128)           3712
                            # output_layer = conv2D filter
 dense_1 (Dense)             (None, 28, 64)            8256

 dense_2 (Dense)             (None, 28, 32)            2080

 flatten (Flatten)           (None, 896)               0

 dense_3 (Dense)             (None, 10)                8970

=================================================================
Total params: 23,018
Trainable params: 23,018
Non-trainable params: 0
_________________________________________________________________

input_shape(None, 28, 28, 1) -> (None, 28, 128)
# output_layer (Conv2D filter)

'''


# 3. Compile and train
model.compile(loss='sparse_categorical_crossentropy', optimizer='adam', metrics=['acc'])

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=32, restore_best_weights=True, verbose=1)

date = datetime.datetime.now()
date = date.strftime("%m%d_%H%M")

modelCheckPoint = ModelCheckpoint(monitor='val_loss', mode='auto', verbose=1,
                                   save_best_only=True,
                                   filepath=filepath + 'k36_dnn1_' + date + '_' + filename)


model.fit(x_train, y_train, epochs=256, batch_size=32,
                    validation_split=0.2,
                    callbacks=[earlyStopping, modelCheckPoint],
                    verbose=1)

model.save(path+'keras36_dnn1_save_model.h5')


# 4. evaluate and predict
result = model.evaluate(x_test, y_test)
print("loss: ", result[0])
print("acc: ", result[1])



'''
Result(CNN)
loss:  0.16121244430541992
acc:  0.9692999720573425

Result(DNN)
loss:  0.0832737535238266
acc:  0.9746000170707703

'''