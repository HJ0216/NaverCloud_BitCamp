from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
import numpy as np
from sklearn.model_selection import train_test_split


# 1. Data
x = np.array(range(1,21))
y = np.array([1,2,4,3,5,7,9,3,8,12,13,8,14,15,9,6,17,23,21,20])

x_train, x_test, y_train, y_test = train_test_split(
    x,y,
    train_size=0.7,
    shuffle=True,
    random_state=123
)

'''
Data를 Train set과 Test set으로 구분하는 이유
모든 데이터를 Train에만 사용 할 경우, Overfit(과적합: 학습 데이터에 대해 과하게 학습하여 실제 데이터에 대한 오차가 증가하는 현상) 문제가 발생할 수 있음

'''


# 2. Model Construction
model = Sequential()
model.add(Dense(64, input_dim=1))
model.add(Dense(32))
model.add(Dense(16))
model.add(Dense(1))


# 3. compile and train
model.compile(loss='mae', optimizer='adam')
model.fit(x_train, y_train, epochs=128, batch_size=16)


# 4. Evalueate and Predict
loss = model.evaluate(x_test, y_test)
print("Loss: ", loss)
'''
evaluate는 train set이 아닌 test set이 대입되는 것이므로 loss 율이 fit보다 높음
predict 전 evaluate를 활용하여 과적합 문제가 발생하였는지 선행적으로 확인해보기 위해 활용

'''

y_predict = model.predict(x)
print("Result: ", y_predict)


import matplotlib.pyplot as plt
plt.scatter(x, y)
plt.plot(x, y_predict, color="red")
plt.show()



'''
Result

Epoch 200/200
14/14 [==============================] - 0s 617us/step - loss: 1.9601
1/1 [==============================] - 0s 101ms/step - loss: 3.0334
Loss:  3.033447265625
Result:  [[ 1.0241305]
 [ 2.037987 ]
 [ 3.051844 ]
 [ 4.0657005]
 [ 5.079557 ]
 [ 6.0934143]
 [ 7.1072702]
 [ 8.121127 ]
 [ 9.134985 ]
 [10.14884  ]
 [11.162696 ]
 [12.176556 ]
 [13.19041  ]
 [14.204266 ]
 [15.218123 ]
 [16.23198  ]
 [17.245836 ]
 [18.259693 ]
 [19.27355  ]
 [20.287403 ]]
 
'''