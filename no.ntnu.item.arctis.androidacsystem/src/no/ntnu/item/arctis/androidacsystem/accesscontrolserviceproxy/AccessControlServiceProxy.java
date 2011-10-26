package no.ntnu.item.arctis.androidacsystem.accesscontrolserviceproxy;

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
		Message message = new Message("UN_ACCESS");
		message.setPayload(username);
		return message;
	}

}
