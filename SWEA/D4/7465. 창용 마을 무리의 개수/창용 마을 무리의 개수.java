
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static int N, M, T, a, b, ans;
	static int p[];
	static int v[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int tc = 1; tc < T + 1; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			makeSet();
			v = new int[N + 1];
			for (int i = 0; i < M; i++) {
				a = sc.nextInt();
				b = sc.nextInt();
				unionSet(a, b);
			}
			for (int i = 1; i < N + 1; i++) {
				v[i] = findSet(i);
			}
			Arrays.sort(v);
//			System.out.println(Arrays.toString(v));
			int cur = v[1];
			ans = 1;
//			System.out.println(Arrays.toString(v));
			for (int i = 2; i < N + 1; i++) {
				if (cur != v[i]) {
//					System.out.print(cur+" "+v[i]+"\n");
					cur=v[i];
					ans += 1;
				}
			}
			System.out.printf("#%d %d\n", tc, ans);
		}

	}

	private static void makeSet() {
		p = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			p[i] = i;
		}
	}

	private static int findSet(int a) {
		if (a == p[a])
			return a;
		return findSet(p[a]);
	}

	private static boolean unionSet(int a, int b) {
		if (findSet(a) == findSet(b))
			return false;
		p[findSet(b)] = findSet(a);
		return true;
	}
}
