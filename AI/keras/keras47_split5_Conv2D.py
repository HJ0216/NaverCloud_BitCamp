import numpy as np

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, SimpleRNN, LSTM, Dropout, Conv2D, Flatten, MaxPooling2D
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

print(bbb)
print(bbb.shape) # (96,5)

x = bbb[:, :-1] # 모든 행, 시작 ~ -1번째 열
y = bbb[:, -1] # 모든 행, -1번째 열(시작: 0번째 열)
x_predict = ccc[:,:]

print(x.shape, y.shape)

x = x.reshape(96,2,2,1) # CNN을 위하여 filter or color 추가
x_predict = x_predict.reshape(7,2,2,1)


# 2. Model Construction
# 데이터가 잘 갖춰져 있을 때는 훈련량을 너무 많이 부여하지 않도록 유의
model = Sequential()
# model.add(SimpleRNN(units=64, input_shape=(4,1)))
# model.add(LSTM(units=64, input_shape=(2,2)))
# reshape 시, timesteps*feature가 유지되도록 reshape
model.add(Conv2D(64, (2,2), padding='same', activation='relu', input_shape=(2,2,1)))
model.add(Flatten())
model.add(Dense(32, activation='relu'))
model.add(Dense(16, activation='relu'))
model.add(Dense(16, activation='relu'))
model.add(Dense(4, activation='relu'))
model.add(Dense(1))


# 3. Compile and Training
model.compile(loss='mse', optimizer='adam')

earlyStopping = EarlyStopping(monitor='loss', mode='min', patience=32, restore_best_weights=True, verbose=1)

model.fit(x, y, epochs=128, callbacks=[earlyStopping], batch_size=2)


# 4. Evaluation and Prediction
loss = model.evaluate(x,y)

y_pred = np.array(x_predict).reshape(7,2,2,1)

result = model.predict(x_predict)
print("Predict[100 ... 107]: ", result)



'''
Result(feature: 2)
Loss:  0.0017532712081447244
Predict[100 ... 106]:
[[ 99.03793 ]
 [ 99.74565 ]
 [100.41812 ]
 [101.055336]
 [101.657684]
 [102.225784]
 [102.7604  ]]
 
 Result(feature: 2, CNN)
[[ 99.9969  ]
 [100.99686 ]
 [101.997665]
 [102.99917 ]
 [104.000656]
 [105.00215 ]
 [106.00365 ]]
'''