import numpy as np

x1_datasets = np.array([range(100), range(301, 401)]).transpose()
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

x3_datasets = np.array([range(100,200), range(1301, 1401)]).transpose()

y = np.array(range(2001, 2101)) # (100,)

from sklearn.model_selection import train_test_split
x1_train, x1_test, x2_train, x2_test, x3_train, x3_test = train_test_split(
    x1_datasets, x2_datasets, x3_datasets, train_size=0.7, random_state=1234
)

y_train, y_test = train_test_split(
    y, train_size=0.7, random_state=1234
)

print(x1_train.shape, x2_train.shape, x3_train.shape, y_train.shape) # (70, 2) (70, 3) (70, 2) (70,)
print(x1_test.shape, x2_test.shape, x3_test.shape, y_test.shape) # (30, 2) (30, 3) (30, 2) (30,)


# 2. Model Construction
from tensorflow.keras.models import Model
from tensorflow.keras.layers import Dense, Input

# 2-1. Model_1
input1 = Input(shape=(2,))
dense1 = Dense(11, activation='relu', name='ds11')(input1)
dense2 = Dense(12, activation='relu', name='ds12')(dense1)
dense3 = Dense(13, activation='relu', name='ds13')(dense2)
output1 = Dense(11, activation='relu', name='ds14')(dense3)

# 2-2. Model_2
input2 = Input(shape=(3,))
dense21 = Dense(21, activation='linear', name='ds21')(input2)
dense22 = Dense(22, activation='linear', name='ds22')(dense21)
output2 = Dense(23, activation='linear', name='ds23')(dense22)

# 2-3. Model_3
input3 = Input(shape=(2,))
dense31 = Dense(11, activation='relu', name='ds31')(input3)
dense32 = Dense(12, activation='relu', name='ds32')(dense31)
dense33 = Dense(13, activation='relu', name='ds33')(dense32)
output3 = Dense(11, activation='relu', name='ds34')(dense33)

# 2-4. Model_merge
from tensorflow.keras.layers import concatenate, Concatenate
merge1 = concatenate([output1, output2, output3], name='mg1')
# merge1 = Concatenate()([output1, output2, output3], name='mg1')
merge2 = Dense(12, activation='relu', name='mg2')(merge1)
merge3 = Dense(13, name='mg3')(merge2)
last_output = Dense(1, name='last')(merge3)

model = Model(inputs=[input1, input2, input3], outputs=last_output)
model.summary()


# 3. compile and train
model.compile(loss='mse', optimizer='adam')
model.fit([x1_train, x2_train, x3_train], y_train, epochs=10, batch_size=8)


# 4. evaluate and predict
loss = model.evaluate([x1_test, x2_test, x3_test], y_test)
print("Loss: ", loss)



'''
Result
Loss:  17042.103515625

'''