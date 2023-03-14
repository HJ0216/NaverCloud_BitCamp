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

from tensorflow.keras.models import Sequential, Model, load_model
from tensorflow.keras.layers import Dense, Input, Dropout
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint

from sklearn.datasets import load_boston
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler, StandardScaler
from sklearn.metrics import mean_squared_error, r2_score

path = './_save/'


# 1. Data
dataset = load_boston()
x = dataset.data # for training
y = dataset.target # for predict

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    train_size=0.7,
    random_state=123
)

scaler = StandardScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test)

print(x_train.shape)
print(x_test.shape)


'''
# 2. Model(Sequential)
model = Sequential()
model.add(Dense(64, input_dim=13, activation='relu'))
model.add(Dropout(0.5)) # 50%를 dropout
model.add(Dense(64, activation='relu'))
model.add(Dropout(0.3))
model.add(Dense(32, activation='relu'))
model.add(Dropout(0.2))
model.add(Dense(32, activation='relu'))
model.add(Dense(16, activation='relu'))
model.add(Dense(1))
model.summary()
'''

# 2. Model(Function)
input1 = Input(shape=(13,))
dense1 = Dense(64, activation='relu')(input1)
drop1 = Dropout(0.3)(dense1)
dense2 = Dense(64, activation='sigmoid')(drop1)
drop2 = Dropout(0.2)(dense2)
dense3 = Dense(32, activation='relu')(drop2)
drop3 = Dropout(0.15)(dense3)
dense4 = Dense(32, activation='linear')(drop3)
output1 = Dense(1, activation='linear')(dense4)
model = Model(inputs=input1, outputs=output1)
model.summary()
'''
summray
node를 random하게 추출하여 훈련을 수행 -> 과적합 문제 해결
summary는 dropout된 node를 나누지 않음
predict 시에는 dropout 사용 X

Total params: 8,225
Trainable params: 8,225
Non-trainable params: 0
'''


# 3. compile and train
model.compile(loss='mse', optimizer='adam', metrics=['mae'])

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=30,
                              restore_best_weights=False, # False: 최적의 weight + patience
                              verbose=1)

import datetime # datetime: data type
date = datetime.datetime.now()
date = date.strftime("%m%d_%H%M")


filepath = './_save/MCP/'
filename = '{epoch:04d}-{val_loss:.4f}.hdf5'


modelCheckPoint = ModelCheckpoint(monitor='val_loss', mode='auto', verbose=1,
                                   save_best_only=True,
                                   filepath=filepath + 'k31_01_' + date + '_' + filename)

model.fit(x_train, y_train,
          epochs=256,
          batch_size=64,
          validation_split=0.2,
          callbacks=[earlyStopping, modelCheckPoint],
          verbose=1)

model.save(path+'keras31_dropout01_save_model.h5') # 가중치 및 모델 세이브


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
Upadated Result with MinMax Scaler
RMSE:  4.737990416535103
R2:  0.7222679303359896

Updated result using Function
RMSE:  3.430171642478747
R2:  0.8544308389149119

Updated result using Dropout
RMSE:  4.195241728686868
R2:  0.7822532637113194

'''