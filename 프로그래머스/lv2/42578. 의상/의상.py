def solution(clothes):
    d = dict()
    for cloth in clothes:
        if cloth[1] in d:
            d[cloth[1]].append(cloth[0])
        else:
            d[cloth[1]] = [cloth[0]]

    s = 1
    for element in d:
        s *= (len(d[element]) + 1)
    return s-1
