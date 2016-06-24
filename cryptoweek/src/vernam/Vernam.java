package vernam;

public class Vernam {
	public static void main(String[] args) {
		String input = "HELLO", output = "?(/'#", key = "wmckl", mode = "decode", result = "";
		char[] alphabet = new char[26];
		for (int x = 0; x < 26; x++) {
			alphabet[x] = (char) ((x % 26) + 97);
		}
		if (mode.equals("encode")) {
			for (int i = 0; i < input.length(); i++) {
				char x = (char) (input.charAt(i) ^ key.charAt(i));
				result += x;
			}
		} else if (mode.equals("decode")) {
			for (int i = 0; i < output.length(); i++) {
				char x = (char) (output.charAt(i) ^ key.charAt(i));
				result += x;
			}
		}
		System.out.println(result);
	}
}