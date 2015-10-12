package com.example.nezarsaleh.shareknitest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nezarsaleh.shareknitest.Arafa.Classes.GetData;


public class ChangePasswordTest extends AppCompatActivity {

    String MyID;
    String oldPass,newPass;
    EditText edit_oldPass;
    EditText edit_newPass;
    Button btn_change;
    TextView txt_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_test);
        edit_oldPass= (EditText) findViewById(R.id.edit_changepass_old);
        edit_newPass = (EditText) findViewById(R.id.edit_changepass_new);
        btn_change = (Button) findViewById(R.id.btn_Changepass_submit);
        txt_change= (TextView) findViewById(R.id.txt_change);

        Intent intent=getIntent();
        SharedPreferences myPrefs = this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
        MyID =  myPrefs.getString("account_id", null);
        Log.d("Test inside change pass",MyID);

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPass = edit_oldPass.getText().toString();
                newPass=edit_newPass.getText().toString();

                    GetData j = new GetData();
                    j.ChangePasswordForm(Integer.parseInt(MyID),oldPass,newPass,getBaseContext(),txt_change);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_password_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
