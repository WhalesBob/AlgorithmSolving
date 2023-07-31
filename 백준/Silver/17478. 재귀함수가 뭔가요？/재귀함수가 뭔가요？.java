import java.io.*;
import java.util.*;

public class Main {
	static List<String> comments;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		comments = new ArrayList<>();
		comments.add("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
		
		comments.add("\"재귀함수가 뭔가요?\"\n");
		comments.add("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
		comments.add("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
		comments.add("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
		
		int count = Integer.parseInt(br.readLine());
		
		bw.write("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
		printRecursive(bw,count,"");
		
		bw.flush();
	}
	static void printRecursive(BufferedWriter bw ,int count, String dash) throws IOException {
		
		if(count == 0) {
			print(bw, comments.get(1), dash);
			print(bw, comments.get(0), dash);
			print(bw, "라고 답변하였지.\n", dash);
			return;
		}
		
		for(int i = 1; i < comments.size(); i++) {
			print(bw, comments.get(i), dash);
		}
		
		printRecursive(bw, count - 1, dash + "____");
		print(bw, "라고 답변하였지.\n", dash);
	}
	static void print(BufferedWriter bw, String msg, String dash) throws IOException {
		bw.write(dash + msg);
	}
}