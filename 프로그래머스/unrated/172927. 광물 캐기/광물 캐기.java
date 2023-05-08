import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        Map<String, Integer> mineralMap = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();

        mineralMap.put("diamond",25);
        mineralMap.put("iron",5);
        mineralMap.put("stone",1);

        int totalMineral = 0;
        int[] valueArray = {25,5,1};

        for(int i = 0; i < picks.length; i++){
            totalMineral += picks[i];
            for(int j = 0; j < picks[i]; j++){
                queue.add(valueArray[i]);
            }
        }
        totalMineral *= 5;

        List<MineralAverage> averageMineral = new ArrayList<>();

        for(int i = 0; i < minerals.length && i < totalMineral; i+=5){
            int sum = 0;
            for(int j = i; j < i+5 && j < totalMineral && j < minerals.length; j++){
                sum += mineralMap.get(minerals[j]);
            }
            averageMineral.add(new MineralAverage((double)sum / (double)5,i));
        }

        Collections.sort(averageMineral);

        int totalSum = 0;
        for(int i = 0; i < averageMineral.size() && i < totalMineral; i++){
            int index = averageMineral.get(i).index;
            int pickValue = queue.remove();
            for(int j = index; j < index + 5 && j < minerals.length; j++ ){
                int value = mineralMap.get(minerals[j]);
                int add = (value / pickValue > 0) ? value / pickValue : 1;
                totalSum += add;
            }
        }

        return totalSum;
    }
    static class MineralAverage implements Comparable<MineralAverage> {
        double average;
        int index;

        public MineralAverage(double average, int index) {
            this.average = average;
            this.index = index;
        }

        @Override
        public int compareTo(MineralAverage o) {
            if(this.average == o.average){
                return 0;
            }
            return this.average < o.average ? 1 : -1;
        }
    }
}