def solution(n, m):
    a,b = max(n,m), min(n,m)
    while(b != 0):
        a,b = b,a%b
    gcd = a
    c = gcd * (n/gcd) * (m/gcd)
    return [gcd,c]