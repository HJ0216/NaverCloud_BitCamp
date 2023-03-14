import numpy as np
from tensorflow.keras.layers import Dense
from tensorflow.keras.models import Sequential


# 1. Data
x = np.array([1,2,3,4,5,6])
y = np.array([1,2,3,5,4,6])


# 2. Model
# Model에 Data를 넣을 때는 리스트의 요소를 1개씩을 넣는 것이 아니라 리스트 or 배열 단위로 Data를 넣음
# 문제1        : list의 element가 과다하면 비효율적인 훈련이 될 수 있기 때문에 batch 단위로 나누어 훈련 시킴
# 해결1 및 문제2: batch로 나누어 훈련을 시킬 경우, 시간이 오래 걸림
#         해결2: 알맞은 batch size 찾기
model = Sequential()
model.add(Dense(64, input_dim=1))
model.add(Dense(64))
model.add(Dense(32))
model.add(Dense(16))
model.add(Dense(1))


# 3. Complile, training
model.compile(loss='mae', optimizer='adam')
model.fit(x, y, epochs=256, batch_size=8)
# Batch_size Default: 32


# 4. Evaluation, Prediction
result = model.predict([7])
print("7의 결과: ", result)



"""
Result

array([1,2,3,4,5,6])
Batch_size: 1 6/6 [==============================] batch_size = element_size
Batch_size: 2 3/3 [==============================] 2개씩 묶어서 총 3번의 훈련을 함
Batch_size: 3 2/2 [==============================]
Batch_size: 4 2/2 [==============================] 나머지가 있을 경우, 나머지끼리 훈련
Batch_size: 6 1/1 [==============================]
Batch_size: If unspecified, batch_size will default to 32

result = model.predict([7])
7의 결과:  [[6.864681]]

"""