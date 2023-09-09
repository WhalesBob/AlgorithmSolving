import heapq

def solution(scoville, K):
    heapq.heapify(scoville)
    time = 0
    while len(scoville) > 1 and scoville[0] < K:
        time += 1
        value = heapq.heappop(scoville) + 2 * heapq.heappop(scoville)
        heapq.heappush(scoville, value)
    
    return time if scoville[0] >= K else -1