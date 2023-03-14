import numpy as np

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, SimpleRNN, LSTM, Dropout



# 1. Data
dataset=np.array([1,2,3,4,5,6,7,8,9,10]) # (10,)
# absence y data

# make a y data
x = np.array([[1,2,3], 
              [2,3,4], 
              [3,4,5], 
              [4,5,6], 
              [5,6,7], 
              [6,7,8], 
              [7,8,9]]) # (7, 3)
y = np.array([4,5,6,7,8,9,10]) # (7,)
# y = np.array([[4],[5],[6],[7],[8],[9],[10]])

x = x.reshape(7,3,1)
# reshape을 안해도 feature에 따라서 1로 변환된 상태
# 1개씩 연산하면서 3개의 데이터를 세트로 해서 총 7 묶음의 데이터 훈련


# 2. Model Construction
model = Sequential()
# model.add(SimpleRNN(64, input_shape=(3,1)))
# model.add(LSTM(64, activation='relu', input_shape=(3,1)))
# 연산과정이 123 -> 4 가 아니라
# 1->2, 2->3, 3->4 과정을 거침
# input_shape(스칼라, 1개씩 연산) <-총 데이터 개수가 빠짐
# input_shape(스칼라, 1) <- 1dms 
model.add(Dense(64, activation='relu', input_shape=(3,)))
model.add(Dense(32, activation='relu'))
model.add(Dropout(0.1))
model.add(Dense(16, activation='relu'))
model.add(Dense(1))
model.summary()


# 3. Compile and Training
model.compile(loss='mse', optimizer='adam')
model.fit(x, y, epochs=1024, batch_size=2)


# 4. evaluation and prediction
loss = model.evaluate(x,y)
print("Loss: ", loss)

# y_pred = np.array([8,9,10]) # shape(3,)

# reshape
y_pred = np.array([8,9,10]).reshape(1,3) # 3 scaler, 1 vector
# y_pred = np.array([8,9,10]).reshape(1,3,1)
# 1개씩 연산, data 크기: 3, 총 data의 수: 1
# [[[8][9][10]]]
# [1234] = [1][2][3][4] 데이터의 순서와 수치가 바뀌지 않으면 reshape 가능


result = model.predict(y_pred)
print("Predict[8,9,10]: ", result)



'''
Result
Loss:  1.1496847867965698
Predict[8,9,10]:  [[9.264934]]

'''