import numpy as np
import pandas as pd

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.callbacks import EarlyStopping

from sklearn.datasets import fetch_covtype
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score


# 1. Data
datasets = fetch_covtype()
x = datasets.data
y = datasets['target']

print(x.shape, y.shape) # (581012, 54) (581012,)
print(np.unique(y, return_counts=True)) # (array([1, 2, 3, 4, 5, 6, 7]), array([211840, 283301,  35754,   2747,   9493,  17367,  20510])


y = pd.get_dummies(y)
print(type(y)) # pandas

'''
Result

y = pd.get_dummies(y): idx 및 head 출력
부분 출력: print(y[:10])
print(y)
        1  2  3  4  5  6  7
0       0  0  0  0  1  0  0
1       0  0  0  0  1  0  0
2       0  1  0  0  0  0  0
3       0  1  0  0  0  0  0
4       0  0  0  0  1  0  0
...    .. .. .. .. .. .. ..
581007  0  0  1  0  0  0  0
581008  0  0  1  0  0  0  0
581009  0  0  1  0  0  0  0
581010  0  0  1  0  0  0  0
581011  0  0  1  0  0  0  0
[581012 rows x 7 columns]

print(y.shape)
(581012, 7)

print(type(y)) 
<class 'pandas.core.frame.DataFrame'>



Error
ValueError: Shape of passed values is (116203, 1), indices imply (116203, 7)
추가하고자 하는 값은 116203개의 값을 7개의 열에 추가해주려고 하는데, 정작 입력한 값은 116203개의 값을 1개의 열 값

Value가 잘못 인식된 이유: get dummies를 통해서 y: numpy-> pandas
-> np method에 pandas 바로 입력하면 해당 error 발생
해결: pandas -> numpy로 바꿔주기

y = y.values # pandas -> numpy
y = y.to_numpy() # pandas -> numpy

'''

y = y.to_numpy() # pandas -> numpy
# y = y.values # pandas -> numpy
print(type(y)) # numpy


x_train, x_test, y_train, y_test = train_test_split(
    x,y,
    shuffle=True,
    random_state=333,
    test_size=0.2,
    stratify=y
)


# 2. Model Construction
model = Sequential()
model.add(Dense(64, activation='relu', input_shape=(54, )))
model.add(Dense(64, activation='sigmoid'))
model.add(Dense(32,activation='relu'))
model.add(Dense(16,activation='linear'))
model.add(Dense(7,activation='softmax'))


# 3. Compile and train
model.compile(loss='categorical_crossentropy',
              optimizer='adam',
              metrics=['accuracy']
              )

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=20, restore_best_weights=True, verbose=1)

model.fit(x_train, y_train, epochs=256, batch_size=128,
          validation_split=0.2,
          verbose=1)


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
loss:  1.0774707794189453
accuracy:  0.5223100781440735
accuracy_score:  0.5223100952643219

Updated result
loss:  0.5851762294769287
accuracy:  0.7449549436569214
accuracy_score:  0.7449549495279812

'''