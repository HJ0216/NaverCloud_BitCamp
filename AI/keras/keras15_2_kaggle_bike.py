# kaggle.com/competitions/bike-sharing-demand
# casual, registered, count 삭제
# datetime -> idx 처리


import numpy as np
import pandas as pd
import time

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense

from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error, r2_score


# 1. Data
path = './_data/bike/'
train_csv = pd.read_csv(path+'train.csv', index_col=0)
print(train_csv) # [10886 rows x 11 columns]

test_csv = pd.read_csv(path+'test.csv', index_col=0)
submission = pd.read_csv(path+'sampleSubmission.csv', index_col=0)

print(train_csv.isnull().sum())
# data_set의 결측치(Null) 값 총계 출력
train_csv = train_csv.dropna()
# pandas.dropna(): null 값을 포함한 데이터 행 삭제
print(train_csv.shape) # (10886, 11)

x = train_csv.drop(['casual', 'registered', 'count'], axis=1)
# column 명이 casual, registered 'count'인 column(axis=1) 삭제
# drop function: df.drop(labels=None, axis=0, index=None, columns=None, level=None, inplace=False, errors='raise')
# labels : 삭제할 레이블명
# axis: 0-index처럼 인식, 1-column처럼 인식

# drop으로 column을 삭제하는 이유
# 'casual', 'registered', 'count'를 예측해도 evaluate할 때 필요가 없으므르 column 삭제

print(x)
# [10886 rows x 8 columns] -> dropna && drop로 인한 변경

y = train_csv['count']
# train_csv에서 count col 추출 - pandas의 기능
print(y) # Length: 10886


x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    shuffle=True,
    train_size=0.7,
    random_state=123
)

print(x_train.shape, x_test.shape) # (7620, 8), (3266, 8)
print(y_train.shape, y_test.shape) # (7620,), (3266,)


# 2. model
model = Sequential()
model.add(Dense(32, input_dim=8, activation='linear'))
model.add(Dense(32, activation='sigmoid'))
model.add(Dense(16, activation='sigmoid'))
model.add(Dense(1, activation='sigmoid')) # output_dim = 1
# Activation default: linear

# SIGMOID
# output_dim activation='sigmoid': return value 0~1
# 이진 분류(binary classification): 0(0.5 미만) or 1(0.5 이상) 반환하기 위해 모든 값이 0~1로 나와야하므로 이 때 sigmoid 사용
# Hidden Layer에서 sigmoid를 사용할 수 있으나, 값이 너무 한정적으로 변하기때문에 이진 분류를 제외한 곳에서 사용을 권장하지 않음

# RELU
# relu도 hidden layer에서만 사용 권장 -> output_dim에서 사용 시, 음수값이 왜곡될 가능성이 있음



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
start = time.time()
model.fit(x_train, y_train, epochs=10, batch_size=32)
end = time.time()


# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
# train data 중 test set를 evaluate -> loss 판단
print("Loss: ", loss)

y_predict = model.predict(x_test)
# train data 중 test set를 predict -> y_predict
print(y_predict)

from sklearn.metrics import mean_squared_error, r2_score

def RMSE (y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))

rmse = RMSE(y_test, y_predict)
print("RMSE: ", rmse)
print("소요 시간: ", end - start)

'''
(tf27) cpu 소요 시간:  18.1283917427063
(tf274gpu) gpu 소요 시간:  77.01734590530396
'''

# for submission
y_submit = model.predict(test_csv)
# test data를 predict -> y_submit
print(y_submit)
print(y_submit.shape) # (6493, 1)

print(submission)
submission['count'] = y_submit
# pandas(submission['count'])에 numpy(y_submit)를 직접 대입시키면 numpy가 pandas가 됨
print(submission)

submission.to_csv(path+'sampleSubmission_1.csv')