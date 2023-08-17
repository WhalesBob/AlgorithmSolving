import java.io.*;
import java.util.*;
 
public class Solution {
    static List<List<Integer>> direction = Arrays.asList(Arrays.asList(0,0), 
            Arrays.asList(0,-1), Arrays.asList(1,0), Arrays.asList(0,1), Arrays.asList(-1,0));
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
         
        for(int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int M = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
             
            int[] moveA = makePlayerMove(br);
            int[] moveB = makePlayerMove(br);
             
            Charger[] chargeSet = makeChargeSet(br, A);
            System.out.printf("#%d %d\n", test_case, getMaxCharge(moveA, moveB, chargeSet, M));
        }
    }
    static int getMaxCharge(int[] moveA, int[] moveB, Charger[] chargeArray, int M) {
        int sum = 0;
        Point A = new Point(0, 0);
        Point B = new Point(9, 9);
        for(int i = 0; i <= M; i++) {
             
            Set<Integer> locateA = getLocate(A, chargeArray);
            Set<Integer> locateB = getLocate(B, chargeArray);
 
            if(haveSame(locateA, locateB)) {
                sum += getHaveSameMax(locateA, locateB, chargeArray);
            }else {
                sum += (getPowerMax(locateA, chargeArray) + getPowerMax(locateB, chargeArray));
            }
             
            if(i < M) {
                A.x += direction.get(moveA[i]).get(0);
                A.y += direction.get(moveA[i]).get(1);
                B.x += direction.get(moveB[i]).get(0);
                B.y += direction.get(moveB[i]).get(1);
            }
        }
         
        return sum;
    }
    static int getHaveSameMax(Set<Integer> setA, Set<Integer> setB, Charger[] chargeArray) {
        if(setA.size() == 1 && setB.size() == 1) {
            return chargeArray[getIndexFromSet(setA)].power;
        }
         
        if(setA.size() == 1) return getMaxValueFromTwoSet(setA, setB, chargeArray);
        if(setB.size() == 1) return getMaxValueFromTwoSet(setB, setA, chargeArray);
         
        int valueA = getMaxFromTwoBigSizeSet(new HashSet<>(setA), new HashSet<>(setB), chargeArray);
        int valueB = getMaxFromTwoBigSizeSet(setB, setA, chargeArray);
        return Math.max(valueA, valueB);
    }
    static int getMaxFromTwoBigSizeSet(Set<Integer> A, Set<Integer> B, Charger[] chargeArray) {
        int max = 0;
        int maxIdx = -1;
        for(Integer indexA : A) {
            if(max < chargeArray[indexA].power) {
                max = chargeArray[indexA].power;
                maxIdx = indexA;
            }
        }
         
        if(B.contains(maxIdx)) {
            B.remove(maxIdx);
        }
         
        int sum = max;
        max = 0;
         
        for(Integer indexB : B) {
            if(max < chargeArray[indexB].power) {
                max = chargeArray[indexB].power;
            }
        }
        return (sum + max);
    }
    static int getMaxValueFromTwoSet(Set<Integer> smallSet, Set<Integer> bigSet, Charger[] chargeArray) {
        int index = getIndexFromSet(smallSet);
        bigSet.remove(index);
        int max = 0;
        for(Integer otherIdx : bigSet) {
            int value = chargeArray[otherIdx].power;
            if(max < value) {
                max = value;
            }
        }
        return (max + chargeArray[index].power);
    }
    static int getIndexFromSet(Set<Integer> set) {
        for(Integer index : set) {
            return index;
        }
        return -1;
    }
    static Set<Integer> getLocate(Point p, Charger[] chargeArray){
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < chargeArray.length; i++) {
            Charger c = chargeArray[i];
            if(getDistance(p, c) <= c.range) {
                set.add(i);
            }
        }   
        return set;
    }
    static int getDistance(Point p, Charger c) {
        return Math.abs(p.x - c.p.x) + Math.abs(p.y - c.p.y);
    }
    static int getPowerMax(Set<Integer> set, Charger[] chargeArray) {
        int max = 0;
        for(Integer index : set) {
            if(chargeArray[index].power > max) {
                max = chargeArray[index].power;
            }
        }
        return max;
    }
    static boolean haveSame(Set<Integer> setA, Set<Integer> setB) {
        for(Integer element : setA) {
            if(setB.contains(element)) {
                return true;
            }
        }
        return false;
    }
    static Charger[] makeChargeSet(BufferedReader br, int size) throws IOException{
        Charger[] array =  new Charger[size];
        for(int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int range = Integer.parseInt(st.nextToken());
            int power = Integer.parseInt(st.nextToken());
            array[i] = new Charger(x-1, y-1, range, power);
        }
        return array;
    }
    static int[] makePlayerMove(BufferedReader br) throws IOException {
        String[] input = br.readLine().trim().split(" ");
        int[] array = new int[input.length];
        for(int i = 0; i < input.length; i++) {
            array[i] = Integer.parseInt(input[i]);
        }
        return array;
    }
     
    static class Charger{
        Point p;
        int range;
        int power;
         
        public Charger(int x, int y, int range, int power) {
            this.p = new Point(x, y);
            this.range = range;
            this.power = power;
        }
    }
    static class Point{
        int x;
        int y;
         
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}