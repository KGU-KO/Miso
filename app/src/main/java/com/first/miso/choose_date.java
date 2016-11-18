package com.first.miso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.appindexing.Thing;
//import com.google.android.gms.common.api.GoogleApiClient;

public class choose_date extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
   // private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_date);

        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent intent = new Intent(choose_date.this, check_info.class);

                startActivity(intent);
            }
        });
        //*****************************
        // previous 버튼 누를때
        Button date_previouse_button = (Button) findViewById(R.id.date_previouse_button);
        date_previouse_button.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent intent = new Intent(choose_date.this, choose_skiplace.class); //이 빨간 부분에 결제화면 액티비티 이름 넣어주기!

                startActivity(intent);
            }
        });

        //*****************************
        // Main 버튼 누를때

        Button date_main_button = (Button) findViewById(R.id.date_main_button);
        date_main_button.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent intent = new Intent(choose_date.this, MainActivity.class); //이 빨간 부분에 결제화면 액티비티 이름 넣어주기!

                startActivity(intent);
            }
        });





    }
}
