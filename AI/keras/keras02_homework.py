# Target: predict [13]

import numpy as np
# numpy: 고성능의 다차원 배열 객체와 이를 다룰 도구를 제공하는 라이브러리


# 1. Refined Deta
x = np.array([1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
y = np.array([1, 2, 3, 4, 5, 6, 7, 8, 9, 10])


# 2. Model Construction(y=ax+b를 구현하기 위해 import)
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
# Dense: output(y) = kernel(weights) * input(x) + bias

model = Sequential()
# Creates a Sequential model instance.
# layer를 순서대로 쌓아줌
model.add(Dense(1, input_dim=1))


# 3. compile and training(best weight, minimum loss)
model.compile(loss='mae', optimizer='adam')
model.fit(x, y, epochs=256)


# 4. Evaluation and Prediction
result = model.predict([13])
print("Prediction[13] Result: ", result)



'''
Result

model.fit(x, y, epochs=2000)
Epoch 2000/2000
1/1 [==============================] - 0s 1ms/step - loss: 4.7467e-04

result = model.predict([13])
Prediction[13] Result:  [[13.000946]]

'''