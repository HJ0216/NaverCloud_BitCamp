# 데이터 -> .npy : 이미지를 수치로 변환하는데 시간을 줄일 수 있음
# .npy file 생성 시, 원본 상태 그대로 저장

# 1. Data
import numpy as np

# np.save('./_data/brain/brain_x_train.npy', arr=xy_train[0][0])
# np.save('./_data/brain/brain_y_train.npy', arr=xy_train[0][1])
# np.save('./_data/brain/brain_x_test.npy', arr=xy_test[0][0])
# np.save('./_data/brain/brain_y_test.npy', arr=xy_test[0][1])
# save -> numpy file로 저장

x_train = np.load('./_data/brain/brain_x_train.npy')
y_train = np.load('./_data/brain/brain_y_train.npy')

x_test = np.load('./_data/brain/brain_x_test.npy')
y_test = np.load('./_data/brain/brain_y_test.npy')

print(x_train.shape, x_test.shape) # (160, 200, 200, 1) (120, 200, 200, 1)
print(y_train.shape, y_test.shape) # (160,) (120,)


# 2. Model
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, Conv2D, Flatten

model = Sequential()
model.add(Conv2D(64,(2,2), input_shape=(200,200,1)))
model.add(Conv2D(64, (3,3), activation='relu'))
model.add(Conv2D(64, (3,3), activation='relu'))
model.add(Flatten())
model.add(Dense(16, activation='relu'))
model.add(Dense(16, activation='relu'))
model.add(Dense(1, activation='sigmoid'))


# 3. Compile and Train
from tensorflow.keras.callbacks import EarlyStopping

model.compile(loss='binary_crossentropy', optimizer='adam', metrics=['acc'])
# hist = model.fit_generator(xy_train, steps_per_epoch=16, epochs=10,
#                     validation_data=xy_test,
#                     validation_steps=4) # x, y, batch_size 끌어오기, 10batch -> 12 훈련

earlystop = EarlyStopping(monitor='val_acc', mode='max', patience=32,
                              restore_best_weights=True,
                              verbose=1)

hist = model.fit(x_train, y_train,
                    batch_size=8,
                    epochs=128,
                    validation_data=(x_test, y_test),
                    )

accuracy = hist.history['acc']
val_acc = hist.history['val_acc']
loss = hist.history['loss']
val_loss = hist.history['val_loss']

print("Loss: ", loss[-1])
print("Val_Loss: ", val_loss[-1])
print("Accuracy: ", accuracy[-1])
print("Val_acc: ", val_acc[-1])



'''
Result
Loss:  0.028131157159805298
Val_Loss:  0.05063672363758087
Accuracy:  0.987500011920929
Val_acc:  0.9750000238418579

'''