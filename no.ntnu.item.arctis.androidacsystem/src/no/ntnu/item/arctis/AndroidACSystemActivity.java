package no.ntnu.item.arctis;

import no.ntnu.item.arctis.android.R;
import android.app.Activity;
import android.os.Bundle;

public class AndroidACSystemActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}