package com.jsp.En;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class EncryptDecrypt {
	private static final String UNICODE_FORMAT="UTF-8";
	
	public static void main(String[] args) {
		try {
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Book name, Release year and Author name.");
			String text=sc.nextLine();
			System.out.println();
			
			SecretKey key=generatekey("AES");
			Cipher chipher;
			chipher=Cipher.getInstance("AES");
			
			byte[] encryptedData=encryptString(text, key, chipher);
			String encryptedString=new String(encryptedData);
			System.out.println("This is Encrypted code for Book name, Release year and Author name");
			System.out.println(encryptedString);
			System.out.println();
			
			System.out.println("Enter encrypted code for decryption:- ");
			String code=sc.nextLine();
			System.out.println();
			
			String decrypted=decryptString(encryptedData, key, chipher);
			if(code.equals(encryptedString)) {
				System.out.println("This is your Original Data.");
				System.out.println(decrypted);
			}else {
				System.out.println("Your Entered code is Incorrect, your Data can't Decrypted.");
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
		
	}

	public static SecretKey generatekey(String encryptionType) {
		try {
			KeyGenerator keyGenerator=KeyGenerator.getInstance(encryptionType);
			SecretKey mykey= keyGenerator.generateKey();
			return mykey;
		}catch (Exception e) {
			return null;
		}
	}
	public static byte[] encryptString(String dataToEncrypt, SecretKey myKey, Cipher cipher) {
		try {
			byte[] text=dataToEncrypt.getBytes(UNICODE_FORMAT);
			cipher.init(Cipher.ENCRYPT_MODE, myKey);
			byte[] textEncrypted=cipher.doFinal(text);
			
			return textEncrypted;
		}catch(Exception e){
			return null;
		}
	}
	
	public static String decryptString(byte[] dataToDecrypt, SecretKey myKey, Cipher cipher) {
		try {
			cipher.init(Cipher.DECRYPT_MODE, myKey);
			byte[] textDecrypted=cipher.doFinal(dataToDecrypt);
			String result=new String(textDecrypted);
			
			return result;
		}catch (Exception e) {
			return null;
		}
	}
}
