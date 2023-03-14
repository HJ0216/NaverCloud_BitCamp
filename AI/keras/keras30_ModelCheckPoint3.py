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

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=20,
                              restore_best_weights=False, # False: 최적의 weight + patience의 weight
                              verbose=1)

modelCheckPoint = ModelCheckpoint(monitor='val_loss', mode='auto', verbose=1,
                                   save_best_only=True,
                                   filepath=path+'MCP/keras30_ModelCheckPoint3.hdf5') # MCP/ = MCP 폴더 하단

model.fit(x_train, y_train,
          epochs=1000,
          batch_size=32,
          validation_split=0.2,
          callbacks=[earlyStopping, modelCheckPoint], # list = [2개 이상]
          verbose=1)

model.save(path+'keras30_ModelCheckPoint3_save_model.h5') # 가중치, 모델 세이브


# 4. evaluate and predict
print('========================= 1. 기본 출력 =========================')
loss = model.evaluate(x_test, y_test)

y_predict = model.predict(x_test)
print("Loss: ", loss)

r2 = r2_score(y_test, y_predict)
print("R2: ", r2)



print('========================= 2. load_model(EarlyStopping) 출력 =========================')
model2 = load_model(path+'keras30_ModelCheckPoint3_save_model.h5')
loss = model2.evaluate(x_test, y_test)

y_predict = model2.predict(x_test)
print("Loss: ", loss)

r2 = r2_score(y_test, y_predict)
print("R2: ", r2)



print('========================= 3. load_model(ModelCheckPoint) 출력 =========================')
model3 = load_model(path+'MCP/keras30_ModelCheckPoint3.hdf5')
loss = model3.evaluate(x_test, y_test)

y_predict = model3.predict(x_test)
print("Loss: ", loss)

r2 = r2_score(y_test, y_predict)
print("R2: ", r2)

'''
EarlyStopping: restore_best_weights=True: Break 지점이 아닌 최적의 weight에서 저장
EarlyStopping: restore_best_weights=False(Default) -> patience만큼 더 가서 weight 저장
-> 일반적으로는 최적 weight보다 안좋은 값이 저장되지만 예외가 발생할 수 있음

train data에서 최적의 weight가 발생한 지점이 test data에서 최적의 weight가 발생한 지점이 아닐 수 있음
train data에서 최적의 weigth가 발생하고 patience만큼 지난 자리의 값의 weight를 저장(restore_best_weights=False)할 때,
그 지점에서 test data를 돌렸을 때, 오히려 weight가 좋을 수 있음(data set이 다르므로 일어날 수 있는 상황)

val_loss: validation data <- break 잡는 기준
loss: train data
r2 score: test data
-> 평가지표와 결과값 지표가 data가 다름

'''



'''
Result
RMSE:  3.9774667461538487
R2:  0.7499457664401593

Updated Result
RMSE:  3.758338531055167
R2:  0.8443360976276741

Updated Result2 with MinMax scalering
RMSE:  5.711671989312524
R2:  0.596387886457775

Updated Result2 with Standard scaler
RMSE:  4.60305594170595
R2:  0.7378618798976347

Upadated Result2 with MinMax Scaler
RMSE:  4.737990416535103
R2:  0.7222679303359896

Updated result using Function
RMSE:  3.430171642478747
R2:  0.8544308389149119

'''