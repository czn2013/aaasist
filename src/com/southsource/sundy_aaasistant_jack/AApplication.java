package com.southsource.sundy_aaasistant_jack;

import android.app.Application;

import com.southsource.sundy_aaasistant_jack.sqlite.base.BaseSqlite;

public class AApplication extends Application {

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		BaseSqlite.closeDatabase();
	}

}
