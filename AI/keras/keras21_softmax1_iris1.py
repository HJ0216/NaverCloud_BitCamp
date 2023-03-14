import pandas as pd

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.utils import to_categorical


from sklearn.model_selection import train_test_split
from sklearn.datasets import load_iris
# iris 꽃잎, 꽃받침의 길이 넓이 등을 통해 어떤 꽃인지 맞추는 data
from sklearn.preprocessing import OneHotEncoder


# 1. Data
datasets = load_iris()
print(datasets.DESCR) # pandas.describe / cvs_type.info
# attribute = column, feature, 열, 특성
# x_column(input_dim=4): sepal (length, width), petal (length, width) (cm)
# output_dim=1(3 class(종류): Iris-Setosa, Iris-Versicolour, Iris-Virginica)
print(datasets.feature_names) # pandas.columns, column name

x = datasets.data
y = datasets['target']

'''
print(x) # shape(150, 4)
print(y) # shape(150,), [0, 1, 2]
'''

'''
# sklearn: one_hot encoding
onehot_encoder=OneHotEncoder(sparse=False)
reshaped=y.reshape(len(y), 1)
onehot=onehot_encoder.fit_transform(reshaped)
print(onehot)
[[1. 0. 0.]...[0. 0. 1.]]
print(onehot.shape) (150, 3)

# keras: to_categorical
to_cat=to_categorical(y)
print(to_cat)
[[1. 0. 0.]...[0. 0. 1.]]
print(to_cat.shape) (150, 3)

# pandas: get_dummies
print(pd.get_dummies(y))
[150 rows x 3 columns]

'''


y=to_categorical(y)
print(y)
print(y.shape)


x_train, x_test, y_train, y_test = train_test_split(
    x,y,
    shuffle=True, # Non-shuffle: y_train, y_test 자료의 치우침이 발생
    random_state=333,
    test_size=0.2,
    stratify=y # 원본 자료 내 비율 유지 - 분류형 데이터에서만 사용 가능
)
'''
print("y_train: ", y_train)
print("y_test: ", y_test)
'''


# 2. Model Construction
model = Sequential()
model.add(Dense(64, activation='relu', input_shape=(4, ))) # input_dim = 4
model.add(Dense(64, activation='sigmoid'))
model.add(Dense(32,activation='relu'))
model.add(Dense(16,activation='linear'))
model.add(Dense(3,activation='softmax')) # 훈련 순서: output_dim -> activation
# 이진분류: output_dim = 1(col), activatoin = 'sigmoid', loss = 'binarycrossentropy'
# 다중분류: output_dim = 3(class), activatoin = 'softmax', loss = 'categorical_crossentropy'
# y_col: 1, y_class(종류): 3
# class의 number: 0, 1, 2 -> 동등한 대상임에도 불구하고 value의 차이가 발생
# One_Hot encoding 실행: 10진 정수 형식을 특수한 2진 binary 형식으로 변환


# 3. Compile and train
model.compile(loss='categorical_crossentropy',
              optimizer='adam',
              metrics=['accuracy']
              )
model.fit(x_train, y_train, epochs=50, batch_size=16,
          validation_split=0.2,
          verbose=1)


# 4. evaluate and predict
loss, accuracy = model.evaluate(x_test, y_test)
print("loss: ", loss)
print("accuracy: ", accuracy)


'''
Result

print("==========================")
print(y_test[:5])
y_predict = model.predict(x_test[:5]) # slicing
print(y_predict)
# predict result - output_dim에 맞춰서 출력
print("==========================")

accuracy:  1.0

y_test
[[1. 0. 0.]
 [0. 0. 1.]
 [1. 0. 0.]
 [0. 1. 0.]
 [0. 1. 0.]]

 y_predict
[[9.9925297e-01 7.4696867e-04 2.5099497e-13] 0
 [4.1013454e-10 2.7499644e-03 9.9725002e-01] 2
 [9.9945968e-01 5.4027850e-04 1.0871933e-13] 0
 [2.5293427e-06 6.0845017e-01 3.9154729e-01] 1
 [6.0919424e-06 8.0725497e-01 1.9273894e-01]] 1
 
 y_predict를 통해서 y_test를 추론할 수 있음
 
'''


# model.evaluate는 직접 0, 1 판단

from sklearn.metrics import accuracy_score
import numpy as np

y_predict = model.predict(x_test)
# y_predict: y_test 값을 확률로 반환(class 확률 합=1)
y_predict = np.argmax(y_predict, axis=1)
# arg 중 가장 큰 확률을 뽑아 위치값을 반환(0, 1, 2...)
print(y_predict)

y_test = np.argmax(y_test, axis=1) # -> onehot encoding 안할 때, 사용X
# (numpy.AxisError: axis 1 is out of bounds for array of dimension 1)
# y_test.shape: [1. 0. 0.] -> 가장 큰 값을찾아 위치를 반환
# -> y_predict와 y_test의 자료 형태를 맞추고 accuracy 비교
print(y_test)

acc = accuracy_score(y_test, y_predict)
print(acc)



'''
Summary

y_test: [1. 0. 0.] 타입
y_predict: 실수 타입
(타입 불일치 오류) ValueError: Classification metrics can't handle a mix of multilabel-indicator and continuous-multioutput targets

(해결) y_predict = np.argmax(y_predict, axis=1)
argmax를 사용해서 arg 중 가장 큰 값을 뽑아 위치값을 반환
(타입 불일치 오류) ValueError: Classification metrics can't handle a mix of multilabel-indicator and continuous-multioutput targets

(해결) y_test = np.argmax(y_test, axis=1)
argmax를 사용해서 arg 중 가장 큰 값을 뽑아 위치값을 반환

(최종 결과)
[0 2 0 2 1 1 0 2 0 2 2 2 2 0 0 0 2 0 2 1 0 2 1 1 0 2 1 1 1 2]
[0 2 0 1 1 1 0 2 0 2 2 2 2 0 0 0 2 0 2 1 0 2 1 1 0 2 1 1 1 1]
를 기준으로 accuracy 판단

'''