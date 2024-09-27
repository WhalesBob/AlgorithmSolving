#include <stdio.h>

int main() {
    int A,B,C;

    scanf("%d %d %d",&A, &B, &C);
    if(C==B){
        printf("-1");
        return 0;
    }
    int answer = (int)(A/(C-B)) + 1;

    if(answer <= 0){
        printf("-1");
    }else{
        printf("%d",answer);
    }
    
    return 0;
}
