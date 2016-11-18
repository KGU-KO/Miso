package com.first.miso;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Application.Domain.User;

public class LoginActivity extends Activity {
    final public static int DEFAULT_PORT = 8888;
    private Socket socket;
    EditText id;
    EditText pwd;
    Button loginButton, signUpButton, exitButton;
    TextView output;
    private ObjectOutputStream o;
    private ObjectInputStream i;
    User user = null;

    int exit = 0;
    String miss = "#";
    String success = "$";
    String newRegister = "@";
    Object read = null;

    int result;
    Handler d = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {     //앱 시작시  초기화설정
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = (EditText) findViewById(R.id.id_input);                 // 아이디 입력칸을 찾는다.
        pwd = (EditText) findViewById(R.id.pw_input);             // 패스워드 입력칸을 찾는다.
        loginButton = (Button) findViewById(R.id.loginButton);        // 버튼을 찾는다.
        signUpButton = (Button) findViewById(R.id.signUpButton);
        exitButton = (Button) findViewById(R.id.exitButton);
        //output = (TextView) findViewById(R.id.output);      // 서버 결과 출력칸을 찾는다.
        d = new Handler();


        exitButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                try {
                    o.writeObject(exit);
                } catch (IOException e) {
                    Log.e("writeObject error: ", e.toString());
                }
                finish();
            }
        });

        // 버튼을 누르는 이벤트 발생, 이벤트 제어문이기 때문에 이벤트 발생 때마다 발동된다. 시스템이 처리하는 부분이 무한루프문에
        //있더라도 이벤트가 발생하면 자동으로 실행된다.
        loginButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                user = new User();

                String data = id.getText().toString();
                user.setId(data);
                String data2 = pwd.getText().toString();
                user.setPwd(data2);

                Log.w("NETWORK", " ID : " + data + " PWD : " + data2);

                if (user != null) {
                    try {
                        o.writeObject(user);
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View v) {
                try {
                    o.writeObject(newRegister);
                } catch (IOException e) {
                    Log.e("writeObject error: ", e.toString());
                }

                new_register nr = new new_register();
                nr.setSocket(socket);
                //Intent intent = new Intent(LoginActivity.this, new_register.class);
                Intent intent = new Intent(LoginActivity.this, nr.getClass());
                startActivity(intent);
            }
        });

        Thread worker = new Thread() {

            public void run() {
                //Looper.prepare();

                try {
                    // 소켓 생성 및 입출력 스트림을 소켓에 연결
                    socket = new Socket("10.0.2.2", DEFAULT_PORT);     // ip, port번호 수정
                    //socket = new Socket("10.10.28.170", 5519);     // ip, port번호 수정
                    o = new ObjectOutputStream(socket.getOutputStream());
                    i = new ObjectInputStream(socket.getInputStream());

                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    while (true) {
                        //result = (int)i.readObject();
                        read = i.readObject();
                        Log.i("READ" , read.toString());

                        if(read.toString().equals(newRegister)) {
                            //
                            Log.w("NEWREGISTER", "회원가입");
                        }

                        else if(read.toString().equals(success)) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            //break;
                        }

                        else if(read.toString().equals(miss)) {
                            d.post(new Runnable() {
                                @Override
                                public void run() {
                                    //Log.w("MISS TYPE", " " + result);
                                    AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                                    alert.setTitle("ID/PASSWORD 에러");
                                    alert.setMessage("다시 입력하세요!!");
                                    alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                                    alert.show();
                                    //Log.w("HERE", " 여기");
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Looper.loop();
            }

        };
        worker.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}