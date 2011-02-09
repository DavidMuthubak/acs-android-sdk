package com.cocoafish.demo;

import android.app.Application;

import android.preference.PreferenceManager;

import com.cocoafish.sdk.Cocoafish;

/**
 * This is an example of a {@link android.app.Application} class.  Ordinarily you would use
 * a class like this as a central repository for information that might be shared between multiple
 * activities.
 * 
 * In this case, we have not defined any specific work for this Application.
 * 
 * See samples/ApiDemos/tests/src/com.example.android.apis/ApiDemosApplicationTests for an example
 * of how to perform unit tests on an Application object.
 */
public class DemoApplication extends Application {
	public static final String APP_ID = "dadb53b0c84fe8da8cbfd89cabf3431d";
	public static final String FACEBOOK_APP_ID = "109836395704353";

    @Override
    public void onCreate() {
        /*
         * This populates the default values from the preferences XML file. See
         * {@link DefaultValues} for more details.
         */
        PreferenceManager.setDefaultValues(this, R.xml.default_values, false);
        
        // initialize Cocoafish
        Cocoafish.initialize(APP_ID, FACEBOOK_APP_ID, getApplicationContext());
    }

    @Override
    public void onTerminate() {
    }
    

}
