import numpy as np

from tensorflow.keras.models import Sequential, Model, load_model
from tensorflow.keras.layers import Dense, SimpleRNN, LSTM, Dropout, Conv2D, Flatten, MaxPooling2D, Conv1D
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint
from tensorflow.keras.datasets import mnist, cifar10, cifar100, fashion_mnist

from sklearn.datasets import fetch_california_housing
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler, StandardScaler
from sklearn.metrics import mean_squared_error, r2_score


# 1. Data
dataset = fetch_california_housing()

x = dataset.data # for training
y = dataset.target # for predict

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    train_size=0.7,
    random_state=123
)

scaler = StandardScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test)

print(x_train.shape, x_test.shape) # (14447, 8) (6193, 8)

x_train = x_train.reshape(14447, 4, 2)
x_test = x_test.reshape(6193, 4, 2)
# datasets 개수 부분 제외하고 전체 곱만 같으면 문제 X
# (14447,8,1) = (14447,4,2) = (14447,2,4) = (14447,8,1)


# 2. Model Construction
model = Sequential()
model.add(Conv1D(128, 2, padding='same', input_shape=(4,2))) 
model.add(Conv1D(64, 2, padding='same')) 
model.add(Dropout(0.2))
model.add(Conv1D(32, 2, padding='same'))
model.add(Flatten())
model.add(Dense(16, activation='relu'))
model.add(Dense(1))


# 3. Compile and Training
model.compile(loss='mse', optimizer='adam')

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=32, restore_best_weights=True, verbose=1)

model.fit(x_train, y_train,
          validation_split=0.2,
          epochs=128,
          callbacks=[earlyStopping],
          batch_size=32)


# 4. Evaluation and Prediction
result = model.evaluate(x_test, y_test)
print("loss: ", result)

y_predict = model.predict(x_test)

r2 = r2_score(y_test, y_predict)
print("R2: ", r2)



'''
Result
loss:  0.3196415901184082
R2:  0.7582663893325312

'''