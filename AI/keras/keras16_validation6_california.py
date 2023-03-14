# improve california

'''
practice
R2: 0.55 이상
'''

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


# 2. model
model = Sequential()
model.add(Dense(64, input_dim=8, activation='relu'))
model.add(Dense(64, activation='relu'))
model.add(Dense(1))


# 3. compile and train
import time
model.compile(loss='mse', optimizer = 'adam')
model.fit(x_train, y_train, epochs=500, batch_size=32)


# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
y_predict = model.predict(x_test)

def RMSE (y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))
print("RMSE: ", RMSE(y_test, y_predict))

r2 = r2_score(y_test, y_predict)
print("R2: ", r2)



'''
Result
RMSE:  0.7833732396652231
R2:  0.5506119270660563

Updated Result
RMSE:  0.6692718956593016
R2:  0.6590382057684656

Updated Result with Validation
Validatoin 0.5
RMSE:  0.7221655054147984
R2:  0.6126330903711181

Validatoin 0.25
RMSE:  0.708103756630952
R2:  0.6275715624078637

Not validatoin
RMSE:  0.6714876488997933
R2:  0.6650923283690218

'''