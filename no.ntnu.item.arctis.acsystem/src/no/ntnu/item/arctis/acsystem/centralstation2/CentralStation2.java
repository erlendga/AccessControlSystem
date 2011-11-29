package no.ntnu.item.arctis.acsystem.centralstation2;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;

import no.ntnu.item.arctis.library.proxies.Message;
import no.ntnu.item.arctis.runtime.Block;

public class CentralStation2 extends Block {

	private Hashtable hTable = new Hashtable();
	
	private void setHTable(Hashtable hTable){
		this.hTable = hTable;
	}
	
	public CentralStation2(){
		Hashtable hTable = new Hashtable();
		hTable.put("erlend", "1234");
		hTable.put("oystein", "5555");
		hTable.put("jonas", "6666");
		setHTable(hTable);
	}

	public Message checkMessage(Message msg) {
		
		if(msg.getPayload() instanceof String){
			
			if (msg.getPayload().toString().contains(":")) {
				String[] userList = msg.getPayload().toString().split(":");
				String username = userList[0]; 
				String password = userList[1];
				//String paswdHash = getMD5(password);			
				
				if(hTable.containsKey(username) && getMD5(hTable.get(username).toString()).equals(password)){
					return makeMessage(msg, "pin_true");
				}
				else return makeMessage(msg, "nok");
			}
			else {
				if(hTable.containsKey(msg.getPayload())){
				return makeMessage(msg, "un_true");
				}
				else {
					return makeMessage(msg, "nok");
				}
			}
		}
		else return makeMessage(msg, "nok");
	}
	
	private Message makeMessage(Message msg, String payload){
		Message response = new Message("access"); 
		response.setReceiver(msg.getSender().getCopy());
		response.setSender(msg.getReceiver().getCopy());	
		response.setPayload(payload);
		return response;	
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
