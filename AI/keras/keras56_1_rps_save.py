import numpy as np

from tensorflow.keras.preprocessing.image import ImageDataGenerator


# 1. Data
IMAGE_WIDTH=300
IMAGE_HEIGHT=300
IMAGE_SIZE=(IMAGE_WIDTH, IMAGE_HEIGHT)
IMAGE_CHANNELS=3

train_datagen = ImageDataGenerator(
    rescale=1./255,
    horizontal_flip=True,
    vertical_flip=True,
    width_shift_range=0.1,
    height_shift_range=0.1,
    rotation_range=15,
    zoom_range=0.2,
    shear_range=0.1,
    fill_mode='nearest'
)

xy_train = train_datagen.flow_from_directory(
    'D:/_data/rps',
    target_size=IMAGE_SIZE,
    batch_size=2520,
    class_mode='categorical',
    color_mode='rgb',
    shuffle='True',
    )
# Found 2520 images belonging to 3 classes.

np.save('D:/_data/rps_numpy/rps_x_train.npy', arr=xy_train[0][0])
np.save('D:/_data/rps_numpy/rps_y_train.npy', arr=xy_train[0][1])


# numpy로 data를 save할 때 scaling 처리만 된 원본을 저장하지만, rps 모델 속도 향상을 위해 전처리 데이터 저장