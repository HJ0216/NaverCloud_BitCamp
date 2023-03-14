import numpy as np

from sklearn.datasets import load_breast_cancer
from sklearn.model_selection import train_test_split

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense


# 1. Data
datasets = load_breast_cancer()
# print(datasets)
# print(datasets.DESCR)
# print(datasets.feature_names)

# data: x, target: y(0 or 1)
x = datasets['data'] # for training
# x = datasets.data
y = datasets['target'] # for predict
# y = datasets.target
print(x.shape, y.shape) # (569, 30) (569,)

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    test_size=0.2,
    shuffle= True,
    random_state = 333
)


# 2. Model Construction
model = Sequential()
model.add(Dense(50, activation='linear', input_shape=(30,)))
model.add(Dense(40, activation='relu'))
model.add(Dense(30, activation='relu'))
model.add(Dense(20, activation='relu'))
model.add(Dense(10, activation='relu'))
model.add(Dense(1, activation='sigmoid'))


# 3. Compile and train
model.compile(loss='binary_crossentropy', optimizer='adam', metrics=['accuracy'])
# Binary Classification Model Construction
# Last Activation=sigmoid, loss='binary_crossentropy'
# 이진분류에서는 정오 판단이 가능하므로 Accuracy 사용이 용이함

from tensorflow.keras.callbacks import EarlyStopping
earlyStopping = EarlyStopping(monitor='accuracy', mode='auto', patience=5, restore_best_weights=True, verbose=1)
# earlyStopping = EarlyStopping(monitor='val_loss', mode='min', patience=20, restore_best_weights=True, verbose=1)
# val_loss가 loss보다 성능이 좋으므로 val_loss 사용

hist = model.fit(x_train, y_train,
          epochs=100, batch_size=16,
          validation_split=0.2,
          callbacks=[earlyStopping],
          verbose=1)


# 4. evaluate and predict
loss, accuracy = model.evaluate(x_test, y_test) # return 값이 2개 이상일 경우, variable 2개 입력 가능
print("loss: ", loss)
print("accuracy: ", accuracy) # x_test, y_test

y_predict = model.predict(x_test) # y_test와 비교
# print(y_predict[:10]) # 10개 출력, 0<sigmoid<1
# print(y_test[:10]) # 10개 출력 y_test = 0 or 1
# 실수와 정수 Type Unmatching Error (ValueError: Classification metrics can't handle a mix of binary and continuous targets)

# Solve: ValueError(Classification metrics can't handle a mix of binary and continuous targets)
pred_class = np.where(y_predict >= 0.5, 1, 0) # 0.5 이상=1, 0.5 미만=0
# 조건에 따라 x or y에서 선택한 요소를 반환


from sklearn.metrics import r2_score, accuracy_score
acc = accuracy_score(y_test, pred_class)
print("accuarcy_score: ", acc)
# accuracy(x_test, pred_class)
# y_predict = model.predict(x_test)
# parameter가 다르므로 accuracy도 차이


print(hist.history)


'''
print(hist.history)
: model.compile(loss='binary_crossentropy', optimizer='adam', metrics=['accuracy']) 기준으로 출력
-> 'loss', 'metrics-accuracy' 출력

EarlyStopping 기준
: monitor='accuracy' -> 'accuracy' 기준으로 5 훈련 이내에 max_accuracy가 갱신이 안되면 훈련 종료
{'loss': [11.85722827911377, 0.9952860474586487, 1.0798938274383545, 1.0318052768707275, 0.5519623756408691, 0.40161049365997314, 0.4947403371334076, 0.4337594509124756, 0.4743689298629761, 0.38900667428970337, 0.4524688720703125, 0.37105923891067505, 0.30691826343536377, 0.2597656846046448, 0.2577860653400421, 0.3232164978981018, 0.46766775846481323, 0.3157420754432678],
'accuracy': [0.5439560413360596, 0.7527472376823425, 0.75, 0.7939560413360596, 0.8516483306884766, 0.8626373410224915, 0.8543956279754639, 0.8571428656578064, 0.8543956279754639, 0.8873626589775085, 0.8708791136741638, 0.8763736486434937, 0.901098906993866, 0.8928571343421936, 0.8983516693115234, 0.8818681240081787, 0.8598901033401489, 0.8846153616905212],
'val_loss': [0.654694676399231, 0.6088137626647949, 0.250082790851593, 0.3005199432373047, 0.19377245008945465, 0.1960064321756363, 0.29922524094581604, 0.22639624774456024, 0.17169664800167084, 0.1672838181257248, 0.262459397315979, 0.27582716941833496, 0.12993116676807404, 0.13144586980342865, 0.158126100897789, 0.3026399314403534, 0.2095157504081726, 0.14485186338424683],
'val_accuracy': [0.7472527623176575, 0.8571428656578064, 0.9230769276618958, 0.8901098966598511, 0.9120879173278809, 0.9230769276618958, 0.8791208863258362, 0.9230769276618958, 0.9560439586639404, 0.9340659379959106, 0.9230769276618958, 0.9120879173278809, 0.9450549483299255, 0.9450549483299255, 0.9340659379959106, 0.8241758346557617, 0.9120879173278809, 0.9560439586639404]}

'''


'''
Result
loss = model.evaluate(x_test, y_test)
print("[binarycrossentropy, accuracy]: ", loss)
Restoring model weights from the end of the best epoch: 32.
[binarycrossentropy, accuracy]:  [0.16636592149734497, 0.9561403393745422]


Updated Result
Restoring model weights from the end of the best epoch: 198.
loss:  0.16303640604019165
accuracy:  0.9561403393745422


Updated Result with converting binary classification from sigmoid
loss:  0.13962924480438232
accuracy:  0.9561403393745422
accuarcy_score:  0.956140350877193
accuracy와 accuarcy_score가 차이나는 이유: y_predict.flatten()로 값 변환을 하였으므로

'''
