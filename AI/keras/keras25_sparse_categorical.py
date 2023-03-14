import pandas as pd
import numpy as np

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.utils import to_categorical


from sklearn.model_selection import train_test_split
from sklearn.datasets import load_iris
from sklearn.preprocessing import OneHotEncoder
from sklearn.metrics import accuracy_score


# 1. Data
datasets = load_iris()
print(datasets.DESCR)
print(datasets.feature_names)

x = datasets.data
y = datasets['target']

'''
print(x) # shape(150, 4)
print(y) # shape(150,), [0, 1, 2]
'''



'''
y=to_categorical(y)
print(y)
print(y.shape)

'''



x_train, x_test, y_train, y_test = train_test_split(
    x,y,
    shuffle=True, # Non-shuffle: y_train, y_test 자료의 치우침이 발생
    random_state=333,
    test_size=0.2,
    stratify=y # 원본 자료 내 비율 유지 - 분류형 데이터에서만 사용 가능
)


# 2. Model Construction
model = Sequential()
model.add(Dense(64, activation='relu', input_shape=(4, )))
model.add(Dense(64, activation='sigmoid'))
model.add(Dense(32,activation='relu'))
model.add(Dense(16,activation='linear'))
model.add(Dense(3,activation='softmax')) # 훈련 순서: output_dim -> activation
# one-hot encoding을 해주진 않았지만 y_col(1) 대신 y_class(3) 값을 입력해도 Error가 발생하지 않게 자동 형변환 됨
# 자동 형변환 이후 activation = softmax로 다중분류 처리


# 3. Compile and train
model.compile(loss='sparse_categorical_crossentropy',
              # sparse_categorical_crossentropy 사용 시, onehot encoding 사용 X
              # ValueError: `labels.shape` must equal `logits.shape` except for the last dimension.
              optimizer='adam',
              metrics=['accuracy']
              )

model.fit(x_train, y_train, epochs=128, batch_size=32,
          validation_split=0.2,
          verbose=1)


# 4. evaluate and predict
loss, accuracy = model.evaluate(x_test, y_test)
print("loss: ", loss)
print("accuracy: ", accuracy)

print("without onehotencoder: y_test: ", y_test) # raw data로 [0, 1]을 
y_predict = model.predict(x_test)
# y_predict: y_test 값을 확률로 반환(class 확률 합=1)
y_predict = np.argmax(y_predict, axis=1)
# arg 중 가장 큰 확률을 뽑아 위치값을 반환(0, 1, 2...)

# y_test = np.argmax(y_test, axis=1) # -> onehot encoding 안할 때, 사용X
# (numpy.AxisError: axis 1 is out of bounds for array of dimension 1)

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

acc = accuracy_score(y_test, y_predict)
print(acc)



'''
Summary

y_test: [1. 0. 0.] 타입
y_predict: 실수 타입
(타입 불일치 오류) ValueError: Classification metrics can't handle a mix of multilabel-indicator and continuous-multioutput targets

(해결) y_predict = np.argmax(y_predict, axis=1)
argmax를 사용해서 arg 중 가장 큰 값을 뽑아 위치값을 반환

(최종 결과)
[0 2 0 2 1 1 0 2 0 2 2 2 2 0 0 0 2 0 2 1 0 2 1 1 0 2 1 1 1 2]
[0 2 0 1 1 1 0 2 0 2 2 2 2 0 0 0 2 0 2 1 0 2 1 1 0 2 1 1 1 1]
를 기준으로 accuracy 판단

'''