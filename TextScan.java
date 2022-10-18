package scanning;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class TextScan {
	public static void main(String[] args) throws Exception {
		ArrayList<String> words = new ArrayList<String>();
		
		FileReader test = new FileReader("test.txt");
		Scanner scnr = new Scanner(test);
		
		while (scnr.hasNext()) {
			words.add(scnr.next());
		}
		
		count(words);
		test();
		
		scnr.close();
	}
	
	static int rank = 1;
	public static void count(ArrayList<String> words) {
		String word = "";
		int count = 0;
		int maxCount = 0;
		
		for (int i = 0; i < words.size(); i++) {
			count = 1;
			
			for (int j = i + 1; j < words.size(); j++) {
				if (words.get(i).equals(words.get(j))) {
					count++;
				}
			}
			if (count > maxCount) {
				maxCount = count;
				word = words.get(i);
			}
		}
		
		System.out.println("#" + rank + ": " + word + " " + maxCount);
		if (rank < 20) {
			while (maxCount != 0) {
				words.remove(word);
				maxCount--;
			}
			rank++;
			count(words);
		}
		
	}
	
	public static void test() throws Exception {
		rank = 1;
		ArrayList<String> testing = new ArrayList<String>();
		
		FileReader test = new FileReader("test.txt");
		Scanner scnr = new Scanner(test);
		
		while (scnr.hasNext()) {
			testing.add(scnr.next());
		}
		
		System.out.println("");
		System.out.println("Double Checking Work...");
		count(testing);
	}
}
