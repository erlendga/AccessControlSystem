package no.ntnu.item.arctis;

import no.ntnu.item.arctis.android.R;
import no.ntnu.item.arctis.androidacsystem.loginuipanel.LoginUIPanel;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;
import android.widget.TextView;

import com.bitreactive.library.android.core.activity.ArctisAndroidActivity;

public class LoginUIPanelActivity extends ArctisAndroidActivity {
	
	public void onCreate(Bundle savedInstanceState) {
		setTheme(android.R.style.Theme_Dialog);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(no.ntnu.item.arctis.android.R.layout.login);
		
		TextView username = (TextView) findViewById(R.id.loginDialogUsername);
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		username.setText(prefs.getString(LoginUIPanel.USERNAME_KEY, ""));
		
		super.onCreate(savedInstanceState);
	}

}
