package no.ntnu.item.arctis.androidacsystem.loginuipanel;

import java.util.ArrayList;

import no.ntnu.item.arctis.android.R;
import no.ntnu.item.arctis.library.objects.login.Credentials;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bitreactive.library.android.core.AndroidBlock;

public class LoginUIPanel extends AndroidBlock {

	public static final String USERNAME_KEY = "username";
	public static final String PASSWORD_KEY = "password";
	public no.ntnu.item.arctis.library.objects.login.Credentials credentials;
	// Instance parameter. Edit only in overview page.
	public final java.lang.String description;
	// Instance parameter. Edit only in overview page.
	public final java.lang.String title;
	private TextView descriptionField;
	private Button validateButton;
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
	private ArrayList<String> password = new ArrayList<String>();
	public no.ntnu.item.arctis.LoginUIPanelActivity activity;
	
	
	public void registerValidateButtonListener() {
//		if(description != null && description.length() > 0){
//			descriptionField = (TextView) activity.findViewById(R.id.descriptionField);
//			descriptionField.setText(description);
//			descriptionField.setVisibility(TextView.VISIBLE);
//		}
		
		TextView titleView = (TextView) activity.findViewById(R.id.titleField);
		titleView.setText(title);
		
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
		
		setAllButtonsEnabeled(false);
	
		validateButton = (Button) activity.findViewById(R.id.okButton);
		viewFlipper = (ViewFlipper) activity.findViewById(R.id.flipper);
		username = (EditText) activity.findViewById(R.id.loginDialogUsername);
		
		comment = (TextView) activity.findViewById(R.id.messageField);
		
		credentials = new Credentials();
		
		validateButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				storeCredentials();
				
				validateButton.setEnabled(false);
				username.setEnabled(false);
				//viewFlipper.showNext();
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
				validateButton.setEnabled(true);
				username.setEnabled(true);
				comment.setText(reason != null ? reason : "");
//				viewFlipper.showPrevious();
			}
		};
		getHandler().post(r);
	}

	public Credentials getCredentials() {
			return new Credentials(username.getText().toString(), "");
	}
	
	private void setAllButtonsEnabeled(boolean enabeled) {
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
				setAllButtonsEnabeled(true);
				password.clear();
			
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
				comment.setText(password.toString());
				if (password.size() == 4) {
					setAllButtonsEnabeled(false);
					sendToBlock("PASS_COMPLETE");
				}
			}
		};
		activity.runOnUiThread(r);
	}

	public void pressedOne() {
		password.add("1");
		buttonClickCondition();
	}

	public void pressedTwo() {
		password.add("2");
		buttonClickCondition();
	}

	public void pressedThree() {
		password.add("3");
		buttonClickCondition();
	}

	public void pressedFour() {
		password.add("4");
		buttonClickCondition();
	}

	public void pressedFive() {
		password.add("5");
		buttonClickCondition();
	}

	public void pressedSix() {
		password.add("6");
		buttonClickCondition();
	}

	public void pressedSeven() {
		password.add("7");
		buttonClickCondition();
	}

	public void pressedEight() {
		password.add("8");
		buttonClickCondition();
	}

	public void pressedNine() {
		password.add("9");
		buttonClickCondition();
	}

	public void pressedZero() {
		password.add("0");
		buttonClickCondition();
	}

	public void checkPasswordLength() {
		Runnable r = new Runnable() {
			
			public void run() {
				if (password.size() == 4) {
					sendToBlock("LOGIN");
				}
				else {
					setAllButtonsEnabeled(false);
					validateButton.setEnabled(true);
					username.setEnabled(true);
					sendToBlock("TIMEOUT");
					comment.setText("Timed out.");
				}
			}
		};
		activity.runOnUiThread(r);
	}

	public Credentials validateUsername() {
		credentials.setUserName(username.getText().toString());
		return credentials;
	}

	public Credentials validatePassword() {
		String pword = "";
		for (String element : password) {
			pword += element;
		}
		credentials.setPassword(pword);
		return credentials;
	}

}
