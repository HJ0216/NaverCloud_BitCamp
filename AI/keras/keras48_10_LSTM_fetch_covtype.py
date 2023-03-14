import numpy as np

from tensorflow.keras.utils import to_categorical
from tensorflow.keras.models import Sequential, Model, load_model
from tensorflow.keras.layers import Dense, SimpleRNN, LSTM, Dropout, Conv2D, Flatten, MaxPooling2D
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint

from sklearn.datasets import fetch_covtype
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler, StandardScaler
from sklearn.metrics import accuracy_score, mean_squared_error, r2_score


# 1. Data
dataset = fetch_covtype()
x = dataset.data # for training
y = dataset.target # for predict

y = to_categorical(y)
y = np.delete(y, 0, axis=1)

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    train_size=0.7,
    random_state=123
)

scaler = StandardScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test)

print(x_train.shape, x_test.shape)

x_train = x_train.reshape(406708, 54, 1)
x_test = x_test.reshape(174304, 54, 1)


# 2. Model Construction
model = Sequential()
model.add(LSTM(units=64, input_shape=(54,1)))
model.add(Dense(32, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(16, activation='relu'))
model.add(Dense(8, activation='relu'))
model.add(Dense(7, activation='softmax'))


# 3. Compile and Training
model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['accuracy'])

earlyStopping = EarlyStopping(monitor='loss', mode='min', patience=32, restore_best_weights=True, verbose=1)

model.fit(x, y, epochs=128, callbacks=[earlyStopping], batch_size=256)


# 4. Evaluation and Prediction
loss, accuracy = model.evaluate(x_test, y_test)
print("loss: ", loss)
print("accuracy: ", accuracy)

y_predict = model.predict(x_test)
y_predict = np.argmax(y_predict, axis=1) # (116203, 7) -> (116203, )
y_test = np.argmax(y_test, axis=1) # (116203, 7) -> (116203,)
# data(y): one hot encoding -> shape: (data_num, class)



'''
Result
loss:  0.062044188380241394
acc:  0.9850000143051147
R2:  0.972367969950624

'''