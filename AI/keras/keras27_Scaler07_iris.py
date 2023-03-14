import pandas as pd

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.utils import to_categorical
from tensorflow.keras.callbacks import EarlyStopping

from sklearn.model_selection import train_test_split
from sklearn.datasets import load_iris
from sklearn.preprocessing import MinMaxScaler, StandardScaler


# 1. Data
datasets = load_iris()
print(datasets.DESCR) # pandas.describe / cvs_type.info
# attribute = column, feature, 열, 특성
# x_column(input_dim=4): sepal (length, width), petal (length, width) (cm)
# output_dim=1(3 class(종류): Iris-Setosa, Iris-Versicolour, Iris-Virginica)
print(datasets.feature_names) # pandas.columns, column name

x = datasets.data
y = datasets['target']

y=to_categorical(y)

x_train, x_test, y_train, y_test = train_test_split(
    x,y,
    shuffle=True,
    random_state=333,
    test_size=0.2,
    stratify=y
)

# scaler = StandardScaler()
scaler = MinMaxScaler()
scaler.fit(x_train)
x_train = scaler.transform(x_train)
<<<<<<< HEAD
>>>>>>> 2010887 (Update keras27_Scaler07_iris.py)
=======
>>>>>>> 5d0526f (ml-practice)
x_test = scaler.transform(x_test)


# 2. Model Construction
model = Sequential()
model.add(Dense(32, activation='relu', input_shape=(4, )))
model.add(Dense(32, activation='sigmoid'))
model.add(Dense(16,activation='relu'))
model.add(Dense(8,activation='linear'))
model.add(Dense(3,activation='softmax'))


# 3. Compile and train
model.compile(loss='categorical_crossentropy',
              optimizer='adam',
              metrics=['accuracy']
              )

earlyStopping = EarlyStopping(monitor='accuracy', mode='auto', patience=32, restore_best_weights=True, verbose=1)

model.fit(x_train, y_train, epochs=256, batch_size=8,
          validation_split=0.2,
          callbacks=[earlyStopping],
          verbose=1)


# 4. evaluate and predict
loss, accuracy = model.evaluate(x_test, y_test)
print("loss: ", loss)
print("test_accuracy: ", accuracy)


from sklearn.metrics import accuracy_score
import numpy as np

y_predict = model.predict(x_test)
y_predict = np.argmax(y_predict, axis=1)
y_test = np.argmax(y_test, axis=1)

acc = accuracy_score(y_test, y_predict)
print("predict_acc: ", acc)


'''
Result using MinMaxScaler
loss:  0.32223355770111084
test_accuracy:  0.9333333373069763
predict_acc:  0.9333333333333333

'''
