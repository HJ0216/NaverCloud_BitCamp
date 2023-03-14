import numpy as np
import datetime

from tensorflow.keras.datasets import cifar10
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, Flatten, Dense, Dropout, MaxPooling2D
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint


# 1. data
(x_train, y_train), (x_test, y_test) = cifar10.load_data()

x_train = x_train/255.
x_test = x_test/255.


# 2. Model
model = Sequential()
model.add(Conv2D(filters=128,
                 kernel_size=(3, 3),
                 padding='same',
                 input_shape=(32, 32, 3),
                 activation='relu'))
model.add(MaxPooling2D())
model.add(Conv2D(filters=64,
                 kernel_size=(3, 3),
                 padding='same'))
model.add(Conv2D(filters=64,
                 kernel_size=(3, 3),
                 padding='same'))
model.add(MaxPooling2D())
model.add(Conv2D(filters=32,
                 kernel_size=(3, 3),
                 padding='same'))
model.add(Flatten())
model.add(Dense(32, activation='relu'))
model.add(Dropout(0.3))
model.add(Dense(10, activation='softmax'))
model.summary()


# 3. Compile and train
model.compile(loss='sparse_categorical_crossentropy', optimizer='adam', metrics=['acc'])

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=32, restore_best_weights=True, verbose=1)

model.fit(x_train, y_train, epochs=256, batch_size=128,
                    validation_split=0.2,
                    callbacks=[earlyStopping],
                    verbose=1)


# 4. evaluate and predict
result = model.evaluate(x_test, y_test)
print("loss: ", result[0])
print("acc: ", result[1])



'''
Result
loss:  0.8916618227958679
acc:  0.6970000267028809

'''