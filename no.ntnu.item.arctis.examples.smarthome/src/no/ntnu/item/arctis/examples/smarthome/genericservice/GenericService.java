package no.ntnu.item.arctis.examples.smarthome.genericservice;

import no.ntnu.item.arctis.library.discovery.discoverservice.RegisterEntry;
import no.ntnu.item.arctis.library.proxies.Address;
import no.ntnu.item.arctis.runtime.Block;

public class GenericService extends Block {

	public java.lang.String serviceSessionID;
	// Instance parameter. Edit only in overview page.
	public final java.lang.String service;
	public RegisterEntry createRegisterEntry() {
		Address serviceAddress = ((Address)getProperty("local-address")).getCopy();
		serviceAddress.setSessionID(serviceSessionID);
		RegisterEntry entry = new RegisterEntry(serviceAddress, service);
		return entry;
	}

	public void createServiceSessionID() {
		if(service==null || service.trim().length()==0) {
			log("A service description must be given. Current value is empty.");
		}
		this.serviceSessionID = sessionID + "_" + service;
	}

	// Do not edit this constructor.
	public GenericService(java.lang.String service) {
	    this.service = service;
	}

}