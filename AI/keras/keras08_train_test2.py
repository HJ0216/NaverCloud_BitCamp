import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense

# 1. Data
x = np.array([1,2,3,4,5,6,7,8,9,10])
y = np.array(range(10)) # 0~9

# numpy data 나누기(train:test)
# train, test set의 범위를 편향되게 할 경우, train or test에 유불리한 결과가 나올 수 있음
# 그렇다고 시작과 끝을 맞춰서 같은 범위 내에서 돌아가게 할 필요는 X (시작과 끝의 데이터를 선택해서 넣게되는 문제가 발생하므로)
x_train = x[:7] # 시작 생략 가능
x_test = x[7:] # 끝 생략 가능
y_train = y[:7]
y_test = y[7:]
print(x_train, x_test, y_train, y_test)

# -로 위치 표현하는 방법
x_train2 = x[:-3]
x_test2 = x[-3:]
print(x_train2, x_test2)


# 2. Model Construction
model = Sequential()
model.add(Dense(64, input_dim=1))
model.add(Dense(64))
model.add(Dense(32))
model.add(Dense(16))
model.add(Dense(8))
model.add(Dense(1))


# 3. Compile and train
model.compile(loss='mae', optimizer='adam')
model.fit(x_train, y_train, epochs=256, batch_size=1)


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