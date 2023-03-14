# improve bike

'''
kaggle.com/competitions/bike-sharing-demand
casual, registered, count 삭제
datetime -> idx 처리

'''

import numpy as np
import pandas as pd

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense

from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error, r2_score


# 1. Data
path = './_data/bike/'
train_csv = pd.read_csv(path+'train.csv', index_col=0)
test_csv = pd.read_csv(path+'test.csv', index_col=0)
submission = pd.read_csv(path+'sampleSubmission.csv', index_col=0)
train_csv = train_csv.dropna()

x = train_csv.drop(['casual', 'registered', 'count'], axis=1)
y = train_csv['count']

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    shuffle=True,
    train_size=0.7,
    random_state=123
)


# 2. model
model = Sequential()
model.add(Dense(64, input_dim=8, activation='linear'))
model.add(Dense(64, activation='sigmoid'))
model.add(Dense(32, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(1))

'''
Case1
activation = linear
RMSE: 157.4314181642394
소요 시간: 18.172726154327393


Case2
activation = sigmoid
RMSE: 263.0283322224913
소요 시간: 17.84301781654358


Case3
activation = relu
RMES: 153.59232454754982
소요 시간: 18.357366800308228

'''


# 3. compile and train
model.compile(loss='mse', optimizer='adam')
model.fit(x_train, y_train, epochs=500, batch_size=16, validation_split=0.25)


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

submission.to_csv(path+'sampleSubmission_2.csv')



'''
Updated Result
RMSE:  152.88134659403144

Updated Result2
RMSE:  149.99826531602525

'''