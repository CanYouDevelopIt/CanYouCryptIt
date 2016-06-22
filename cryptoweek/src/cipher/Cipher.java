package cipher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;

public class Cipher implements ICipher {

	public static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	@Override
	public Object generateKey() {

		char[] chars = alphabet.toCharArray();
		int index = chars.length, randomIndex;
		char tmp;

		while (0 != index) {
			randomIndex = (int) Math.floor(Math.random() * index);
			index -= 1;
			tmp = chars[index];
			chars[index] = chars[randomIndex];
			chars[randomIndex] = tmp;
		}

		return new String(chars);
	}

	public HashMap<Character, Character> tableConversion(Object key) {
		HashMap<Character, Character> table = new HashMap<Character, Character>();
		String k = key.toString();
		for (int i = 0; i < alphabet.length(); i++) {
			table.put(alphabet.charAt(i), k.charAt(i));
		}
		return table;
	}

	@Override
	public void encode(File message, File key, File encrypted) {
		try {
			StringBuilder stringBuilder = new StringBuilder();
			String cle = "";
			BufferedReader rKey = new BufferedReader(new FileReader(key));
			while (rKey.ready()) {
				cle += rKey.readLine();
			}
			rKey.close();
			HashMap<Character, Character> table = new HashMap<Character, Character>();
			for (int i = 0; i < alphabet.length(); i++) {
				table.put(alphabet.charAt(i), cle.charAt(i));
			}

			String mess = "";
			BufferedReader rMess = new BufferedReader(new FileReader(message));
			while (rMess.ready()) {
				mess += rMess.readLine();
			}
			rMess.close();
			for (int i = 0; i < message.length(); i++) {
				stringBuilder.append(table.get(mess.charAt(i)));
			}

			FileWriter fw = new FileWriter(encrypted);
			fw.write(stringBuilder.toString());
			fw.close();
		} catch (Exception e) {

		}
	}

	@Override
	public void decode(File message, File key, File crypted) {
		try {
			StringBuilder stringBuilder = new StringBuilder();
			String cryp = "";
			BufferedReader rCrypted = new BufferedReader(new FileReader(crypted));
			while (rCrypted.ready()) {
				cryp += rCrypted.readLine();
			}
			rCrypted.close();

			String cle = "";
			BufferedReader rKey = new BufferedReader(new FileReader(key));
			while (rKey.ready()) {
				cle += rKey.readLine();
			}
			rKey.close();

			HashMap<Character, Character> table = new HashMap<Character, Character>();
			for (int i = 0; i < alphabet.length(); i++) {
				table.put(alphabet.charAt(i), cle.charAt(i));
			}

			for (int i = 0; i < cryp.length(); i++) {
				for (Entry<Character, Character> entry : table.entrySet()) {
					if (Objects.equals(cryp.charAt(i), entry.getValue())) {
						stringBuilder.append(entry.getKey());
					}
				}
			}

			FileWriter fw = new FileWriter(message);
			fw.flush();
			fw.write(stringBuilder.toString());
			fw.close();

		} catch (Exception e) {

		}
	}

	public Object readKey(File f) {

		String key = "";
		try {
			BufferedReader r = new BufferedReader(new FileReader(f));
			while (r.ready()) {
				key += r.readLine();
			}
			r.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return key;
	}

	public void writeKey(File f) {
		try {
			FileWriter fw = new FileWriter(f);
			String key = (String) generateKey();
			fw.write(key);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		Cipher cipher = new Cipher();
		File key = new File("files/key.txt");
		File message = new File("files/message.txt");
		File encrypted = new File("files/encrypted.txt");
		// Object cle = cipher.generateKey();
		// table = cipher.tableConversion(key);
		// System.out.println("Key : " + cle);
		cipher.writeKey(key);
		cipher.encode(message, key, encrypted);
		// cipher.decode(message, key, encrypted);
		// System.out.println(cipher.readKey(f));
	}

}
