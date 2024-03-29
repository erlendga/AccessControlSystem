package no.ntnu.item.arctis.androidacsystem.centralstation;

import java.util.Hashtable;

import no.ntnu.item.arctis.library.objects.login.Credentials;
import no.ntnu.item.arctis.library.proxies.Message;
import no.ntnu.item.arctis.runtime.Block;

public class CentralStation extends Block {
	private Hashtable hTable = new Hashtable();
	
	private void setHTable(Hashtable hTable){
		this.hTable = hTable;
	}
	
	public CentralStation(){
		Hashtable hTable = new Hashtable();
		hTable.put("erlend", "1234");
		hTable.put("oystein", "5555");
		hTable.put("jonas", "6666");
		setHTable(hTable);
	}
	
	public Message checkMessage(Message msg) {
		if(msg.getPayload() instanceof String){
			if(hTable.containsKey(msg.getPayload())){
				return makeMessage(msg, "un_true");
			}
//			if(msg.getPayload().equals("erlend")){
//				return makeMessage(msg, "un_true");
//			}
			else {
				return makeMessage(msg, "nok");
			}
		}
		else{ 
			Credentials a = new Credentials();
			a = (Credentials)msg.getPayload();
			String pwd = a.getPassword();
			String usr = a.getUserName();
			if(hTable.containsKey(usr) && hTable.get(usr).equals(pwd)){
				return makeMessage(msg, "pin_true");
			}
//			if(usr.equals("erlend") && pwd.equals("1234")){
//				return makeMessage(msg, "pin_true");	
//			}
			else return makeMessage(msg, "nok");
		}
	}
	
	private Message makeMessage(Message msg, String payload){
		Message response = new Message("access"); 
		response.setReceiver(msg.getSender().getCopy());
		response.setSender(msg.getReceiver().getCopy());	
		response.setPayload(payload);
		return response;	
	}
}
