import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense


# 1. Data
x = np.array([1,2,3,4,5,6,7,8,9,10])
y = np.array(range(10)) # [0,1,2,3,4,5,6,7,8,9]
# train set과 test set을 구분

x_train = np.array([1, 2, 3, 4, 5, 6, 7]) # (7, )
x_test = np.array([8, 9, 10]) # (3, )

y_train = np.array(range(7)) # 0~6
y_test = np.array(range(7, 10)) # 7~9


# 2. Model Construction
model = Sequential()
model.add(Dense(64, input_dim=1))
model.add(Dense(32))
model.add(Dense(16))
model.add(Dense(8))
model.add(Dense(1))


# 3. Compile and train
model.compile(loss='mae', optimizer='adam')
model.fit(x_train, y_train, epochs=256, batch_size=16)


# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
print("Loss: ", loss)

result = model.predict([11])
print("Result: ", result)



'''
# Result

model.fit(x_train, y_train, epochs=1000, batch_size=1)
Epoch 1000/1000
7/7 [==============================] - 0s 833us/step - loss: 0.1056

loss = model.evaluate(x_test, y_test)
1/1 [==============================] - 0s 114ms/step - loss: 0.3096

result = model.predict([11])
Result:  [[10.397283]]

'''