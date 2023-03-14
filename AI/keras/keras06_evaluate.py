import numpy as np
from tensorflow.keras.layers import Dense
from tensorflow.keras.models import Sequential

# 1. Data
x = np.array([1,2,3,4,5,6])
y = np.array([1,2,3,5,4,6])


# 2. Model
model = Sequential()
model.add(Dense(64, input_dim=1))
model.add(Dense(64))
model.add(Dense(32))
model.add(Dense(1))


# 3. Complile, training
model.compile(loss='mae', optimizer='adam')
model.fit(x, y, epochs=128, batch_size=4)


# 4. Evaluation, Prediction
loss = model.evaluate(x,y)
# evaluete: loss 값 반환
# evaluate도 fit과 동일한 수행 과정
# 훈련하는 데이터와 평가하는 데이터는 달라야함
print("loss: ", loss)

result = model.predict([7])
print("7의 결과: ", result)

# 문제: loss와 result 중 어떤 값을 중심으로 예측치의 좋고 나쁨을 평가할 수 있을까
# -> loss가 낮을수록 최적의 weight에 가까워지므로 predict가 아닌 loss로 판단



'''
Result

model.fit(x, y, epochs=200, batch_size=3)
Epoch 200/200
2/2 [==============================] - 0s 1000us/step - loss: 0.3474

loss = model.evaluate(x,y)
1/1 [==============================] - 0s 90ms/step - loss: 0.3457
predict 값은 fit, 훈련 결과와는 다른 계산이므로 loss가 더 커질 수 있음

result = model.predict([7])
7의 결과:  [[6.93415]]

'''