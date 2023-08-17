import java.util.*;

public class Solution{
    static int min;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for(int test_case = 1; test_case <= t; test_case++){
            int n = sc.nextInt();
            min = Integer.MAX_VALUE;

            List<Integer> startPoint = takeInputPoint(sc);
            List<Integer> destination = takeInputPoint(sc);
            List<List<Integer>> houses = new ArrayList<>();

            for(int i = 0; i < n; i++){
                List<Integer> house = takeInputPoint(sc);
                houses.add(house);
            }

            boolean[] visited = new boolean[houses.size()];
            dfs(houses, visited, startPoint, destination, 0);
            System.out.printf("#%d %d\n",test_case, min);
        }
    }
    static List<Integer> takeInputPoint(Scanner sc){
        int x = sc.nextInt();
        int y = sc.nextInt();
        return Arrays.asList(x,y);
    }
    static void dfs(List<List<Integer>> houseList, boolean[] visited, List<Integer> point, List<Integer> end, int currentValue){
        if(isAllVisited(visited)){
            currentValue += takeOneLength(point, end);
            if(currentValue < min){
                min = currentValue;
            }
            return;
        }
        for(int i = 0; i < houseList.size(); i++){
            if(!visited[i]){
                visited[i] = true;
                int plusLength = currentValue + takeOneLength(point, houseList.get(i));
                dfs(houseList, visited, houseList.get(i), end, plusLength);
                visited[i] = false;
            }
        }
    }
    static boolean isAllVisited(boolean[] visited){
        for (boolean visit : visited) {
            if (!visit) {
                return false;
            }
        }
        return true;
    }
    static int takeOneLength(List<Integer> before, List<Integer> after){
        return Math.abs(before.get(0) - after.get(0)) + Math.abs(before.get(1) - after.get(1));
    }
}