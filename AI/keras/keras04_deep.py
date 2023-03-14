import numpy as np
from tensorflow.keras.layers import Dense
from tensorflow.keras.models import Sequential


# 1. Data
x = np.array([1,2,3,4,5])
y = np.array([1,2,3,5,4])


# 2. Model
model = Sequential()
model.add(Dense(64, input_dim=1)) # 이전 layer의 output이 다음 layer의 input이 되므로 생략 가능
model.add(Dense(64)) # Layer 쌓기
model.add(Dense(32))
model.add(Dense(16))
model.add(Dense(8))
model.add(Dense(1))


# 3. Complile, training
model.compile(loss='mae', optimizer='adam')
model.fit(x, y, epochs=128)


# 4. Evaluation, Prediction
result = model.predict([6])
print("6의 결과: ", result)

# Hyper-parameter tuning: loss를 줄이기 위해 epochs, node, layer, batch_size 등 변경
# epochs 높이기
# node의 개수 늘리기(model.add(Dense(1)))
# layer의 계층 늘리기
# batch_size 조절하기
# 과도한 훈련으로 인한 과적합(Overfitting) 문제 발생 유의



'''
Result

model.fit(x, y, epochs=500)
Epoch 500/500
1/1 [==============================] - 0s 2ms/step - loss: 0.4176

result = model.predict([6])
6의 결과:  [[5.926441]]

'''