import numpy as np

from tensorflow.keras.preprocessing.image import ImageDataGenerator


# ImageDataGenerator Processing
train_datagen = ImageDataGenerator(
    rescale=1./255., # Scaling
    horizontal_flip=True, # 수평 뒤집기
    vertical_flip=True, # 수직 뒤집기
    width_shift_range=0.1, # 가로 이동
    height_shift_range=0.1, # 세로 이동
    rotation_range=5,# 훈련 시, 과적합 문제를 해결하기 위해 shift, ratatoin 시행
    zoom_range=1.2, # 20% 확대
    shear_range=0.7, # 절삭
    fill_mode='nearest' # 이동 시, 발생하는 빈 칸을 어떻게 채울 것인가
)

test_datagen = ImageDataGenerator(
    rescale=1./255.
)

# test data: data preprocessing X

# flow_from_directory를 통한 imagegenerator 만들기
xy_train = train_datagen.flow_from_directory(
    './_data/brain/train', # data path
    target_size=(200, 200), # data shape 통일
    batch_size=10,
    # total data: 160 -> batch_size=10: 160개를 10개씩 잘라서 훈련
    # 1 epoch 당 총 16번(iteration) 훈련 진행
    # dataset_scale check: batch_size를 높게 잡아 dataset scale 확인 가능
    class_mode='binary', # 폴더 라벨링 방식 지정: binary(0 1)
    color_mode='grayscale', # 색상: 흑백 / 컬러(rgb)
    shuffle='True',
    )
# Found 160 images belonging to 2 classes
# total 160장의 이미지가 2 classes(2 dir, folder)에 저장
# parameter, 가장 마지막에 ','가 있어도 문제 X

xy_test = train_datagen.flow_from_directory(
    './_data/brain/test',
    target_size=(200, 200),
    batch_size=10,
    class_mode='binary',
    color_mode='grayscale',
    shuffle='True',
    )
# Found 120 images belonging to 2 classes.

print(xy_train)
# <keras.preprocessing.image.DirectoryIterator object at 0x000002134BCFCA60>
# data type: tuple(x(numpy), y(numpy))의 집합
'''
print(xy_train[0])
(array([[[[0.08627451], [0.08627451], ..., [0.0858703 ], [0.08754121]],
        ...,
        [[0.3088285 ], [0.22372028], ..., [0.17596895], [0.15582304]]]], dtype=float32),
array([1., 1., 0., 1., 1., 1., 1., 0., 1., 0.], dtype=float32))


total_data: 160
batch_size: 10
x0: xy_train[0][0], y0: xy_train[0][1]
x1: xy_train[1][0], y1: xy_train[1][1]
...
x15: xy_train[15][0], y15: xy_train[15][1]

print(xy_train[0][0].shape) # data_x: (10, 200, 200, 1) = (batch_size, target_size_row, target_size_col, color)
print(xy_train[0][1].shape) # data_y: (10, ) = (batch_size,)

'''

print(type(xy_train)) # <class 'keras.preprocessing.image.DirectoryIterator'>: tuple(x(numpy),y(numpy))의 집합
print(type(xy_train[0])) # <class 'tuple'> tuple(x(numpy),y(numpy)): 수정 불가능한 list
print(type(xy_train[0][0])) # x: <class 'numpy.ndarray'>
print(type(xy_train[0][1])) # y: <class 'numpy.ndarray'>
