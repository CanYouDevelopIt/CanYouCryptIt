package transposition;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.esgi.crypto.AttackTransposition;

public class Transposition {

	static String INPUT;
	static String KEY;
	static int KEY_LENGTH;

	public static void main(String[] args) {
		new AttackTransposition().execute(new File("files/encrypted.txt"), new File("files/key.txt"), new File("files/message.txt"));

	}

	private static String encode() {
		List<String> blocks = new ArrayList<String>();
		int index = 0;

		// Cut sentence by blocks
		while (index < INPUT.length()) {
			blocks.add(INPUT.substring(index, Math.min(index + KEY_LENGTH,INPUT.length())));
			index += KEY_LENGTH;
		}
		System.out.println("Message: [" + INPUT + "]");

		// Encode
		String encoded = "";
		for (String block : blocks) {
			int i = 0;
			for (char ch : block.toCharArray()) {
				int val = Character.getNumericValue(KEY.charAt(i));
				encoded += block.charAt(val);
				i++;
			}
		}
		System.out.println("Encoded: [" + encoded + "]");
		return encoded;
	}

	private static String decode(String msg) {
		List<String> blocks = new ArrayList<String>();
		int index = 0;

		// Cut sentence by blocks
		while (index < msg.length()) {
			blocks.add(msg.substring(index, Math.min(index + KEY_LENGTH,msg.length())));
			index += KEY_LENGTH;
		}
		System.out.println("Encrypted: [" + msg + "]");

		// Decode
		String decoded = "";
		String KEY_REVERSE = new StringBuilder(KEY).reverse().toString();
					//"24013";
		KEY_REVERSE = "23041";
		
		for (String block : blocks) {
			int i = 0;
			for (char ch : block.toCharArray()) {
				int val = Character.getNumericValue(KEY_REVERSE.charAt(i));
				decoded += block.charAt(val);
				i++;
			}
		}
		System.out.println("Decoded:   [" + decoded + "]");
		return decoded;
	}

}
