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
from tensorflow.keras.layers import Dense, Input
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

'''
# 2. Model(Function)
input1 = Input(shape=(13,))
dense1 = Dense(64, activation='relu')(input1)
dense2 = Dense(64, activation='sigmoid')(dense1)
dense3 = Dense(32, activation='relu')(dense2)
dense4 = Dense(32, activation='linear')(dense3)
output1 = Dense(1, activation='linear')(dense4)
model = Model(inputs=input1, outputs=output1)
model.summary()


# 3. compile and train
model.compile(loss='mse', optimizer='adam', metrics=['mae'])

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=20, restore_best_weights=True, verbose=1)

modelCheckPoint = ModelCheckpoint(monitor='val_loss', mode='auto', verbose=1,
                                   save_best_only=True,
                                   filepath=path+'MCP/keras30_ModelCheckPoint1.hdf5')

hist = model.fit(x_train, y_train,
          epochs=1000,
          batch_size=32,
          validation_split=0.2,
          callbacks=[earlyStopping, modelCheckPoint],
          verbose=1)


MCP 저장(keras30_ModelCheckPoint1.hdf5)
RMSE:  4.393303432855621
R2:  0.7612078343831213
'''

model = load_model(path+'MCP/keras30_ModelCheckPoint1.hdf5')
# load_model로 모델과 가중치 불러오기

'''
load model
RMSE:  4.393303432855621
R2:  0.7612078343831213
'''


# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
y_predict = model.predict(x_test)

def RMSE (y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))
print("RMSE: ", RMSE(y_test, y_predict))

r2 = r2_score(y_test, y_predict)
print("R2: ", r2)



'''
Updated Result
RMSE:  3.758338531055167
R2:  0.8443360976276741

Updated Result with Standard scaler
RMSE:  4.60305594170595
R2:  0.7378618798976347

Updated result using Function
RMSE:  3.430171642478747
R2:  0.8544308389149119

'''