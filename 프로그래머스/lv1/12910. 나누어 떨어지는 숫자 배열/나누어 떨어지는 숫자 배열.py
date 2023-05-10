def solution(arr, divisor):
    array = []
    for i in arr:
        if(i % divisor == 0):
            array.append(i)
            
    if(len(array) == 0):
        array.append(-1)
        
    array.sort()    
    return array   