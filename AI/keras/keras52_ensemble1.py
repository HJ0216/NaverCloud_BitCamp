import numpy as np

x1_datasets = np.array([range(100), range(301, 401)]).transpose() # .transpose() = .T
print(x1_datasets.shape) # (100, 2)
print(x1_datasets)
'''
[[  0 301]
 [  1 302]
 [  2 303]
 ...
  [ 98 399]
 [ 99 400]]
'''

x2_datasets = np.array([range(101, 201), range(411, 511), range(150, 250)]).transpose()
print(x2_datasets.shape) # (100, 3)

y = np.array(range(2001, 2101)) # (100,)

from sklearn.model_selection import train_test_split
x1_train, x1_test, x2_train, x2_test, y_train, y_test = train_test_split(
    x1_datasets, x2_datasets, y, train_size=0.7, random_state=1234
)

print(x1_train.shape, x2_train.shape, y_train.shape) # (70, 2) (70, 3) (70,)
print(x1_test.shape, x2_test.shape, y_test.shape) # (30, 2) (30, 3) (30,)


# 2. Model Construction
from tensorflow.keras.models import Model
from tensorflow.keras.layers import Dense, Input

# 2-1. Model_1
input1 = Input(shape=(2,))
dense1 = Dense(11, activation='relu', name='ds11')(input1) # name: summary에서 별칭
dense2 = Dense(12, activation='relu', name='ds12')(dense1)
dense3 = Dense(13, activation='relu', name='ds13')(dense2)
output1 = Dense(11, activation='relu', name='ds14')(dense3)

# 2-2. Model_2
input2 = Input(shape=(3,))
dense21 = Dense(21, activation='linear', name='ds21')(input2)
dense22 = Dense(22, activation='linear', name='ds22')(dense21)
output2 = Dense(23, activation='linear', name='ds23')(dense22)

# 2-3. Model_merge
from tensorflow.keras.layers import concatenate
merge1 = concatenate([output1, output2], name='mg1')
# merge1 = Concatenate()([output1, output2], name='mg1')
merge2 = Dense(12, activation='relu', name='mg2')(merge1)
merge3 = Dense(13, name='mg3')(merge2)
last_output = Dense(1, name='last')(merge3)

model = Model(inputs=[input1, input2], outputs=last_output)

model.summary()
'''
Model: "model"
__________________________________________________________________________________________________
 Layer (type)                   Output Shape         Param #     Connected to
==================================================================================================
 input_1 (InputLayer)           [(None, 2)]          0           []

 ds11 (Dense)                   (None, 11)           33          ['input_1[0][0]']
__________________________________________________________________________________________________

 input_2 (InputLayer)           [(None, 3)]          0           []

 ds12 (Dense)                   (None, 12)           144         ['ds11[0][0]']
__________________________________________________________________________________________________

 ds21 (Dense)                   (None, 21)           84          ['input_2[0][0]']
__________________________________________________________________________________________________

 ds13 (Dense)                   (None, 13)           169         ['ds12[0][0]']
__________________________________________________________________________________________________

 ds22 (Dense)                   (None, 22)           484         ['ds21[0][0]']
__________________________________________________________________________________________________

 ds14 (Dense)                   (None, 11)           154         ['ds13[0][0]']
__________________________________________________________________________________________________

 ds23 (Dense)                   (None, 23)           529         ['ds22[0][0]']
__________________________________________________________________________________________________

 mg1 (Concatenate)              (None, 34)           0           ['ds14[0][0]',
                                                                  'ds23[0][0]']

 mg2 (Dense)                    (None, 12)           420         ['mg1[0][0]']

 mg3 (Dense)                    (None, 13)           169         ['mg2[0][0]']

 last (Dense)                   (None, 1)            14          ['mg3[0][0]']

==================================================================================================
Total params: 2,200
Trainable params: 2,200
Non-trainable params: 0
__________________________________________________________________________________________________
'''


# 3. compile and train
model.compile(loss='mse', optimizer='adam')
model.fit([x1_train, x2_train], y_train, epochs=10, batch_size=8)
# 모델 2개를 훈련시켜야하므로 훈련의 입력값도 2개 필요


# 4. evaluate and predict
loss = model.evaluate([x1_test, x2_test], y_test)
# 모델 2개를 훈련시켰으므로 평가의 입력값도 2개 필요
print("Loss: ", loss)



'''
Result
Loss:  15543.3212890625

'''