import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++){
            int personCount = Integer.parseInt(br.readLine());
            Person[] personInfo = getPersonInfo(br, personCount);

            Arrays.sort(personInfo);
            bw.write(getMaxOfPick(personInfo) + "\n");
        }
        bw.flush();
    }
    static int getMaxOfPick(Person[] personInfo){
        Stack<Person> stack = new Stack<>();
        stack.push(personInfo[0]);

        for(int personIndex = 1; personIndex < personInfo.length; personIndex++){
            if(stack.peek().faceIndex > personInfo[personIndex].faceIndex){
                stack.push(personInfo[personIndex]);
            }
        }
        return stack.size();
    }
    static Person[] getPersonInfo(BufferedReader br, int count) throws IOException {
        Person[] people = new Person[count];
        for(int i = 0; i < count; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int paper = Integer.parseInt(st.nextToken());
            int face = Integer.parseInt(st.nextToken());
            people[i] = new Person(paper, face);
        }
        return people;
    }
    static class Person implements Comparable<Person>{
        int paperIndex;
        int faceIndex;

        public Person(int paperIndex, int faceIndex) {
            this.paperIndex = paperIndex;
            this.faceIndex = faceIndex;
        }

        @Override
        public int compareTo(Person o) {
            if(this.paperIndex == o.paperIndex){
                return 0;
            }
            return this.paperIndex > o.paperIndex ? 1 : -1;
        }
    }
}