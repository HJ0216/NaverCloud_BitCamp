# improve bike

'''
kaggle.com/competitions/bike-sharing-demand
casual, registered, count 삭제
datetime -> idx 처리

'''

import numpy as np
import pandas as pd

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.callbacks import EarlyStopping

from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error, r2_score


# 1. Data
path = './_data/bike/'
train_csv = pd.read_csv(path+'train.csv', index_col=0)
test_csv = pd.read_csv(path+'test.csv', index_col=0)
submission = pd.read_csv(path+'sampleSubmission.csv', index_col=0)
train_csv = train_csv.dropna()

x = train_csv.drop(['casual', 'registered', 'count'], axis=1)
y = train_csv['count']

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    shuffle=True,
    train_size=0.7,
    random_state=123
)


# 2. model
model = Sequential()
model.add(Dense(64, input_dim=8, activation='linear'))
model.add(Dense(64, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(1))


# 3. compile and train
model.compile(loss='mse', optimizer='adam')
earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=10, restore_best_weights=True, verbose=1)
hist = model.fit(x_train, y_train,
          epochs=500,
          batch_size=32,
          validation_split=0.2,
          callbacks=[earlyStopping],
          verbose=1)


# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
y_predict = model.predict(x_test)

def RMSE (y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))

rmse = RMSE(y_test, y_predict)
print("RMSE: ", rmse)


import matplotlib.pyplot as plt

plt.figure(figsize=(9, 6)) 
plt.plot(hist.history['loss'], c='red', marker='.', label='loss')
plt.plot(hist.history['val_loss'], c='blue', marker='.', label='val_loss')

plt.title('Kaggle Bike loss')
plt.xlabel('epochs')
plt.ylabel('loss')
plt.grid()
plt.legend(loc='upper right')
plt.show()


# for submission
y_submit = model.predict(test_csv)
submission['count'] = y_submit
submission.to_csv(path+'sampleSubmission_3.csv')



'''
Updated Result
RMSE:  152.88134659403144

Updated Result2
RMSE:  149.99826531602525

Updated Result3


'''