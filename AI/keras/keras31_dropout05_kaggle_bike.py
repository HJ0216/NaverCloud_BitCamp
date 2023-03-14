# improve bike

'''
kaggle.com/competitions/bike-sharing-demand
casual, registered, count 삭제
datetime -> idx 처리

'''

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

# scaler = StandardScaler()
scaler = MinMaxScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test)
test_csv = scaler.transform(test_csv)


# 2. model
input1 = Input(shape=(8,))
dense1 = Dense(64, activation='relu')(input1)
drop1 = Dropout(0.5)(dense1)
dense2 = Dense(64, activation='sigmoid')(drop1)
drop2 = Dropout(0.3)(dense2)
dense3 = Dense(32, activation='relu')(drop2)
drop3 = Dropout(0.2)(dense3)
dense4 = Dense(32, activation='relu')(drop3)
output1 = Dense(1, activation='linear')(dense4)
model = Model(inputs=input1, outputs=output1)


# 3. compile and train
model.compile(loss='mse', optimizer='adam', metrics=['mae'])
earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=30, restore_best_weights=True, verbose=1)

date = datetime.datetime.now()
date = date.strftime("%m%d_%H%M")

modelCheckPoint = ModelCheckpoint(monitor='val_loss', mode='auto', verbose=1,
                                   save_best_only=True,
                                   filepath=filepath + 'k31_05_' + date + '_' + filename)

model.fit(x_train, y_train,
          epochs=1000,
          batch_size=32,
          validation_split=0.2,
          callbacks=[earlyStopping, modelCheckPoint],
          verbose=1)

model.save(path2+'keras31_dropout05_save_model.h5') # 가중치 및 모델 세이브

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
submission.to_csv(path+'sampleSubmission_0118.csv')



'''
Updated Result
RMSE:  152.88134659403144

Updated Result2
RMSE:  149.99826531602525

Updated Result using MinMaxScaler
loss 22635.67578125
RMSE:  150.45157752219103
R2:  0.30323941332889803

Updated result using Function
loss [21499.1015625, 106.30323791503906]
RMSE:  146.62569397638993
R2:  0.33822516890740884

Updated result using Dropout
loss [22046.734375, 109.48329162597656]
RMSE:  148.48144082671115
R2:  0.3213678490926184

'''
