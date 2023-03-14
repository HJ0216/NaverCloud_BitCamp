import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense


# 1. data
x = np.array([[1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
             [1, 1, 1, 1, 2, 1.3, 1.4, 1.5, 1.6, 1.4],
             [9, 8, 7, 6, 5, 4, 3 ,2, 1, 0]])
y = np.array([2, 4, 6, 8, 10, 12, 14, 16, 18, 20])

print(x.shape) # (3 vector,10 scalar)
print(y.shape) # (10 scalar,)

x = x.T
# Data의 특성은 열(특성, feature, column)값(scalar)으로 들어가야해서 행렬 변환

print(x.shape)
# x = np.array([1,1,9], 
#              [2,1,8], 
#              [3,1,7], 
#              [4,1,6], 
#              [5,2,5], 
#              [6,1.3,4], 
#              [7,1.4,3], 
#              [8,1.5,2], 
#              [9, 1.6,1], 
#              [10, 1.4,0])


# 2. Model
model = Sequential()
model.add(Dense(64, input_dim=3))
model.add(Dense(32))
model.add(Dense(16))
model.add(Dense(1))


# 3. Compile
model.compile(loss='mae', optimizer='adam')
model.fit(x, y, epochs=256, batch_size=32)
# 2행씩 훈련 5번(5/5)


# 4. Evaluate and Predict
loss = model.evaluate(x, y)
print("loss: ", loss)

result = model.predict([[10, 1.4, 0]])
print("[10, 1.4, 0] result: ", result)



'''
# Result

model.fit(x, y, epochs=300, batch_size=2)
Epoch 300/300
5/5 [==============================] - 0s 750us/step - loss: 0.1140

loss = model.evaluate(x, y)
1/1 [==============================] - 0s 91ms/step - loss: 0.0352

result = model.predict([[10, 1.4, 0]])
[10, 1.4, 0] result:  [[20.014843]]

'''