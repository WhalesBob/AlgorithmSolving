combination = []

def solution(number):
    count = 0
    number.sort()
    visited = [False] * len(number)
    makeCombination(number, visited, 0, len(number) ,3)
    
    for arr in combination:
        sum = 0
        for e in arr:
            sum += e
        if(sum == 0):
            count+=1
    return count        
            

def makeCombination(arr,visited,start,n,r):
    if(r == 0):
        combination.append(makeArray(arr,visited))
        return
    
    for i in range(start, n):
        visited[i] = True
        makeCombination(arr, visited, i+1, n, r-1)
        visited[i] = False
        
def makeArray(arr, visited):
    array = []
    for i in range(0, len(arr)):
        if(visited[i]):
            array.append(arr[i])
            
    return array        
    