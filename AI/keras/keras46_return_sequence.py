import numpy as np

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, SimpleRNN, LSTM, Dropout
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint


# 1. Data
x = np.array([[1,2,3], 
              [2,3,4], 
              [3,4,5], 
              [4,5,6], 
              [5,6,7], 
              [6,7,8], 
              [7,8,9],
              [8,9,10], 
              [9,10,11], 
              [10,11,12], 
              [20,30,40], 
              [30,40,50], 
              [40,50,60]]) # (13, 3)
y = np.array([4,5,6,7,8,9,10,11,12,13,50,60,70]) # (13,)

y_predict = np.array([50,60,70])


# 2. Model Construction
model = Sequential()
# model.add(SimpleRNN(units=64, input_shape=(3,1))) # input_length=3, input_dim=1, input_dim 단위로 연산
model.add(LSTM(units=64,
               input_shape=(3,1),
               return_sequences='True'))
# conv2D : input_dim 4 -> output_dim 4
# LSTM: input_dim 3 -> output_dim 2 / # (N,3,1) -> (N, 64) 
# return_sequences = True: (None, 3, 64) input_dim만 변화
model.add(LSTM(units=64))
# ValueError: Input 0 of layer "lstm_1" is incompatible with the layer: expected ndim=3, found ndim=2. Full shape received: (None, 64)
# 만일 return sequence 후 Dense 처리를 바로 할 경우, 총 3개의 차원 반환
'''


Loss:  1.325040340423584
Predict[50,60,70]:  [[69.40528]]
'''
model.add(Dense(64, activation='relu'))
model.add(Dropout(0.2))
model.add(Dense(32, activation='relu'))
model.add(Dropout(0.1))
model.add(Dense(32, activation='relu'))
model.add(Dense(1))
model.summary()


# 3. Compile and Training
model.compile(loss='mse', optimizer='adam')

earlyStopping = EarlyStopping(monitor='loss', mode='min', patience=32,
                              restore_best_weights=True,
                              verbose=1)

model.fit(x, y, epochs=512, callbacks=[earlyStopping], batch_size=1)


# 4. Evaluation and Prediction
loss = model.evaluate(x,y)
print("Loss: ", loss)

y_pred = np.array([50,60,70]).reshape(1,3,1)

result = model.predict(y_pred)
print("Predict[50,60,70]: ", result)



'''
Result
Loss:  2.875849485397339
Predict[50,60,70]:  [[80.20353]]

'''