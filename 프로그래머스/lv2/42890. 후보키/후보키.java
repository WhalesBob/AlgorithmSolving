import java.util.*;

class Solution {
    static List<List<Integer>> combinations;
    public int solution(String[][] relation){
        combinations = new ArrayList<>();
        List<Integer> indexList = new ArrayList<>();
        for(int i = 0; i < relation[0].length; i++){
            indexList.add(i);
        }

        boolean[] visited = new boolean[indexList.size() + 1];
        for(int i = 1; i <= indexList.size(); i++){
            makeCombination(indexList, visited, 0, indexList.size(), i);
        }

        List<Integer> answer = new ArrayList<>();
        while(combinations.size() != 0){
            List<Integer> c = combinations.remove(0);
            if(canBeKey(relation, c)){
                answer.add(c.size());
                List<List<Integer>> newCombinations = new ArrayList<>();
                for(List<Integer> value : combinations){
                    boolean isSubSet = true;
                    for(int i = 0; i < c.size(); i++){
                        if(!value.contains(c.get(i))){
                            isSubSet = false;
                            break;
                        }
                    }
                    if(!isSubSet){
                        newCombinations.add(value);
                    }
                }
                combinations = newCombinations;
            }
            
        }
        return answer.size();
    }

    static void makeCombination(List<Integer> arr, boolean[] visited, int start, int n, int r){
        if(r == 0){
            combinations.add(addOn(arr, visited));
            return;
        }

        for(int i = start; i < arr.size(); i++){
            visited[i] = true;
            makeCombination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }
    static List<Integer> addOn(List<Integer> arr, boolean[] visited){
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < arr.size(); i++){
            if(visited[i]){
                result.add(arr.get(i));
            }
        }
        return result;
    }
    static boolean canBeKey(String[][] relation, List<Integer> comb){
        List<String> keyList = new ArrayList<>();
        for(int i = 0; i < relation.length; i++){
            String key = "";
            for(int j = 0; j < comb.size(); j++){
                key += relation[i][comb.get(j)];
            }
            if(keyList.contains(key)){
                return false;
            }
            keyList.add(key);
        }
        return true;
    }
}