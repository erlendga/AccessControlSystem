package no.ntnu.item.arctis.acsystem.centralstation2;

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
			if(hTable.containsKey(msg.getPayload())){
				return makeMessage(msg, "un_true");
			}
			else {
				return makeMessage(msg, "nok");
			}
		}
		else{ 
			String[] userList = msg.getPayload().toString().split("-");
			String username = userList[0]; 
			String password = userList[1];
			
			if(hTable.containsKey(username) && hTable.get(username).equals(password)){
				return makeMessage(msg, "pin_true");
			}
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
