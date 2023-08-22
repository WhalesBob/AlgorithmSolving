import java.io.*;
import java.util.*;

public class Main {
	static Map<Integer, Set<Integer>> relationMap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int peopleCount = Integer.parseInt(st.nextToken());
		int relationCount = Integer.parseInt(st.nextToken());
		
		relationMap = makeRelationShip(br, relationCount);
		
		for(Integer key : relationMap.keySet()) {
			dfs(key, 0, new HashSet<>(Arrays.asList(key)));
		}
		System.out.println(0);
	}
	static void dfs(int person,int count,Set<Integer> alreadyHave) {
		if(count >= 4) {
			System.out.println(1);
			System.exit(0);
		}
		
		for(Integer friend : relationMap.get(person)) {
			if(!alreadyHave.contains(friend)) {
				alreadyHave.add(friend);
				dfs(friend, count + 1, alreadyHave);
				alreadyHave.remove(friend);
			}
		}
	}
	static Map<Integer, Set<Integer>> makeRelationShip(BufferedReader br, int size) throws IOException{
		Map<Integer, Set<Integer>> map = new HashMap<>();
		
		for(int i = 0;i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(!map.containsKey(a)){
				map.put(a, new HashSet<>());
			}
			
			if(!map.containsKey(b)) {
				map.put(b, new HashSet<>());
			}
			
			map.get(a).add(b);
			map.get(b).add(a);
		}
		
		return map;
	}
}