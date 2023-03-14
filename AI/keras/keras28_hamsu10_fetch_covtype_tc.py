import numpy as np
import pandas as pd
from sklearn.preprocessing import MinMaxScaler

from tensorflow.keras.models import Sequential, Model
from tensorflow.keras.layers import Dense, Input
from tensorflow.keras.utils import to_categorical
from tensorflow.keras.callbacks import EarlyStopping

from sklearn.datasets import fetch_covtype
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score

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
input1 = Input(shape=(54,))
dense1 = Dense(64, activation='relu')(input1)
dense2 = Dense(64, activation='linear')(dense1)
dense3 = Dense(32, activation='relu')(dense2)
dense4 = Dense(16, activation='relu')(dense3)
output1 = Dense(7, activation='softmax')(dense4)
model = Model(inputs=input1, outputs=output1)


# 3. Compile and train
model.compile(loss='categorical_crossentropy',
              optimizer='adam',
              metrics=['accuracy']
              )

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=32, restore_best_weights=True, verbose=1)

model.fit(x_train, y_train, epochs=128, batch_size=512,
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
Result
loss:  0.6315832138061523
accuracy:  0.7298176288604736
accuracy_score:  0.729817646704474

Result using MinMaxScaler
loss:  0.2680917978286743
accuracy:  0.8892885446548462
accuracy_score:  0.8892885725841846

Result using Function
loss:  0.2973416745662689
accuracy:  0.8811562657356262
accuracy_score:  0.8811562524203334

'''
