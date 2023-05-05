class Solution
{
    public int solution(int n, int a, int b)
    {
        int count = 1;
        a--; b--;
        while(a != b && a/2 != b/2){
            a /= 2;
            b /= 2;
            count++;
        }

        return count;
    }
}