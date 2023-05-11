import math

def solution(food):    
    string = ''
    for f in range(1, len(food)):
        for i in range(0,math.floor(food[f]/2)):
            string += str(f)
    
    rev = string[::-1]
    string += '0'
    string += rev
    return string
