# ensemble_model2.py

import numpy as np

from sklearn.model_selection import train_test_split

from tensorflow.keras.models import Model
from tensorflow.keras.layers import Dense, Input


# 1. Data
x_datasets = np.array([range(100), range(301, 401)]).transpose()

y1 = np.array(range(2001, 2101)) # (100,)
y2 = np.array(range(201, 301)) # (100,)

x_train, x_test = train_test_split(
    x_datasets, train_size=0.7, random_state=1234
)

y1_train, y1_test, y2_train, y2_test = train_test_split(
    y1, y2, train_size=0.7, random_state=1234
)

print(x_train.shape, y1_train.shape, y2_train.shape) # (70, 2) (70,) (70,)
print(x_test.shape, y1_test.shape, y2_test.shape) # (30, 2) (30,) (30,)


# 2. Model Construction
# 2-1. Model_1
input1 = Input(shape=(2,))
dense1 = Dense(11, activation='relu', name='ds11')(input1)
dense2 = Dense(12, activation='relu', name='ds12')(dense1)
dense3 = Dense(13, activation='relu', name='ds13')(dense2)
output = Dense(14, activation='relu', name='ds14')(dense3)

# 2-2. Model_branch1
dense21 = Dense(11, activation='relu', name='ds21')(output)
# input 변수 선언없이, last_output Dense Layer Branch Model에서 직접 받기
dense22 = Dense(12, activation='relu', name='ds22')(dense21)
dense23 = Dense(13, activation='relu', name='ds23')(dense22)
output_b1 = Dense(14, activation='relu', name='ds24')(dense23)

# 2-3. Model_branch2
dense31 = Dense(11, activation='relu', name='ds31')(output)
dense32 = Dense(12, activation='relu', name='ds32')(dense31)
dense33 = Dense(13, activation='relu', name='ds33')(dense32)
output_b2 = Dense(14, activation='relu', name='ds34')(dense33)

model = Model(inputs=[input1], outputs=[output_b1, output_b2])
model.summary()


# 3. compile and train
model.compile(loss='mse', optimizer='adam', metrics=['mae'])
model.fit(x_train, [y1_train, y2_train], epochs=128, batch_size=8)


# 4. evaluate and predict
loss = model.evaluate(x_test, [y1_test, y2_test])
print("Loss: ", loss)



'''
Result
loss: 3059665.2500 / ds24_loss: 3034864.0000 / ds34_loss: 24801.2871
-> model n개 출력: 각 model의 loss 및 loss의 합계도 출력(n+1개)

ds24_mae: 1481.5680 / ds34_mae: 104.1349
-> model n개 출력: 각 model의 mae 출력(합계는 loss 부분만 출력)


'''