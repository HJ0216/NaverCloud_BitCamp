import numpy as np

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, SimpleRNN, LSTM, Dropout
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint


# 1. data

#a = np.array(range(1,6)) # 1~5
a = np.array(range(1,11)) # 1~10
# timesteps = 3
timesteps = 5

def split_x(dataset, timesteps):
    aaa = [] # 빈 list 생성
    for i in range(len(dataset) - timesteps + 1): # for i in range(3->range(3): 0, 1, 2), range(4->2), range(5->1) : 반환하는 리스트 개수
        subset = dataset[i: (i+timesteps)] # dataset[0(이상):3(미만)] [1:4] [2:5]: dataset 위치에 있는 값 반환
        aaa.append(subset) # append: 추가
    return np.array(aaa)

bbb = split_x(a, timesteps)
print(bbb)
'''
timesteps = 5
[[1 2 3 4 5]]

timesteps = 4
[[1 2 3 4]
 [2 3 4 5]]
 
 timesteps = 3
 [[1 2 3]
 [2 3 4]
 [3 4 5]]
 
'''

'''
[[ 1  2  3  4  5]
 [ 2  3  4  5  6]
 [ 3  4  5  6  7]
 [ 4  5  6  7  8]
 [ 5  6  7  8  9]
 [ 6  7  8  9 10]]
'''
print(bbb.shape) # (6, 5)

# make x, y data
x = bbb[:, :-1] # 모든 행, 시작 ~ -1번째 열
y = bbb[:, -1] # 모든 행, -1번째 열(시작: 0번째 열)

print(x, y)
'''
x: 
[[1 2 3 4]
 [2 3 4 5]
 [3 4 5 6]
 [4 5 6 7]
 [5 6 7 8]
 [6 7 8 9]]
 
 y:
 [ 5  6  7  8  9 10]
[5][6][7][8][9][10]

'''


# 2. Model Construction
model = Sequential()
# model.add(SimpleRNN(units=64, input_shape=(4,1)))
model.add(LSTM(units=256, input_shape=(4,1)))
model.add(Dense(256, activation='relu'))
model.add(Dropout(0.2))
model.add(Dense(128, activation='relu'))
model.add(Dropout(0.1))
model.add(Dense(64, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(1))
model.summary()


# 3. Compile and Training
model.compile(loss='mse', optimizer='adam')

earlyStopping = EarlyStopping(monitor='loss', mode='min', patience=256, restore_best_weights=True, verbose=1)

model.fit(x, y, epochs=1024, callbacks=[earlyStopping], batch_size=2)


# 4. Evaluation and Prediction
loss = model.evaluate(x,y)
print("Loss: ", loss)

y_pred = np.array([7,8,9,10]).reshape(1,4,1)
result = model.predict(y_pred)
print("Predict[7,8,9,10]: ", result)



'''
Result
Loss:  0.0017532712081447244
Predict[7,8,9,10]:  [[10.874769]]

'''