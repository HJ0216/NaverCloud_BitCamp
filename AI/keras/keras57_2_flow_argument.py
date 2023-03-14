import numpy as np

from tensorflow.keras.preprocessing.image import ImageDataGenerator
# 이미지를 데이터로 변경하고 증폭시켜서 훈련을 시키는 역할을 하는 class
from tensorflow.keras.datasets import fashion_mnist


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


(x_train, y_train), (x_test, y_test) = fashion_mnist.load_data()
argument_size = 40000
randidx = np.random.randint(x_train.shape[0], size=argument_size) # (60,000)
# 6만 중 랜덤하게 4만 추출
# random.randint -> 중복 난수 추출
# 중복없이 난수 추출하고 싶을 경우, while 문으로 중복값 제거 과정 필요

print(randidx)
print(len(randidx))


x_argument = x_train[randidx].copy() # 데이터의 원본을 건드리지 않고 카피본을 만들어 arg에 저장
y_argument = y_train[randidx].copy()

print(x_argument.shape, y_argument.shape) # (40000, 28, 28) (40000,)

x_argument = x_argument.reshape(40000,28,28,1)


# flow_from_directory를 통한 imagegenerator 만들기
x_argumented = train_datagen.flow(
    x_argument,
    y_argument,
    batch_size=argument_size,
    shuffle=True,
    )

# 변환된 40,000개의 데이터
print(x_argumented[0][0].shape) # (40000,28,28,1)
print(x_argumented[0][1].shape) # (40000,)

# 원본 데이터와 합체하기 위해 shape 맞추기
x_train = x_train.reshape(60000,28,28,1)

x_train = np.concatenate((x_train, x_argumented[0][0]))
y_train = np.concatenate((y_train, x_argumented[0][1]))

print(x_train.shape)
print(y_train.shape)
