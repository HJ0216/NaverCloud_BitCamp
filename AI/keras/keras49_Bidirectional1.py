import numpy as np

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, SimpleRNN, LSTM, Dropout, Bidirectional # 양방향 연산
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint


# 1. Data
a = np.array(range(1, 101))
x_predict = np.array(range(96, 106))
# 모두 x 데이터이므로 y 데이터를 split 할 필요 X

timesteps = 5 # x: 4개, y: 1개

def split_x(dataset, timesteps):
    aaa = [] # 빈 list 생성
    for i in range(len(dataset) - timesteps + 1): # for i in range(3->range(3): 0, 1, 2), range(4->2), range(5->1) : 반환하는 리스트 개수
        subset = dataset[i: (i+timesteps)] # dataset[0(이상):3(미만)] [1:4] [2:5]: dataset 위치에 있는 값 반환
        aaa.append(subset)
    return np.array(aaa)

bbb = split_x(a, timesteps)
ccc = split_x(x_predict, timesteps-1)
'''
# timesteps의 변수를 timesteps1, timesteps2로 나눠서 사용할 수 있음
timesteps1 = 5
timesteps2 = 4

bbb = split_x(a, timesteps1) # 5 적용
ccc = split_x(x_predict, timesteps2) # 4 적용

'''

# print(ccc)
# print(ccc.shape)

x = bbb[:, :-1] # 모든 행, 시작 ~ -1번째 열
y = bbb[:, -1] # 모든 행, -1번째 열(시작: 0번째 열)
x_predict = ccc[:,:]

print(x.shape, x_predict.shape)
# (96, 4) (7, 4)


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

'''
3차원 이상 작업 불가(split 후 reshape)
x_train, x_test, y_train, y_test = train_test_split()
x_train = x_train.reshape(7,4,1)
x_test = x_test.reshape(7,4,1)
x_predict = x_predict.reshape(7,4,1)

'''


# 2. Model Construction
model = Sequential()
# model.add(SimpleRNN(units=64, input_shape=(4,1)))
model.add(LSTM(units=16, input_shape=(4,1)))
# model.add(Bidirectional(LSTM(units=16), input_shape=(4,1)))
# Bidirection은 모델이 아니므로 모델 선택 필요
# layer에는 model만 입력하고, input_shape은 밖으로
# model.add(Dense(32, activation='relu'))
# model.add(Dense(16, activation='relu'))
# model.add(Dense(16, activation='relu'))
model.add(Dense(4, activation='relu'))
model.add(Dense(1))
model.summary()
# Bidirection의 model의 연산량의 2배

# 3. Compile and Training
model.compile(loss='mse', optimizer='adam')

earlyStopping = EarlyStopping(monitor='loss', mode='min', patience=32, restore_best_weights=True, verbose=1)

model.fit(x, y, epochs=128, callbacks=[earlyStopping], batch_size=2)


# 4. Evaluation and Prediction
loss = model.evaluate(x,y)

y_pred = np.array(x_predict).reshape(7,4,1)

result = model.predict(x_predict)
print("Predict[100 ... 107]: ", result)



'''
Result(Non-Bi)
[[ 98.24471 ]
 [ 98.72647 ]
 [ 99.13137 ]
 [ 99.4674  ]
 [ 99.74331 ]
 [ 99.96789 ]
 [100.149445]]

Result(Bi)
[[98.52119 ]
 [98.89825 ]
 [99.20789 ]
 [99.4599  ]
 [99.66357 ]
 [99.827385]
 [99.95875 ]]

'''