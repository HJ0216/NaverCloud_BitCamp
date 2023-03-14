import numpy as np

from tensorflow.keras.datasets import mnist, cifar10, cifar100
from tensorflow.keras.models import Sequential, Model
from tensorflow.keras.layers import Conv2D, Flatten, Dense, Dropout, MaxPooling2D, Input
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint

path = './_save/'


# 1. data
(x_train, y_train), (x_test, y_test) = mnist.load_data()
# print(x_train.shape, y_train.shape) # (60000, 28, 28) (60000,)

x_train = x_train.reshape(60000, 28, 28, 1)
x_test = x_test.reshape(10000, 28, 28, 1)


# 2. Model
input1 = Input(shape=(28,28,1))
# Dense 대신 conv2D
dense1 = Conv2D(filters=128,
                 kernel_size=(3, 3),
                 padding='same',
                 strides=1,
                 input_shape=(28, 28, 1),
                 activation='relu')(input1)
dense2 = MaxPooling2D()(dense1)
dense3 = Conv2D(filters=64,
                 kernel_size=(3, 3),
                 padding='same')(dense2)
dense4 = MaxPooling2D()(dense3)
dense5 = Conv2D(filters=32,
                 kernel_size=(3, 3),
                 padding='same')(dense4)
dense6 = Flatten()(dense5)
dense7 = Dense(32, activation='relu')(dense6)
output1 = Dense(10, activation='softmax')(dense7)
model = Model(inputs=input1, outputs=output1)
model.summary()


# 3. Compile and train
model.compile(loss='sparse_categorical_crossentropy', optimizer='adam', metrics=['acc'])

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=32, restore_best_weights=True, verbose=1)

modelCheckPoint = ModelCheckpoint(monitor='val_loss', mode='auto', verbose=1,
                                   save_best_only=True,
                                   filepath=path+'MCP/keras38_1_mnist_MCP.hdf5')

model.fit(x_train, y_train, epochs=256, batch_size=64,
                    validation_split=0.2,
                    callbacks=[earlyStopping, modelCheckPoint],
                    verbose=1)

model.save(path+'keras38_1_mnist_save_model.h5')


# 4. evaluate and predict
result = model.evaluate(x_test, y_test)
print("loss: ", result[0])
print("acc: ", result[1])



'''
Result
loss:  0.061419177800416946
acc:  0.9833999872207642

'''