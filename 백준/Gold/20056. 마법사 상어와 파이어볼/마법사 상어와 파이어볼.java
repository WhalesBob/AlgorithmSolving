import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(0,-1),
			Arrays.asList(1,-1), Arrays.asList(1,0), Arrays.asList(1,1), 
			Arrays.asList(0,1), Arrays.asList(-1, 1), Arrays.asList(-1,0), Arrays.asList(-1,-1));
	
	static Set<Fireball> fireballSet;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int mapLength = Integer.parseInt(st.nextToken());
		int fireballCount = Integer.parseInt(st.nextToken());
		int orderCount = Integer.parseInt(st.nextToken());
		
		fireballSet = new HashSet<>();
		Set<Fireball>[][] map = makeFireballMap(br, mapLength, fireballCount);
		System.out.println(getRemainFireballMass(map, orderCount));
	}
	static int getRemainFireballMass(Set<Fireball>[][] map, int orderCount) {
		
		Set<Integer> duplicateSet = new HashSet<>();
		
		for(int i = 0; i < orderCount; i++) {
			for(Fireball ball : fireballSet) {
				map[ball.y][ball.x].remove(ball);
				ball.move(map.length);
				
			}
			
			duplicateSet.clear();
			for(Fireball ball : fireballSet) {
				if(map[ball.y][ball.x].size() >= 1) {
					duplicateSet.add(getKey(ball.x, ball.y));
				}
				
				map[ball.y][ball.x].add(ball);
			}
			
			for(Integer key : duplicateSet) {
				int x = key % 1000;
				int y = key / 1000;
				
				boolean haveOdd = false;
				boolean haveEven = false;
				int massSum = 0;
				int speedSum = 0;
				int count = 0;
				
				for(Fireball ball : map[y][x]) {
					if(ball.direct % 2 == 1) {
						haveOdd = true;
					}else {
						haveEven = true;
					}
					
					massSum += ball.mass;
					speedSum += ball.speed;
					
					fireballSet.remove(ball);
					count++;
				}
				
				map[y][x].clear();
				
				if(massSum >= 5) {
					int newDirection = haveOdd && haveEven ? 1 : 0;

					for(; newDirection < 8; newDirection += 2) {
						Fireball newBall = new Fireball(x, y, massSum / 5, newDirection, speedSum / count);
						map[y][x].add(newBall);
						fireballSet.add(newBall);
					}
				}
			}
		}
		
		int sum = 0;
		for(Fireball ball : fireballSet) {
			sum += ball.mass;
		}
		
		return sum;
	}
	static int getKey(int x, int y) {
		return 1000 * y + x;
	}
	static Set<Fireball>[][] makeFireballMap(BufferedReader br, int size, int count) throws IOException{
		Set<Fireball>[][] map = new Set[size][size];
		for(int y = 0; y < size; y++) {
			for(int x = 0; x < size; x++) {
				map[y][x] = new HashSet<>();
			}
		}
		for(int i = 0; i < count; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int direct = Integer.parseInt(st.nextToken());
			
			Fireball ball = new Fireball(x, y, m, direct, speed);
			map[y][x].add(ball);
			fireballSet.add(ball);
		}
		return map;
	}
	static boolean canGo(int x, int y, Fireball[][] matrix) {
		return (0 <= x && x < matrix[0].length)  && (0 <= y && y < matrix.length);
	}
	static class Fireball{
		int x;
		int y;
		int mass;
		int direct;
		int speed;
		
		public Fireball(int x, int y, int mass, int direct, int speed) {
			this.x = x;
			this.y = y;
			this.mass = mass;
			this.direct = direct;
			this.speed = speed;
		}
		
        public void move(int bound) {
            x = Fireball.moveOneAxis(x, direction.get(direct).get(0) * speed, bound);
            y = Fireball.moveOneAxis(y, direction.get(direct).get(1) * speed, bound);
        }

        static int moveOneAxis(int a, int togo, int bound){
            a += togo;

            if(a < 0){
                a += (bound * Math.ceil((-1D * a) / (double)bound));
            }else if(a >= bound){
                a %= bound;
            }

            return a;
        }
	}
}