
import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
	static int N, ans, a, b, c;
	static ArrayDeque<int[]> q;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); // 입력받기 위해 Scanner 선언
		N = sc.nextInt(); // 1분기는 몇분인지 받기 위한 정수N
		q = new ArrayDeque<int[]>(); // 작업,업무 평가점수, 걸리는시간을 한번에 넣기 위해 int[]로 타입 정함, 마지막에 하던일을 다시 꺼내기 위해 ArrayDeque를
										// 이용함
		for (int i = 0; i < N; i++) { // N분동안의 정보 받기
			a = sc.nextInt();
			if (a != 0) { // a가 0이 아닐 경우 업무정보를 받아야함
				b = sc.nextInt(); // 점수 받기
				c = sc.nextInt(); // 소요 시간 받기
				q.addLast(new int[] { a, b, c }); // 업무 정보 추가
			} else {
				q.addLast(new int[] { 0, 0, 0 }); // 작업이 없을때 정보 추가
			}
		}
		int[] cur = q.pollFirst(); // 첫 정보를 꺼냄
		cur[2] -= 1; // 꺼내자마자 1분동안 일을 하기때문에 -1분을 함
		if (N == 1) { // 만약 분기가 1분이라면 처음것만 확인하면 됨
			if (cur[2] == 0) // 1분짜리 일이라면 바로 완료
				ans += cur[1]; // 정답에 추가
		} else { // 분기가 1분보다 클 경우 실행, 위 if문을 안쓸경우 분기가 1분일 때 1 10 1 값을 처리하지 못함
			if (cur[2] == 0) {
				ans += cur[1];
			}
			for (int i = 0; i < N - 1; i++) { // 이미 1분째는 처리했기때문에 N-1회만큼 반복
				if(!q.isEmpty()) {
					int[] tmp = q.pollFirst(); // 1분이 지난 뒤 일이 들어왔는지 확인하기 위해 꺼냄
//			System.out.println("꺼낸 일 확인 : "+tmp[0]+" "+tmp[1]+" "+tmp[2]);
					if (tmp[0] == 0) { // 일이 없으면
//				System.out.println("하던일 하기");
						cur[2] -= 1; // 현재 하고 있는 일을 1분만큼 더 했기때문에 -1함
					} else { // 새로운 업무가 있으면
//				System.out.println("새로운 업무가 생김");
//				System.out.println(cur[0]+" "+cur[1]+" "+cur[2]+"다시 넣기");
						q.addLast(cur); // 원래 하던 업무 다시 넣기
						cur = tmp; // 새 업무를 지금 하는 일로 변경
						cur[2] -= 1; // 1분만큼 진행
					}
					
					if (cur[2] == 0) { // 작업을 다 종료했으면
						ans += cur[1]; // 평가점수 더하기
//					System.out.println(i + "번째" + ans);
						if (!q.isEmpty())
							cur = q.pollLast(); // 작업을 다 했기 때문에 원래 하던일 다시 꺼내기
					}
				}
			}

		}
		System.out.println(ans); // 정답 출력
	}

}
