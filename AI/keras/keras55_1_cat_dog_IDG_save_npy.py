import numpy as np
import pandas as pd 
import random
import os
import matplotlib.pyplot as plt

from sklearn.model_selection import train_test_split

from tensorflow.keras.preprocessing.image import ImageDataGenerator, load_img
from tensorflow.keras.utils import to_categorical


IMAGE_WIDTH=128
IMAGE_HEIGHT=128
IMAGE_SIZE=(IMAGE_WIDTH, IMAGE_HEIGHT)
IMAGE_CHANNELS=3


filenames = os.listdir("D:/_data/dogs-vs-cats/train/")
categories = []
for filename in filenames:
    category = filename.split('.')[0] # filename: cat.0.jpg
    if category == 'dog':
        categories.append(1) # dog -> 1 추가
    else:
        categories.append(0) # cat -> 0 추가
# category 분류: 1: dog, 0: cat

df = pd.DataFrame({
    'filename': filenames,
    'category': categories
})

'''
print(df.head())

       filename  category
0     cat.0.jpg         0
1     cat.1.jpg         0
2    cat.10.jpg         0
3   cat.100.jpg         0
4  cat.1000.jpg         0
'''

'''
Sample image show

sample = random.choice(filenames)
image = load_img("D:/_data/dogs-vs-cats/train/"+sample)
plt.imshow(image)
plt.show()
'''

df["category"] = df["category"].replace({0: 'cat', 1: 'dog'}) 

train_df, validate_df = train_test_split(df, test_size=0.20, random_state=42)
# train: 20,000 / val: 5,000

train_df = train_df.reset_index(drop=True) # Index_reset, drop=true: Idx에서 setting한 col을 df에서 삭제할지 여부
validate_df = validate_df.reset_index(drop=True)
'''
df_tmp
   c0 c1 c2
c4
r0  0  1  2
r1  3  4  5
r2  6  7  8

df_tmp.reset_index()
      c0 c1 c2
   c4
0  r0  0  1  2
1  r1  3  4  5
2  r2  6  7  8

df_tmp.reset_index(drop=True)
  c0 c1 c2
0  0  1  2
1  3  4  5
2  6  7  8

'''

print(train_df.shape) #(20000, 2)
total_train = train_df.shape[0] # 20000

print(validate_df.shape) #(5000, 2)
total_validate = validate_df.shape[0] # 5000

batch_size=25000

train_datagen = ImageDataGenerator(
    rotation_range=15,
    rescale=1./255,
    shear_range=0.1,
    zoom_range=0.2,
    horizontal_flip=True,
    width_shift_range=0.1,
    height_shift_range=0.1
)
train_generator = train_datagen.flow_from_dataframe(
    train_df, 
    "D:/_data/dogs-vs-cats/train/", 
    x_col='filename',
    y_col='category',
    target_size=IMAGE_SIZE,
    class_mode='categorical', # 다중 분류
    batch_size=batch_size
)

validation_datagen = ImageDataGenerator(rescale=1./255)
validation_generator = validation_datagen.flow_from_dataframe(
    validate_df, 
    "D:/_data/dogs-vs-cats/train/", 
    x_col='filename',
    y_col='category',
    target_size=IMAGE_SIZE,
    class_mode='categorical',
    batch_size=batch_size
)

'''
example image plt.show()
example_df = train_df.sample(n=1).reset_index(drop=True)
example_generator = train_datagen.flow_from_dataframe(
    example_df, 
    "D:/_data/dogs-vs-cats/train/", 
    x_col='filename',
    y_col='category',
    target_size=IMAGE_SIZE,
    class_mode='categorical'
)

plt.figure(figsize=(12, 12))
for i in range(0, 15):
    plt.subplot(5, 3, i+1)
    for X_batch, Y_batch in example_generator:
        image = X_batch[0]
        plt.imshow(image)
        break
plt.tight_layout()
plt.show()

'''

# print(type(validation_generator)) # <class 'keras.preprocessing.image.DataFrameIterator'>
# print(type(train_generator[0][0])) # <class 'numpy.ndarray'>
# print(type(train_generator[0][1])) # <class 'numpy.ndarray'>
print(train_generator[0][0].shape) # (20000, 128, 128, 3)
print(train_generator[0][1].shape) # (20000, 2):  one hot encoding type: class -> col
# batch_size를 total image 개수에 맞춰서 train_generator[0]에 모두 담음

np.save('D:/_data/dogs-vs-cats/numpy/cat_dog_x_train.npy', arr=train_generator[0][0])
np.save('D:/_data/dogs-vs-cats/numpy/cat_dog_y_train.npy', arr=train_generator[0][1])

np.save('D:/_data/dogs-vs-cats/numpy/cat_dog_x_val.npy', arr=validation_generator[0][0])
np.save('D:/_data/dogs-vs-cats/numpy/cat_dog_y_val.npy', arr=validation_generator[0][1])

# np.save: numpy file로 저장