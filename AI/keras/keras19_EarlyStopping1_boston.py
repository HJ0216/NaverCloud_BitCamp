from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.callbacks import EarlyStopping # 대문자: Class, 소문자: method, variable

from sklearn.datasets import load_boston
from sklearn.model_selection import train_test_split


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
# model.add(Dense(5, input_dim=13))
# input_dim=scalar: (vector, scalar) 형태에서만 사용 가능
model.add(Dense(64, input_shape=(13,)))
# (vector, scalar) 이상 input_shape 사용
model.add(Dense(64))
model.add(Dense(32))
model.add(Dense(1))


# 3. Compile and train
model.compile(loss='mse', optimizer='adam')

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=5, restore_best_weights=True, verbose=1)
# mode: accuracy-max, loss-min, max인지 min인지 모를 때, auto 사용
# patience=5: 갱신이 되지 않더라도 5번 참음
# verbose를 통해 earlyStopping 과정 볼 수 있음: Restoring model weights from the end of the best epoch: 25.

hist = model.fit(x_train, y_train,
          epochs=500,
          batch_size=4,
          validation_split=0.2,
          callbacks=[earlyStopping],
          # 정지된 지점-5: min(val_loss)
          # 문제: 5번 인내 후, 최소가 아닌 val_loss 지점에서의 weight가 아닌 끊긴 지점에서의 weight가 반환
          # 해결: restore_best_weights="True"를 통해 최적의 weight 지점을 반환
          # restore_best_weights="False" Defualt
          # 최적의 weight로 predict 수행(false일 경우, epoch가 마무리된 weight를 기준으로 predict 수행)
          verbose=1
          )


# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
print("loss", loss)
print(hist) # <keras.callbacks.History object at 0x0000016CC0BBC4F0>
print(hist.history)
# hist.history
# model.compile(loss='mse', optimizer='adam') 기준으로 결과값 반환
# loss 결과값을 dictionary(key-value(list)) 형태로 반환
# key: 'loss', 'val_loss' / value = list[] 형태의 epochs의 loss, val_loss 값들의 집합
# {'loss': [25004.84765625, 1219.100830078125, 160.86378479003906, 82.83763122558594, 73.0763931274414, 71.3211669921875, 71.86249542236328, 70.77513885498047, 68.52639770507812, 68.08159637451172],
# 'val_loss': [2787.144775390625, 272.2074279785156, 78.57952880859375, 58.332862854003906, 55.535221099853516, 54.82481002807617, 54.39116668701172, 56.427764892578125, 59.47801971435547, 55.58904266357422]}

print(hist.history['loss']) # dictionary type에서 key 값 입력 시, value 반환
print(hist.history['val_loss'])


import matplotlib.pyplot as plt

plt.figure(figsize=(9, 6))
plt.plot(hist.history['loss'], c='red', marker='.', label='loss')
plt.plot(hist.history['val_loss'], c='blue', marker='.', label='val_loss')

plt.title('Boston loss')
plt.xlabel('epochs')
plt.ylabel('loss')
plt.grid()
plt.legend(loc='upper right')
plt.show()



'''
Result
Restoring model weights from the end of the best epoch: 14.
loss 69.12869262695312


plt.show()
-> plt을 통해 overfit 문제가 발생하는 지점을 찾을 수 있음
-> overfit 지점 이후: 최소 loss의 지점 = 최적 weight의 지점

'''