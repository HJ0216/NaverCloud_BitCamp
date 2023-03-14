import numpy as np
import datetime

from sklearn.datasets import load_breast_cancer
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler, StandardScaler
from sklearn.metrics import r2_score, accuracy_score

from tensorflow.keras.models import Sequential, Model
from tensorflow.keras.layers import Dense, Input, Dropout
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint


path = './_save/'
filepath = './_save/MCP/'
filename = '{epoch:04d}-{val_loss:.4f}.hdf5'


# 1. Data
datasets = load_breast_cancer()
x = datasets['data'] # for training
y = datasets['target'] # for predict

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    test_size=0.2,
    shuffle= True,
    random_state = 333
)

# scaler = StandardScaler()
scaler = MinMaxScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test)


# 2. Model Construction
input1 = Input(shape=(30,))
dense1 = Dense(64, activation='relu')(input1)
drop1 = Dropout(0.2)(dense1)
dense2 = Dense(64, activation='linear')(drop1)
drop2 = Dropout(0.15)(dense2)
dense3 = Dense(32, activation='relu')(drop2)
drop3 = Dropout(0.15)(dense3)
dense4 = Dense(32, activation='relu')(drop3)
output1 = Dense(1, activation='sigmoid')(dense4)
model = Model(inputs=input1, outputs=output1)


# 3. Compile and train
model.compile(loss='binary_crossentropy', optimizer='adam', metrics=['accuracy'])

earlyStopping = EarlyStopping(monitor='accuracy', mode='auto', patience=32, restore_best_weights=True, verbose=1)

date = datetime.datetime.now()
date = date.strftime("%m%d_%H%M")

modelCheckPoint = ModelCheckpoint(monitor='val_loss', mode='auto', verbose=1,
                                   save_best_only=True,
                                   filepath=filepath + 'k31_06_' + date + '_' + filename)

model.fit(x_train, y_train,
          epochs=512, batch_size=32,
          validation_split=0.2,
          callbacks=[earlyStopping, modelCheckPoint],
          verbose=1)

model.save(path+'keras31_dropout06_save_model.h5') # 가중치 및 모델 세이브


# 4. evaluate and predict
loss, accuracy = model.evaluate(x_test, y_test)
print("loss: ", loss)
print("accuracy: ", accuracy)

y_predict = model.predict(x_test)
pred_class = np.where(y_predict >= 0.5, 1, 0)

acc = accuracy_score(y_test, pred_class)
print("accuarcy_score: ", acc)


'''
Result
loss = model.evaluate(x_test, y_test)
print("[binarycrossentropy, accuracy]: ", loss)
Restoring model weights from the end of the best epoch: 32.
[binarycrossentropy, accuracy]:  [0.16636592149734497, 0.9561403393745422]


Updated Result
Restoring model weights from the end of the best epoch: 198.
loss:  0.16303640604019165
accuracy:  0.9561403393745422


Updated Result with converting binary classification from sigmoid
loss:  0.13962924480438232
accuracy:  0.9561403393745422
accuarcy_score:  0.956140350877193
accuracy와 accuarcy_score가 차이나는 이유: y_predict.flatten()로 값 변환을 하였으므로


Updated result using MinMaxScaler
loss:  0.4332122802734375
accuracy:  0.8070175647735596
accuarcy_score:  0.8070175438596491


Updated result using Function
loss:  0.1376401036977768
accuracy:  0.9736841917037964
accuarcy_score:  0.9736842105263158


Updated result using Dropout
loss:  0.17975987493991852
accuracy:  0.9736841917037964
accuarcy_score:  0.9736842105263158

'''
