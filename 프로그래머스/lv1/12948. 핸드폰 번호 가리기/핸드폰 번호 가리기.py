def solution(phone_number):
    answer = ''
    array = list(str(phone_number))
    for i in range(0,len(array)):
        if(i < len(array)-4):
            answer = answer + '*'
        else:
            answer = answer + array[i]
            
    return answer        