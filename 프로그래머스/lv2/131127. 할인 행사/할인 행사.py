def can_buy_all(d, want, number):
    for i in range(len(want)):
        if want[i] not in d:
            return False
        else:
            if d[want[i]] < number[i]:
                return False
    return True

def replace_item(d,discount,index):
    d[discount[index]] -= 1
    
    if discount[index+10] in d:
        d[discount[index+10]] += 1
    else:
        d[discount[index+10]] = 1

def solution(want, number, discount):
    disc_dict = {}
    for i in range(10):
        if discount[i] in disc_dict:
            disc_dict[discount[i]] += 1
        else:
            disc_dict[discount[i]] = 1
    
    all_sum = sum(number)
    count = 0
    for i in range(0, len(discount) - all_sum + 1):
        if can_buy_all(disc_dict, want, number):
            count += 1
        if i+10 < len(discount):
            replace_item(disc_dict,discount,i)         
    return count