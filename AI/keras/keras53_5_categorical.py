# 1. Data
from tensorflow.keras.preprocessing.image import ImageDataGenerator

train_datagen = ImageDataGenerator(
    rescale=1./255.,
    horizontal_flip=True,
    vertical_flip=True,
    width_shift_range=0.1,
    height_shift_range=0.1,
    rotation_range=5,
    zoom_range=1.2,
    shear_range=0.7,
    fill_mode='nearest'
)

test_datagen = ImageDataGenerator(
    rescale=1./255.
)

# test는 정확한 평가 하기 위해 data를 처리하지 않음

xy_train = train_datagen.flow_from_directory(
    './_data/brain/train',
    target_size=(200, 200), # data 처리 시, 모든 데이터의 사이즈를 동일하게 맞춤
    batch_size=10,
    class_mode='categorical',
    # 폴더 라벨링 방식 지정: 이진 분류 binary(0 1) -> activation='sigmoid', loss='binary_crossentroy'
    # 폴더 라벨링 방식 지정: 다중 분류 categorical(0 1 2 ...) -> activation='softmax', loss='categorical_crossentroy'
    color_mode='grayscale',
    shuffle='True',
    )
# Found 160 images belonging to 2 classes.

xy_test = train_datagen.flow_from_directory(
    './_data/brain/test',
    target_size=(200, 200),
    batch_size=10,
    class_mode='categorical',
    color_mode='grayscale',
    shuffle='True',
    )
# Found 120 images belonging to 2 classes.

# print(type(xy_train)): DirectoryIterator
# print(type(xy_train[0])): tuple
# print(type(xy_train[0][0])): numpy
# print(type(xy_train[0][1])): numpy

# print(xy_train): DirectoryIterator
# print(xy_train[0]): tuple(x,y)
# print(xy_train[0][0]): x
# print(xy_train[0][1]): y

print(xy_train[0][1])
'''
class_mode: binary
shape: (10,)
[1. 0. 1. 1. 0. 1. 0. 1. 0. 0.]

class_mode: categorical(one-hot type)
shape: (10, 2) - class
[[0. 1.]
 [1. 0.]
 [0. 1.]
 [1. 0.]
 [0. 1.]
 [1. 0.]
 [1. 0.]
 [1. 0.]
 [0. 1.]
 [1. 0.]]
'''