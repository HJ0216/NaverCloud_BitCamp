# improve boston

'''
[Practice]
trains set: 0.7 이상
평가지표1 R2: 0.8 이상
평가지표2 RMSE 사용
loss: mae or mse
optimizer: adam
matrics: optional
model(train set, epochs, batch_size=optional)
evaluate: x_test, y_test
 
'''


import numpy as np

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense

from sklearn.datasets import load_boston
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler, StandardScaler

# 1. Data
dataset = load_boston()
x = dataset.data # for training
y = dataset.target # for predict

# scaler = MinMaxScaler() # 정규화
scaler = StandardScaler() # 표준화
scaler.fit(x) # x의 값의 범위만큼 가중치 생성
x = scaler.transform(x) # 생성된 가중치만큼의 data 형태 변형

print(x)
print(type(x)) # <class 'numpy.ndarray'>

print("Min: ", np.min(x))
print("Max: ", np.max(x))

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    train_size=0.7,
    random_state=123
)
# stratify: y 타입이 분류에서만 사용


# 2. Model
model = Sequential()
model.add(Dense(64, input_dim=13, activation='relu'))
model.add(Dense(64, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(1))


# 3. compile and train
model.compile(loss='mse', optimizer = 'adam', metrics=['mae'])
model.fit(x_train, y_train, epochs=100, batch_size=4, validation_split=0.25)


# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
y_predict = model.predict(x_test)

from sklearn.metrics import mean_squared_error, r2_score
def RMSE (y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))
print("RMSE: ", RMSE(y_test, y_predict))

r2 = r2_score(y_test, y_predict)
print("R2: ", r2)



'''
Result
RMSE:  3.9774667461538487
R2:  0.7499457664401593

Updated Result
RMSE:  3.758338531055167
R2:  0.8443360976276741

Updated Result2 with MinMax scalering
RMSE:  5.711671989312524
R2:  0.596387886457775

Updated Result2 with Standard scalering
RMSE:  4.60305594170595
R2:  0.7378618798976347

'''