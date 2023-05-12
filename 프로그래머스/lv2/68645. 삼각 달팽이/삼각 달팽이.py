import math
def solution(n):
    array = [0] * math.floor((n*(n+1)/2))
    turn_count,value,index,add_value = 0,0,0,1
    while(turn_count < n):
        if(turn_count % 3 == 0):
            for i in range(0,n - turn_count):
                index += value
                array[index] = add_value
                add_value += 1
                value += 1

        elif(turn_count % 3 == 1):
            for i in range(0,n - turn_count):
                index += 1
                array[index] = add_value
                add_value += 1

        else:
            for i in range(0, n-turn_count):
                index -= value
                array[index] = add_value
                add_value += 1
                value -= 1

        turn_count += 1
    return array