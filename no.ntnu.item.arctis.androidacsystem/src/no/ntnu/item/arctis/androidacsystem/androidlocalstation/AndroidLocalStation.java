package no.ntnu.item.arctis.androidacsystem.androidlocalstation;

import no.ntnu.item.arctis.android.R;
import no.ntnu.item.arctis.library.objects.login.Credentials;
import no.ntnu.item.arctis.library.proxies.Address;
import no.ntnu.item.arctis.runtime.Block;
import android.app.Activity;

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

	public void validateUsername(Credentials credentials) {
		String username = credentials.getUserName();
		if (username.equals("erlend") || username.equals("erlendga")) {
			sendToBlock("OK", username);
		}
		else {
			sendToBlock("NOK");
		}
	}

	public String denied() {
		return "User not accepted.";
	}

	public void validate(Credentials credentials) {
		String username = credentials.getUserName();
		String password = credentials.getPassword();
		if (username.equals("erlend") && password.equals("1234") || username.equals("erlendga") && password.equals("5678")) {
			sendToBlock("PASS_OK");
		}
		else {
			sendToBlock("PASS_NOK");
		}
	}

	public String getMessage(Credentials credentials) {
		String message = "Welcome in " + credentials.getUserName();
		return message;
	}

}
