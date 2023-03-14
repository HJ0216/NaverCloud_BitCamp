import numpy as np

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from sklearn.model_selection import train_test_split


# 1. Data
x = np.array(range(1,21))
y = np.array([1,2,4,3,5,7,9,3,8,12,13,8,14,15,9,6,17,23,21,20])

x_train, x_test, y_train, y_test = train_test_split(
    x,y,
    train_size=0.7,
    shuffle=True,
    random_state=123
)


# 2. Model Construction
model = Sequential()
model.add(Dense(64, input_dim=1))
model.add(Dense(32))
model.add(Dense(8))
model.add(Dense(1))


# 3. compile and train
model.compile(loss='mse', optimizer='adam')
model.fit(x_train, y_train, epochs=256, batch_size=8)


# 4. Evalueate and Predict
loss = model.evaluate(x_test, y_test)
print("Loss: ", loss)



'''
# Result

Hyper parameter tuning
mae: 3.1052515506744385
mse: 15.402148246765137


# 데이터의 모형에 따라 mae, mse 선택
mae
1. 이상치에 민감하지 않음
2. 데이터 모형의 범위가 크게 분산되어 있을 때 사용(과측정 예방)

mse
1. 이상치에 민감함
2. 데이터 모형의 범위가 좁을 때 사용(과소한 측정값 보완)

'''