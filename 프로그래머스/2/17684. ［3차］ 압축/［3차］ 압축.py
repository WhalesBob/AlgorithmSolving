from collections import deque

def solution(msg):
    idx, dic = make_dict_initial()
    idx_array = []
    deq = deque(c for c in msg)

    while len(deq) != 0:
        add_idx, target_dict = get_idx_and_dict(deq, dic)
        idx_array.append(add_idx)

        if len(deq) != 0:
            target_dict[deq[0]] = (idx, dict())
            idx += 1
        else:
            break

    return idx_array


def get_idx_and_dict(deq, dic):
    target_dict = dic
    target_idx = dic[deq[0]][0]

    while len(deq) != 0:
        if deq[0] in target_dict:
            c = deq[0]
            target_idx = target_dict[c][0]
            target_dict = target_dict[c][1]
            deq.popleft()
        else:
            break

    return target_idx, target_dict


def make_dict_initial():
    index = 1
    dic = {0: None}

    for c in range(ord('A'), ord('Z') + 1):
        dic[chr(c)] = (index, dict())
        index += 1

    return index, dic