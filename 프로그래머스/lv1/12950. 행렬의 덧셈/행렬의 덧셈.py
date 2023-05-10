import numpy as np

def solution(arr1, arr2):
    result = []
    for i in range(0,len(arr1)):
        tmp = []
        for j in range(0,len(arr1[0])):
            tmp.append(arr1[i][j] + arr2[i][j])
        
        result.append(tmp)
        
    return result  