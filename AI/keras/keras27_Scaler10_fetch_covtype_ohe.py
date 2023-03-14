import numpy as np
import pandas as pd

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.callbacks import EarlyStopping

from sklearn.datasets import fetch_covtype
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
from sklearn.preprocessing import MinMaxScaler, OneHotEncoder

# 1. Data
datasets = fetch_covtype()

x = datasets.data
y = datasets['target']

y = y.reshape(581012, 1)

ohe = OneHotEncoder()
ohe.fit(y) # ohe에 y를 fit한 가중치를 넣음
y = ohe.transform(y) # 가중치가 들어간 ohe에 transform y 실행
y = y.toarray()

'''
y_ohe_fit = ohe.fit(y)
print(y_ohe_fit)
-> OneHotEncoder(): fit() 함수 실행 후 Encoder가 반환됨

y = y_ohe_fit.transform(y)
-> 반환받은 OneHotEncoder()를 transform에 사용

하기 문법 사용 권고
ohe.fit(y)
y = ohe.transform(y)
y = ohe.fit_transform(y) # transform - one hot encoder 형태

'''


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
model.add(Dense(64, activation='sigmoid'))
model.add(Dense(32,activation='relu'))
model.add(Dense(16,activation='linear'))
model.add(Dense(7,activation='softmax'))


# 3. Compile and train
model.compile(loss='categorical_crossentropy',
              optimizer='adam',
              metrics=['accuracy']
              )

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=16, restore_best_weights=True, verbose=1)

model.fit(x_train, y_train, epochs=128, batch_size=64,
          validation_split=0.2,
          callbacks = [earlyStopping],
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
loss:  0.596627414226532
accuracy:  0.7471494078636169
accuracy_score:  0.7471493851277505

Result using MinMaxScaler
loss:  0.2795166075229645
accuracy:  0.8866552710533142
accuracy_score:  0.8866552498644613

Updated result
loss:  0.23012806475162506
accuracy:  0.90787672996521
accuracy_score:  0.9078767329587016

'''
