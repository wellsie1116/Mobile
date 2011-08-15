package edu.rosehulman.android.directory.beta;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;


public class RegisterActivity extends Activity {
    
	private static String TAG = "RegisterActivity";
	
    private static String PREFS_FILE = "PREFS_BETA";
    private static String PREF_HAS_RUN = "HAS_RUN";
    
    private TextView txtEmail;
    private TextView txtName;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        
        txtEmail = (TextView)findViewById(R.id.txtEmail);
        txtName = (TextView)findViewById(R.id.txtName);
        
        findViewById(R.id.btnBack).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				btnBack_onClick();
			}
        });
        
        findViewById(R.id.btnRegister).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				btnRegister_onClick();
			}
        });
    }
    
    private void btnBack_onClick() {
    	setResult(Activity.RESULT_CANCELED);
    	finish();
    }
    
    private void btnRegister_onClick() {
    	
    	//get our registration data
    	String email = txtEmail.getText().toString();
    	String name = txtName.getText().toString();
    	
    	//make sure required fields are populated
    	if ("".equals(email) || 
    			!Pattern.compile(".*@.*\\..*").matcher(email).matches()) {
    		Toast.makeText(this, "A valid email address is required", 1000).show();
    		return;
    	}
    	
    	//start the registration process
    	new RegisterTask(this, email, name).execute();    	
    }

    private class RegisterTask extends AsyncTask<Void, Void, Boolean> {
    	
    	private Context m_context;
    	private ProgressDialog m_status;
    	
    	private String m_email;
    	private String m_name;
    	
    	public RegisterTask(Context context, String email, String name) {
    		m_context = context;
    		m_email = email;
    		m_name = name;
    	}

    	@Override
    	protected void onPreExecute() {
    		String title = getString(R.string.title_registering);
    		String message = getString(R.string.details_registering);
        	m_status = ProgressDialog.show(m_context, title, message, false);
    	}
    	
		@Override
		protected Boolean doInBackground(Void... arg0) {
			
			long ticks = System.currentTimeMillis();
			
			Boolean res = register(m_email, m_name);
			
			if (res) {
				try {
					//take at least 2 seconds to execute
					Thread.sleep(2000 - (System.currentTimeMillis() - ticks));
				} catch (InterruptedException e) { }
			}
			
			return res;
		}
		
		@Override
    	protected void onPostExecute(Boolean result) {
    		m_status.dismiss();
    		
    		if (result) {
    			//mark that we are registered
    			SharedPreferences prefs = getSharedPreferences(PREFS_FILE, MODE_WORLD_READABLE);
    			Editor edit = prefs.edit();
    	        edit.putBoolean(PREF_HAS_RUN, true);
    	        edit.commit();
    	        
    	        //jump to the startup activity
    	        startActivity(new Intent(RegisterActivity.this, StartupActivity.class));
    	        
    	        //remove ourselves from the app stack
    	        setResult(Activity.RESULT_OK);
    			finish();
    		} else {
    			Toast.makeText(m_context, "An error occurred while registering", 2000).show();
    		}
    	}
		

	    private String getDeviceIdentifier() {
	    	return Secure.getString(getContentResolver(), Secure.ANDROID_ID);
	    }
	    
	    private String getCarrier() {
	    	TelephonyManager manager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
	    	return manager.getNetworkOperatorName();
	    }
	    
	    private String getOSInfo() {
	    	String procVersion;
	    	try {
	    		 BufferedReader reader = new BufferedReader(new FileReader("/proc/version"), 256);
	    		 try {
	    			 procVersion = reader.readLine();
	    		 } finally {
	    			 reader.close();
	    		 }
	    	} catch (IOException ex) {
	    		procVersion = "Unavailable";
	    	}
	    	
	    	try {
				return new JSONObject().
						put("display", Build.DISPLAY).
						put("sdk", Build.VERSION.SDK).
						put("release", Build.VERSION.RELEASE).
						put("version", procVersion).
						toString();
			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			}
	    }
	    
	    private String getModel() {
	    	try {
				return new JSONObject().
						put("manufacturer", Build.MANUFACTURER).
						put("device", Build.DEVICE).
						put("model", Build.MODEL).
						toString();
			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			}
	    }
	    
	    private String getBuild() {
	    	PackageInfo packageInfo;
	    	try {
	    		packageInfo = getPackageManager().getPackageInfo("edu.rosehulman.android.directory", 0); 
			} catch (NameNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			return String.valueOf(packageInfo.versionCode);
	    }

        private boolean register(String email, String name) {

        	try {
    	    	HttpClient http = new DefaultHttpClient();
    	    	HttpPost post = new HttpPost("http://mobile.csse.rose-hulman.edu/beta/actions.cgi");
    	    	
    	    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	    	params.add(new BasicNameValuePair("action", "register"));
    	    	params.add(new BasicNameValuePair("email", email));
    	    	if (!"".equals(name))
    	    		params.add(new BasicNameValuePair("name", name));
    	    	params.add(new BasicNameValuePair("deviceIdentifier", getDeviceIdentifier()));
    	    	params.add(new BasicNameValuePair("platform", "android"));
    	    	String carrier = getCarrier();
    	    	if (carrier != null)
    	    		params.add(new BasicNameValuePair("carrier", carrier));
    	    	params.add(new BasicNameValuePair("OSInfo", getOSInfo()));
    	    	params.add(new BasicNameValuePair("model", getModel()));
    	    	params.add(new BasicNameValuePair("buildNumber", getBuild()));
    	    	
    			post.setEntity(new UrlEncodedFormEntity(params));
    			ResponseHandler<String> handler = new BasicResponseHandler();
    			String result = http.execute(post, handler);
    			JSONObject root = new JSONObject(result);
    			
    			boolean success = root.getBoolean("success");
    			if (!success) {
    				Log.e(TAG , root.getJSONArray("errors").toString());
    				return false;
    			}
    			
    			//root.getBoolean("newUser");
    			//root.getBoolean("newDevice");

    			//save our auth token
    			Resources res = getResources();
    			
    			SharedPreferences prefs = getSharedPreferences(res.getString(R.string.prefs_main), MODE_PRIVATE);
    			Editor edit = prefs.edit();
    	        edit.putString(res.getString(R.string.pref_auth_token), root.getString("authToken"));
    	        edit.commit();	        
    	        
    		} catch (ClientProtocolException e) {
    			e.printStackTrace();
    			return false;
    		} catch (IOException e) {
    			e.printStackTrace();
    			return false;
    		} catch (JSONException e) {
    			e.printStackTrace();
    			return false;
    		}
        	
        	return true;
        }
    }
}
