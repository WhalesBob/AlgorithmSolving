def solution(x):
    sum = 0
    array = list(str(x))
    for i in array:
        print(i)
        sum = sum + int(i)
        
    return (x % sum == 0)