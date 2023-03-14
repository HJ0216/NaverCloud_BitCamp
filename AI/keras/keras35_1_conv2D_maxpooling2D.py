from tensorflow.keras.datasets import mnist
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, MaxPooling2D


# 1. data
(x_train, y_train), (x_test, y_test) = mnist.load_data()

# CNN Conv2D 처리하기 위해 4D(Tensor)화
x_train = x_train.reshape(60000, 28, 28, 1)
x_test = x_test.reshape(10000, 28, 28, 1)


# 2. Model
model = Sequential()
model.add(Conv2D(filters=128,
                 kernel_size=(3, 3),
                 strides=1,
                 input_shape=(28, 28, 1),
                 activation='relu'))
model.add(MaxPooling2D(pool_size=(5, 5)))
model.summary()
'''
Model: "sequential()"
______________________________________
 Layer (type)      Output Shape
======================================
(Conv2D)           (None, 26, 26, 128) -> stride: defualt=1, 자르고 난 나머지 연산 대상에 포함
(MaxPooling2D)     (None, 5, 5, 128)   -> stride: default=kernel_size(겹치지 않게 진행), 자르고 난 나머지 연산 대상에 미포함

'''