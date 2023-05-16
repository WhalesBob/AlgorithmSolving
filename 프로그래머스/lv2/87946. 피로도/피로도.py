from itertools import permutations

def solution(k, dungeons):
    dungeons.sort(key=lambda x:x[0], reverse=True)
    global counted_max
    counted_max = 0
    for dun in permutations(dungeons, len(dungeons)):
        have = k
        for i in range(0,len(dun)):
            if have < dun[i][0]:
                if i > counted_max:
                    counted_max = i
                break
            else:
                have -= dun[i][1]
                if i == len(dun) - 1:
                    return len(dun)
            
    return counted_max