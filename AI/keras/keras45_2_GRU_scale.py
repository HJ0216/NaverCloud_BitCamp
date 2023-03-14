import numpy as np

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, SimpleRNN, LSTM, GRU, Dropout


# 1. Data
x = np.array([[1,2,3], 
              [2,3,4], 
              [3,4,5], 
              [4,5,6], 
              [5,6,7], 
              [6,7,8], 
              [7,8,9],
              [8,9,10], 
              [9,10,11], 
              [10,11,12], 
              [20,30,40], 
              [30,40,50], 
              [40,50,60]]) # (13, 3)
y = np.array([4,5,6,7,8,9,10,11,12,13,50,60,70]) # (13,)

y_predict = np.array([50,60,70])


# 2. Model Construction
model = Sequential()
# model.add(SimpleRNN(units=64, input_shape=(3,1))) # input_length=3, input_dim=1, input_dim 단위로 연산
model.add(GRU(units=128, input_shape=(3,1)))
model.add(Dense(128, activation='relu'))
model.add(Dropout(0.2))
model.add(Dense(64, activation='relu'))
model.add(Dropout(0.1))
model.add(Dense(64, activation='relu'))
model.add(Dense(1))
model.summary()


# 3. Compile and Training
model.compile(loss='mse', optimizer='adam')
model.fit(x, y, epochs=512, batch_size=1)


# 4. Evaluation and Prediction
loss = model.evaluate(x,y)
print("Loss: ", loss)

y_pred = np.array([50,60,70]).reshape(1,3,1)

result = model.predict(y_pred)
print("Predict[50,60,70]: ", result)



'''
Result
Loss:  0.27495041489601135
Predict[50,60,70]:  [[75.708244]]

'''