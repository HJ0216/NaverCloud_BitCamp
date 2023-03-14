import numpy as np

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense

from sklearn.datasets import load_boston
from sklearn.model_selection import train_test_split


# 1. Data
datasets = load_boston()
x = datasets.data
y = datasets.target

print(x.shape, y.shape) # (506, 13) (506,)

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    test_size=0.2,
    shuffle= True,
    random_state = 333
)


# 2. Model Construction
model = Sequential()
model.add(Dense(64, input_shape=(13,)))
model.add(Dense(64))
model.add(Dense(32))
model.add(Dense(1))


# 3. Compile and train
model.compile(loss='mse', optimizer='adam')
hist = model.fit(x_train, y_train,
          epochs=10,
          batch_size=16,
          validation_split=0.2,
          verbose=1)


# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
print("loss", loss)

import matplotlib.pyplot as plt
x_len = np.arange(len(hist.history['loss']))
plt.scatter(x_len, hist.history['loss'])
# len(hist.history['loss']): fit(x_train)의 loss의 개수 = epochs
# 산점도(x 생략 불가)
plt.show() # 작성한 plt show
