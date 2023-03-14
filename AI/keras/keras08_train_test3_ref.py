import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from sklearn.model_selection import train_test_split


# 1.
x = np.array([[1,2,3,4,5,6,7,8,9,10], [11,22,33,44,55,66,77,88,99,100]])
y = np.array([1,2,3,4,5,6,7,8,9,10]) # [0,1,2,3,4,5,6,7,8,9]

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    train_size = 0.7,
    shuffle = True,
    random_state=111
)

print(x_train)
print(y_train)



'''
ValueError: Found input variables with inconsistent numbers of samples: [2, 10]
'''


# 2.
x = np.array([[1,2,3,4,5,6,7,8,9,10], [11,22,33,44,55,66,77,88,99,100]])
y = np.array([[1,2,3,4,5,6,7,8,9], [11,22,33,44,55,66,77,88,99]]) # [0,1,2,3,4,5,6,7,8,9]

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    train_size = 0.7,
    shuffle = True,
    random_state=111
)

print(x_train)
print(y_train)



'''
[[ 1  2  3  4  5  6  7  8  9 10]]
[[1 2 3 4 5 6 7 8 9]]

train_test_split 대상: 가장 큰 포장단위
train_test_split 주의: scalar 개수는 다르더라도 가장 큰 포장단위의 개수는 같아야 함
'''