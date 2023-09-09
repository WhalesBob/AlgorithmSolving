def solution(n):
    memo = [0,1]
    for i in range(2, n+1):
        value = (memo[-1] + memo[-2]) % 1234567
        memo.append(value)
    return memo[-1]