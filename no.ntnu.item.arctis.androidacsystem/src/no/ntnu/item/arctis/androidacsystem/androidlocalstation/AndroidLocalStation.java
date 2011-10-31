package no.ntnu.item.arctis.androidacsystem.androidlocalstation;

import no.ntnu.item.arctis.library.objects.login.Credentials;
import no.ntnu.item.arctis.runtime.Block;
import android.app.Activity;

public class AndroidLocalStation extends Block {

	public no.ntnu.item.arctis.LoginUIPanelActivity activity;

	public Activity getActivity() {
		return activity;
	}

	public String getUsername(Credentials credentials) {
		String username = credentials.getUserName();
		return username;
	}

	public String setDenyMessage() {
		return "Access denied";
	}

	public String getCredentials(Credentials credentials) {
		String username = credentials.getUserName();
		String password = credentials.getPassword();
		return username + "-" + password;
	}
}
