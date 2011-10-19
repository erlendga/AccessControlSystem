package no.ntnu.item.arctis.examples.smarthome.accesscontrolservice;

import no.ntnu.item.arctis.library.proxies.Message;
import no.ntnu.item.arctis.runtime.Block;

public class AccessControlService extends Block {

	public no.ntnu.item.arctis.library.proxies.Message request;
	public no.ntnu.item.arctis.library.proxies.Message message;

	public Message extractMessage(Message message) {
	
			System.out.println("received message at ACS..."+ message);			
			System.out.println("message sender/receiver: "+ message.getSender() + message.getReceiver());
		
			String pincode = (String) message.getPayload();
			
			boolean result = Integer.valueOf(pincode)==1234;
			
			Message response = new Message("access"); 
			response.setReceiver(message.getSender().getCopy());
			response.setSender(message.getReceiver().getCopy());	
			response.setPayload(result);
			return response;
	}
}
