import numpy as np
import pandas as pd

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.callbacks import EarlyStopping


from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error, r2_score
# import 후 미사용 시, 옅은 색깔로 표시됨


# 1. Data
path = './_data/ddarung/'
train_csv = pd.read_csv(path+'train.csv', index_col=0)
test_csv = pd.read_csv(path+'test.csv', index_col=0)
submission = pd.read_csv(path+'submission.csv', index_col=0)
train_csv = train_csv.dropna()
print(train_csv.shape) # (1459, 10) -> (1328, 10)

x = train_csv.drop(['count'], axis=1)
print(x.shape) # [1459 rows x 9 columns]: dropna() count column 삭제

y = train_csv['count']
print(y.shape) # (1328,)


x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    shuffle=True,
    train_size=0.7,
    random_state=111
)

print(x_train.shape, x_test.shape) # (929, 9) (399, 9)
print(y_train.shape, y_test.shape) # (929,) (399,)


# 2. Model Construction
model = Sequential()
model.add(Dense(64, input_shape=(9,)))
model.add(Dense(64))
model.add(Dense(32))
model.add(Dense(1))


# 3. Compile and train
model.compile(loss='mse', optimizer='adam')
earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=5, restore_best_weights=True, verbose=1)
hist = model.fit(x_train, y_train,
          epochs=500,
          batch_size=8,
          validation_split=0.2,
          callbacks=[earlyStopping],
          verbose=1)


# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
y_predict = model.predict(x_test)

def RMSE (y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))
rmse = RMSE(y_test, y_predict)

print("loss", loss)
print("RMSE: ", rmse)


# for submission
y_submit = model.predict(test_csv)

submission['count'] = y_submit
submission.to_csv(path+'submission_0117.csv')

print(hist)
print(hist.history)


import matplotlib.pyplot as plt

plt.figure(figsize=(9, 6)) 
plt.plot(hist.history['loss'], c='red', marker='.', label='loss')
plt.plot(hist.history['val_loss'], c='blue', marker='.', label='val_loss')

plt.title('Seoul Ddarung loss')
plt.xlabel('epochs')
plt.ylabel('loss')
plt.grid()
plt.legend(loc='upper right')
plt.show()



'''
Result
loss 3360.444580078125
RMSE:  57.96934266653727


'''