/*
 * Copyright 2010-2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amazonaws.demo.polly;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.Snackbar;
import android.app.AlertDialog;

public class PollyActivity extends AppCompatActivity {

    public static final int sub = 1001; /*다른 액티비티를 띄우기 위한 요청코드(상수)*/
    private View mainlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mainlayout = findViewById(R.id.main);
        final Context context = this;
        String model = Build.MODEL;

        Button button = (Button)findViewById(R.id.pollyGo); /*페이지 전환버튼*/
        Button button_signIn = (Button)findViewById(R.id.signIn); /*로그인 버튼*/
        Button beyond_go = (Button)findViewById(R.id.beyondGo); /*버튼 GO AWS*/
        Button button_first = (Button)findViewById(R.id.first); /*버튼 1*/
        Button button_second = (Button)findViewById(R.id.second); /*버튼 2*/
        Button button_third = (Button)findViewById(R.id.third); /*버튼 3*/

        //Pixel 기종일때 Raise Bug code ...
        if(model.equals("Pixel")){
            int bug = 0/0;
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivityForResult(intent,sub);//액티비티 띄우기
            }
        });

        button_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usrId = (EditText) findViewById(R.id.userId);
                String usrIdVal = usrId.getText().toString();
                EditText usrPwd = (EditText) findViewById(R.id.password);
                String usrPwdVal = usrPwd.getText().toString();

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                CharSequence text;

                if(usrIdVal.equals("admin") && usrPwdVal.equals("test")){
                    text = "로그인 완료";

                    TextView loginLabel = (TextView) findViewById(R.id.text_view_id);
                    TextView passwordLabel = (TextView) findViewById(R.id.text_view_password);
                    TextView login_msg = (TextView) findViewById(R.id.log_in_label);
                    TextView device_msg = (TextView) findViewById(R.id.device_id_label);
                    Button login_btn = (Button) findViewById(R.id.signIn);

                    loginLabel.setVisibility(View.INVISIBLE);
                    passwordLabel.setVisibility(View.INVISIBLE);
                    usrId.setVisibility(View.INVISIBLE);
                    usrPwd.setVisibility(View.INVISIBLE);
                    login_btn.setVisibility(View.INVISIBLE);

                    login_msg.setVisibility(View.VISIBLE);
                    device_msg.setVisibility(View.VISIBLE);

                    login_msg.setText(usrIdVal+"님 환영합니다.");
                    device_msg.setText(model);
                }else{
                    text = "로그인 실패";
                }
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        beyond_go.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String url = "http://aws.amazon.com";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });

        button_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "button 1";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        button_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(mainlayout, " Demo! GO!", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

        button_third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // 제목셋팅
                alertDialogBuilder.setTitle("프로그램 종료");

                // AlertDialog 셋팅
                alertDialogBuilder
                        .setMessage("프로그램을 종료할 것입니까?")
                        .setCancelable(false)
                        .setPositiveButton("종료",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        // 프로그램을 종료한다

                                        Context context = getApplicationContext();
                                        CharSequence text = "프로그램 종료";
                                        int duration = Toast.LENGTH_SHORT;

                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show();
                                    }
                                })
                        .setNegativeButton("취소",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        // 다이얼로그를 취소한다
                                        dialog.cancel();
                                    }
                                });

                // 다이얼로그 생성
                AlertDialog alertDialog = alertDialogBuilder.create();

                // 다이얼로그 보여주기
                alertDialog.show();

            }
        });
    }
}
