def solution(arr):
    while(len(arr) > 1):
        a = arr.pop(0)
        b = arr.pop(0)
        arr.append(int(get_lcm(a,b)))

    return arr.pop(0)

def get_lcm(a,b):
    ori_a, ori_b = max(a,b), min(a,b)
    while(b != 0):
        a, b = b, a%b

    gcd = a
    return gcd * (ori_a / gcd) * (ori_b / gcd)