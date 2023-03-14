import numpy as np

from tensorflow.keras.models import Sequential, Model, load_model
from tensorflow.keras.layers import Dense, SimpleRNN, LSTM, Dropout, Conv2D, Flatten, MaxPooling2D
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint
from tensorflow.keras.datasets import mnist, cifar10, cifar100, fashion_mnist

from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler, StandardScaler
from sklearn.metrics import mean_squared_error, r2_score


# 1. Data
(x_train, y_train), (x_test, y_test) = cifar100.load_data()

print(x_train.shape, x_test.shape) # (50000, 32, 32, 3) (10000, 32, 32, 3)

x_train = x_train.reshape(50000, 32, 96)
x_test = x_test.reshape(10000, 32, 96)

x_train=x_train/255.
x_test=x_test/255.

print(np.unique(y_train, return_counts=True))


'''
print(np.unique(y_train, return_counts=True))
(array([ 0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16,
       17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33,
       34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
       51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67,
       68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84,
       85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99]),
array([500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500,
       500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500,
       500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500,
       500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500,
       500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500,
       500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500,
       500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500,
       500, 500, 500, 500, 500, 500, 500, 500, 500], dtype=int64))
'''


# 2. Model Construction
model = Sequential()
model.add(LSTM(units=64, input_shape=(32,96)))
model.add(Dense(32, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dropout(0.2))
model.add(Dense(16, activation='relu'))
model.add(Dense(100, activation='softmax'))


# 3. Compile and Training
model.compile(loss='sparse_categorical_crossentropy', optimizer='adam', metrics=['acc'])

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=64, restore_best_weights=True, verbose=1)

model.fit(x_train, y_train,
          validation_split=0.2,
          epochs=512,
          callbacks=[earlyStopping],
          batch_size=256)


# 4. Evaluation and Prediction
result = model.evaluate(x_test, y_test)
print("loss: ", result[0])
print("acc: ", result[1])

y_predict = model.predict(x_test)
y_predict = np.argmax(y_predict, axis=1)

r2 = r2_score(y_test, y_predict)
print("R2: ", r2)



'''
Result
loss:  3.272155523300171
acc:  0.21330000460147858
R2:  -0.6126769876987699

'''