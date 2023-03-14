# samsung_predict_open_stock_price.py
# For Detail: https://hj0216.tistory.com/74

import pandas as pd
import numpy as np

from tensorflow.keras.models import Model
from tensorflow.keras.layers import Dense, Input, concatenate, SimpleRNN, LSTM, Dropout, GRU, Bidirectional, Flatten
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint

from sklearn.model_selection import train_test_split

path = './keras/'
# path = 현재 dir 아래 keras dir로 경로 지정


# 1.1. Data Preprocessing(Samsung)
samsung = pd.read_csv(path+'삼성전자 주가.csv', encoding='CP949', nrows=1166, usecols=[1,2,3,4,16], header=0)
# 한글 데이터의 경우, pandas의 read_cvs 사용 시 깨짐 현상 발생 -> encoding='CP949' 추가
# 액면 분할로 인한 주가 차이가 약 50배 이상 차이나므로 액면 분할 이후 데이터만 수집 -> nrows 사용
# 훈련 데이터로 특성 5개만 추출: 1(시가), 2(고가), 3(저가), 4(종가), 16(외인비) -> usecols 사용
# 첫번쩨 행: col name -> header=0 지정

samsung['시가'] = samsung['시가'].str.replace(',', '').astype('float')
samsung['고가'] = samsung['고가'].str.replace(',', '').astype('float')
samsung['저가'] = samsung['저가'].str.replace(',', '').astype('float')
samsung['종가'] = samsung['종가'].str.replace(',', '').astype('float')
# csv 파일에서 형식이 회계 또는 통화로 되어있을 경우 ',' 때문에 oject로 인식되므로 str(,) 삭제 후 형변환
samsung['외인비'] = samsung['외인비']*1400
# 외인비의 수치가 다른 특성에 비해 과도하게 낮으므로 1400을 곱하여 다른 특성과 비슷한 수치를 만들어줌

samsung = samsung.sort_index(ascending=False)
# samsung 출력 시, sort_index가 적용되지 않은 상태로 출력되므로 변수를 재선언하여 정렬이 적용된 내용을 담아줘야 함
# samsung = samsung.sort_values(ascending=True, by=['일자'])

'''
print(samsung[:1165].tail())
samsung datasets 마지막 행 제외 하위 5개 목록 출력: 2023.01.18-26

    시가     고가    저가    종가    외인비
5  60,700  61,000  59,900  60,400  70126.0
4  60,500  61,500  60,400  61,500  70210.0
3  62,100  62,300  61,100  61,800  70238.0
2  63,500  63,700  63,000  63,400  70392.0
1  63,800  63,900  63,300  63,900  70476.0
'''

samsung_open = samsung['시가'][1:]
# 전일 데이터를 기반으로 익일 데이터를 예측하는 것으므로 y data로 사용할 시가 데이터는 첫 날(2018년 5월 4일) 데이터를 제외함
'''
print(samsung_open.head())
samsung_open datasets 상위 5개 목록 출력: 2015.05.08-14

1164    52,600
1163    52,600
1162    51,700
1161    52,000
1160    51,000
'''

x1_train, x1_test, y_train, y_test = train_test_split(
    samsung[:1165], samsung_open,
    shuffle=True,
    train_size=0.7,
    random_state=123
)
# samsung[:1165]: 훈련용 데이터에서 예측에 필요한 01월 27일자 데이터 제외

print(x1_train.shape, x1_test.shape) # (815, 5) (350, 5)
print(y_train.shape, y_test.shape) # (815,) (350,)

x1_train = x1_train.to_numpy()
x1_test = x1_test.to_numpy()
# reshape을 위한 DataFrame -> Numpy

x1_train = x1_train.reshape(815, 5, 1)
x1_test = x1_test.reshape(350, 5, 1)


# 1.2. Data Preprocessing(Amore)
amore = pd.read_csv(path+'아모레퍼시픽 주가.csv', encoding='CP949', nrows=1166, usecols=[1,2,3,4,16])
# nrows=1902 (amore 액면분할 기준): Make sure all arrays contain the same number of samples.
# -> nrows=1166 (samsung 액면분할 기준 data shape과 맞춤)
amore['시가'] = amore['시가'].str.replace(',', '').astype('float')
amore['고가'] = amore['고가'].str.replace(',', '').astype('float')
amore['저가'] = amore['저가'].str.replace(',', '').astype('float')
amore['종가'] = amore['종가'].str.replace(',', '').astype('float')
amore['외인비'] = amore['외인비']*5700
amore = amore.sort_index(ascending=False)

x2_train, x2_test = train_test_split(
    amore[:1165],
    shuffle=True,
    train_size=0.7,
    random_state=123
)

print(x2_train.shape, x2_test.shape) # (815, 5) (350, 5)

'''
# 2. Model Construction
# 2-1. Model_1(Samsung)
input1 = Input(shape=(5,1))
lstm1_1 = LSTM(units=64, return_sequences=True,
                        input_shape=(5,1))(input1)
# gru1_2 = GRU(64, activation='relu')(lstm1_1)
dense1_2 = Dense(32, activation='relu')(lstm1_1)
dense1_3 = Dense(16, activation='relu')(dense1_2)
flatten1_4 = Flatten()(dense1_3)
# A `Concatenate` layer requires inputs with matching shapes except for the concatenation axis.
output1 = Dense(16, activation='relu')(flatten1_4)

# 2-2. Model_2(Amore)
input2 = Input(shape=(5,))
dense2_1 = Dense(64, activation='relu')(input2)
dense2_2 = Dense(32, activation='linear')(dense2_1)
dropout2_3 = Dropout(0.1)(dense2_2)
dense2_4 = Dense(16, activation='linear')(dropout2_3)
output2 = Dense(8, activation='relu')(dense2_4)

# 2-3. Model_merge
merge3 = concatenate([output1, output2])
merge3_1 = Dense(64, activation='relu')(merge3)
merge3_2 = Dense(32, activation='relu')(merge3_1)
last_output = Dense(1)(merge3_2)

model = Model(inputs=[input1, input2], outputs=last_output)

model.summary()


# 3. compile and train
model.compile(loss='mse', optimizer='adam')

earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=64, restore_best_weights=True, verbose=1)

modelCheckPoint = ModelCheckpoint(monitor='val_loss', mode='auto', verbose=1,
                                   save_best_only=True,
                                   filepath='./_save/MCP/samsumg_open_MCP.hdf5')

model.fit([x1_train, x2_train], y_train,
          validation_split=0.2,
          callbacks=[earlyStopping, modelCheckPoint],
          epochs=256,
          batch_size=64)
'''
from tensorflow.keras.models import load_model


model = load_model('./keras/samsumg_open_MCP.hdf5')

# 4. evaluate and predict
loss = model.evaluate([x1_test, x2_test], y_test)


result = model.predict([samsung[1165:].to_numpy().reshape(1,5,1),amore[1165:]])
# train data shape과 predict data shape 맞추기

print("Samsung Electronics market price prediction : ", result)

'''
Result
Samsung Electronics market price prediction :  [[65331.867]]

'''