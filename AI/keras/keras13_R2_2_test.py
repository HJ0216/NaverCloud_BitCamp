'''
[practice]

1. <<R2를 음수가 아닌 0.5 이하로 줄이기>>
2. Data: 유지
3. Layer: 인풋 및 아웃풋 포함 7개 이상
4. batch_size = 1
5. hidden layer node: 각각 10~100
6. train set: 70%
7. epochs>=100
8. loss: mse or mae
9. activation 사용 금지
'''


import numpy as np

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense

from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error, r2_score


# 1. Data
x = np.array(range(1,21))
y = np.array(range(1,21))

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    train_size=0.7,
    shuffle=True,
    random_state=123
)


# 2. Model Construction
model = Sequential()
model.add(Dense(10, input_dim=1))
model.add(Dense(100))
model.add(Dense(1000))
model.add(Dense(1))


# 3. compile and train
model.compile(loss='mae', optimizer = 'adam')
model.fit(x_train, y_train, epochs=10000, batch_size=1)


# 4. Evalueate and Predict
loss = model.evaluate(x_test, y_test)
print("Loss: ", loss)

y_predict = model.predict(x_test)
print(y_test)
print(y_predict)


'''
Result

[ 1 18 16  2  9  6]
[[ 1.4867625]
 [19.201803 ]
 [17.117676 ]
 [ 2.5288236]
 [ 9.823254 ]
 [ 6.697069 ]]

'''



def RMSE (y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))
print("RMSE: ", RMSE(y_test, y_predict))

r2 = r2_score(y_test, y_predict)
print("R: ", r2)


'''
Result

RMSE:  0.3096209736011438
R:  0.9977114421070464


Updated Result
RMSE:  0.13378200334666585
R:  0.9992035667749072

'''