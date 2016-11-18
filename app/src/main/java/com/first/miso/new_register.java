package com.first.miso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.net.Socket;

public class new_register extends AppCompatActivity {

    private Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_register);


        Button  register_make_button= (Button) findViewById(R.id. register_make_button);
        register_make_button.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {

                Toast.makeText(new_register.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(new_register.this, LoginActivity.class);

                startActivity(intent);
            }
        });

        Button  register_cancel_button = (Button) findViewById(R.id. register_cancel_button);
        register_cancel_button.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent intent = new Intent(new_register.this, MainActivity.class);

                startActivity(intent);
            }
        });

    }

    public void setSocket(Socket _sokect) {
        socket = _sokect;
    }
}
