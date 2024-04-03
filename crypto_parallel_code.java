package Sem_7;

import java.util.HashMap;
import java.util.Map;
class my_thread_class implements Runnable{
	private Character start;
	private Character end;
	Map<Character, Integer> lookuptable;
	Map< Integer,Character> lookuptable_for_letters;
	String plaintext, ciphertext;
	public my_thread_class(Character start, Character end,Map<Character, Integer> lookuptable, Map< Integer,Character> lookuptable_for_letters, String plaintext, String ciphertext) {
		this.start = start;
		this.end = end;
		this.lookuptable = lookuptable;
		this.lookuptable_for_letters = lookuptable_for_letters;
		this.plaintext = plaintext;
		this.ciphertext = ciphertext;
	}
	public String decrypt(String ciphertext, String key) {
		int key_length = key.length();
		String plaintext = "";
		int plainvalue;
		for (int i = 0; i < ciphertext.length();i++) {
			plainvalue = (lookuptable.get(ciphertext.charAt(i))-lookuptable.get(key.charAt(i%key_length))+26)%26;
			plaintext += lookuptable_for_letters.get(plainvalue);
		}
		return plaintext;
		
		
	}
	public void run() {
		String curr_key = "";
		String curr_plain;
		outerloop:
		for (Character curr = start;curr <= end;curr++) {
			for(int j = 0; j < 26; j++) {
				for(int k = 0; k < 26;k++) {
					for(int l = 0; l < 26; l++) {
						for(int m = 0; m < 26;m++) {
							
						curr_key = ""+ curr+ lookuptable_for_letters.get(j)+lookuptable_for_letters.get(k)+lookuptable_for_letters.get(l)+lookuptable_for_letters.get(m);
						curr_plain = decrypt(ciphertext,curr_key);

						if (plaintext.equals(curr_plain)) {
							System.out.println("Successfully  signed in !");
							System.out.println("plaintext is "+plaintext+"  curr text is "+curr_plain+" therefore the key is  "+curr_key);
							
							break outerloop;
							
						}
					}
				}
			}
			
		}
		}
	}
}


public class crypto_parallel_code {
	static String give_me_cipher(String plaintext, String key) {// encryption using Vigenère cipher
		int key_length = key.length();
		String ciphertext;
		Map<Character, Integer> lookuptable = new HashMap<>();
		Map< Integer,Character> lookuptable_for_letters = new HashMap<>();

		char letter = 'a';
		for (int i = 0; i < 26; i++) {
			lookuptable.put(letter,i);
			lookuptable_for_letters.put(i,letter);

			letter++;
		}

		System.out.println(lookuptable);
		ciphertext = "";
		int ciphervalue;
		for (int i = 0; i < plaintext.length();i++) {
			ciphervalue = (lookuptable.get(plaintext.charAt(i))+lookuptable.get(key.charAt(i%key_length)))%26;
			ciphertext += lookuptable_for_letters.get(ciphervalue);
		}
		return ciphertext;
		}
public static void main(String args[]) {
	Map<Character, Integer> lookuptable = new HashMap<>();
	Map< Integer,Character> lookuptable_for_letters = new HashMap<>();

	char letter = 'a';
	for (int i = 0; i < 26; i++) {
		lookuptable.put(letter,i);
		lookuptable_for_letters.put(i,letter);
		letter++;
	}
	String plaintext = "abrakadabra";
	String key = "games";
	String ciphertext = give_me_cipher(plaintext, key);
	Thread thread1 = new Thread(new my_thread_class('a','e',lookuptable, lookuptable_for_letters,plaintext, ciphertext));
	Thread thread2 = new Thread(new my_thread_class('f','j',lookuptable, lookuptable_for_letters,plaintext, ciphertext));
	Thread thread3 = new Thread(new my_thread_class('k','o',lookuptable, lookuptable_for_letters,plaintext, ciphertext));
	Thread thread4 = new Thread(new my_thread_class('p','u',lookuptable, lookuptable_for_letters,plaintext, ciphertext));
	Thread thread5 = new Thread(new my_thread_class('v','z',lookuptable, lookuptable_for_letters,plaintext, ciphertext));
	System.out.println("cipher text is "+ciphertext);
	long start = System.currentTimeMillis();

	thread1.start();
	thread2.start();
	thread3.start();
	thread4.start();
	thread5.start();
    try {
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
	long endtime = System.currentTimeMillis();
	long total = endtime- start;
	System.out.println("time taken for parallel execution is "+total);
}
}
//  zbscjaecarb
// 4 threads had 440 ms approx

//{a=0, b=1, c=2, d=3, e=4, f=5, g=6, h=7, i=8, j=9, k=10, l=11, m=12, n=13, o=14, p=15, q=16, r=17, s=18, t=19, u=20, v=21, w=22, x=23, y=24, z=25}
//cipher text is fnoclnqnhmfcezqr
//time taken for parallel execution is 698

