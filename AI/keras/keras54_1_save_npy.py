# 데이터 -> .npy : 이미지를 수치로 변환하는데 시간을 줄일 수 있음
# .npy file 생성 시, 원본 상태 그대로 저장


# 1. Data
import numpy as np

from tensorflow.keras.preprocessing.image import ImageDataGenerator

train_datagen = ImageDataGenerator(
    rescale=1./255.,
    # horizontal_flip=True,
    # vertical_flip=True,
    # width_shift_range=0.1,
    # height_shift_range=0.1,
    # rotation_range=5,
    # zoom_range=1.2,
    # shear_range=0.7,
    # fill_mode='nearest'
)

test_datagen = ImageDataGenerator(
    rescale=1./255.
)

xy_train = train_datagen.flow_from_directory(
    './_data/brain/train',
    target_size=(200, 200),
    batch_size=10000,
    class_mode='binary', # 폴더 라벨링 방식 지정: binary(0 1)
    color_mode='grayscale',
    shuffle='True',
    )

xy_test = train_datagen.flow_from_directory(
    './_data/brain/test',
    target_size=(200, 200),
    batch_size=10000,
    class_mode='binary',
    color_mode='grayscale',
    shuffle='True',
    )
# Found 120 images belonging to 2 classes.

np.save('./_data/brain/brain_x_train.npy', arr=xy_train[0][0])
np.save('./_data/brain/brain_y_train.npy', arr=xy_train[0][1])
# np.save('./_data/brain/brain_xy_train.npy', array=xy_train[0])
# xy_train[0]: tuple type이므로 np.save로 저장 불가

np.save('./_data/brain/brain_x_test.npy', arr=xy_test[0][0])
np.save('./_data/brain/brain_y_test.npy', arr=xy_test[0][1])
# np.save('./_data/brain/brain_xy_test.npy', array=xy_test[0])