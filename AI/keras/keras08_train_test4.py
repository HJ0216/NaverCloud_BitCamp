import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from sklearn.model_selection import train_test_split


# 1. Data
x = np.array([range(10), range(21,31), range(201,211)]) # (3,10)

y = np.array([[1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
             [1, 1, 1, 1, 2, 1.3, 1.4, 1.5, 1.6, 1.4]]) # (2, 10)

x = x.T
y = y.T

'''
x
[0,1,2,3,4,5,6,7,8,9] -> [0,1,2,3,4,5,6] // [7,8,9]
[21,22,23,24,25,26,27,28,29,30] -> [21,22,23,24,25,26,27] // [28,29,30]
[201,202,203,204,205,206,207,208,209,210] -> [201,202,203,204,205,206,207] // [208,209,210]

x.T
[0,21,201]
[1,22,202]
[2,23,203]
[3,24,204]
[4,25,205]
[5,26,206]
[6,27,207]

[7,28,208]
[8,29,209]
[9,30,210]
'''



# [Practice] train_test_split를 이용하여 7:3로 잘라서 모델 구현
x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    train_size = 0.7,
    shuffle = True,
    random_state=123
)
# train_test_split(): 내부를 쪼개는 함수가 아니라 가장 겉을 감싸고 있는 단위를 쪼개는 함수
# 2개 이상 변수를 대입했을 때는 가장 외부의 개수를 맞춰야 함

print("x_train:\n", x_train)
print("x_test:\n", x_test)
print("y_train:\n", y_train)
print("y_test:\n", y_test)

'''
x_train:
 [[  5  26 206]
 [  8  29 209]
 [  3  24 204]
 [  1  22 202]
 [  6  27 207]
 [  9  30 210]
 [  2  23 203]]
x_test:
 [[  4  25 205]
 [  0  21 201]
 [  7  28 208]]
y_train:
 [[ 6.   1.3]
 [ 9.   1.6]
 [ 4.   1. ]
 [ 2.   1. ]
 [ 7.   1.4]
 [10.   1.4]
 [ 3.   1. ]]
y_test:
 [[5.  2. ]
 [1.  1. ]
 [8.  1.5]]
'''

'''
x_train = x_train.T
x_test = x_test.T
y_train = y_train.T
y_test = y_test.T

print("x_train:\n", x_train)
print("x_test:\n", x_test)
print("y_train:\n", y_train)
print("y_test:\n", y_test)

x_train:
 [[  5   8   3   1   6   9   2]
 [ 26  29  24  22  27  30  23]
 [206 209 204 202 207 210 203]]
x_test:
 [[  4   0   7]
 [ 25  21  28]
 [205 201 208]]
y_train:
 [[ 6.   9.   4.   2.   7.  10.   3. ]
 [ 1.3  1.6  1.   1.   1.4  1.4  1. ]]
y_test:
 [[5.  1.  8. ]
 [2.  1.  1.5]]
 
input_dim 기준: 데이터의 특징 = 열(column)
x_train = x_train.T 할 경우, 데이터의 특징인 열이 input_dim으로 들어가는 것이 아니라 한 특징의 데이터의 요소가 대입됨
데이터를 predict할 때도 각 특징별 데이터가 아닌 한 열의 데이터 값만 들어가게 됨
'''


# 2. Model
model = Sequential()
model.add(Dense(64, input_dim=3))
model.add(Dense(32))
model.add(Dense(32))
model.add(Dense(8))
model.add(Dense(2))


# 3. Compile
model.compile(loss='mae', optimizer='adam')
model.fit(x, y, epochs=512, batch_size=32)


# 4. (Evaluate) and Predict
result = model.predict([[9, 30, 210]])
print("[9, 30, 210] result: ", result)



'''
# Result

model.fit(x, y, epochs=1000) # batch_size: Default
Epoch 1000/1000
1/1 [==============================] - 0s 1000us/step - loss: 0.3318

result = model.predict([[9, 30, 210]])
[9, 30, 210] result:  [[9.433049  1.4707237]]


# Result2 with Batch

model.fit(x, y, epochs=1000, batch_size=2)
Epoch 1000/1000
5/5 [==============================] - 0s 1000us/step - loss: 0.1897

result = model.predict([[9, 30, 210]])
[9, 30, 210] result:  [[10.102887   1.4885552]]

'''