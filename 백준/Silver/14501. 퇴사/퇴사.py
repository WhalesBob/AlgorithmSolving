from sys import stdin
global max_profit, time, profit

def track(current_index, current_profit):
    global max_profit
    if current_index >= len(profit):
        if max_profit < current_profit:
            max_profit = current_profit
        return

    next_index = current_index + time[current_index]
    if next_index <= n:
        track(next_index, current_profit + profit[current_index])
    track(current_index + 1, current_profit)

if __name__ == '__main__':
    n = int(stdin.readline())
    time, profit = [], []
    max_profit = 0

    for i in range(0, n):
        t, p = map(int, stdin.readline().split(" "))
        time.append(t)
        profit.append(p)

    for i in range(n - 1, -1, -1):
        if i + time[i] - 1 >= n:
            time.pop()
            profit.pop()
        else:
            break

    track(0, 0)
    print(max_profit)