import numpy as np
import datetime

from tensorflow.keras.models import Sequential, Model
from tensorflow.keras.layers import Dense, Input, Dropout
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint

from sklearn.datasets import load_diabetes
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler, StandardScaler
from sklearn.metrics import mean_squared_error, r2_score


path = './_save/'
filepath = './_save/MCP/'
filename = '{epoch:04d}-{val_loss:.4f}.hdf5'


# 1. Data
datasets = load_diabetes()
x = datasets.data
y = datasets.target

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    train_size=0.7,
    random_state=111
)

# scaler = StandardScaler()
scaler = MinMaxScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test)


# 2. Model Construction
input1 = Input(shape=(10,))
dense1 = Dense(64, activation='relu')(input1)
drop1 = Dropout(0.3)(dense1)
dense2 = Dense(64, activation='sigmoid')(drop1)
drop2 = Dropout(0.2)(dense2)
dense3 = Dense(32, activation='relu')(drop2)
output1 = Dense(1, activation='linear')(dense3)
model = Model(inputs=input1, outputs=output1)


# 3. Compile and train
model.compile(loss='mse', optimizer='adam', metrics=['mae'])
earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=32, restore_best_weights=True, verbose=1)

date = datetime.datetime.now()
date = date.strftime("%m%d_%H%M")

modelCheckPoint = ModelCheckpoint(monitor='val_loss', mode='auto', verbose=1,
                                   save_best_only=True,
                                   filepath=filepath + 'k31_03_' + date + '_' + filename)

model.fit(x_train, y_train,
          epochs=512,
          batch_size=16,
          validation_split=0.2,
          callbacks=[earlyStopping, modelCheckPoint],
          verbose=1)

model.save(path+'keras31_dropout03_save_model.h5') # 가중치 및 모델 세이브


# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
y_predict = model.predict(x_test)

def RMSE (y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))
print("RMSE: ", RMSE(y_test, y_predict))

r2 = r2_score(y_test, y_predict)
print("R2: ", r2)



'''
Result
Restoring model weights from the end of the best epoch: 45.
loss 3139.306396484375

Updated Result using MinMaxScaler
RMSE:  58.15208371512175
R2:  0.37283925642017157

Updated result using Function
RMSE:  56.52655687272751
R2:  0.4074112947471106

Updated result using dropout
RMSE:  56.243265393893736
R2:  0.41333610862141645

'''
