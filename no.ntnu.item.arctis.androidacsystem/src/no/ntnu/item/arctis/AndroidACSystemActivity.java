package no.ntnu.item.arctis;

import no.ntnu.item.arctis.android.R;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import com.bitreactive.library.android.core.activity.ArctisAndroidActivity;

public class AndroidACSystemActivity extends ArctisAndroidActivity {
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}