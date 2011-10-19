package no.ntnu.item.arctis.androidacsystem.androidlocalstation;

import android.app.Activity;
import no.ntnu.item.arctis.library.proxies.Address;
import no.ntnu.item.arctis.runtime.Block;

public class AndroidLocalStation extends Block {

	public no.ntnu.item.arctis.AndroidACSystemActivity activity;

	public Address getLocalAddress() {
		Address local = new Address();
		local.setPort("52000");
		local.setIP("localhost");
		setProperty("local-address", local);
		return local;
	}

	public void setRegistryAddress() {
		Address registry = new Address();
		registry.setPort("52000");
		registry.setIP("localhost");
		registry.setSessionID("registry");
		setProperty("register-address", registry);
	}

	public Activity getActivity() {
		return activity;
	}

}
