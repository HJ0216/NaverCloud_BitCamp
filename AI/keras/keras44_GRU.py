# calc_param_RNN_LSTM_GRU.py

import numpy as np

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, SimpleRNN, LSTM, GRU, Dropout


# 1. Data
dataset=np.array([1,2,3,4,5,6,7,8,9,10]) # (10,)
# absence y data

# make a y data
x = np.array([[1,2,3], 
              [2,3,4], 
              [3,4,5], 
              [4,5,6], 
              [5,6,7], 
              [6,7,8], 
              [7,8,9]]) # (7, 3)
y = np.array([4,5,6,7,8,9,10]) # (7,)
# y = np.array([[4],[5],[6],[7],[8],[9],[10]]) 과 동일

x = x.reshape(7,3,1)


# 2. Model Construction
model = Sequential()
# model.add(SimpleRNN(units=10, input_shape=(3,1)))
# model.add(LSTM(units=10, input_shape=(3,1)))
model.add(GRU(units=10, input_shape=(3,1)))
model.add(Dense(1))
model.summary()
'''
Model: "sequential"
_________________________________________________________________
 Layer (type)                Output Shape              Param #
=================================================================
 simple_rnn (SimpleRNN)      (None, 10)                120
-----------------------------------------------------------------
 lstm (LSTM)                 (None, 10)                480
-----------------------------------------------------------------
 gru (GRU)                   (None, 10)                390
_________________________________________________________________

SimpleRNN Param #
Total params = recurrent_weights + input_weights + biases
= (units*units)+(features*units) + (1*units)
= units(units + feature + 1)


LSTM Param #
Params # = 4 * (output_dim(output_dim + input_dim + 1))
= 4 * SimpleRNN (gate가 4개라서 SimpleRNN의 4배)


(Deprecated) GRU Param #
Params # = 3 * ((input_dim + 1) * output_dim + output_dim^2)
= 3 * SimpleRNN (gate가 3개라서 SimpleRNN의 3배)


(Renew) GRU Param #
Params # = 3 * output_dim * (input_dim + bias + reset_after bias + output_dim)
(reset_after=False: 3 * output_dim * (input_dim + bias + output_dim))
-> 3 * 10 * (1 + 1 + 1 + 10)
(Default: reset_after=True)

'''