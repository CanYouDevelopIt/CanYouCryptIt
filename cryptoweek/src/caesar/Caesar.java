package caesar;

import java.io.FileReader;

public class Caesar {
	
	public static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static void main(String[] args) {
		String message = "TESTCAESAR";
		String result = "";
		
		for(int i = 0; i < message.length(); i ++) {
			int index = (alphabet.indexOf(message.charAt(i) + 3) % 26);
			result += alphabet.charAt(index);
		}
		System.out.println(result);
	}
}