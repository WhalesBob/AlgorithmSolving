def solution(s):
    d = {"zero":0 ,"one": 1, "two": 2, "three": 3, "four": 4, "five": 5, "six": 6, "seven": 7, "eight": 8, "nine": 9}

    input = ''
    result = []
    for i in s:
        if (i.isdigit()):
            result.append(int(i))
        else:
            input += i
            if (input in d):
                result.append(d[input])
                input = ''

    return int(''.join(str(a) for a in result))