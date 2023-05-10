import math

def solution(n):
    return math.pow(math.sqrt(n)+1,2) if (math.ceil(math.sqrt(n)) == math.sqrt(n)) else -1