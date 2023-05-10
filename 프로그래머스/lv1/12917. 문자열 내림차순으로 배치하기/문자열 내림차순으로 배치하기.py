def solution(s):
    array = list(s)
    array.sort(reverse=True)
    return ''.join(s for s in array)