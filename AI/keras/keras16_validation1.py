import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense


# 1. Data
# data 분리 1: direct
# For train
x_train = np.array(range(1, 11)) # 1,2,3,4,5,6,7,8,9,10
y_train = np.array(range(1, 11))

# For evaluate
x_test = np.array([11,12,13])
y_test = np.array([11,12,13])

# For validatoin
x_validation = np.array([14,15,16])
y_validation = np.array([14,15,16])


# 2. Model
model = Sequential()
model.add(Dense(32, input_dim=1))
model.add(Dense(32, activation='relu'))
model.add(Dense(16, activation='relu'))
model.add(Dense(1))


# 3. compile and train
model.compile(loss='mse', optimizer='adam')
model.fit(x_train, y_train, epochs=500, batch_size=8,
          validation_data=(x_validation, y_validation))
# validation_data를 통해서 val_loss 추가
# 훈련 + '검증(Validation)' + 평가 (fit + 'validation'+ evaluate)


# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
print("loss: ", loss)

result = model.predict([17])
print("predict [17]: ", result)
