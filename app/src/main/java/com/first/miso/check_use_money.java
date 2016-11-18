package com.first.miso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class check_use_money extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_use_money);
        //*******************************************
        //A site 버튼 클릭시
        Button usemoney_button1 = (Button) findViewById(R.id.usemoney_button1);
        usemoney_button1.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                //Intent intent = new Intent(check_use_money.this, ????.class);

                //startActivity(intent);
            }
        });
        //*******************************************
        //B site 버튼 클릭시
        Button usemoney_button2 = (Button) findViewById(R.id.usemoney_button2);
        usemoney_button2.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                //Intent intent = new Intent(check_use_money.this, ????.class);

                //startActivity(intent);
            }
        });
        //*******************************************
        //C site 버튼 클릭시
        Button usemoney_button3 = (Button) findViewById(R.id.usemoney_button3);
        usemoney_button3.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                //Intent intent = new Intent(check_use_money.this, ????.class);

                //startActivity(intent);
            }
        });
        //*******************************************
        //D site 버튼 클릭시
        Button usemoney_button4 = (Button) findViewById(R.id.usemoney_button4);
        usemoney_button4.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                //Intent intent = new Intent(check_use_money.this, ????.class);

                //startActivity(intent);
            }
        });
        //*******************************************
        //Main 버튼 클릭시
        Button usemoney_main = (Button) findViewById(R.id.usemoney_main);
        usemoney_main.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent intent = new Intent(check_use_money.this, MainActivity.class);

                startActivity(intent);
            }
        });
    }
}
