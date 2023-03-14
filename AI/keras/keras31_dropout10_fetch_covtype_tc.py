import numpy as np
import pandas as pd
import datetime

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, Dropout
from tensorflow.keras.utils import to_categorical
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint

from sklearn.datasets import fetch_covtype
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
from sklearn.preprocessing import MinMaxScaler


path = './_save/'
filepath = './_save/MCP/'
filename = '{epoch:04d}-{val_loss:.4f}.hdf5'


# 1. Data
datasets = fetch_covtype()
x = datasets.data
y = datasets['target']

y = to_categorical(y)
y = np.delete(y, 0, axis=1)


x_train, x_test, y_train, y_test = train_test_split(
    x,y,
    shuffle=True,
    random_state=333,
    test_size=0.2,
    stratify=y
)

# scaler = StandardScaler()
scaler = MinMaxScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test)


# 2. Model Construction
model = Sequential()
model.add(Dense(64, activation='relu', input_shape=(54, )))
model.add(Dropout(0.3))
model.add(Dense(64, activation='sigmoid'))
model.add(Dropout(0.3))
model.add(Dense(32,activation='relu'))
model.add(Dropout(0.2))
model.add(Dense(16,activation='linear'))
model.add(Dense(7,activation='softmax'))


# 3. Compile and train
model.compile(loss='categorical_crossentropy',
              optimizer='adam',
              metrics=['accuracy']
              )

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=20, restore_best_weights=True, verbose=1)

date = datetime.datetime.now()
date = date.strftime("%m%d_%H%M")

modelCheckPoint = ModelCheckpoint(monitor='val_loss', mode='auto', verbose=1,
                                   save_best_only=True,
                                   filepath=filepath + 'k31_10_tc_' + date + '_' + filename)

model.fit(x_train, y_train, epochs=300, batch_size=64,
          validation_split=0.2,
          callbacks=[earlyStopping, modelCheckPoint],
          verbose=1)

model.save(path+'keras31_dropout10_tc_save_model.h5') # 가중치 및 모델 세이브


# 4. evaluate and predict
loss, accuracy = model.evaluate(x_test, y_test)
print("loss: ", loss)
print("accuracy: ", accuracy)

y_predict = model.predict(x_test)
y_predict = np.argmax(y_predict, axis=1)
y_test = np.argmax(y_test, axis=1)

acc = accuracy_score(y_test, y_predict)
print("accuracy_score: ", acc)


'''
Result
loss:  0.6315832138061523
accuracy:  0.7298176288604736
accuracy_score:  0.729817646704474

Result using MinMaxScaler
loss:  0.2680917978286743
accuracy:  0.8892885446548462
accuracy_score:  0.8892885725841846

Result using Dropout
loss:  0.42928606271743774
accuracy:  0.8176897168159485
accuracy_score:  0.817689732623082

'''
