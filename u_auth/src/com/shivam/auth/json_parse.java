package com.shivam.auth;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class json_parse extends Activity {
	String response;
	String build;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        try
        {
            HttpClient client = new DefaultHttpClient();  
            String getURL = "http://10.0.2.2/ulogin/main_course.json";
	        HttpGet get = new HttpGet(getURL);
	        HttpResponse responseGet = client.execute(get);  
	        HttpEntity resEntityGet = responseGet.getEntity();  
	        
	        if (resEntityGet != null) {
				InputStream instream = resEntityGet.getContent();
				BufferedReader str = new BufferedReader(new InputStreamReader(
						instream));
	
				String ans = new String("");
				build = new String("");
				while ((ans = str.readLine()) != null) {
					build = build + ans;
					Log.d("JSON", ans);
				}
				
				JSONArray arr = new JSONArray(build);
				String arrlen = Integer.toString(arr.length());
				Log.d("Array Length", arrlen);
				JSONObject not_available = arr.getJSONObject(0);
				JSONArray ing_unAvailable = not_available.getJSONArray("unavailable");
				String [] ingr = new String[ing_unAvailable.length()];
				for(int k=0;k<ing_unAvailable.length();k++)
				{
					JSONObject obj = ing_unAvailable.getJSONObject(k);
					ingr[k] = obj.getString("ingredient");
				}
				for(int i=1;i<arr.length();i++)
				{
					JSONObject food = null;
					food = arr.getJSONObject(i);
					String name = food.getString("name");
					Log.d("name",name);
					String description = food.getString("description");
					Log.d("desc",description);
					String rating = food.getString("rating");
					Log.d("rating",rating);
					String price = food.getString("price");
					Log.d("price",price);
					String cooktime = food.getString("cooktime");
					Log.d("cooktime",cooktime);
					JSONArray ingredients = food.getJSONArray("ingredients");
					String [] ing = new String[ingredients.length()];
					for(int k=0;k<ingredients.length();k++)
					{
						JSONObject ingd = ingredients.getJSONObject(k);
						ing[k] = ingd.getString("ingredient");
					}
					
				}

			}
        } 
        catch(Exception e)
        {}
        
        
        
	}
}
