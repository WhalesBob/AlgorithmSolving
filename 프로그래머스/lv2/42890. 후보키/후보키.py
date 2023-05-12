combinations = []

def solution(relation):
    indexList = list(range(0, len(relation[0])))
    visited = [False] * (len(indexList) + 1)
    for i in range(1, len(indexList)+1):
        makeCombination(indexList, visited, 0, len(indexList), i)

    new_comb = combinations
    answer = []
    while (len(new_comb) != 0):
        c = new_comb.pop(0)
        if (canBeKey(relation, c)):
            answer.append(c)
            new_comb = [value for value in new_comb if not all(item in value for item in c)]

    return len(answer)

def makeCombination(arr, visited, start, n, r):
    if (r == 0):
        combinations.append(addOn(arr, visited))
        return

    for i in range(start, len(arr)):
        visited[i] = True
        makeCombination(arr, visited, i + 1, n, r - 1)
        visited[i] = False

def addOn(arr, visited):
    array = []
    for i in range(0, len(arr)):
        if (visited[i]):
            array.append(arr[i])
    return array

def canBeKey(relation, keyIndex):
    s = list()

    for r in relation:
        sub = [r[i] for i in keyIndex]

        if (sub in s):
            return False
        else:
            s.append(sub)
    return True