import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int playerCount = Integer.parseInt(st.nextToken());
		int maxPeople = Integer.parseInt(st.nextToken());
		
		List<Room> roomList = new ArrayList<>();
		Person[] personArray = makeArray(br, playerCount);
		
		out:for(Person person : personArray) {
			for(Room room : roomList) {
				if(room.set.size() < maxPeople && Math.abs(room.startLevel - person.level) <= 10) {
					room.set.add(person);
					continue out;
				}
			}
			roomList.add(new Room(person));
		}
		
		for(Room room : roomList) {
			bw.write(room.set.size() == maxPeople ? "Started!\n" : "Waiting!\n");
			for(Person p : room.set) {
				bw.write(p.level + " " + p.name + "\n");
			}
		}
		bw.flush();
	}
	static Person[] makeArray(BufferedReader br, int size) throws IOException {
		Person[] array = new Person[size];
		for(int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int level = Integer.parseInt(st.nextToken());
			String name = st.nextToken().trim();
			
			array[i] = new Person(level, name);
		}
		
		return array;
	}
	static class Person implements Comparable<Person>{
		int level;
		String name;
		
		public Person(int level, String name) {
			this.level = level;
			this.name = name;
		}

		@Override
		public int compareTo(Person o) {
			return this.name.compareTo(o.name);
		}
	}
	static class Room{
		int startLevel;
		Set<Person> set;
		
		public Room(Person person) {
			this.startLevel = person.level;
			set = new TreeSet<>(Arrays.asList(person));
		}
	}
}