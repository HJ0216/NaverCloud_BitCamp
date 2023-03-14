import numpy as np
import tensorflow as tf

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense


# tensorflow version print
print(tf.__version__) # 2.7.4


# 1. Refined Data
# 데이터 전처리(Data Preprocessing): data를 일관된 형태로 전환하는 모든 과정
x = np.array([1, 2, 3, 4, 5])
y = np.array([1, 2, 3, 5, 4])


# 2. Model Construction (y=wx+b 구축)
model = Sequential()
model.add(Dense(1, input_dim=1))


# 3. Compile and training
model.compile(loss='mae', optimizer='adam')
model.fit(x, y, epochs=256)


# 4. Evaluation and Prediction
results = model.predict([6])
print("predict 6: ", results)



'''
Result

model.fit(x, y, epochs=3000)
Epoch 3000/3000
1/1 [==============================] - 0s 1ms/step - loss: 0.4000

results = model.predict([6])
predict 6:  [[5.9994235]]

'''