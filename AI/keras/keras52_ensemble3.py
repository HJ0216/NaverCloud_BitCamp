# ensemble_model2.py

import numpy as np

from sklearn.model_selection import train_test_split

from tensorflow.keras.models import Model
from tensorflow.keras.layers import Dense, Input


# 1. Data
x1_datasets = np.array([range(100), range(301, 401)]).transpose() # .transpose() = .T
x2_datasets = np.array([range(101, 201), range(411, 511), range(150, 250)]).transpose()
x3_datasets = np.array([range(100,200), range(1301, 1401)]).transpose()

y1 = np.array(range(2001, 2101)) # (100,)
y2 = np.array(range(201, 301)) # (100,)

x1_train, x1_test, \
    x2_train, x2_test, \
        x3_train, x3_test = train_test_split(
    x1_datasets, x2_datasets, x3_datasets, train_size=0.7, random_state=1234
)
        # param 개수가 너무 많거나 param 이름이 너무 긴 경우, \를 이용해서 코드 가독성을 향상시킬 수 있음

y1_train, y1_test, y2_train, y2_test = train_test_split(
    y1, y2, train_size=0.7, random_state=1234
)

print(x1_train.shape, x2_train.shape, x3_train.shape) # (70, 2) (70, 3) (70, 2) (70,)
print(x1_test.shape, x2_test.shape, x3_test.shape) # (30, 2) (30, 3) (30, 2) (30,)


# 2. Model Construction
# 2-1. Model_1
input1 = Input(shape=(2,))
dense1 = Dense(11, activation='relu', name='ds11')(input1)
dense2 = Dense(12, activation='relu', name='ds12')(dense1)
dense3 = Dense(13, activation='relu', name='ds13')(dense2)
output1 = Dense(11, activation='relu', name='ds14')(dense3)

# 2-2. Model_2
input2 = Input(shape=(3,))
dense21 = Dense(21, activation='linear', name='ds21')(input2) # 동일한 이름 사용 가능
dense22 = Dense(22, activation='linear', name='ds22')(dense21)
output2 = Dense(23, activation='linear', name='ds23')(dense22)

# 2-3. Model_3
input3 = Input(shape=(2,))
dense31 = Dense(11, activation='relu', name='ds31')(input3)
dense32 = Dense(12, activation='relu', name='ds32')(dense31)
dense33 = Dense(13, activation='relu', name='ds33')(dense32)
output3 = Dense(11, activation='relu', name='ds34')(dense33)

# 2-4. Model_merge
from tensorflow.keras.layers import concatenate, Concatenate # Concat = Function
merge1 = Concatenate()([output1, output2, output3])
merge2 = Dense(12, activation='relu', name='mg2')(merge1)
merge3 = Dense(13, name='mg3')(merge2) # summary에서 별칭
last_output = Dense(1, name='last')(merge3) # 1 = y_col

# 2-5. Model_branch1
# input4 = Dense(1, name="main_output1")(last_output)
dense41 = Dense(11, activation='relu', name='ds41')(last_output)
dense42 = Dense(12, activation='relu', name='ds42')(dense41)
dense43 = Dense(13, activation='relu', name='ds43')(dense42)
output4 = Dense(11, activation='relu', name='ds44')(dense43)

# 2-6. Model_branch2
# input5 = Dense(1, name="main_output2")(last_output)
# last_output Dense Layer Branch Model에서 직접 받기
dense51 = Dense(11, activation='relu', name='ds51')(last_output)
dense52 = Dense(12, activation='relu', name='ds52')(dense51)
dense53 = Dense(13, activation='relu', name='ds53')(dense52)
output5 = Dense(11, activation='relu', name='ds54')(dense53)

model = Model(inputs=[input1, input2, input3], outputs=[output4, output5])
model.summary()


# 3. compile and train
model.compile(loss='mse', optimizer='adam', metrics=['mae'])
model.fit([x1_train, x2_train, x3_train], [y1_train, y2_train], epochs=128, batch_size=8)


# 4. evaluate and predict
loss = model.evaluate([x1_test, x2_test, x3_test], [y1_test, y2_test])
print("Loss: ", loss)



'''
Result
1/1 [==============================] - 0s 171ms/step
loss: 1974813.1250 / ds44_loss: 1936965.1250 / ds54_loss: 37848.0078
-> model n개 출력: 각 model의 loss 및 loss의 합계도 출력(n+1개)
metrics = ['mae']
-> model n개 출력: 각 model의 mae 출력(합계는 loss 부분만 출력)

'''