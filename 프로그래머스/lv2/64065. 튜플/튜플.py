def solution(s):
    s = s.replace("}","").replace("{","")
    array = list(map(int,s.split(",")))
    
    d = {}
    for element in array:
        if element in d:
            d[element] += 1
        else:
            d[element] = 1
    
    answer = []
    sorted_dict = sorted(d.items(), key = lambda x:x[1], reverse=True)
    for key,value in sorted_dict:
        answer.append(key)
        
    return answer