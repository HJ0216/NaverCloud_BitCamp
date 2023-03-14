import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense

# 1. Data
x = np.array([range(10), range(21, 31), range(201, 211)])
print(x.shape) # (3, 10)

y = np.array([[1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
             [1, 1, 1, 1, 2, 1.3, 1.4, 1.5, 1.6, 1.4]]) # (2, 10)

x = x.T # (10, 3), input_dim = 3(scalar)
y = y.T # (10, 2), output_dim = 2


# 2. Model
model = Sequential()
model.add(Dense(64, input_dim=3))
model.add(Dense(64))
model.add(Dense(32))
model.add(Dense(16))
model.add(Dense(2))


# 3. Compile
model.compile(loss='mae', optimizer='adam')
model.fit(x, y, epochs=256, batch_size=8)


# 4. (Evaluate) and Predict
result = model.predict([[9, 30, 210]])
print("[9, 30, 210] result: ", result)



'''
# Result

model.fit(x, y, epochs=1000) # batch_size: Default
Epoch 1000/1000
1/1 [==============================] - 0s 1000us/step - loss: 0.3318

result = model.predict([[9, 30, 210]])
[9, 30, 210] result:  [[9.433049  1.4707237]]


# Result2 with Batch

model.fit(x, y, epochs=1000, batch_size=2)
Epoch 1000/1000
5/5 [==============================] - 0s 1000us/step - loss: 0.1897

result = model.predict([[9, 30, 210]])
[9, 30, 210] result:  [[10.102887   1.4885552]]

'''