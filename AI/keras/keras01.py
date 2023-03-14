import tensorflow as tf
# tf version print
print(tf.__version__)

import numpy as np

# 1. Deta
x = np.array([1, 2, 3])
y = np.array([1, 2, 3])


# 2. Model Construction
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense

model = Sequential()
model.add(Dense(1, input_dim=1))
# add(Dense(y배열([1, 2, 3])-output, x배열([1, 2, 3])-input))
# dim: Data column 기준


# 3. compile and training(best weight, minimum loss)
model.compile(loss='mae', optimizer='adam')
<<<<<<< HEAD
<<<<<<< HEAD
#mae: min absolute error, loss값을 최소로 하기위해 mae를 사용
#adam: loss 최적화 = adam 사용
model.fit(x, y, epochs=10)
#model.fit: training start
# epochs: training 횟수(횟수가 너무 많아지면, 오히려 틀어지는 경우가 생길 수 있음)

model.fit(x, y, epochs=100)
=======
=======
>>>>>>> c8ca8b9 (ml-practice)
# 'mae': min absolute error
# 'adam': loss 최적화 = adam 사용
model.fit(x, y, epochs=256)
# model.fit: training
# epochs: training 횟수(과적합을 고려하여 횟수 조절)
<<<<<<< HEAD
>>>>>>> 263feee (ml-practice)
=======
>>>>>>> c8ca8b9 (ml-practice)
# 초기 랜덤값이 다르므로 실행 시 마다 loss가 달라짐


# 4. Evaluation and Prediction
result = model.predict([4])
print('Result1: ', result)
# print("출력값", 변수)


# 5. ect
model.fit(x, y, epochs=512)
result = model.predict([4])
# result 값이 덮어씌어짐
print('Result2: ', result)

model.fit(x, y, epochs=1024)
result = model.predict([4])
print('Result3: ', result)


'''
Result1

Epoch 100/100
1/1 [==============================] - 0s 2ms/step - loss: 8.6560e-04
Result1:  [[4.001488]]


Result2

Epoch 1000/1000
1/1 [==============================] - 0s 999us/step - loss: 0.0035
Result2:  [[3.9903853]]


Result3

Epoch 3000/3000
1/1 [==============================] - 0s 1ms/step - loss: 2.3774e-04
Result3:  [[4.000531]]


'''
