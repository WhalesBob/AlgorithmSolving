def solution(s):
    queue = [x for x in s]
    count = 0
    stack = []
    for i in range(0, len(s)):
        if isGood(queue, stack):
            count += 1
        
        element = queue.pop(0)
        queue.append(element)
    
    return count

    
def isGood(s, stack):
    for element in s:
        if element == '[' or element == '(' or element == '{':
            stack.append(element)
        else:
            if len(stack) == 0:
                return False
            
            p = stack.pop()
            is_small = (element == ')' and p == '(')
            is_middle = (element == '}' and p == '{')
            is_large = (element == ']' and p == '[')
            if not(is_small or is_middle or is_large):
                return False
    return len(stack) == 0
           