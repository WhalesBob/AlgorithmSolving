import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char[] ballsArray = br.readLine().trim().toCharArray();

        List<Integer> compareList = new ArrayList<>();
        compareList.add(getBallsConvertCountLeft(makeCloneArray(ballsArray), 'R','B'));
        compareList.add(getBallsConvertCountLeft(makeCloneArray(ballsArray), 'B','R'));
        compareList.add(getBallsConvertCountRight(makeCloneArray(ballsArray), 'R','B'));
        compareList.add(getBallsConvertCountRight(makeCloneArray(ballsArray), 'B','R'));

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < 4; i++){
            int value = compareList.get(i);
            if(min > value){
                min = value;
            }
        }
        System.out.println(min);

    }
    static int getBallsConvertCountLeft(char[] ballsArray, char target, char sub){
        int lastTargetIndex = -1;
        int count = 0;
        for(int i = 0; i < ballsArray.length; i++){
            if(ballsArray[i] == target){
                if(i != lastTargetIndex + 1){
                    count++;
                    swap(ballsArray, lastTargetIndex + 1, i);
                }
                lastTargetIndex ++;
            }
        }
        return count;
    }
    static int getBallsConvertCountRight(char[] ballsArray, char target, char sub){
        int count = 0;
        int lastTargetIndex = ballsArray.length;
        for(int i = ballsArray.length -1; i >= 0; i--){
            if(ballsArray[i] == target){
                if(i != lastTargetIndex - 1){
                    count++;
                    swap(ballsArray, lastTargetIndex -1, i);
                }
                lastTargetIndex--;
            }
        }
        return count;
    }
    static char[] makeCloneArray(char[] array){
        char[] newArray = new char[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }
    static void swap(char[] array, int a, int b){
        char temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}