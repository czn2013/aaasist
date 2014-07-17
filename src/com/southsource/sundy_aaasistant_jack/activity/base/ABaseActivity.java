package com.southsource.sundy_aaasistant_jack.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

public class ABaseActivity extends Activity {

	protected void toastMsg(CharSequence text){
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}
	
	protected void openActivity( Class<?>  pActivity) {
		Intent _Intent = new  Intent(this,pActivity);
		startActivity(_Intent);
	}
	
	protected void openActivityForResult( Class<?>  pActivity, int requestCode) {
		Intent _Intent = new  Intent(this,pActivity);
		startActivityForResult(_Intent, requestCode);
	}
}
