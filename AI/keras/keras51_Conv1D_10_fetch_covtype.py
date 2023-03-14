import pandas as pd
import numpy as np

from tensorflow.keras.models import Sequential, Model, load_model
from tensorflow.keras.utils import to_categorical
from tensorflow.keras.layers import Dense, SimpleRNN, LSTM, Dropout, Conv2D, Flatten, MaxPooling2D, Conv1D
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint
from tensorflow.keras.datasets import mnist, cifar10, cifar100, fashion_mnist

from sklearn.datasets import fetch_covtype
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler, StandardScaler
from sklearn.metrics import mean_squared_error, r2_score


# 1. Data
dataset = fetch_covtype()

x = dataset.data # for training
y = dataset.target # for predict

y = to_categorical(y)
print(y.shape) # (581012, 8)

y = np.delete(y, 0, axis=1)
print(y.shape)  # (581012, 7)

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    train_size=0.7,
    random_state=123
)

scaler = StandardScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler .transform(x_test)

print(x_train.shape, x_test.shape) # (406708, 54) (174304, 54)

x_train = x_train.reshape(406708, 9, 6)
x_test = x_test.reshape(174304, 9, 6)


# 2. Model Construction
model = Sequential()
model.add(Conv1D(128, 2, padding='same', input_shape=(9,6))) 
model.add(Conv1D(64, 2, padding='same')) 
model.add(Dropout(0.2))
model.add(Conv1D(32, 2, padding='same'))
model.add(Flatten())
model.add(Dense(16, activation='relu'))
model.add(Dense(7, activation='softmax'))


# 3. Compile and Training
model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['accuracy'])

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=32, restore_best_weights=True, verbose=1)

model.fit(x_train, y_train,
          validation_split=0.2,
          epochs=128,
          callbacks=[earlyStopping],
          batch_size=256)


# 4. Evaluation and Prediction
loss, accuracy = model.evaluate(x_test, y_test)
print("loss: ", loss)
print("accuracy: ", accuracy)

y_predict = model.predict(x_test)
y_predict = np.argmax(y_predict, axis=1) # (116203, 7) -> (116203, )
y_test = np.argmax(y_test, axis=1) # (116203, 7) -> (116203,)
# data y를 one hot encoding 해준 상태로 (data_num, class)로 shape이 return 됨

r2 = r2_score(y_test, y_predict)
print("R2: ", r2)


'''
Result
loss:  0.5182842016220093
accuracy:  0.7794886827468872
R2:  0.42494066843707257

'''