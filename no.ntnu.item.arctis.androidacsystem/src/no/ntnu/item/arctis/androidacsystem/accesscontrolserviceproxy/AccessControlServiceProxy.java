package no.ntnu.item.arctis.androidacsystem.accesscontrolserviceproxy;

import no.ntnu.item.arctis.library.proxies.Address;
import no.ntnu.item.arctis.library.proxies.Message;
import no.ntnu.item.arctis.runtime.Block;

public class AccessControlServiceProxy extends Block {

	public String found(Address address) {
		return "Access Control Service was discovered.";
	}

	public String notfound() {
		return "Access Control Service was not discovered.";
	}

	public Message createUsernameMessage(String username) {
		Message message = new Message("un_access");
		message.setPayload(username);
		return message;
	}

	public Message createCredentialsMesssage(String credentials) {
		Message message = new Message("pin_access");
		message.setPayload(credentials);
		return message;
	}

	public String extractMessage(Message message) {
		String  payload = (String)  message.getPayload();
		return payload;
	}
}