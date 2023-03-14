'''
practice
R2: 0.55 이상
'''

import time
import numpy as np

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense

from sklearn.datasets import fetch_california_housing
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error, r2_score



# 1. Data
datasets = fetch_california_housing()
x = datasets.data
y = datasets.target

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    train_size=0.7,
    random_state=111
)

'''
Result

print(x.shape)
print(y.shape)

print(dataset.feature_names)
['CRIM' 'ZN' 'INDUS' 'CHAS' 'NOX' 'RM' 'AGE' 'DIS' 'RAD' 'TAX' 'PTRATIO' 'B' 'LSTAT']

print(dataset.DESCR)
DataSet Descripiton
'''


# 2. model
model = Sequential()
model.add(Dense(64, input_dim=8))
model.add(Dense(64))
model.add(Dense(1))


# 3. compile and train
model.compile(loss='mse', optimizer = 'adam', metrics=['mae'])

start = time.time()
model.fit(x_train, y_train, epochs=500, batch_size=16)
end = time.time()


# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
print("Loss: ", loss)

y_predict = model.predict(x_test)
print(y_test)
print(y_predict)

def RMSE (y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))
print("RMSE: ", RMSE(y_test, y_predict))

r2 = r2_score(y_test, y_predict)
print("R2: ", r2)
print("소요 시간: ", end - start)



'''
Result
RMSE:  0.7833732396652231
R2:  0.5506119270660563

Updated Result
RMSE:  0.8095353801159251
R2:  0.5118656898498388


걸린시간
cpu: 소요 시간:  352.6150553226471
gpu: 소요 시간:  1283.045045375824
'''