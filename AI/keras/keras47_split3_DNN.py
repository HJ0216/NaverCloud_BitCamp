# make DNN model construction

import numpy as np

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, SimpleRNN, LSTM, Dropout
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint


# 1. Data
a = np.array(range(1, 101))
x_predict = np.array(range(96, 106))

timesteps = 5 # x: 4개, y: 1개

def split_x(dataset, timesteps):
    aaa = []
    for i in range(len(dataset) - timesteps + 1):
        subset = dataset[i: (i+timesteps)]
        aaa.append(subset)
    return np.array(aaa)

bbb = split_x(a, timesteps)
ccc = split_x(x_predict, timesteps-1)

x = bbb[:, :-1] # 모든 행, 시작 ~ -1번째 열
y = bbb[:, -1] # 모든 행, -1번째 열(시작: 0번째 열)
x_predict = ccc[:,:]

print(x_predict) # (7, 4)
'''
[[ 96  97  98  99]
 [ 97  98  99 100]
 [ 98  99 100 101]
 [ 99 100 101 102]
 [100 101 102 103]
 [101 102 103 104]
 [102 103 104 105]]
 '''


# 2. Model Construction
# data가 잘 갖춰져 있는 경우에는 model layer 수를 과도하게 주지 않도록 유의
model = Sequential()
# model.add(SimpleRNN(units=64, input_shape=(4,1)))
# model.add(LSTM(units=64, input_shape=(4,1)))
model.add(Dense(32, activation='relu', input_shape=(4,)))
model.add(Dense(16, activation='relu'))
model.add(Dense(16, activation='relu'))
model.add(Dense(4, activation='relu'))
model.add(Dense(1))


# 3. Compile and Training
model.compile(loss='mse', optimizer='adam')

earlyStopping = EarlyStopping(monitor='loss', mode='min', patience=32, restore_best_weights=True, verbose=1)

model.fit(x, y, epochs=128, callbacks=[earlyStopping], batch_size=1)


# 4. Evaluation and Prediction
loss = model.evaluate(x,y)

y_pred = np.array(x_predict).reshape(7,4)

result = model.predict(x_predict)
print("Predict[100 ... 107]: ", result)


'''
Result
Loss:  0.0017532712081447244
Predict[100 ... 106]:
[[ 99.576385]
 [100.32554 ]
 [101.01736 ]
 [101.64716 ]
 [102.21582 ]
 [102.72842 ]
 [103.18755 ]]
 
DNN
Predict[100 ... 107]:
[[100.00206 ]
 [101.00212 ]
 [102.00219 ]
 [103.00223 ]
 [104.002304]
 [105.00237 ]
 [106.00243 ]]
 
'''