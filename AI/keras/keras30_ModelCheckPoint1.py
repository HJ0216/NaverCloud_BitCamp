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

from tensorflow.keras.models import Sequential, Model
from tensorflow.keras.layers import Dense, Input
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint

from sklearn.datasets import load_boston
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler, StandardScaler

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
                                   save_best_only=True, # best만 save
                                   filepath=path+'MCP/keras30_ModelCheckPoint1.hdf5') # MCP/ = MCP 폴더 하단
# 가중치 및 모델 저장 확장자: h5
# 가중치 저장 확장자: hdf5

model.fit(x_train, y_train,
          epochs=1000,
          batch_size=32,
          validation_split=0.2,
          callbacks=[earlyStopping, modelCheckPoint], # list = [2개 이상]
          verbose=1)

'''
epochs=1000
Epoch 00001: val_loss improved from inf to 522.52173, saving model to ./_save/MCP\keras30_ModelCheckPoint1.hdf5
-> 처음 훈련은 최상의 결과값이므로 저장
Epoch 00002: val_loss improved from 522.52173 to 444.32184, saving model to ./_save/MCP\keras30_ModelCheckPoint1.hdf5
-> 2번째 훈련 개선 -> 덮어쓰기
-> 반복
Epoch 00041: val_loss did not improve from 9.04129
-> 개선되지 않을 경우 저장 X
-> 개선되지 않은 결과가 20번 반복될 경우, EarlyStopping = 가장 성능이 좋은 ModelCheckPoint 지점
Epoch 81/1000
1/9 [==>...........................] - ETA: 0s - loss: 6.8561 - mae: 1.6694
Restoring model weights from the end of the best epoch: 61.

EarlyStopping: 최적의 weight가 갱신이 안되면 훈련을 끊어주는 역할
ModelCheckPoint: 최적의 weight가 갱신될 때마다 저장해주는 역할

MCP 저장
RMSE:  4.393303432855621
R2:  0.7612078343831213
-> 확인하는 파일: keras30_ModelCheckPoint2_load_model.py



'''


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
Result
RMSE:  3.758338531055167
R2:  0.8443360976276741

Updated Result with Standard scaler
RMSE:  4.60305594170595
R2:  0.7378618798976347

Updated result using Function
RMSE:  3.430171642478747
R2:  0.8544308389149119

'''
