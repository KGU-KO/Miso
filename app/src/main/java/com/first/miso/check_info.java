package com.first.miso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class check_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_info);

        //결제하기 버튼 누를때
        Button paybutton = (Button) findViewById(R.id.paybutton);
        paybutton.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                //Intent intent = new Intent(check_info.this, check.class); //이 빨간 부분에 결제화면 액티비티 이름 넣어주기!

                //startActivity(intent);
            }
        });
        //취소하기 버튼 누를때
        Button cancelbutton = (Button) findViewById(R.id.cancelbutton);
        cancelbutton.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent intent = new Intent(check_info.this, reserve_cancel.class);

                startActivity(intent);
            }
        });


    }
}
