package no.ntnu.item.arctis.examples.smarthome.registerservice;

import no.ntnu.item.arctis.library.proxies.Address;
import no.ntnu.item.arctis.library.proxies.Message;
import no.ntnu.item.arctis.runtime.Block;

public class RegisterService extends Block {

	public no.ntnu.item.arctis.library.discovery.discoverservice.RegisterEntry registerEntry;

	public no.ntnu.item.arctis.library.proxies.Message createRegisterMessage() {
		Message register = new Message("register");
		
//		Address serviceAddress = ((Address)getProperty("local-address")).getCopy();
//		serviceAddress.setSessionID(serviceSessionID);
//		RegisterEntry entry = new RegisterEntry(serviceAddress, service);
		
		register.setPayload(registerEntry);
		
		return register;
	}

	public String getSignalID(Message message) {
		return message.getSignalID().toLowerCase();
	}

	public Address getRegisterAddress() {
		return (Address) getProperty("register-address");		
	}

	public void unexpectedSignal(String signalID) {
		log("Register service received a signal with the unexpected ID " + signalID + ".");
	}

	// Do not edit this constructor.
	public RegisterService() {
	}

}
