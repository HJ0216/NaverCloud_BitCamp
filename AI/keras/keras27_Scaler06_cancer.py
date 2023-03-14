import numpy as np

from sklearn.datasets import load_breast_cancer
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler, StandardScaler
from sklearn.metrics import r2_score, accuracy_score


from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.callbacks import EarlyStopping


# 1. Data
datasets = load_breast_cancer()
x = datasets['data'] # for training
y = datasets['target'] # for predict

x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    test_size=0.2,
    shuffle= True,
    random_state = 333
)

# scaler = StandardScaler()
scaler = MinMaxScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test)


# 2. Model Construction
model = Sequential()
model.add(Dense(32, activation='linear', input_shape=(30,)))
model.add(Dense(32, activation='relu'))
model.add(Dense(16, activation='relu'))
model.add(Dense(16, activation='relu'))
model.add(Dense(8, activation='relu'))
model.add(Dense(1, activation='sigmoid'))


# 3. Compile and train
model.compile(loss='binary_crossentropy', optimizer='adam', metrics=['accuracy'])

earlyStopping = EarlyStopping(monitor='accuracy', mode='auto', patience=32, restore_best_weights=True, verbose=1)

hist = model.fit(x_train, y_train,
          epochs=512, batch_size=16,
          validation_split=0.2,
          callbacks=[earlyStopping],
          verbose=1)


# 4. evaluate and predict
loss, accuracy = model.evaluate(x_test, y_test)
print("loss: ", loss)
print("accuracy: ", accuracy)

y_predict = model.predict(x_test)
pred_class = np.where(y_predict >= 0.5, 1, 0)

acc = accuracy_score(y_test, pred_class)
print("accuarcy_score: ", acc)


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


Updated result using MinMaxScaler
loss:  0.16533207893371582
accuracy:  0.9649122953414917
accuarcy_score:  0.9649122807017544

'''
