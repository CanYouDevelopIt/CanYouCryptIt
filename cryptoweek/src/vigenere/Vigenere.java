package vigenere;

public class Vigenere {

	public static void main(String[] args) {

		String input = "jadoreecouterlaradiotoutelajournee";
		input = input.toUpperCase();
		String key = "MUSIQUE";
		String mode = "encode";

		String output = "";

		// create alphabet table
		char matrix[][] = new char[26][26];

		for (int x = 0; x < 26; x++) {
			for (int y = 0; y < 26; y++) {
				matrix[x][y] = (char) (((x + y) % 26) + 65);
			}
		}

		if ("decode".equals(mode)) {
			for (int i = 0; i < input.length(); i++) {
				for (int x = 0; x < 26; x++) {
					if (matrix[x][key.charAt(i % key.length()) - 65] == input.charAt(i)) {
						output += (char) (x + 65);
						break;
					}
				}
			}
		} else if ("encode".equals(mode)) {
			for (int i = 0; i < input.length(); i++) {
				output += matrix[input.charAt(i) - 65][key.charAt(i % key.length()) - 65];
			}
		}
		System.out.println("MODE: " + mode);
		System.out.println("KEY: " + key);
		System.out.println("INPUT: " + input);
		System.out.println();
		System.out.print("OUTPUT: " + output);
		System.exit(0);
	}

}
