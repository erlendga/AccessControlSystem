package no.ntnu.item.arctis.androidacsystem.accesscontrolserviceproxy;

import no.ntnu.item.arctis.library.objects.login.Credentials;
import no.ntnu.item.arctis.library.proxies.Address;
import no.ntnu.item.arctis.library.proxies.Message;
import no.ntnu.item.arctis.runtime.Block;

public class AccessControlServiceProxy extends Block {

	public String found(Address address) {
		return "Access Control Service is found.";
	}

	public String notfound() {
		return "Access Control Service is not found.";
	}

	public Message createUsernameMessage(String username) {
		Message message = new Message("un_access");
		message.setPayload(username);
		return message;
	}

	public Message createCredentialsMesssage(Credentials credentials) {
		Message message = new Message("pin_access");
		message.setPayload(credentials);
		return message;
	}

	public String extractMessage(Message message) {
		String  payload = (String)  message.getPayload();
		return payload;
//		if (message.getPayload() instanceof String){	
//			
//			if(payload.equalsIgnoreCase("un_true")) {
//				return payload;
//			} 
//			else if (payload.equalsIgnoreCase("pin_true")) {
//				return payload;
//			}
//			else {
//				return false;
//			}
//		}
//		else {
//			return (Boolean) message.getPayload();
//		}
	}
}
