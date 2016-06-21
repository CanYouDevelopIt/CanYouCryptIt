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

	public static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	public static HashMap<Character, Character> table;

	@Override
	public Object generateKey(){

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

	public HashMap<Character, Character> tableConversion(Object key){
		HashMap<Character,Character> table = new HashMap<Character, Character>();
		String k = key.toString();
		for(int i=0 ; i < alphabet.length() ; i++){
			table.put(alphabet.charAt(i), k.charAt(i));
		}
		return table;
	}

	@Override
	public String encode(String message, Object key) {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0 ; i < message.length(); i++){
			stringBuilder.append(table.get(message.charAt(i)));
		}

		return stringBuilder.toString();
	}

	@Override
	public String decode(String crypted, Object key) {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0 ; i < crypted.length(); i++){
			for (Entry<Character, Character> entry : table.entrySet()) {
		        if (Objects.equals(crypted.charAt(i), entry.getValue())) {
		            stringBuilder.append(entry.getKey());
		        }
		    }
		}

		return stringBuilder.toString();
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
		File f = new File("files/cipher.txt");
		Object key = cipher.generateKey();
		table = cipher.tableConversion(key);
		System.out.println("Key : " + key);
		String encode = cipher.encode("test", key);
		System.out.println("Encode : " + encode);
		String decode = cipher.decode(encode, key.toString());
		System.out.println("Decode : " + decode);
		cipher.writeKey(f);
		System.out.println(cipher.readKey(f));
	}

}
