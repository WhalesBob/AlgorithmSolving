combinations=[]

def solution(numbers):
    answer = set()
    visited = [False] * len(numbers)
    combination(numbers, visited, 0, len(numbers), 2)
    
    for arr in combinations:
        x,y = arr
        answer.add(x+y)
    
    result = list(answer)
    return sorted(result)
    

def combination(arr, visited, start, n, r):
    if(r == 0):
        addCombination(arr,visited)
        return
    
    for i in range(start, n):
        visited[i] = True
        combination(arr,visited,i+1,n,r-1)
        visited[i] = False
        
def addCombination(arr,visited):
    array = []
    for i in range(0,len(arr)):
        if(visited[i] == True):
            array.append(arr[i])
    
    combinations.append(array)