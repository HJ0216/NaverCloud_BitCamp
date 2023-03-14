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
argument_size = 100

# flow_from_directory를 통한 imagegenerator 만들기
x_data = train_datagen.flow(
    np.tile(x_train[0].reshape(28*28), argument_size).reshape(-1, 28, 28, 1), # x, -1 = total data scale
    np.zeros(argument_size), # y
    batch_size=argument_size,
    shuffle=True,
    )

print(x_data[0])
print(x_data[0][0].shape) # (100, 28, 28, 1)
print(x_data[0][1].shape) # (100,)

import matplotlib.pyplot as plt
plt.figure(figsize=(7,7))
for i in range(49):
    plt.subplot(7,7,i+1)
    plt.axis('off')
    plt.imshow(x_data[0][0][i], cmap='gray')
plt.show()