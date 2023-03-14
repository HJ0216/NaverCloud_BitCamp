import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense


# 1. Data
x = np.array(range(1, 17))
y = np.array(range(1, 17))

# data 분리 2: slicing[초과:이하]
x_train = x[:10] # 시작을 생략하면 처음부터
y_train = y[:10]
x_test = x[10:13]
y_test = y[10:13]
x_validation = x[13:] # 끝을 생략하면 끝까지
y_validation = y[13:]

print(x_train)
print(y_train)
print(x_test)
print(y_test)
print(x_validation)
print(y_validation)


# 2. Model
model = Sequential()
model.add(Dense(32, input_dim=1))
model.add(Dense(16, activation='relu'))
model.add(Dense(8, activation='relu'))
model.add(Dense(1))


# 3. compile and train
model.compile(loss='mse', optimizer='adam')
model.fit(x_train, y_train, epochs=500, batch_size=8,
          validation_data=(x_validation, y_validation))


# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
print("loss: ", loss)

result = model.predict([17])
print("predict [17]: ", result)
