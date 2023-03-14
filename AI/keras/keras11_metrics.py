import numpy as np

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense

from sklearn.model_selection import train_test_split


# 1. Data
x = np.array(range(1,21))
y = np.array([1,2,4,3,5,7,9,3,8,12,13,8,14,15,9,6,17,23,21,20])

x_train, x_test, y_train, y_test = train_test_split(
    x,y,
    train_size=0.7,
    shuffle=True,
    random_state=123
)


# 2. Model Construction
model = Sequential()
model.add(Dense(64, input_dim=1))
model.add(Dense(32))
model.add(Dense(8))
model.add(Dense(1))


# 3. compile and train
model.compile(loss='mse', optimizer='adam',
              metrics=['mae', 'accuracy', 'acc']) # accuracy = acc
# metrics: loss에 1개의 지표밖에 사용하지 못하므로 추가적으로 loss 타입을 사용하고 싶을 때 사용
model.fit(x_train, y_train, epochs=256, batch_size=16)


# 4. Evalueate and Predict
loss = model.evaluate(x_test, y_test)
print("Loss: ", loss)



'''
Result

model.compile(loss='mse', optimizer='adam', metrics=['mae', 'accuracy', 'acc']) # accuracy = acc

Epoch 200/200
14/14 [==============================] - 0s 695us/step - loss: 10.3352 - mae: 2.1783 - accuracy: 0.0714 - acc: 0.0714 
1/1 [==============================] - 0s 126ms/step - loss: 15.0868 - mae: 3.0703 - accuracy: 0.0000e+00 - acc: 0.0000e+00
Loss:  [15.086791038513184, 3.0702998638153076, 0.0, 0.0]

'''