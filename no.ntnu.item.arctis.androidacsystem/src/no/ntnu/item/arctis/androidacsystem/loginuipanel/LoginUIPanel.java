package no.ntnu.item.arctis.androidacsystem.loginuipanel;

import java.util.ArrayList;

import no.ntnu.item.arctis.android.R;
import no.ntnu.item.arctis.library.objects.login.Credentials;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bitreactive.library.android.core.AndroidBlock;
import com.bitreactive.library.android.core.activity.ArctisAndroidActivity;

public class LoginUIPanel extends AndroidBlock {

	public static final String USERNAME_KEY = "username";
	public static final String PASSWORD_KEY = "password";
	public no.ntnu.item.arctis.library.objects.login.Credentials credentials;
	// Instance parameter. Edit only in overview page.
	public final java.lang.String description;
	// Instance parameter. Edit only in overview page.
	public final java.lang.String title;
	private TextView descriptionField;
	private Button usernameButton;
	private ViewFlipper viewFlipper;
	private EditText username;
	private TextView comment;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private Button button6;
	private Button button7;
	private Button button8;
	private Button button9;
	private Button button0;
	private ArrayList<String> pinList = new ArrayList<String>();
	public no.ntnu.item.arctis.LoginUIPanelActivity activity;
	private ImageView imageView;
	private TextView progress;
	
	
	public void registerValidateButtonListener() {
		button1 = (Button) activity.findViewById(R.id.button1);
		button2 = (Button) activity.findViewById(R.id.button2);
		button3 = (Button) activity.findViewById(R.id.button3);
		button4 = (Button) activity.findViewById(R.id.button4);
		button5 = (Button) activity.findViewById(R.id.button5);
		button6 = (Button) activity.findViewById(R.id.button6);
		button7 = (Button) activity.findViewById(R.id.button7);
		button8 = (Button) activity.findViewById(R.id.button8);
		button9 = (Button) activity.findViewById(R.id.button9);
		button0 = (Button) activity.findViewById(R.id.button0);
		
		setPanelButtonsEnabeled(false);
		
		imageView = (ImageView) activity.findViewById(R.id.android_logo);	
		usernameButton = (Button) activity.findViewById(R.id.okButton);
		viewFlipper = (ViewFlipper) activity.findViewById(R.id.flipper);
		username = (EditText) activity.findViewById(R.id.loginDialogUsername);
		progress = (TextView) activity.findViewById(R.id.progressTextField);	
		comment = (TextView) activity.findViewById(R.id.messageField);
		credentials = new Credentials();
		
		usernameButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				storeCredentials();			
				usernameButton.setEnabled(false);
				username.setEnabled(false);
				progress.setText("Validating username...");
				viewFlipper.showPrevious();
				comment.setText("");
				sendToBlock("VALIDATE");
			}
		});
	}
	
	protected void storeCredentials() {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
		Editor edit = prefs.edit();
		edit.putString(USERNAME_KEY, username.getText().toString());
		edit.commit();
	}
	
	
	
	// Do not edit this constructor.
	public LoginUIPanel(java.lang.String description, java.lang.String title) {
	    this.description = description;
	    this.title = title;
	}

	public void displayReason(final String reason) {
		Runnable r = new Runnable() {
			public void run() {
				comment.setText(reason != null ? reason : "");
				imageView.setImageResource(R.drawable.android_focused);
				viewFlipper.showNext();
			}
		};
		getHandler().post(r);
	}
	
	private void setPanelButtonsEnabeled(boolean enabeled) {
		button1.setEnabled(enabeled);
		button2.setEnabled(enabeled);
		button3.setEnabled(enabeled);
		button4.setEnabled(enabeled);
		button5.setEnabled(enabeled);
		button6.setEnabled(enabeled);
		button7.setEnabled(enabeled);
		button8.setEnabled(enabeled);
		button9.setEnabled(enabeled);
		button0.setEnabled(enabeled);
	}

	public void registerPanelButtonListeners() {
		Runnable r = new Runnable() {
			
			public void run() {
				comment.setText("Username accepted. Enter PIN");
				viewFlipper.showNext();
				setPanelButtonsEnabeled(true);
				pinList.clear();
			
				button1.setOnClickListener(new OnClickListener() {		
					public void onClick(View v) {			
						sendToBlock("ONE");
					}
				});
				
				button2.setOnClickListener(new OnClickListener() {		
					public void onClick(View v) {
						sendToBlock("TWO");
					}
				});
				
				button3.setOnClickListener(new OnClickListener() {		
					public void onClick(View v) {
						sendToBlock("THREE");
					}
				});
				
				button4.setOnClickListener(new OnClickListener() {		
					public void onClick(View v) {
						sendToBlock("FOUR");
					}
				});
				
				button5.setOnClickListener(new OnClickListener() {		
					public void onClick(View v) {
						sendToBlock("FIVE");
					}
				});
				
				button6.setOnClickListener(new OnClickListener() {		
					public void onClick(View v) {
						sendToBlock("SIX");
					}
				});
				
				button7.setOnClickListener(new OnClickListener() {		
					public void onClick(View v) {
						sendToBlock("SEVEN");
					}
				});
				
				button8.setOnClickListener(new OnClickListener() {		
					public void onClick(View v) {
						sendToBlock("EIGHT");
					}
				});
				
				button9.setOnClickListener(new OnClickListener() {		
					public void onClick(View v) {
						sendToBlock("NINE");
					}
				});
				
				button0.setOnClickListener(new OnClickListener() {		
					public void onClick(View v) {
						sendToBlock("ZERO");
					}
				});
			}
		};
		activity.runOnUiThread(r);
	}
	
	private void buttonClickCondition() {
		Runnable r = new Runnable() {
			
			public void run() {
				comment.setText(pinList.toString());
				if (pinList.size() == 4) {
					setPanelButtonsEnabeled(false);
					sendToBlock("PASS_COMPLETE");
				}
			}
		};
		activity.runOnUiThread(r);
	}

	public void pressedOne() {
		pinList.add("1");
		buttonClickCondition();
	}

	public void pressedTwo() {
		pinList.add("2");
		buttonClickCondition();
	}

	public void pressedThree() {
		pinList.add("3");
		buttonClickCondition();
	}

	public void pressedFour() {
		pinList.add("4");
		buttonClickCondition();
	}

	public void pressedFive() {
		pinList.add("5");
		buttonClickCondition();
	}

	public void pressedSix() {
		pinList.add("6");
		buttonClickCondition();
	}

	public void pressedSeven() {
		pinList.add("7");
		buttonClickCondition();
	}

	public void pressedEight() {
		pinList.add("8");
		buttonClickCondition();
	}

	public void pressedNine() {
		pinList.add("9");
		buttonClickCondition();
	}

	public void pressedZero() {
		pinList.add("0");
		buttonClickCondition();
	}

	public Credentials setUsername() {
		credentials.setUserName(username.getText().toString());
		return credentials;
	}

	public void setPIN() {
		Runnable r = new Runnable() {		
			public void run() {
				String pin = "";
				for (String digit : pinList) {
					pin += digit;
				}
				credentials.setPIN(pin);
				progress.setText("Validating PIN...");
				viewFlipper.showPrevious();
				sendToBlock("CREDENTIALS", credentials);
			}
		};
		activity.runOnUiThread(r);
	}

	public void loggedOn() {
		Runnable r = new Runnable() {
			
			public void run() {
				comment.setText("Access accepted.");
				imageView.setImageResource(R.drawable.android_normal);
				viewFlipper.showNext();
			}
		};
		activity.runOnUiThread(r);
	}

	public void changeColorToYellow() {
		Runnable r = new Runnable() {
			
			public void run() {
				usernameButton.setEnabled(true);
				username.setEnabled(true);
				imageView.setImageResource(R.drawable.android_pressed);
			}
		};
		activity.runOnUiThread(r);
	}

	public void displayACSResponseMessage(final String message) {
		Runnable r = new Runnable() {			
			public void run() {
				viewFlipper.showNext();
				comment.setText(message);
			}
		};
		activity.runOnUiThread(r);
	}

	public void timeout() {
		Runnable r = new Runnable() {
			
			public void run() {
				setPanelButtonsEnabeled(false);
				comment.setText("Timed out.");	
				sendToBlock("TIMEOUT");	
			}
		};
		activity.runOnUiThread(r);
	}

}
