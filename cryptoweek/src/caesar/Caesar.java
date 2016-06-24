package caesar;

import java.io.FileReader;

public class Caesar {

	public static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static void main(String[] args) {
		String message = "TESTCAESAR";
		int decalage = 3;
		String output = "WHVWFDHVDU";
		String mode = "decode";
		String result = "";

		if(mode.equals("encode")) {
			for(int i = 0; i < message.length(); i ++) {
				int index = (alphabet.indexOf(message.charAt(i) + decalage) % 26);
				result += alphabet.charAt(index);
			}
		} else if(mode.equals("decode")) {
			for(int i = 0; i < message.length(); i ++) {
				int index = (alphabet.indexOf(output.charAt(i) - decalage) % 26);
				result += alphabet.charAt(index);
			}
		}
		System.out.println(result);
	}
}