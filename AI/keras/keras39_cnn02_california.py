import numpy as np

from tensorflow.keras.models import Sequential, Model, load_model
from tensorflow.keras.layers import Dense, Input, Dropout, Conv2D, Flatten, MaxPooling2D
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint

from sklearn.datasets import fetch_california_housing
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler, StandardScaler
from sklearn.metrics import mean_squared_error, r2_score

path = './_save/'


# 1. Data
dataset = fetch_california_housing()
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

print(x_train.shape, x_test.shape)

x_train = x_train.reshape(14447, 4, 2, 1)
x_test = x_test.reshape(6193, 4, 2, 1)


# 2. Model(Sequential)
model = Sequential()
model.add(Conv2D(128, (2,2), padding='same', activation='relu', input_shape=(4,2,1)))
model.add(MaxPooling2D(pool_size=(2, 1)))
model.add(Conv2D(64, (2,2), padding='same', activation='relu'))
model.add(MaxPooling2D(pool_size=(2, 1)))
model.add(Conv2D(32, (2,2), padding='same', activation='relu'))
model.add(Flatten())
model.add(Dense(32, activation='relu'))
model.add(Dropout(0.3))
model.add(Dense(16, activation='relu'))
model.add(Dropout(0.2))
model.add(Dense(1)) # 분류모델이 아니므로 끝이 softmax가 아니어도 됨
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

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=32,
                              restore_best_weights=True,
                              verbose=1)

modelCheckPoint = ModelCheckpoint(monitor='val_loss', mode='auto', verbose=1,
                                   save_best_only=True,
                                   filepath='MCP/keras39_2_california_MCP.hdf5')

model.fit(x_train, y_train,
          epochs=256,
          batch_size=64,
          validation_split=0.2,
          callbacks=[earlyStopping, modelCheckPoint],
          verbose=1)

model.save(path+'keras39_2_california_save_model.h5') # 가중치 및 모델 세이브


# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
y_predict = model.predict(x_test)

def RMSE (y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))
print("RMSE: ", RMSE(y_test, y_predict))

r2 = r2_score(y_test, y_predict)
print("R2: ", r2)



'''
Result(DNN)
RMSE:  0.7463498785444389
R2:  0.5627911964358545

* 이미지가 아닌 데이터는 CNN이 좋은가 DNN이 좋은가
Result(CNN)
RMSE:  0.5170457249088533
R2:  0.7978231206250308

'''