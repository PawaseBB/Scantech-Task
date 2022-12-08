package com.jsp.En;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Scanner;

import javax.crypto.Cipher;

public class CipherDecrypt {
   public static void main(String args[]) throws Exception{
	   Scanner sc=new Scanner(System.in);
	   System.out.println("Enter Book name, Release year and Author name.");
	   String data=sc.nextLine();
	   System.out.println();
	   
	   //Creating a Signature object
      Signature sign = Signature.getInstance("SHA256withRSA");
      
      //Creating KeyPair generator object
      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
      
      //Initializing the key pair generator
      keyPairGen.initialize(2048);
      
      //Generate the pair of keys
      KeyPair pair = keyPairGen.generateKeyPair();   
      
      //Getting the public key from the key pair
      PublicKey publicKey = pair.getPublic();  

      //Creating a Cipher object
      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

      //Initializing a Cipher object
      cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	  
      //Add data to the cipher
      byte[] input = data.getBytes();	  
      cipher.update(input);
	  
      //encrypting the data
      byte[] cipherText = cipher.doFinal();	 
      System.out.println("This is Encrypted code:-");
      System.out.println(new String(cipherText, "UTF8"));
      System.out.println();
    
      //Initializing the same cipher for decryption
      cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());
      
      System.out.println("Enter Encrypted code:-");
      String encrypt=sc.nextLine();
      System.out.println();
      
      //Decrypting the text
      byte[] decipheredText = cipher.doFinal(cipherText);
      System.out.println();
      System.out.println("This is Decrypted text:-");
      System.out.println(new String(decipheredText));
   }
}