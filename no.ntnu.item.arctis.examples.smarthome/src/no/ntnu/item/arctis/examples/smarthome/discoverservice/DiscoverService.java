package no.ntnu.item.arctis.examples.smarthome.discoverservice;

import no.ntnu.item.arctis.library.proxies.Address;
import no.ntnu.item.arctis.library.proxies.Message;
import no.ntnu.item.arctis.runtime.Block;

public class DiscoverService extends Block {

	public no.ntnu.item.arctis.library.proxies.Message message;
	// Instance parameter. Edit only in overview page.
	public final java.lang.String service;

	public no.ntnu.item.arctis.library.proxies.Message createDiscoverMessage() {
		Message register = new Message("discover");
		register.setPayload(service);
		return register;
	}

	public String getSignalID(Message message) {
		return message.getSignalID().toLowerCase();
	}

	public Address getRegisterAddress() {
		return ((Address) getProperty("register-address")).getCopy();
	}

	// Do not edit this constructor.
	public DiscoverService(java.lang.String service) {
	    this.service = service;
	}

	public Address extractAddress(Message message) {
		return ((Address) message.getPayload()).getCopy();
	}

	public void logTimeout() {
		log("Could not discover service for " + service + ". Timeout during discovery.");
	}

}
