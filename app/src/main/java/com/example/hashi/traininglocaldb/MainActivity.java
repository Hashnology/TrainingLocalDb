package com.example.hashi.traininglocaldb;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText et_name, et_email, et_password;
    private Button btn_password;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);
        context = MainActivity.this;

        /*link your widgets to java class*/
        btn_password = findViewById(R.id.btn_submit);
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);


        /*make a click event on button*/

        btn_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*get values from edit text*/
                String str_name = et_name.getText().toString();
                String str_email = et_email.getText().toString();
                String str_password = et_password.getText().toString();

                MyLocalDatBase datBase = new MyLocalDatBase(context);
                datBase.open();
                datBase.insertUsers(str_name, str_email, str_password);
                datBase.close();

            }
        });

    }
}
