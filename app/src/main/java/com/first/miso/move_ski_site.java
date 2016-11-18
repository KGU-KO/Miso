package com.first.miso;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class move_ski_site extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.move_ski_site);

        //**************************************************
        // A 스키장 웹사이트로 이동
        Button move_button1 = (Button) findViewById(R.id.move_button1);
        move_button1.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://naver.com")));
            }
        });

        //**************************************************
        // B 스키장 웹사이트로 이동
        Button move_button2 = (Button) findViewById(R.id.move_button2);
        move_button2.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://naver.com")));
            }
        });

        //**************************************************
        // C 스키장 웹사이트로 이동
        Button move_button3 = (Button) findViewById(R.id.move_button3);
        move_button3.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://naver.com")));
            }
        });

        //**************************************************
        // D 스키장 웹사이트로 이동
        Button move_button4 = (Button) findViewById(R.id.move_button4);
        move_button4.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://naver.com")));
            }
        });

        //**************************************************
        // undo 웹사이트로 이동
        Button move_undo = (Button) findViewById(R.id.move_undo);
        move_undo.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent intent = new Intent(move_ski_site.this, MainActivity.class); //이 빨간 부분에 결제화면 액티비티 이름 넣어주기!

                startActivity(intent);
            }
        });


    }
}
