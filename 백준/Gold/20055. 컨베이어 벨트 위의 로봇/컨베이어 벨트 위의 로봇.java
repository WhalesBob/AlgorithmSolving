import java.io.*;
import java.util.*;

public class Main {
    static int durabilityCount;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        LinkedList<BeltPlate> upBelt = initBeltPlate(st, n);
        LinkedList<BeltPlate> downBelt = initBeltPlate(st, n);

        System.out.println(solution(upBelt, downBelt, k));
    }

    static int solution(LinkedList<BeltPlate> upBelt, LinkedList<BeltPlate> downBelt, int maxDurabilityCount) {
        int stageCount = 0;
        durabilityCount = 0;

        while(durabilityCount < maxDurabilityCount) {
            stageCount++;
            turnBelt(upBelt, downBelt);
            moveRobot(upBelt);
            putNewRobotOnFirstPlate(upBelt);
        }

        return stageCount;
    }
    static LinkedList<BeltPlate> initBeltPlate(StringTokenizer st, int size) throws IOException{
        LinkedList<BeltPlate> belt = new LinkedList<>();

        for(int i = 0; i < size; i++) {
            belt.add(new BeltPlate(Integer.parseInt(st.nextToken()), false));
        }

        return belt;
    }
    static void turnBelt(LinkedList<BeltPlate> upPlate, LinkedList<BeltPlate> downPlate) {
        BeltPlate outPlate = upPlate.removeLast();
        outPlate.isRobotOn = false;
        downPlate.addFirst(outPlate);

        BeltPlate inPlate = downPlate.removeLast();
        upPlate.addFirst(inPlate);

        BeltPlate lastPlate = upPlate.getLast();
        lastPlate.isRobotOn = false;
    }
    static void moveRobot(LinkedList<BeltPlate> upPlate) {
        Iterator<BeltPlate> iterator = upPlate.descendingIterator();

        BeltPlate current = null;
        while(iterator.hasNext()) {
            BeltPlate previous = iterator.next();
            if(isOkayToMoveRobot(previous,current)){
                putRobot(current);
                previous.isRobotOn = false;
            }
            current = previous;
        }
    }
    static boolean isOkayToMoveRobot(BeltPlate before,BeltPlate after){
        return before.isRobotOn && (after != null && after.durability > 0 && !after.isRobotOn);
    }
    static void putNewRobotOnFirstPlate(LinkedList<BeltPlate> upPlate){
        BeltPlate firstPlate = upPlate.getFirst();
        if(firstPlate.durability > 0){
            putRobot(firstPlate);
        }
    }
    static void putRobot(BeltPlate plate){
        plate.durability--;
        if(plate.durability == 0){
            durabilityCount++;
        }
        plate.isRobotOn = true;
    }

    static class BeltPlate{
        int durability;
        boolean isRobotOn;

        public BeltPlate(int d, boolean robot) {
            this.durability = d;
            this.isRobotOn = robot;

        }
    }
}