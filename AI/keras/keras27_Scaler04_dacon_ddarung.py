import numpy as np
import pandas as pd

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.callbacks import EarlyStopping


from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error, r2_score
from sklearn.preprocessing import MinMaxScaler, StandardScaler


# 1. Data
path = './_data/ddarung/'
train_csv = pd.read_csv(path+'train.csv', index_col=0)
test_csv = pd.read_csv(path+'test.csv', index_col=0)
submission = pd.read_csv(path+'submission.csv', index_col=0)
train_csv = train_csv.dropna()

x = train_csv.drop(['count'], axis=1)
y = train_csv['count']


x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    shuffle=True,
    train_size=0.7,
    random_state=111
)

# scaler = StandardScaler()
scaler = MinMaxScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test) # train_csv의 test는 train의 fit 활용
test_csv = scaler.transform(test_csv)
# train data를 표준화하여 training & validation 진행
# 훈련을 표준화하여 진행했으므로 submit용 data도 표준화 후 예측 진행
'''
original_x: 100, scaling_x: 1 original_y: 500
훈련을 scaling_x -> original_y로 했으면
예측도 scaling_x -> original_y로 진행

'''

 


# 2. Model Construction
model = Sequential()
model.add(Dense(64, input_shape=(9,)))
model.add(Dense(64))
model.add(Dense(32))
model.add(Dense(32))
model.add(Dense(1))


# 3. Compile and train
model.compile(loss='mse', optimizer='adam', metrics=['mae'])
earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=32, restore_best_weights=True, verbose=1)
hist = model.fit(x_train, y_train,
          epochs=512,
          batch_size=16,
          validation_split=0.2,
          callbacks=[earlyStopping],
          verbose=1)


# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
y_predict = model.predict(x_test)
print("loss", loss)


def RMSE (y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))
rmse = RMSE(y_test, y_predict)
print("RMSE: ", rmse)

r2 = r2_score(y_test, y_predict)
print("R2: ", r2)


# for submission
y_submit = model.predict(test_csv)
# training한 데이터는 scaling된 x, scaling된 x가 가르키는 y 값
# (문제) submit을 위해 predict할 data는 x가 scaling이 되지 않으면 훈련의 효과가 나타나지 않아 예측이 잘 이뤄지지 않을 수 있음
# (해결) test_csv = scaler.transform(test_csv): test할 x 값도 scaling


submission['count'] = y_submit
submission.to_csv(path+'submission_0120.csv')



'''
Result
loss 3360.444580078125
RMSE:  57.96934266653727

Updated Result using MinMaxScaler
loss 2880.295166015625
RMSE:  54.01102397913288
R2:  0.6049261814023652

'''
