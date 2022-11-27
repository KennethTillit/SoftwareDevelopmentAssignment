package primeNumbers;

import java.io.File;
import java.io.IOException;

public class Runtime {
	public static void main(String[] args) throws IOException {
		String filepath1 = "Downloads\\Server.jar";
		String filepath2 = "Downloads\\Client.jar";
		
		String javaHome = System.getProperty("java.home");
	    String javaBin = javaHome +
	            File.separator + "bin" +
	            File.separator + "java";
		
		ProcessBuilder pb = new ProcessBuilder(javaBin, "-jar", filepath1);
		Process p = pb.start();
		
		pb = new ProcessBuilder(javaBin, "-jar", filepath2);
		p = pb.start();
	}
}
