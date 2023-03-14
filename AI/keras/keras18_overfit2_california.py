from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense

from sklearn.datasets import fetch_california_housing
from sklearn.model_selection import train_test_split


# 1. Data
datasets = fetch_california_housing()
x = datasets.data
y = datasets.target

print(x.shape, y.shape) # (20640, 8) (20640,)

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    test_size=0.2,
    shuffle= True,
    random_state = 333
)


# 2. Model Construction
model = Sequential()
model.add(Dense(64, input_shape=(8,)))
model.add(Dense(64))
model.add(Dense(32))
model.add(Dense(1))


# 3. Compile and train
model.compile(loss='mse', optimizer='adam')
hist = model.fit(x_train, y_train,
          epochs=300,
          batch_size=16,
          validation_split=0.2,
          verbose=1)


# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
print("loss", loss)

print(hist)
print(hist.history)
print(hist.history['loss'])
print(hist.history['val_loss'])


import matplotlib.pyplot as plt

plt.figure(figsize=(9, 6)) 
plt.plot(hist.history['loss'], c='red', marker='.', label='loss')
plt.plot(hist.history['val_loss'], c='blue', marker='.', label='val_loss')

plt.title('california_housing loss')
plt.xlabel('epochs')
plt.ylabel('loss')
plt.grid()
plt.legend(loc='upper right')
plt.show()



'''
Result
loss 0.7481603622436523

'''