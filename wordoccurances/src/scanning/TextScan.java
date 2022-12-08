package scanning;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class TextScan {
	public static void main(String[] args) throws Exception {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/wordoccurrences";
			String username = "Kenneth";
			String password = "KTI060800";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
		
		FileReader test = new FileReader("test.txt");
		Scanner scnr = new Scanner(test);
		
		int id = 1;
		String query = "insert into word (wordid, word)"
				+ "values (?, ?)";
		
		while (scnr.hasNext()) {
			PreparedStatement prepared = conn.prepareStatement(query);
			prepared.setInt(1, id);
			prepared.setString(2, scnr.next());
			prepared.execute();
			id++;
		}
		
		Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet srs = st.executeQuery("SELECT * FROM word");
		
		srs.last();
		int end = srs.getInt("wordid");
		id = end + 1;
		
		PreparedStatement prepared = conn.prepareStatement(query);
		prepared.setInt(1, id);
		prepared.setString(2, "moon");
		prepared.execute();
		id += 1;
		prepared.setInt(1, id);
		prepared.setString(2, "moon");
		prepared.execute();
		id += 1;
		prepared.setInt(1, id);
		prepared.setString(2, "moon");
		prepared.execute();
		id += 1;
		prepared.setInt(1, id);
		prepared.setString(2, "moon");
		prepared.execute();
		id += 1;
		prepared.setInt(1, id);
		prepared.setString(2, "moon");
		prepared.execute();
		id += 1;
		prepared.setInt(1, id);
		prepared.setString(2, "moon");
		prepared.execute();
		id += 1;
		prepared.setInt(1, id);
		prepared.setString(2, "dinosaur");
		prepared.execute();
		id += 1;
		prepared.setInt(1, id);
		prepared.setString(2, "dinosaur");
		prepared.execute();
		id += 1;
		prepared.setInt(1, id);
		prepared.setString(2, "dinosaur");
		prepared.execute();
		id += 1;
		prepared.setInt(1, id);
		prepared.setString(2, "dinosaur");
		prepared.execute();
		id += 1;
		prepared.setInt(1, id);
		prepared.setString(2, "dinosaur");
		prepared.execute();
		id += 1;
		prepared.setInt(1, id);
		prepared.setString(2, "dinosaur");
		prepared.execute();
		id += 1;
		
		
		count();
		
		scnr.close();
		conn.close();
	}
	
	static int rank = 1;
	
	public static void count() throws Exception {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/wordoccurrences";
		String username = "Kenneth";
		String password = "KTI060800";
		Class.forName(driver);
		
		Connection conn = DriverManager.getConnection(url, username, password);
		System.out.println("Connected");
		
		Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet srs = st.executeQuery("SELECT * FROM word");
		srs.last();
		int end = srs.getInt("wordid");
		srs.first();
		int current = 0;
		
		ArrayList<String> words = new ArrayList<String>();
		while (current < end) {
			words.add(srs.getString("word"));
			current++;
			srs.next();
		}
		conn.close();
		count(words);
	}
	
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
}
