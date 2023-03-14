# improve ddarung

import numpy as np
import pandas as pd

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense

from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error, r2_score


# 1. Data
path = './_data/ddarung/'
train_csv = pd.read_csv(path+'train.csv', index_col=0)
test_csv = pd.read_csv(path+'test.csv', index_col=0)
submission = pd.read_csv(path+'submission.csv', index_col=0)

train_csv = train_csv.dropna()
# pandas.dropna(): null 값을 포함한 데이터 행 삭제

x = train_csv.drop(['count'], axis=1)
y = train_csv['count']

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    shuffle=True,
    train_size=0.7,
    random_state=1234
)

print(x_train.shape, x_test.shape) # (929, 9) (399, 9)
print(y_train.shape, y_test.shape) # (929,) (399,)


# 2. model
model = Sequential()
model.add(Dense(64, input_dim=9)) # input_dim = 9
model.add(Dense(64))
model.add(Dense(1)) # output_dim = 1


# 3. compile and train
model.compile(loss='mse', optimizer='adam')
model.fit(x_train, y_train, epochs=500, batch_size=16)


# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
y_predict = model.predict(x_test)

def RMSE (y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))

rmse = RMSE(y_test, y_predict)
print("RMSE: ", rmse)

r2 = r2_score(y_test, y_predict)
print("R2: ", r2)


# for submission
y_submit = model.predict(test_csv)

submission['count'] = y_submit
submission.to_csv(path+'submission_0106.csv')


'''
Result
RMSE:  67.0261917455131

Updated Result
RMSE:  54.21809318010885



'''