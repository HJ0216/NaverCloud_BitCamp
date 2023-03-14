import numpy as np
import pandas as pd
import datetime

from tensorflow.keras.models import Sequential, Model
from tensorflow.keras.layers import Dense, Input, Dropout
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint


from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error, r2_score
from sklearn.preprocessing import MinMaxScaler, StandardScaler


path2 = './_save/'
# path 경로가 중첩되므로 name 정정
filepath = './_save/MCP/'
filename = '{epoch:04d}-{val_loss:.4f}.hdf5'


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
x_test = scaler.transform(x_test) # test는 train의 fit 활용
test_csv = scaler.transform(test_csv)


# 2. Model Construction
input1 = Input(shape=(9,))
dense1 = Dense(64, activation='relu')(input1)
drop1 = Dropout(0.2)(dense1)
dense2 = Dense(64, activation='sigmoid')(drop1)
drop2 = Dropout(0.15)(dense2)
dense3 = Dense(32, activation='relu')(drop2)
drop3 = Dropout(0.2)(dense3)
output1 = Dense(1, activation='linear')(drop3)
model = Model(inputs=input1, outputs=output1)


# 3. Compile and train
model.compile(loss='mse', optimizer='adam', metrics=['mae'])
earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=32, restore_best_weights=True, verbose=1)

date = datetime.datetime.now()
date = date.strftime("%m%d_%H%M")

modelCheckPoint = ModelCheckpoint(monitor='val_loss', mode='auto', verbose=1,
                                   save_best_only=True,
                                   filepath=filepath + 'k31_04_' + date + '_' + filename)


model.fit(x_train, y_train,
          epochs=512,
          batch_size=32,
          validation_split=0.2,
          callbacks=[earlyStopping, modelCheckPoint],
          verbose=1)

model.save(path2+'keras31_dropout04_save_model.h5') # 가중치 및 모델 세이브


# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
y_predict = model.predict(x_test)

def RMSE (y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))
rmse = RMSE(y_test, y_predict)
print("RMSE: ", rmse)

r2 = r2_score(y_test, y_predict)
print("R2: ", r2)


# for submission
y_submit = model.predict(test_csv)

submission['count'] = y_submit
submission.to_csv(path+'submission_0118.csv')



'''
Result
loss 3360.444580078125
RMSE:  57.96934266653727

Updated Result using MinMaxScaler
loss 2880.295166015625
RMSE:  54.01102397913288
R2:  0.6049261814023652

Updated result using Function
loss [2031.6160888671875, 34.216949462890625]
RMSE:  45.07345481571253
R2:  0.7248591194498237

Updated result using dropout
RMSE:  48.416561144415375
R2:  0.6825310119320898

'''