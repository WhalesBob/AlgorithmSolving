def solution(arr):
    element = 10 ** 100
    for i in arr:
        if(element > i):
            element = i
            
    arr.remove(element)     
    if(len(arr) == 0):
        arr.append(-1)
        
    return arr    