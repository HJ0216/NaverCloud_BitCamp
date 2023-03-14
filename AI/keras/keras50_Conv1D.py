import numpy as np

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, SimpleRNN, LSTM, Dropout, GRU, Bidirectional, Conv1D, Flatten
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint


# 1. Data
a = np.array(range(1, 101))
x_predict = np.array(range(96, 106))

timesteps = 5

def split_x(dataset, timesteps):
    aaa = [] 
    for i in range(len(dataset) - timesteps + 1):
        subset = dataset[i: (i+timesteps)]
        aaa.append(subset)
    return np.array(aaa)

bbb = split_x(a, timesteps)
ccc = split_x(x_predict, timesteps-1)

# print(ccc)
# print(ccc.shape)

x = bbb[:, :-1] # 모든 행, 시작 ~ -1번째 열
y = bbb[:, -1] # 모든 행, -1번째 열(시작: 0번째 열)
x_predict = ccc[:,:]

print(x.shape, x_predict.shape) # (96, 4) (7, 4)

'''
print(x, y)
x: [1 2 3 4] ... [96 97 98 99]
y: [5 6 ... 99 100]
'''

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

# x_predict = x_predict.reshape(7,4,1)


# 2. Model Construction
model = Sequential()
# model.add(SimpleRNN(units=64, input_shape=(4,1)))
# model.add(LSTM(units=16, input_shape=(4,1)))
# model.add(Bidirectional(LSTM(units=16, return_sequences=True),
#                         input_shape=(4,1)))
# model.add(GRU(4, activation='relu'))
model.add(Conv1D(128, 2, padding='same', input_shape=(4,1))) 
model.add(Conv1D(64, 2, padding='same', input_shape=(4,1))) 
model.add(Conv1D(64, 2, padding='same', input_shape=(4,1))) 
model.add(Conv1D(64, 2, padding='same', input_shape=(4,1))) 
model.add(Conv1D(64, 2, padding='same', input_shape=(4,1)))
model.add(Flatten())
# conv1d_4 (Conv1D) (None, 4, 64): output_shape=3
# Flatten: output_shape=2
# 출력 단위를 맞추기
model.add(Dense(16, activation='relu'))
model.add(Dense(1))
model.summary()


# 3. Compile and Training
model.compile(loss='mse', optimizer='adam')

earlyStopping = EarlyStopping(monitor='loss', mode='min', patience=32, restore_best_weights=True, verbose=1)

model.fit(x, y, epochs=2, callbacks=[earlyStopping], batch_size=2)


# 4. Evaluation and Prediction
loss = model.evaluate(x,y)
print(loss)

# y_pred = np.array(x_predict).reshape(7,4)

result = model.predict(x_predict)
print("Predict[100 ... 107]: ", result)



'''
Result(Bi)
Predict[100 ... 107]:
[[ 99.63187 ]
 [100.36405 ]
 [101.034744]
 [101.644295]
 [102.1946  ]
 [102.68868 ]
 [103.13039 ]]

'''