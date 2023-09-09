def solution(n):
    tile = [1, 2]
    if(n == 1):
        return 1
    for i in range(2,n):
        tile.append((tile[-1] + tile[-2]) % 1000000007)
    return tile[-1]
    