import numpy as np
import pandas as pd

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense

from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error, r2_score



# 1. Data
path = './_data/ddarung/'
# 동일한 경로의 파일을 여러번 당겨올 경우, 변수를 지정해서 사용
# ./ = 현재 폴더
# _data/ = _data 폴더
# ddarung/ = ddarung 폴더

train_csv = pd.read_csv(path+'train.csv', index_col=0)
# path + 'train.csv': ./_data/ddarung/train.csv
# index_col을 입력하지 않을 경우 idx도 데이터로 인식하게 됨 (0번째 column은 data가 아닌 idx임을 안내)
print(train_csv) # [1459 rows x 10 columns]
# index_col=0 미 입력시, train_csv = pd.read_csv(path + 'train.csv') print(train_csv) [1459 rows x 11 columns]

test_csv = pd.read_csv(path+'test.csv', index_col=0)
submission = pd.read_csv(path+'submission.csv', index_col=0)

print(train_csv.columns) # sklearn.feature_names, column name

print(train_csv.info())
# null 값 제외 출력
# Int64Index: 715 entries, 총 데이터 수
# 결측치(Missing Value): 총 데이터 수 - Non-Null (수집못한 데이터)
print(test_csv.info())
print(train_csv.describe()) # sklearn.DESC

# 결측치 처리 1. 주변 평균값 2. 0 대입 3. 근처값과 동일 4. 임의의 값 5. 결측 데이터 제거
# 결측치 처리 - '결측 데이터 제거'
print(train_csv.isnull().sum())
# data_set의 결측치(Null) 값 총계 출력
train_csv = train_csv.dropna()
# pandas.dropna(): null 값을 포함한 데이터 행 삭제
print(train_csv.isnull().sum())
# pandas.isnull(): null 값 출력
# pandas.isnull().sum(): null 값 총계 출력
print(train_csv.shape) # (1459, 10) -> (1328, 10)

'''
info -> null이 아닌 값(Non-Null) 출력
isnull -> 결측치(Null) 값 총계 출력
'''



x = train_csv.drop(['count'], axis=1)
# column 명이 'count'인 column 삭제
# axis = 0: idx, axis = 1: column
print(x)
# [1459 rows x 9 columns]: dropna() count column 삭제

y = train_csv['count']
# train_csv에서 col 명이 count인 data set 출력
print(y)
print(y.shape) # (1328,)


x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    shuffle=True,
    train_size=0.7,
    random_state=111
)

print(x_train.shape, x_test.shape) # (929, 9) (399, 9)
print(y_train.shape, y_test.shape) # (929,) (399,)


# 2. model
model = Sequential()
model.add(Dense(64, input_dim=9))
model.add(Dense(64))
model.add(Dense(1))


# 3. compile and train
import time
model.compile(loss='mse', optimizer='adam')
# RMSE가 평가지표이므로 유사한 mse 사용
start = time.time()
model.fit(x_train, y_train, epochs=500, batch_size=32)
end = time.time()


# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
print("Loss: ", loss)

y_predict = model.predict(x_test)
print(y_predict)

def RMSE (y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))

rmse = RMSE(y_test, y_predict)
print("RMSE: ", rmse)

print("소요 시간: ", end - start)

'''
(tf27) cpu 소요 시간:  59.87569808959961
(tf274gpu) gpu 소요 시간:  179.78400087356567
'''

# for submission
y_submit = model.predict(test_csv)
print(y_submit)
print(y_submit.shape) # (715, 1)

print(submission)
submission['count'] = y_submit
print(submission)
# pandas(submission['count'])에 numpy(y_submit)를 직접 대입시키면 numpy가 pandas가 됨

submission.to_csv(path+'submission_01050251.csv')
# (pandas)submission을 cvs로 변환(경로+'파일명.cvs'에 저장)
r2 = r2_score(y_test, y_predict)
print("R2: ", r2)


'''
Result

RMSE:  67.0261917455131


Updated Result

RMSE:  60.13453670580413
R2:  0.5102647475435887
'''