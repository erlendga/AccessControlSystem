package no.ntnu.item.arctis.androidacsystem.localstation.app;

import com.bitreactive.library.android.core.AndroidBlock;

import no.ntnu.item.arctis.library.proxies.Address;

public class App extends AndroidBlock {

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

	public void setAndroidContext() {
		System.out.println("Setting android context...");
		android.content.Context context = getContext();
		setProperty("androidContext", context);
	}
}