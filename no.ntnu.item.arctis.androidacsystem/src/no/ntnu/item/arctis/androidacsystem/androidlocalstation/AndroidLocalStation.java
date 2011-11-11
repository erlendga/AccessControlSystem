package no.ntnu.item.arctis.androidacsystem.androidlocalstation;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import no.ntnu.item.arctis.library.objects.login.Credentials;
import no.ntnu.item.arctis.runtime.Block;
import android.app.Activity;

public class AndroidLocalStation extends Block {

	public no.ntnu.item.arctis.LoginUIPanelActivity activity;

	public Activity getActivity() {
		return activity;
	}

	public String getUsername(Credentials credentials) {
		String username = credentials.getUserName();
		return username;
	}

	public String setDenyMessage() {
		return "Access denied";
	}

	public String getCredentials(Credentials credentials) {
		String username = credentials.getUserName();
		String password = credentials.getPassword();
		
		String pwdHash = getMD5(password);
		return username + ":" + pwdHash;
	}
	
	private static String getMD5(String input) {	
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md .digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest );
			String hashtext = number.toString();
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
		
	}
}
