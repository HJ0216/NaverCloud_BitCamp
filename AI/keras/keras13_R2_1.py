import numpy as np

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense

from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error, r2_score
# class 삽입 시, ','로 다중 삽입 가능


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
model.compile(loss='mse', optimizer='adam', metrics=['mae'])
model.fit(x_train, y_train, epochs=128, batch_size=1)


# 4. Evalueate and Predict
loss = model.evaluate(x_test, y_test)
print("Loss: ", loss)

y_predict = model.predict(x_test)

print("==========")
print(y_test)
print(y_predict)
print("==========")

'''
Result
==========
[ 9  7  5 23  8  3]
[[14.496417]
 [ 6.170927]
 [ 5.245874]
 [17.271582]
 [ 8.94609 ]
 [ 8.021036]]
 ==========
'''



def RMSE (y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))
print("RMSE: ", RMSE(y_test, y_predict))

r2 = r2_score(y_test, y_predict)
print("R: ", r2)



'''
Result

RMSE:  3.8482795786702315
-> loss이므로 낮을수록 성능이 좋음
R2:  0.6485608399723322
-> accuracy이므로 높을수록 성능이 좋음

'''