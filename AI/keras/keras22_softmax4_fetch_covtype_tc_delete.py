import matplotlib.pyplot as plt

from sklearn.datasets import load_wine
from sklearn.datasets import load_boston

# 1. Data
import numpy as np
arr = np.array([[1 ,2, 3, 4],
                  [5, 6, 7, 8],
                  [9, 10, 11, 12]])
arr = np.delete(arr, 0, axis=1)
print(arr)

'''
arr = np.array([[1 ,2, 3, 4],
                  [5, 6, 7, 8],
                  [9, 10, 11, 12]])
arr = np.delete(arr, 0, axis=1)
print(arr)
[[ 2  3  4]
 [ 6  7  8]
 [10 11 12]]
0열 삭제


arr = np.array([[1 ,2, 3, 4],
                [5, 6, 7, 8],
                [9, 10, 11, 12]])
arr = np.delete(arr, 0, axis=0)
print(arr)
[[ 5  6  7  8]
 [ 9 10 11 12]]
 0행 삭제


arr = np.array([[1 ,2, 3, 4],
                [5, 6, 7, 8],
                [9, 10, 11, 12]])
arr = np.delete(arr, 1, axis=0)
print(arr)
[[ 1  2  3  4]
 [ 9 10 11 12]]
1행 삭제


arr = np.array([[1 ,2, 3, 4],
                [5, 6, 7, 8],
                [9, 10, 11, 12]])
arr = np.delete(arr, 1, axis=1)
print(arr)
[[ 1  3  4]
 [ 5  7  8]
 [ 9 11 12]]
1열 삭제
<<<<<<< HEAD
'''
=======
'''
>>>>>>> b8bdc70 (Update keras22_softmax4_fetch_covtype_tc_delete.py)
