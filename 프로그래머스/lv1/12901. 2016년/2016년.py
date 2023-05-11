from datetime import datetime

def solution(a, b):
    week = ["SUN","MON","TUE","WED","THU","FRI","SAT"]
    oneone = datetime.strptime("20160101","%Y%m%d")
    month = str(a) if a >= 10 else "0" + str(a)
    day = str(b) if b >= 10 else "0" + str(b)
    toCompare = datetime.strptime("2016" + month + day,"%Y%m%d")
    
    dateDiff = (toCompare - oneone).days
    
    index = ((dateDiff % 7) + 5) % 7
    return week[index]