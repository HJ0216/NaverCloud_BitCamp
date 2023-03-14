# improve diabetes

'''
[practice]
R2 0.62 이상

'''

import numpy as np

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense

from sklearn.datasets import load_diabetes
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error, r2_score



# 1. Data
datasets = load_diabetes()
x = datasets.data
y = datasets.target

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    train_size=0.7
)

# 2. model
model = Sequential()
model.add(Dense(64, input_dim=10, activation='relu'))
model.add(Dense(64, activation='relu'))
model.add(Dense(1))


# 3. compile and train
model.compile(loss='mse', optimizer='adam')
model.fit(x_train, y_train, epochs=500, batch_size=24, validation_split=0.25)


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
RMSE:  52.858866615480345
R2:  0.5106541423504969

Updated Result
RMSE:  53.98534822132831
R2:  0.5645423075543741

Not validatoin
RMSE:  56.03175512764787
R2:  0.5041898706748968

'''