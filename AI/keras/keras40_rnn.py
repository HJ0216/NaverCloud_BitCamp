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
# y = np.array([[4],[5],[6],[7],[8],[9],[10]]) 과 동일

x = x.reshape(7,3,1)
'''
# reshape을 해주는 이유: 추후 1개씩, 2개씩, 3개씩 연산이 필요한 경우가 생기므로
input_dim=1인 경우 생략 가능
x = np.array([[[1],[2],[3]], 
              [[2],[3],[4]], 
              [[3],[4],[5]], 
              [[4],[5],[6]], 
              [[5],[6],[7]], 
              [[6],[7],[8]], 
              [[7],[8],[9]]]) # (7,3,1)

# reshape이 맞는지 확인하는 법
# Data 개수 제외 다른 차원의 개수 모두 곱해서 동일한지 확인

'''


# 2. Model Construction
model = Sequential()
model.add(SimpleRNN(64, input_shape=(3,1)))
# input_dim=1: 1->2, 2->3, 3->4 과정을 거침
model.add(Dense(32, activation='relu'))
model.add(Dropout(0.2))
model.add(Dense(32, activation='relu'))
model.add(Dropout(0.2))
model.add(Dense(16, activation='relu'))
model.add(Dense(1))
model.summary()


# 3. Compile and Training
model.compile(loss='mse', optimizer='adam')
model.fit(x,y, epochs=1024, batch_size=2)


# 4. evaluation and prediction
loss = model.evaluate(x,y)
print("Loss: ", loss)

# y_pred = np.array([8,9,10]) # shape(3,)
y_pred = np.array([8,9,10]).reshape(1,3,1)
# DNN layer는 2차원 이상 -> reshape 필요


# training. (7, 3, 1)
# training data와 predict data의 shape이 다름
result = model.predict(y_pred)
print("Predict[8,9,10]: ", result)



'''

'''