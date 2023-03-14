import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense

# 1. Data
x = np.array([[1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
             [1, 1, 1, 1, 2, 1.3, 1.4, 1.5, 1.6, 1.4]]) # input_dim = 10(행렬에서 열 기준)
# np.array([[element1], [element2]]): 다중 배열을 만들 경우, element1과 element2의 개수를 일치 시켜야 함
y = np.array([2, 4, 6, 8, 10, 12, 14, 16, 18, 20]) # output_dim = 1

# Data.shape: (행: 데이터 개수, 열: 데이터 요소)
print(x.shape) # (2, 10)
print(y.shape) # (10, ) -> keras08 확인

# Data.T 행렬 전환
x = x.T
print(x.shape) # (10, 2)
# x = np.array([1,1], 
#              [2,1], 
#              [3,1], 
#              [4,1], 
#              [5,2], 
#              [6,1.3], 
#              [7,1.4], 
#              [8,1.5], 
#              [9, 1.6], 
#              [10, 1.4])


# 2. Model
model = Sequential()
model.add(Dense(64, input_dim=2))
# input_dim = 2 (입력값 기준으로 열의 개수, Input Layer), output = 5(hidden layer로 임의값 설정 가능)
model.add(Dense(32))
model.add(Dense(32))
model.add(Dense(16))
model.add(Dense(1))
# output_dim = 1 (출력값 기준으로 열의 개수, Output Layer)


# 3. Compile and Train
model.compile(loss='mae', optimizer='adam')
model.fit(x, y, epochs=256, batch_size=8)
# batch를 자르는 기준은 총 data 개수가 아니라, 1열에 포함된 scala 개수


# 4. Evaluate and Predict
loss = model.evaluate(x, y)
# 문제: 훈련한 값(x, y)을 evaluate로 집어넣음
print("loss: ", loss)
# 해결: 훈련 데이터(Tranin Set)와 평가(Test Set) 데이터를 나누기

result = model.predict([[10, 1.4]])
# predict(x1, x2)에서 x2 값을 알 수 없으므로 우선 훈련값(x1, x2)을 넣어서 y와 유사한지 학인
print("[10, 1.4] result: ", result)



'''
# Result

Epoch 100/100
10/10 [==============================] - 0s 667us/step - loss: 0.0817

Evaluate
1/1 [==============================] - 0s 98ms/step - loss: 0.1372

Predict
[10, 1.4] result:  [[19.758385]]

'''