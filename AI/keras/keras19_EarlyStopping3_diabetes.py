from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.callbacks import EarlyStopping

from sklearn.datasets import load_diabetes
from sklearn.model_selection import train_test_split


# 1. Data
datasets = load_diabetes()
x = datasets.data
y = datasets.target
print(x.shape) # (442, 10)
print(y.shape) # (442, )


x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    train_size=0.7,
    random_state=111
)


# 2. Model Construction
model = Sequential()
model.add(Dense(64, input_shape=(10,)))
model.add(Dense(64))
model.add(Dense(32))
model.add(Dense(1))


# 3. Compile and train
model.compile(loss='mse', optimizer='adam')
earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=5, restore_best_weights=True, verbose=1)
hist = model.fit(x_train, y_train,
          epochs=500,
          batch_size=8,
          validation_split=0.2,
          callbacks=[earlyStopping],
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

plt.title('Diabetes loss')
plt.xlabel('epochs')
plt.ylabel('loss')
plt.grid()
plt.legend(loc='upper right')
plt.show()



'''
Result
Restoring model weights from the end of the best epoch: 45.
loss 3139.306396484375

'''