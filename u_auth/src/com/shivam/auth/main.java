package com.shivam.auth;

import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class main extends Activity {
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
            
            //Using HttpGet
            
            try
            {
	            HttpClient client = new DefaultHttpClient();  
	            user_name = URLEncoder.encode(user_name, "utf-8");
	            user_pwd = URLEncoder.encode(user_pwd, "utf-8");
		        String getURL = "http://10.0.2.2/ulogin/login.php"+"?uname="+user_name+"&pwd="+user_pwd;
		        HttpGet get = new HttpGet(getURL);
		        HttpResponse responseGet = client.execute(get);  
		        HttpEntity resEntityGet = responseGet.getEntity();  
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
            {}
          }  
       });
    }
 }

    
