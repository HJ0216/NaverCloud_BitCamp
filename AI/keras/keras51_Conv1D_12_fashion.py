# conv1D_fashion.py

import pandas as pd
import numpy as np

from tensorflow.keras.models import Sequential, Model, load_model
from tensorflow.keras.utils import to_categorical
from tensorflow.keras.layers import Dense, SimpleRNN, LSTM, Dropout, Conv2D, Flatten, MaxPooling2D, Conv1D
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint
from tensorflow.keras.datasets import mnist, cifar10, cifar100, fashion_mnist

from sklearn.datasets import fetch_covtype
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler, StandardScaler
from sklearn.metrics import mean_squared_error, r2_score


# 1. Data
(x_train, y_train), (x_test, y_test) = fashion_mnist.load_data()

print(x_train.shape, x_test.shape) # (60000, 28, 28) (10000, 28, 28)

x_train=x_train/255.
x_test=x_test/255.


# 2. Model Construction
model = Sequential()
model.add(Conv1D(128, 2, padding='same', input_shape=(28,28))) 
model.add(Conv1D(64, 2, padding='same')) 
model.add(Dropout(0.2))
model.add(Conv1D(32, 2, padding='same'))
model.add(Flatten())
model.add(Dense(16, activation='relu'))
model.add(Dense(10, activation='softmax'))


# 3. Compile and Training
model.compile(loss='sparse_categorical_crossentropy', optimizer='adam', metrics=['accuracy'])

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=32, restore_best_weights=True, verbose=1)

model.fit(x_train, y_train,
          validation_split=0.2,
          epochs=128,
          callbacks=[earlyStopping],
          batch_size=256)


# 4. Evaluation and Prediction
loss, accuracy = model.evaluate(x_test, y_test)
print("loss: ", loss)
print("accuracy: ", accuracy)

y_predict = model.predict(x_test)
y_predict = np.argmax(y_predict, axis=1) # (10000, 10) -> (10000,)

r2 = r2_score(y_test, y_predict) # y_test = (10000,), y_predict = (10000,)
print("R2: ", r2)



'''
Result
loss:  0.3900017738342285
accuracy:  0.864799976348877
R2:  0.7752727272727273

'''