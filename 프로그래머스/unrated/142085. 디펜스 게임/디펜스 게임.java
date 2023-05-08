import java.util.*;

class Solution {
    static int minValue;
    public int solution(int n, int k, int[] enemy) {
        if(enemy.length <= k){
            return enemy.length;
        }
        PriorityQueue<Integer> enemyQueue = new PriorityQueue<>();
        minValue = 0;

        for(int i = 0; i < k; i++){
            enemyQueue.add(enemy[i]);
        }

        for(int i = k; i < enemy.length; i++){
            if(!canDefense(enemyQueue,enemy[i],n,k)){
                return i;
            }
        }
        return enemy.length;
    }
    static boolean canDefense(PriorityQueue<Integer> enemyQueue,int newEnemy, int soldierCount, int skillCount){
        enemyQueue.add(newEnemy);
        minValue += enemyQueue.remove();
        return soldierCount >= minValue;
    }
}