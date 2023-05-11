def solution(n, arr1, arr2):
    answer = []
    for element in arr1:
        s = list(str(bin(element)))[2:]
        while (len(s) < n):
            s.insert(0, '0')
        answer.append(s)

    for i in range(0, len(arr2)):
        s = list(str(bin(arr2[i])))[2:]
        while (len(s) < n):
            s.insert(0, '0')

        for j in range(0, len(s)):
            if (answer[i][j] == '0' and s[j] == '1'):
                answer[i][j] = '1'

    real = []
    for element in answer:
        tmp = []
        for e in element:
            if (e == '0'):
                tmp.append(' ')
            else:
                tmp.append('#')
        st = ''.join(a for a in tmp)
        real.append(st)

    return real