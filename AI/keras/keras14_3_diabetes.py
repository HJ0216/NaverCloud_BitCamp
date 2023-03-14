'''
[practice]
R2 0.62 이상

'''

import sklearn as sk
print(sk.__version__)

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

print(x)
print(y)

'''
print(x.shape) # (442, 10)
print(y.shape) # (442, )

print(datasets.feature_names)
['age', 'sex', 'bmi', 'bp', 's1', 's2', 's3', 's4', 's5', 's6']

'''

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    train_size=0.7,
    random_state=111
)

# 2. model
model = Sequential()
model.add(Dense(64, input_dim=10))
model.add(Dense(64))
model.add(Dense(1))


# 3. compile and train
model.compile(loss='mse', optimizer='adam')
model.fit(x_train, y_train, epochs=200, batch_size=10)


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



'''
RMSE:  52.858866615480345
R2:  0.5106541423504969
'''