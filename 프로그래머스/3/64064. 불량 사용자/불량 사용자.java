import java.util.HashSet;
import java.util.Set;

class Solution {
    static Set<Integer> indexSet;
    public int solution(String[] user_id, String[] banned_id) {
        indexSet = new HashSet<>();
        dfs(user_id, banned_id, new boolean[user_id.length], 0);
        return indexSet.size();
    }
    static void dfs(String[] userId, String[] bannedId, boolean[] visited, int currentIndex){
        if(currentIndex >= bannedId.length){
            indexSet.add(makeIndexInteger(visited));
            return;
        }

        for(int i = 0; i < userId.length; i++){
            if(!visited[i] && isAvailable(bannedId[currentIndex], userId[i])){
                visited[i] = true;
                dfs(userId, bannedId, visited, currentIndex + 1);
                visited[i] = false;
            }
        }
    }
    static boolean isAvailable(String banned, String user){
        char[] bannedChar = banned.toCharArray();
        char[] userChar = user.toCharArray();

        if(bannedChar.length != userChar.length){
            return false;
        }

        for(int i = 0; i < user.length(); i++){
            if(!(bannedChar[i] == '*' || bannedChar[i] == userChar[i])){
                return false;
            }
        }
        return true;
    }
    static int makeIndexInteger(boolean[] visited){
        StringBuilder builder = new StringBuilder();
        for(int i = visited.length - 1; i >= 0; i--){
            if(visited[i]){
                builder.append(i);
            }
        }
        return Integer.parseInt(builder.toString());
    }
}