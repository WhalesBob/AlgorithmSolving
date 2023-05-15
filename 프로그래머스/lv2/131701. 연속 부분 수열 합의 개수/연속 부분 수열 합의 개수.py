import math
def solution(elements):
    n = len(elements)
    elements *= 2
    answer = [sum(elements[i:i+limit]) for limit in range(1,n) for i in range(n)]
    s = set(answer)
    return len(s) + 1