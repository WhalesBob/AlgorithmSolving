from collections import Counter

def solution(str1, str2):
    tmp1 = [str1[i:i+2] for i in range(len(str1))]
    arr1 = Counter([x.upper() for x in tmp1 if x.isalpha() and len(x) == 2])
    tmp2 = [str2[i:i+2] for i in range(len(str2))]
    arr2 = Counter([x.upper() for x in tmp2 if x.isalpha() and len(x) == 2])
    
    cross = list((arr1 & arr2).elements())
    union = list((arr1 | arr2).elements())
    print(cross)
    print(union)
    
    return 65536 if (len(union) == 0 and len(cross) == 0) else int((len(cross) / len(union)) * 65536)