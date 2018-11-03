package com.example.user.sound;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    final int DIALOG_DATE = 1;
    final int DIALOG_TIME = 2;
    final int DIALOG_RADIO = 3;
    private Context context;
    AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    int mod = 0;
    int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = (Button) findViewById(R.id.button1);
        Button b2 = (Button) findViewById(R.id.button2);
        Button b3 = (Button) findViewById(R.id.button3);
        Button b4 = (Button) findViewById(R.id.button4);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_DATE); // 날짜 설정 다이얼로그 띄우기
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_TIME);
            }

        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_RADIO); // 다이얼로그 3 띄우기
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
            }
        });
    } // end of onCreate

    int hOD;
    int nue;
    int year;
    int monthOfYear;
    int dayOfMonth;

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        Log.d("test", "onCreateDialog");

        switch (id) {
            case DIALOG_DATE:
                DatePickerDialog dpd = new DatePickerDialog
                        (MainActivity.this, // 현재화면의 제어권자
                                new DatePickerDialog.OnDateSetListener() {
                                    public void onDateSet(DatePicker view,
                                                          int year1, int monthOfYear1, int dayOfMonth1) {

                                        Toast.makeText(getApplicationContext(),
                                                year1 + "년 " + (monthOfYear1 + 1) + "월 " + dayOfMonth1 + "일 을 선택했습니다",
                                                Toast.LENGTH_SHORT).show();
                                        year = year1;
                                        monthOfYear = monthOfYear1;
                                        dayOfMonth = dayOfMonth1;
                                    }
                                }
                                , // 사용자가 날짜설정 후 다이얼로그 빠져나올때
                                //    호출할 리스너 등록
                                2018, 10, 03); // 기본값 연월일
                return dpd;
            case DIALOG_TIME:
                TimePickerDialog tpd =
                        new TimePickerDialog(MainActivity.this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view,
                                                          int hourOfDay, int minute) {
                                        Toast.makeText(getApplicationContext(),
                                                hourOfDay + "시 " + minute + "분 을 선택했습니다",
                                                Toast.LENGTH_SHORT).show();
                                        hOD = hourOfDay;
                                        nue = minute;
                                    }
                                }, // 값설정시 호출될 리스너 등록
                                1, 00, false); // 기본값 시분 등록
                // true : 24 시간(0~23) 표시
                // false : 오전/오후 항목이 생김
                return tpd;
            case DIALOG_RADIO:
                AlertDialog.Builder builder3 =
                        new AlertDialog.Builder(MainActivity.this);
                final String str2[] = {"소리모드", "무음모드", "진동모드"};
                builder3.setTitle("모드를 선택하세요.")
                        .setPositiveButton("선택완료",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getApplicationContext(),
                                                str2[temp] + "을 선택했음",
                                                Toast.LENGTH_SHORT).show();
                                        mod = temp;
                                    }
                                })
                        .setNegativeButton("취소", null)
                        .setSingleChoiceItems
                                (str2,// 리스트배열 목록
                                        -1, // 기본 설정값
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int which) {
                                                temp = which;
                                            }
                                        });    // 리스너

                return builder3.create(); // 다이얼로그 생성한 객체 리턴
        }

        return super.onCreateDialog(id);
    }
    Date data = new Date();
    public void setAlarm() {
        while (true) {
            int gH =data.getHours();
            int gM = data.getMinutes();
            int gY = data.getYear();
            int gMh = (data.getMonth() + 1);
            int gD = data.getDate();
            if (hOD == gH && nue == gM && year == gY && monthOfYear == gMh && dayOfMonth == gD) {
                if(mod==0) {
                    mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    break;
                }
                if(mod==1) {
                    mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    break;
                }
                if(mod==2) {
                    mAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                    break;
                }
                }
            }
        }
    }
