package no.ntnu.item.arctis.acsystem.centralunit.component;

import no.ntnu.item.arctis.library.proxies.Address;
import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block{

	public void setRegistryAddress() {
		Address registry = new Address();
		registry.setPort("4439");
		registry.setIP("129.241.208.204");
		registry.setSessionID("registry");
		setProperty("register-address", registry);
	}

	public Address getLocalAddress() {
		Address local = new Address();
		local.setPort("52000");
		local.setIP("localhost");
		setProperty("local-address", local);
		return local;
	}
}