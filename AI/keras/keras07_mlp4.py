import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense


# 1. Data
x = np.array([range(10)])
# range(10): [0,1,2,3,4,5,6,7,8,9] 1 vector 창출
y = np.array([[1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
             [1, 1, 1, 1, 2, 1.3, 1.4, 1.5, 1.6, 1.4],
             [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]])

x = x.T # (10, 1), input_dim = 1
y = y.T # (10, 3), output_dim = 3

# 2. Model
model = Sequential()
model.add(Dense(128, input_dim=1))
model.add(Dense(64))
model.add(Dense(16))
model.add(Dense(8))
model.add(Dense(3))


# 3. Compile
model.compile(loss='mae', optimizer='adam')
model.fit(x, y, epochs=256, batch_size=16)


# 4. (Evaluate) and Predict
result = model.predict([[10]]) # predict(x값, input_dim에 맞추기)
print("[10] result: ", result)



'''
# Result1

model.fit(x, y, epochs=1000, batch_size=2)
Epoch 1000/1000
5/5 [==============================] - 0s 998us/step - loss: 0.1198

result = model.predict([[10]])
[10] result:  [[10.696075   1.6338055 -0.7048712]]


# Result2: batch_size가 작아질 수록 loss가 올라감

model.fit(x, y, epochs=1000, batch_size=4)
3/3 [==============================] - 0s 1ms/step - loss: 0.1321

result = model.predict([[10]])
[10] result:  [[11.616149   1.7001956 -1.1217551]]

# Cf. input_dim && output_dim은 Metrix까지만 사용 가능
1. Data
x = np.array([[range(10), range(21, 31), range(201, 211)],
              [range(10), range(21, 31), range(201, 211)]])
y = np.array([[[1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
             [1, 1, 1, 1, 2, 1.3, 1.4, 1.5, 1.6, 1.4]],
             [[1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
             [1, 1, 1, 1, 2, 1.3, 1.4, 1.5, 1.6, 1.4]]]) # (2, 2, 10)

print(x.shape) # (2, 3, 10)
print(y.shape) # (2, 2, 10)

x = x.T
y = y.T

print(x.shape) # (2, 3, 10) input_dim=10
print(y.shape) # (2, 2, 10) output_dim=10

2. Model
model = Sequential()
model.add(Dense(10, input_dim=10)) # input_dim = 3
model.add(Dense(10)) # output_dim = 2
input_dim && output_dim은 Metirx까지만 사용 가능
Error: This TensorFlow binary is optimized with oneAPI Deep Neural Network Library

'''