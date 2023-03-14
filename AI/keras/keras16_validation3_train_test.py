import numpy as np

from sklearn.model_selection import train_test_split

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense


# 1. Data
x = np.array(range(1, 17))
y = np.array(range(1, 17))


# data 분리 3: train_test_split
x_train, x_test_tmp, y_train, y_test_tmp = train_test_split(
    x, y,
    shuffle=False,
    train_size=0.625,
    random_state=123
)

x_test, x_val, y_test, y_val = train_test_split(
    x_test_tmp, y_test_tmp,
    shuffle=False,
    train_size=0.5,
    random_state=123
)

print(x_train)
print(y_train)
print(x_test)
print(y_test)
print(x_val)
print(y_val)


# 2. Model
model = Sequential()
model.add(Dense(32, input_dim=1))
model.add(Dense(16, activation='relu'))
model.add(Dense(8, activation='relu'))
model.add(Dense(1))


# 3. compile and train
model.compile(loss='mse', optimizer='adam')
model.fit(x_train, y_train, epochs=500, batch_size=8,
          validation_data=(x_val, y_val))
# validation_data를 통해서 val_loss 추가


# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
print("loss: ", loss)

result = model.predict([17])
print("predict [17]: ", result)
