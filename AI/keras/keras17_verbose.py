import time

from sklearn.datasets import load_boston
from sklearn.model_selection import train_test_split

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense


# 1. Data
datasets = load_boston()
x = datasets.data
y = datasets.target

print(x.shape, y.shape) # (506, 13) (506,)

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    test_size=0.2,
    shuffle= True,
    random_state = 333
)


# 2. Model Construction
model = Sequential()
# model.add(Dense(5, input_dim=13)) # (vector, scalar) input_dim 사용 가능
model.add(Dense(5, input_shape=(13,))) # 다차원의 경우 input_shape 사용
model.add(Dense(4))
model.add(Dense(3))
model.add(Dense(2))
model.add(Dense(1))


# 3. Compile and train
model.compile(loss='mse', optimizer='adam')
start = time.time()
model.fit(x_train, y_train, epochs=50, batch_size=1, validation_split=0.2, verbose=0)
# 함수 수행 시 발생하는 상세한 정보들을 표준 출력으로 자세히 내보낼 것인가를 나타냄
# 0: 미출력, 1(Default): 자세히, 2: 함축적 정보 출력 3. 2보다 더 함축적 정보 출력
# fit 수행 시, 수행 과정을 어떤식으로 보여주는가에 따라 소요시간이 변화함
end = time.time()

# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
print("loss", loss)
print("소요 시간: ", end-start)


'''
Result

verbose = 0
진행과정 나오지 않음
소요 시간:  11.600938081741333

verbose = 1
Epoch 50/50
323/323 [==============================] - 0s 762us/step - loss: 45.8877 - val_loss: 38.3869
소요 시간:  12.756858348846436

verbose = 2
Epoch 50/50
323/323 - 0s - loss: 43.5736 - val_loss: 33.5931 - 214ms/epoch - 664us/step
소요 시간:  11.219664096832275

verbose = 3 이상
Epoch 50/50
소요 시간:  11.527057409286499

'''