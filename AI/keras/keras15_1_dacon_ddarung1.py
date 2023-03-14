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
# print(train_csv) [1459 rows x 10 columns]
# print(train_csv.shape) (1459, 10)
# index_col=0 미 입력시, train_csv = pd.read_csv(path + 'train.csv') print(train_csv) [1459 rows x 11 columns]

test_csv = pd.read_csv(path+'test.csv', index_col=0)
# 대회 제출용 data set
submission = pd.read_csv(path+'submission.csv', index_col=0)
# 대회 test_cvs로 submission에 정리해서 제출

print(train_csv.columns) # sklearn.features, column name

print(train_csv.info())
# Int64Index: 715 entries, 총 데이터 수
# 결측치: 총 데이터 수 - Non-Null (수집못한 데이터)
# 결측치 처리 1. 주변 평균값 2. 0 3. 근처값과 동일 4. 임의의 값 5. 삭제
print(test_csv.info())
print(train_csv.describe()) # sklearn.DESC

x = train_csv.drop(['count'], axis=1)
# count column 삭제(axis=0: idx, axis=1: col)
print(x) # [1459 rows x 9 columns]

y = train_csv['count']
print(y) # [1459 rows x 9 columns]

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    shuffle=True,
    train_size=0.7,
    random_state=111
)

print(x_train.shape, x_test.shape) # (1021, 9) (438, 9)
print(y_train.shape, y_test.shape) # (1021,) (438,)


# 2. model
model = Sequential()
model.add(Dense(1, input_dim=9)) # input_dim = data_column, output_dim = 1


# 3. compile and train
model.compile(loss='mse', optimizer='adam')
# RMSE가 평가지표이므로 유사한 mse 사용
model.fit(x_train, y_train, epochs=1, batch_size=32)
# x_train: x = train_csv.drop(['count'], axis=1)
# y_train: y = train_csv['count']
# Raw Data에는 결측치(Missing Value) 존재
# -> loss가 nan으로 추출되고 예측치에도 nan이 나오는 오류가 발생하게 됨


# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
print("Loss: ", loss)

y_predict = model.predict(x_test)
print(y_predict)

from sklearn.metrics import mean_squared_error, r2_score
def RMSE (y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))

rmse = RMSE(y_test, y_predict)
print("RMSE: ", rmse)


# for submission
y_submit = model.predict(test_csv)