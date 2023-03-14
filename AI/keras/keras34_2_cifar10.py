import datetime
import numpy as np

from tensorflow.keras.datasets import mnist, cifar10, cifar100
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, Flatten, Dense, Dropout, MaxPooling2D
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint


path = './_save/'
filepath = './_save/MCP/'
filename = '{epoch:04d}-{val_loss:.4f}.hdf5'


# 1. data
(x_train, y_train), (x_test, y_test) = cifar10.load_data()
print(x_train.shape, y_train.shape) # (50000, 32, 32, 3) (50000, 1)
print(x_test.shape, y_test.shape) # (10000, 32, 32, 3) (10000, 1)

# pixel값의 최대 수치인 255로 직접 나눠주어 정규화 scaling
x_train = x_train/255.
x_test = x_test/255.

print(np.unique(y_train, return_counts=True))
'''
(array([0, 1, 2, 3, 4, 5, 6, 7, 8, 9], dtype=uint8),
 array([5000, 5000, 5000, 5000, 5000, 5000, 5000, 5000, 5000, 5000], dtype=int64))
 
'''

# scaling 시, flatten 후 차원 돌려주기


# 2. Model
model = Sequential()
model.add(Conv2D(filters=128,
                 kernel_size=(2, 2),
                 padding='same',
                 input_shape=(32, 32, 3),
                 activation='relu'))
model.add(MaxPooling2D((2, 2)))
model.add(Conv2D(filters=64,
                 kernel_size=(2, 2),
                 padding='same',
                 activation='relu'))
model.add(MaxPooling2D((2, 2)))
model.add(Conv2D(filters=64,
                 kernel_size=(2, 2),
                 padding='same',
                 activation='relu'))
model.add(Flatten())
model.add(Dense(32, activation='relu'))
model.add(Dropout(0.5))
# dropout = rate: Float between 0 and 1. Fraction of the input units to drop.
# 사용하지 않을 node의 비율
model.add(Dense(10, activation='softmax'))


# 3. Compile and train
model.compile(loss='sparse_categorical_crossentropy', optimizer='adam', metrics=['acc'])

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=20, restore_best_weights=True, verbose=1)

date = datetime.datetime.now()
date = date.strftime("%m%d_%H%M")

modelCheckPoint = ModelCheckpoint(monitor='val_loss', mode='auto', verbose=1,
                                   save_best_only=True,
                                   filepath=filepath + 'k34_2_' + date + '_' + filename)


model.fit(x_train, y_train, epochs=128, batch_size=512,
                    validation_split=0.2,
                    callbacks=[earlyStopping, modelCheckPoint],
                    verbose=1)

model.save(path+'keras34_2_cifar10_save_model.h5') # 가중치 및 모델 세이브


# 4. evaluate and predict
result = model.evaluate(x_test, y_test)
print("loss: ", result[0])
print("acc: ", result[1])



'''
Result
loss:  2.3027520179748535
acc:  0.10000000149011612

Result with train and test data scaling
loss:  0.9031396508216858
acc:  0.6916000247001648



'''