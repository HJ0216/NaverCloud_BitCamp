import numpy as np

from tensorflow.keras.models import Sequential, Model, load_model
from tensorflow.keras.layers import Dense, SimpleRNN, LSTM, Dropout, Conv2D, Flatten, MaxPooling2D
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint
from tensorflow.keras.datasets import mnist, cifar10, cifar100, fashion_mnist

from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler, StandardScaler
from sklearn.metrics import mean_squared_error, r2_score


# 1. Data
(x_train, y_train), (x_test, y_test) = mnist.load_data()

print(x_train.shape, x_test.shape) # (60000, 28, 28) (10000, 28, 28)

x_train=x_train/255.
x_test=x_test/255.

'''
print(np.unique(y_train, return_counts=True))

(array([0, 1, 2, 3, 4, 5, 6, 7, 8, 9], dtype=uint8),
array([5923, 6742, 5958, 6131, 5842, 5421, 5918, 6265, 5851, 5949], dtype=int64))
'''


# 2. Model Construction
model = Sequential()
model.add(LSTM(units=64, input_shape=(28,28)))
model.add(Dense(32, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dropout(0.2))
model.add(Dense(16, activation='relu'))
model.add(Dense(10, activation='softmax')) # 다중 분류


# 3. Compile and Training
model.compile(loss='sparse_categorical_crossentropy', optimizer='adam', metrics=['acc'])
# 다중분류, one hot encoding X -> sparse categorical crossentropy

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=32, restore_best_weights=True, verbose=1)

model.fit(x_train, y_train,
          validation_split=0.2,
          epochs=128,
          callbacks=[earlyStopping],
          batch_size=256)


# 4. Evaluation and Prediction
result = model.evaluate(x_test, y_test)
print("loss: ", result[0])
print("acc: ", result[1])

y_predict = model.predict(x_test)
y_predict = np.argmax(y_predict, axis=1)

r2 = r2_score(y_test, y_predict)
print("R2: ", r2)



'''
Result
loss:  0.07328376173973083
acc:  0.9812999963760376
R2:  0.9632089716433644


'''