global count

def dfs(index, numbers, target, value):
    global count
    if index == len(numbers):
        if value == target:
            count += 1
        return 
    
    dfs(index+1, numbers, target, value + numbers[index])
    dfs(index+1, numbers, target, value - numbers[index])    
    
def solution(numbers, target):
    global count
    count = 0
    dfs(0, numbers, target, 0)
    return count

    