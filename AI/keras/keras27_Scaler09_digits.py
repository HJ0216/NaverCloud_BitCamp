import numpy as np
import pandas as pd
from sklearn.preprocessing import MinMaxScaler

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.utils import to_categorical
from tensorflow.keras.callbacks import EarlyStopping

from sklearn.datasets import load_digits
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score


# 1. Data
datasets = load_digits()
x = datasets.data
y = datasets['target']
y=to_categorical(y)

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
model.add(Dense(64, activation='relu', input_shape=(64, )))
model.add(Dense(64, activation='sigmoid'))
model.add(Dense(32,activation='relu'))
model.add(Dense(16,activation='linear'))
model.add(Dense(10,activation='softmax'))


# 3. Compile and train
model.compile(loss='categorical_crossentropy',
              optimizer='adam',
              metrics=['accuracy']
              )

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=32, restore_best_weights=True, verbose=1)

model.fit(x_train, y_train, epochs=512, batch_size=32,
          validation_split=0.2,
          callbacks=[earlyStopping],
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
Result using MinMaxScaler
loss:  0.0801929384469986
accuracy:  0.9777777791023254
accuracy_score:  0.9777777777777777

'''
