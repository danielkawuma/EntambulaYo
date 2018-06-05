package com.daniel.entambulayo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView info;
    private Button login;
    private Button signUp;
    private int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.editName);
        Password = (EditText)findViewById(R.id.editPassword);
        login = (Button)findViewById(R.id.btnLogin);
        info = (TextView)findViewById(R.id.tvInfo);
        signUp = (Button)findViewById(R.id.btnSignUp);
	/*create sqlite database*/
        SQLiteDatabase database = openOrCreateDatabase("EntambulaYo.db",MODE_PRIVATE,null);
        database.execSQL("create table if not exists client(Name text,Password text) ");
        database.execSQL("insert into client values('Samson','ben')");
        Cursor cursor = database.rawQuery("select * from client", null);
        cursor.moveToFirst();

        String nem = cursor.getString(0);
        String password = cursor.getString(1);


        info.setText("No. of remaining attempts:3");
        info.setText(nem + "\n" + password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(),Password.getText().toString());
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    private void validate(String userName, String userPassword){

        if((userName.equals("admin")) && (userPassword.equals("1234"))){

        Intent intent = new Intent(MainActivity.this,SecondActivity.class);

        startActivity(intent);

        }
        else{
            counter--;
            info.setText("No. of remaining attempts: "+String.valueOf(counter));
            if(counter == 0){
                login.setEnabled(false);
            }

        }

    }
}
