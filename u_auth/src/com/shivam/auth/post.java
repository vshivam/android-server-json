package com.shivam.auth;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class post extends Activity {
    /** Called when the activity is first created. */
	EditText uname;
	EditText pwd;
	Button login;
	String user_name;
	String user_pwd;
	String response;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        uname = (EditText)findViewById(R.id.EditText01);
        pwd = (EditText)findViewById(R.id.EditText02);
        login = (Button)findViewById(R.id.Button01);
        
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            user_name = uname.getText().toString();
            user_pwd = pwd.getText().toString();
            
            try
            {
            	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("uname", user_name));
				nameValuePairs.add(new BasicNameValuePair("pwd", user_pwd));
				
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost("http://10.0.2.2/ulogin/login.php");
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse httpResponse = httpclient.execute(httppost);
		        HttpEntity resEntityGet = httpResponse.getEntity();  

		        if (resEntityGet != null) 
		        {  
		                    //do something with the response
                    		response = EntityUtils.toString(resEntityGet);

		                    Log.i("GET RESPONSE",response);
		                    Log.d("response", response);
		        }
		        if(response.compareTo("1")==0)
		        	Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_LONG).show();
		        else
		        	Toast.makeText(getApplicationContext(), "Invalid User ID or Password", Toast.LENGTH_LONG).show();

	            } 
            catch(Exception e)
            {
            	
            }
         }  
       });
    }
 }

    
