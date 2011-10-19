package no.ntnu.item.arctis.examples.smarthome.deregisterservice;

import no.ntnu.item.arctis.library.proxies.Address;
import no.ntnu.item.arctis.library.proxies.Message;
import no.ntnu.item.arctis.runtime.Block;

public class DeregisterService extends Block {

	public java.lang.String serviceSessionID;
	public no.ntnu.item.arctis.library.discovery.discoverservice.RegisterEntry registerEntry;

	public no.ntnu.item.arctis.library.proxies.Message createRegisterMessage() {
		Message register = new Message("deregister");
		register.setPayload(registerEntry);
		return register;
	}

	public no.ntnu.item.arctis.library.proxies.Address getRegisterAddress() {
		return (Address) getProperty("register-address");
	}

	public void unexpectedSignal(String signalID) {
		log("Register service received a signal with the unexpected ID " + signalID + ".");
	}

	public java.lang.String getSignalID(no.ntnu.item.arctis.library.proxies.Message message) {
		return message.getSignalID();
	}

	// Do not edit this constructor.
	public DeregisterService() {
	}
}