def solution(price, money, count):

    sum = 0
    for i in range(1,count+1):
        sum += (price * i)

    return (sum - money) if sum > money else 0