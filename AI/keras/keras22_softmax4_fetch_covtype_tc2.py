import numpy as np
import pandas as pd

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.utils import to_categorical
from tensorflow.keras.callbacks import EarlyStopping

from sklearn.datasets import fetch_covtype
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score

# 1. Data
datasets = fetch_covtype()
x = datasets.data
y = datasets['target'] - 1
# Raw data는 건드리지 않기

print(x.shape, y.shape) # (581012, 54) (581012,)
print(np.unique(y, return_counts=True))

y=to_categorical(y)
print(y.shape) # (581012, 7)

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

model.fit(x_train, y_train, epochs=100, batch_size=128,
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

loss:  0.6046483516693115
accuracy:  0.7435866594314575
accuracy_score:  0.7435866543893015
'''