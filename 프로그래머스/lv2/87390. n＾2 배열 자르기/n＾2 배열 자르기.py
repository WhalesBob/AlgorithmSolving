def solution(n, left, right):
    return [max(int(i/n),int(i%n))+1 for i in range(left,right+1)]