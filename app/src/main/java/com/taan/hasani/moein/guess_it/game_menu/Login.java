package com.taan.hasani.moein.guess_it.game_menu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.taan.hasani.moein.guess_it.appcontroller.AppController;
import com.taan.hasani.moein.guess_it.helpingclasses.Player;
import com.taan.hasani.moein.volley.R;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;

public class Login extends AppCompatActivity {

    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login_bt = (Button) findViewById(R.id.login);
        final EditText username_editext = (EditText) findViewById(R.id.username);
        final EditText password_editext = (EditText) findViewById(R.id.password);
        player = new Player(this);

        login_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String username=username_editext.getText().toString();
                String password=password_editext.getText().toString();

                player.login(username, password);

            }
        });

    }
}
