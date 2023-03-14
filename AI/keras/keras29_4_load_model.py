# improve boston

'''
[Practice]
trains set: 0.7 이상
평가지표1 R2: 0.8 이상
평가지표2 RMSE 사용
loss: mae or mse
optimizer: adam
matrics: optional
model(train set, epochs, batch_size=optional)
evaluate: x_test, y_test
 
'''


import numpy as np

from tensorflow.keras.models import Sequential, Model, load_model
from tensorflow.keras.layers import Dense, Input
from tensorflow.keras.callbacks import EarlyStopping


from sklearn.datasets import load_boston
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler, StandardScaler


# 1. Data
dataset = load_boston()
x = dataset.data # for training
y = dataset.target # for predict

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    train_size=0.7,
    random_state=123
)
# stratify: y 타입이 분류에서만 사용


# scaler = StandardScaler()
scaler = MinMaxScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test)


# 2. Model(Function)
path = './_save/'
# ./ 현재폴더(현재 VS code 경로: study) -> _save/ _save 폴더
# path = '../_save/' # ../ 이전폴더 -> _save/ _save 폴더
# path = 'c:/study/_save/' # 경로: 대소문자 구분 X

# model.save(path+'keras29_1_save_model.h5')

model = load_model(path + 'keras29_3_save_model.h5')
# keras29_3_save_model.py와 동일한 가중치
# RMSE:  3.745943953275478
# R2:  0.8263958011288042


# 3. compile and train



# 4. evaluate and predict
loss = model.evaluate(x_test, y_test)
y_predict = model.predict(x_test)

from sklearn.metrics import mean_squared_error, r2_score
def RMSE (y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))
print("RMSE: ", RMSE(y_test, y_predict))

r2 = r2_score(y_test, y_predict)
print("R2: ", r2)




'''
Result
RMSE:  3.9774667461538487
R2:  0.7499457664401593

Updated Result
RMSE:  3.758338531055167
R2:  0.8443360976276741

Updated Result2 with MinMax scalering
RMSE:  5.711671989312524
R2:  0.596387886457775

Updated Result2 with Standard scaler
RMSE:  4.60305594170595
R2:  0.7378618798976347

Upadated Result2 with MinMax Scaler
RMSE:  4.737990416535103
R2:  0.7222679303359896

Updated result using Function
RMSE:  3.430171642478747
R2:  0.8544308389149119

'''
