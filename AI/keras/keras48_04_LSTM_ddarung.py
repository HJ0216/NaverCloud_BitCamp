import pandas as pd
import numpy as np

from tensorflow.keras.models import Sequential, Model, load_model
from tensorflow.keras.layers import Dense, SimpleRNN, LSTM, Dropout, Conv2D, Flatten, MaxPooling2D
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint

from sklearn.datasets import load_diabetes
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler, StandardScaler
from sklearn.metrics import mean_squared_error, r2_score

path2 = './_save/'


# 1. Data
path = './_data/ddarung/'
train_csv = pd.read_csv(path+'train.csv', index_col=0)
test_csv = pd.read_csv(path+'test.csv', index_col=0)
submission = pd.read_csv(path+'submission.csv', index_col=0)

train_csv = train_csv.dropna()

x = train_csv.drop(['count'], axis=1)
y = train_csv['count']


x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    shuffle=True,
    train_size=0.7,
    random_state=111
)

scaler = MinMaxScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test) # test는 train의 fit 활용
test_csv = scaler.transform(test_csv)

print(x_train.shape, x_test.shape)
print(test_csv.shape) # (715, 9)

x_train = x_train.reshape(929, 9, 1)
x_test = x_test.reshape(399, 9, 1)


# 2. Model Construction
model = Sequential()
model.add(LSTM(units=64, input_shape=(9,1)))
model.add(Dense(32, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(16, activation='relu'))
model.add(Dense(8, activation='relu'))
model.add(Dense(1))


# 3. Compile and Training
model.compile(loss='mse', optimizer='adam')

earlyStopping = EarlyStopping(monitor='loss', mode='min', patience=32, restore_best_weights=True, verbose=1)

model.fit(x_train, y_train, epochs=128, callbacks=[earlyStopping], batch_size=16)


# 4. Evaluation and Prediction
loss = model.evaluate(x_test,y_test)
y_predict = model.predict(x_test)

def RMSE (y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))
print("RMSE: ", RMSE(y_test, y_predict))

r2 = r2_score(y_test, y_predict)
print("R2: ", r2)


# for submission
test_csv = test_csv.reshape(715, 9, 1)
# training이 진행된 모델이 conv2D로 시작되어 input_shape이 4차원으로 이뤄져야 함
# predict에 대입될 데이터도 만들어놓은 모델을 이용하므로 4차원으로 reshape 해줘야 함

y_submit = model.predict(test_csv)
submission['count'] = y_submit
submission.to_csv(path+'submission_0130.csv')



'''
Result
RMSE:  52.98995039505182
R2:  0.6197226542445109

'''