import numpy as np
import pandas as pd

from tensorflow.keras.models import Sequential, Model
from tensorflow.keras.layers import Dense, Input
from tensorflow.keras.utils import to_categorical
from tensorflow.keras.callbacks import EarlyStopping

from sklearn.model_selection import train_test_split
from sklearn.datasets import load_iris
from sklearn.preprocessing import MinMaxScaler, StandardScaler, OneHotEncoder
from sklearn.metrics import accuracy_score

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
x_test = scaler.transform(x_test)


# 2. Model Construction
input1 = Input(shape=(4,))
dense1 = Dense(64, activation='relu')(input1)
dense2 = Dense(64, activation='linear')(dense1)
dense3 = Dense(32, activation='relu')(dense2)
dense4 = Dense(32, activation='relu')(dense3)
output1 = Dense(3, activation='softmax')(dense4)
model = Model(inputs=input1, outputs=output1)


# 3. Compile and train
model.compile(loss='categorical_crossentropy',
              optimizer='adam',
              metrics=['accuracy']
              )

earlyStopping = EarlyStopping(monitor='accuracy', mode='auto', patience=32, restore_best_weights=True, verbose=1)

model.fit(x_train, y_train, epochs=256, batch_size=16,
          validation_split=0.2,
          callbacks=[earlyStopping],
          verbose=1)


# 4. evaluate and predict
loss, accuracy = model.evaluate(x_test, y_test)
print("loss: ", loss)
print("test_accuracy: ", accuracy)



y_predict = model.predict(x_test)
y_predict = np.argmax(y_predict, axis=1)
y_test = np.argmax(y_test, axis=1)

acc = accuracy_score(y_test, y_predict)
print("predict_acc: ", acc)


'''
Result using MinMaxScaler
loss:  0.48310545086860657
test_accuracy:  0.8666666746139526
predict_acc:  0.8666666666666667

Result using Function
loss:  0.1522248238325119
test_accuracy:  0.9333333373069763
predict_acc:  0.9333333333333333

'''
