package com.taan.hasani.moein.volley.game_menu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.taan.hasani.moein.volley.R;
import com.taan.hasani.moein.volley.appcontroller.AppController;
import com.taan.hasani.moein.volley.game.choosing_theGame;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Loading extends AppCompatActivity {

    private static int TIME_OUT = 1000;
    private final String MY_PREFS_NAME="username and password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        final String MY_PREFS_NAME="username and password";

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String username = prefs.getString("usename", null);
                String password = prefs.getString("password", null);
               // String name=prefs.getString("name",null);
                if (username != null || password!=null) {

                   //  Toast.makeText(getApplicationContext(),"Welcome "+name,Toast.LENGTH_LONG).show();
                    sending_info(username,password);

                }else{
                    Intent intent=new Intent(Loading.this,Entrance_signup_login.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, TIME_OUT);


        //check for login

    }

    public void sending_info(String username_, String password_){
        final HashMap<String, String> info = new HashMap<>();
        String url = "http://online6732.tk/guessIt.php";

        info.put("action","login");
        info.put("username",username_);
        info.put("password",password_);

        JSONObject jsonObject=new JSONObject(info);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,
                url, jsonObject,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    if(response.getString("dataIsRight").equals("yes")){

                        String id__=response.getString("userID");//#######
                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor.putString("userID",id__);
                        editor.apply();

                        Intent i=new Intent(Loading.this, Main_menu.class);
                        startActivity(i);
                        finish();
                    }

                    else{
                        Toast.makeText(getApplicationContext(),
                                response.getString("dataIsRight"),Toast.LENGTH_LONG).show();
                        finish();
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            e.toString(),Toast.LENGTH_LONG).show();

                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

}