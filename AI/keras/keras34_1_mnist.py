import numpy as np
import datetime

from tensorflow.keras.datasets import mnist, cifar10, cifar100
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, Flatten, Dense, Dropout
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint


path = './_save/'
filepath = './_save/MCP/'
filename = '{epoch:04d}-{val_loss:.4f}.hdf5'

# 1. data
(x_train, y_train), (x_test, y_test) = mnist.load_data()
print(x_train.shape, y_train.shape)
# (60000, 28, 28) (60000,)
# 행(28), 열(28), 흑백(1-생략)인 이미지 데이터 60000개
# scalar=1인 데이터 60000개
print(x_test.shape, y_test.shape)
'''
reshpae
CNN Conv2D 처리하기 위해 4D(Tensor)화
(60000, 28, 28) -> (60000, 28, 28, 1)
'''

x_train = x_train.reshape(60000, 28, 28, 1)
x_test = x_test.reshape(10000, 28, 28, 1)


print(x_train.shape, y_train.shape) # (60000, 28, 28, 1) (60000,)
print(x_test.shape, y_test.shape) # (10000, 28, 28) (10000,)


# train, test 분리되어있으므로 split 필요 X


print(np.unique(y_train, return_counts=True))

'''
y_train 다중 분류인지 데이터 특성 파악
(array([0, 1, 2, 3, 4, 5, 6, 7, 8, 9], dtype=uint8), # y_calss
array([5923, 6742, 5958, 6131, 5842, 5421, 5918, 6265, 5851, 5949], dtype=int64) y_class의 개수)
'''

'''
scaler = StandardScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test)

ValueError: Found array with dim 4.
StandardScaler expected <= 2.
StandardScaler, MinMaxScaler는 dim=2인 경우에 가능
'''


# 2. Model
model = Sequential()
model.add(Conv2D(filters=128,
                 kernel_size=(2, 2),
                 input_shape=(28, 28, 1),
                 activation='relu')) # Conv2D 후, result (27, 27, 128)
model.add(Conv2D(filters=64,
                 kernel_size=(2, 2))) # Conv2D 후, result (26, 26, 64)
# Sequential Model output->input이므로 입력값 작성 생략
model.add(Conv2D(filters=64,
                 kernel_size=(2, 2))) # Conv2D 후, result (25, 25, 64)
model.add(Flatten()) # input_dim=25*25*64=40000 = column
model.add(Dense(32, activation='relu'))  # 32- 임의
# 60000=batch_size(총 훈련 필요 대상), 40000=input_dim
model.add(Dropout(0.3))
model.add(Dense(10, activation='softmax')) # 10=y_class
# model.summary()


# 3. Compile and train
model.compile(loss='sparse_categorical_crossentropy', optimizer='adam', metrics=['acc'])
# one-hot encoding 안했으므로, sparse
# one-hot encoding 후 (60000, 10=y_class)


earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=20, restore_best_weights=True, verbose=1)

date = datetime.datetime.now()
date = date.strftime("%m%d_%H%M")

modelCheckPoint = ModelCheckpoint(monitor='val_loss', mode='auto', verbose=1,
                                   save_best_only=True,
                                   filepath=filepath + 'k34_1_' + date + '_' + filename)


model.fit(x_train, y_train, epochs=64, batch_size=512,
                    validation_split=0.2,
                    callbacks=[earlyStopping, modelCheckPoint],
                    verbose=1)

model.save(path+'keras34_1_mnist_save_model.h5') # 가중치 및 모델 세이브


# 4. evaluate and predict
result = model.evaluate(x_test, y_test)
print("loss: ", result[0])
print("acc: ", result[1])



'''
Result
loss:  0.11852584034204483
acc:  0.9685999751091003

Updated Result
loss:  0.13651755452156067
acc:  0.975600004196167

'''