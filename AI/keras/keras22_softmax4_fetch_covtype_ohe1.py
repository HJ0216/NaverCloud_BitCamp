import numpy as np
import pandas as pd

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.callbacks import EarlyStopping

from sklearn.datasets import fetch_covtype
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
from sklearn.preprocessing import OneHotEncoder

# 1. Data
datasets = fetch_covtype()
x = datasets.data
y = datasets['target']
print(x.shape, y.shape) # (581012, 54) (581012,)
print(np.unique(y, return_counts=True))
'''
(array([1, 2, 3, 4, 5, 6, 7]),
array([211840, 283301,  35754,   2747,   9493,  17367,  20510],
dtype=int64))

print(datasets.DESCR) # numpy.DESCR = pandas.describe / cvs_type.info
print(datasets.feature_names) # numpy.feature_names = pandas.columns, column name
'''



onehot_encoder=OneHotEncoder(sparse=False)
# return one-hot numeric an array
# Encode categorical features as a one-hot numeric array.
# sparse : bool, default=True
# Will return sparse matrix if set True else will return an array.
print("Original y ", y)
# y = datasets['target']
# [5 5 2 ... 3 3 3]
# print(y.shape) (581012,)
reshaped=y.reshape(len(y), 1) # (581012, 1): 581012의 scalar를 1 vector에 담아줌
# len(y): 581012 = data의 개수 -> reshaped.shape (581012, 1)
# [0,1,....,581011] -> [[0,1,...,581011]]
# reshape시 주의 사항: data의 값과 순서는 변경되면 안됨
print("Before onehot_encoder y: ", y)
# before y:  [5 5 2 ... 3 3 3]
y=onehot_encoder.fit_transform(reshaped)

'''
OneHotEncoder sol2
ohe = OneHotEncoder()
y = ohe.fit(y)
-> ValueError: Expected 2D, got 1D
-> OneHotEncoder 사용 가능한 shape은 최종 matrix(2차원)
y = y.reshape(581012, 1) vector -> matrix

from sklearn.preprocessing import OneHotEncoder
ohe = OneHotEncoder()
ohe.fit(y) # 훈련 가중치 저장
y = ohe.transform(y) # one-hot encoder 형태로 type 변환
print(y)
(0, 4) 1.0 -> 4번째가 1
(1, 4) 1.0 -> 4번째가 1
(2, 1) 1.0 -> 1번째가 1
...
print(type(y)) <clss 'scipy.sparse._csr.csr_matrix'>
onehot encoder 형태의 scipy type

(*암기) TypeError: use X.toarray() to convert to a dense numpy array.
y = y.toarray()
print(type(y)) <class 'numpy'>
numpy array의 one-hot encoder type으로 변환
[[0. 0. 0. ... 1. 0. 0.]
 [0. 0. 0. ... 1. 0. 0.]
 [0. 0. 1. ... 0. 0. 0.]
 [0. 0. 1. ... 0. 0. 0.]
 [0. 0. 1. ... 0. 0. 0.]]
-> onehot type(class -> column으로 변환하여 값을 도출)

print(y.shape) #(581012, 7)
transformed array.shape (n_samples, n_features_new=encoding에 따라 class-> col: one-hot encoder 형태이므로)

'''



x_train, x_test, y_train, y_test = train_test_split(
    x,y,
    shuffle=True,
    random_state=333,
    test_size=0.2,
    stratify=y
)


# 2. Model Construction
model = Sequential()
model.add(Dense(64, activation='relu', input_shape=(54, )))
model.add(Dense(64, activation='sigmoid'))
model.add(Dense(32,activation='relu'))
model.add(Dense(16,activation='linear'))
model.add(Dense(7,activation='softmax'))


# 3. Compile and train
model.compile(loss='categorical_crossentropy',
              optimizer='adam',
              metrics=['accuracy']
              )

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=20, restore_best_weights=True, verbose=1)

model.fit(x_train, y_train, epochs=100, batch_size=128,
          validation_split=0.2,
          verbose=1)


# 4. evaluate and predict
loss, accuracy = model.evaluate(x_test, y_test)
print("loss: ", loss)
print("accuracy: ", accuracy)

y_predict = model.predict(x_test)
y_predict = np.argmax(y_predict, axis=1)
y_test = np.argmax(y_test, axis=1)

acc = accuracy_score(y_test, y_predict)
print("accuracy_score: ", acc)


'''
Result

loss:  0.596627414226532
accuracy:  0.7471494078636169
accuracy_score:  0.7471493851277505

'''