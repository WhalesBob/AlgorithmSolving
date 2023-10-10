import java.io.*;
import java.util.*;

public class Main {
	static PriorityQueue<Element> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] array = makeArray(br, n);
        System.out.println(getMaxLength(array, k));
    }
    static int[] makeArray(BufferedReader br, int size) throws IOException {
        int[] array = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < size; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        return array;
    }
    static int getMaxLength(int[] array, int maxHave){
        int left = 0, right = 0, maxLength = 1;
        Map<Integer, Element> haveMap = new HashMap<>();
        queue = new PriorityQueue<>();
        putInMap(haveMap, array[0]);

        while(left <= right && right < array.length - 1){
            if(canPlus(haveMap, array[right + 1], maxHave)){
                putInMap(haveMap, array[++right]);
                if(right - left + 1 > maxLength){
                    maxLength = right - left + 1;
                }
            }else{
                removeInMap(haveMap, array[left++]);
                putInMap(haveMap, array[++right]);
            }
        }

        return maxLength;
    }
    static void putInMap(Map<Integer, Element> haveMap, int number){
        if(haveMap.containsKey(number)){
        	Element element = haveMap.get(number);
            element.count++;
            queue.remove(element);
            queue.add(element);
        }else{
        	Element element = new Element(number);
            haveMap.put(number, element);
            queue.add(element);
        }
    }
    static void removeInMap(Map<Integer, Element> haveMap, int number){
    	Element element = haveMap.get(number);
    	element.count--;
    	queue.remove(element);
    	
    	if(element.count == 0) {
    		haveMap.remove(number);
    	}else {
    		queue.add(element);
    	}
    }
    static boolean canPlus(Map<Integer, Element> haveMap, int number, int limit){
    	if(number == queue.peek().data) {
    		return queue.peek().count + 1 <= limit;
    	}else {
    		boolean elementLimit = !haveMap.containsKey(number) || haveMap.get(number).count + 1 <= limit;
    		return queue.peek().count <= limit && elementLimit;
    	}
    }
    static class Element implements Comparable<Element>{
    	int data;
    	int count;
    	
		public Element(int data) {
			this.data = data;
			this.count = 1;
		}

		@Override
		public int hashCode() {
			return data;
		}

		@Override
		public boolean equals(Object obj) {
			return obj instanceof Element && this.data == ((Element)obj).data;
		}

		@Override
		public int compareTo(Element o) {
			return Integer.compare(o.count, this.count);
		}
    }
}