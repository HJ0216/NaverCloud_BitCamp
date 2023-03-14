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
from tensorflow.keras.callbacks import EarlyStopping


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
    # stratify = y : y 타입이 분류에서만 사용
)


scaler = StandardScaler()
# scaler = MinMaxScaler()
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

# model.save_weights(path +'keras29_5_save_weights1.h5')
# model.load_weights(path + 'keras29_5_save_weights1.h5')
# Model이 저장 안되고, 가중치만 저장이 됨 -> 사용 시, 모델 필요
# weight를 가져왔으므로 fit 필요 X
# RuntimeError: You must compile your model before training/testing. Use `model.compile(optimizer, loss)`.

# 3. compile and train
model.compile(loss='mse', optimizer='adam', metrics=['mae'])
# earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=20, restore_best_weights=True, verbose=1)
# hist = model.fit(x_train, y_train,
#           epochs=500,
#           batch_size=16,
#           validation_split=0.2,
#           callbacks=[earlyStopping],
#           verbose=1)

# model.save_weights(path+'keras29_5_save_weights2.h5')
model.load_weights(path + 'keras29_5_save_weights2.h5')
# RuntimeError: You must compile your model before training/testing. Use `model.compile(optimizer, loss)`.
# weight만 저장되었으므로 compile 필요

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
