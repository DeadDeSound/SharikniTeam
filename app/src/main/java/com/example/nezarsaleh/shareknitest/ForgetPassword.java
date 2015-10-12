package com.example.nezarsaleh.shareknitest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nezarsaleh.shareknitest.Arafa.Classes.GetData;


public class ForgetPassword extends AppCompatActivity {

    String mobileNumber,Email;
    EditText edit_number;
    EditText edit_mail;
    Button btn_submit;
    TextView txt_submit;
    String url="http://www.sharekni-web.sdg.ae/_mobfiles/CLS_MobAccount.asmx/ForgetPassword?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        edit_number = (EditText) findViewById(R.id.edit_Forgetpass_mobile);
        edit_mail = (EditText) findViewById(R.id.edit_Forgetpass_email);
        btn_submit = (Button) findViewById(R.id.btn_Forgetpass_submit);
        txt_submit = (TextView) findViewById(R.id.txt_submit);



        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobileNumber =edit_number.getText().toString();
                Email =edit_mail.getText().toString();

                if (edit_mail == null||mobileNumber.length()<= 9) {
                    Toast.makeText(getBaseContext(), "Check ur Username and Password", Toast.LENGTH_SHORT).show();
                }else {
                    url = url + "mobile=" + mobileNumber + "&email=" + Email;
                    Log.d("URL Login : ", url);
                    GetData j = new GetData();
                    j.ForgetPasswordForm(mobileNumber,Email,getBaseContext());
                }


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forget_password, menu);
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
