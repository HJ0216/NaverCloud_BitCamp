import numpy as np
import pandas as pd

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.utils import to_categorical
from tensorflow.keras.callbacks import EarlyStopping


from sklearn.datasets import load_wine
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import OneHotEncoder
from sklearn.metrics import accuracy_score



# 1. Data
datasets = load_wine()
x = datasets.data
y = datasets['target']

print(x.shape, y.shape) # (178, 13) (178,)
# print(y)를 통해서 012로 이뤄져있으면 분류
# (문제) 데이터가 많을 때 판단이 어려울수 있음
# (해결) np.unique
print(np.unique(y, return_counts=True))
# [0 1 2] 중복값을 제외한 y 값, return_counts를 통해 element의 개수 반환
# output_dim = 1, y_class = 3

y=to_categorical(y)
# get_dummies와 차이

x_train, x_test, y_train, y_test = train_test_split(
    x,y,
    shuffle=True,
    random_state=333,
    test_size=0.2,
    stratify=y
)


# 2. Model Construction
model = Sequential()
model.add(Dense(64, activation='relu', input_shape=(13, )))
model.add(Dense(64, activation='sigmoid'))
model.add(Dense(32,activation='relu'))
model.add(Dense(16,activation='linear'))
model.add(Dense(3,activation='softmax'))


# 3. Compile and train
model.compile(loss='categorical_crossentropy',
              optimizer='adam',
              metrics=['accuracy']
              )

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=5, restore_best_weights=True, verbose=1)

model.fit(x_train, y_train, epochs=500, batch_size=8,
          validation_split=0.2,
          verbose=1)


# 4. evaluate and predict
loss, accuracy = model.evaluate(x_test, y_test)
print("loss: ", loss)
print("accuracy: ", accuracy)

y_predict = model.predict(x_test)
y_predict = np.argmax(y_predict, axis=1)
y_test = np.argmax(y_test, axis=1)

print("==========")
print(y_predict)
print(y_test)
print("==========")

acc = accuracy_score(y_test, y_predict)
print(acc)
