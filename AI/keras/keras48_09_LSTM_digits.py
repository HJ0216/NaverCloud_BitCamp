import numpy as np

from tensorflow.keras.utils import to_categorical
from tensorflow.keras.models import Sequential, Model, load_model
from tensorflow.keras.layers import Dense, SimpleRNN, LSTM, Dropout, Conv2D, Flatten, MaxPooling2D
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint

from sklearn.datasets import load_digits
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler, StandardScaler
from sklearn.metrics import mean_squared_error, r2_score


# 1. Data
dataset = load_digits()

x = dataset.data # for training
y = dataset.target # for predict

y=to_categorical(y)

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    train_size=0.7,
    random_state=123
)

scaler = StandardScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler .transform(x_test)

print(x_train.shape, x_test.shape)
# (1257, 64) (540, 64)

x_train = x_train.reshape(1257, 64, 1)
x_test = x_test.reshape(540, 64, 1)


# 2. Model Construction
model = Sequential()
model.add(LSTM(units=64, input_shape=(64,1)))
model.add(Dense(32, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(16, activation='relu'))
model.add(Dense(8, activation='relu'))
model.add(Dense(10, activation='softmax'))


# 3. Compile and Training
model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['accuracy'])

earlyStopping = EarlyStopping(monitor='accuracy', mode='max', patience=32,
                              restore_best_weights=True,
                              verbose=1)

model.fit(x_train, y_train,
          epochs=256,
          batch_size=32,
          validation_split=0.2,
          callbacks=[earlyStopping],
          verbose=1)


# 4. Evaluation and Prediction
loss = model.evaluate(x_test,y_test)
y_predict = model.predict(x_test)

def RMSE (y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))
print("RMSE: ", RMSE(y_test, y_predict))

r2 = r2_score(y_test, y_predict)
print("R2: ", r2)


'''
Result
RMSE:  3.8884847650276644
R2:  -0.8378256856830362

'''