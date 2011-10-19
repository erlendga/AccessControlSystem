package no.ntnu.item.arctis.examples.smarthome.accesscontrolserviceproxy;

import no.ntnu.item.arctis.library.proxies.Address;
import no.ntnu.item.arctis.library.proxies.Message;
import no.ntnu.item.arctis.runtime.Block;

public class AccessControlServiceProxy extends Block {

	

	public no.ntnu.item.arctis.library.proxies.Message message;

	public void found(Address addr) {
		System.out.println("access control service is found.."+addr.toString());
	}

	public void notfound() {
			System.out.println("access control service is not found..");
	}

	public Message createPinMessage(String pincode) {
		System.out.println("creating pin msg at ACS proxy");
		Message m = new Message("access");
		m.setPayload(pincode);
		return m;
	}

	public boolean extractMessage(Message msg) {
	
		System.out.println("received message from ACS at ACS proxy......"+msg);
		
		if (msg.getPayload() instanceof String){	
			String  str = (String)  msg.getPayload();
			if(str.equalsIgnoreCase("true")) {
				return true;
			} else {
				return false;
			}
		}
		else {
			return (Boolean) msg.getPayload();
		}
	}

}
