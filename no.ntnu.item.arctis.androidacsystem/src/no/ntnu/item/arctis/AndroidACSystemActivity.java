package no.ntnu.item.arctis;

import com.bitreactive.library.android.core.activity.ArctisAndroidActivity;

import no.ntnu.item.arctis.android.R;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

public class AndroidACSystemActivity extends ArctisAndroidActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}