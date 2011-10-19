package no.ntnu.item.arctis.acsystem.androidlocalstation;

import com.bitreactive.library.android.core.activity.Activity;

import no.ntnu.item.arctis.library.proxies.Address;
import no.ntnu.item.arctis.runtime.Block;

public class AndroidLocalStation extends Block {

	public void setRegistryAddress() {
		Address registry = new Address();
		registry.setPort("52000");
		registry.setIP("localhost");
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

	public Activity getActivity() {
		
	}

}
