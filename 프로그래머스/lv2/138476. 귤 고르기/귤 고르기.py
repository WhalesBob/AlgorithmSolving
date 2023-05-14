def solution(k, tangerine):
    d = dict()
    for t in tangerine:
        d[t] = ((d[t] + 1) if t in d else 1)

    count, sum = 0,0
    sorted_dict = sorted(d.items(), key=lambda item: item[1], reverse=True)
    for sub in sorted_dict:
        sum += sub[1]
        count += 1
        if sum >= k:
            break

    return count
