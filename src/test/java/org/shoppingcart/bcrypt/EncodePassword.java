package org.shoppingcart.bcrypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodePassword {

	public static void main(String[] args) {
		String password="123456";
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String encode = encoder.encode(password);
		System.out.println("Encoded: "+encode);
	}
}
