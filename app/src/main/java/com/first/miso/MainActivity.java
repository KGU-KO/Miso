package com.first.miso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {

    Handler h1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //**********************************************************
        //스키장 예약하기 누를때 액션
        Button button = (Button) findViewById(R.id.button);
        //h1 = (Handler)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, choose_skiplace.class);
                startActivity(intent);
            }
        });
        //**********************************************************
        //스키장 예약 확인/취소 누를때 액션
        //**********************************************************
        //**********************************************************
        //예약을 안한경우 이버튼을 누르면 어떡하지??
        //**********************************************************
        //**********************************************************

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,check_info.class);

                startActivity(intent);
            }
        });

        //**********************************************************
        //스키장 이용요금확인 누를때 액션
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, check_use_money.class);

                startActivity(intent);
            }
        });
        //**********************************************************
        //스키장 스키장사이트이동 누를때 액션
        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, move_ski_site.class);

                startActivity(intent);
            }
        });

        //탈퇴하기 버튼 누를떄 액션
        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, withdraw.class);

                startActivity(intent);
            }
        });




    }
}
