import numpy as np

from tensorflow.keras.models import Sequential, Model, load_model
from tensorflow.keras.layers import Dense, SimpleRNN, LSTM, Dropout, Conv2D, Flatten, MaxPooling2D
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint

from sklearn.datasets import load_boston
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler, StandardScaler
from sklearn.metrics import mean_squared_error, r2_score


# 1. Data
dataset = load_boston()

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

print(x_train.shape, x_test.shape)
# (354, 13), (152, 13)

x_train = x_train.reshape(354, 13, 1)
x_test = x_test.reshape(152, 13, 1)


# 2. Model Construction
model = Sequential()
model.add(LSTM(units=64, input_shape=(13,1)))
# reshape 시, timesteps*feature가 유지되도록 reshape
model.add(Dense(64, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(16, activation='relu'))
model.add(Dense(8, activation='relu'))
model.add(Dense(1))
model.summary()


# 3. Compile and Training
model.compile(loss='mse', optimizer='adam')

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=32, restore_best_weights=True, verbose=1)

model.fit(x_train, y_train, epochs=64, callbacks=[earlyStopping], validation_split=0.5, batch_size=8)


# 4. Evaluation and Prediction
loss = model.evaluate(x_test,y_test)
y_predict = model.predict(x_test)

def RMSE (y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))
print("RMSE: ", RMSE(y_test, y_predict))

r2 = r2_score(y_test, y_predict)
print("R2: ", r2)


'''
Result
RMSE:  4.848290913688558
R2:  0.7091861963163169

'''