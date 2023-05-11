import math

def solution(nums):
    d = dict()
    for n in nums:
        if(n in d):
            d[n]+=1
        else:
            d[n] = 1

    return math.floor(len(nums)/2) if (len(d) > len(nums) / 2) else len(d)
