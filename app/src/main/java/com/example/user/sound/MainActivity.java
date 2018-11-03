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
    int temp;
    Date data = new Date();
//    int gH = data.getHours();//시간
//    int gM = data.getMinutes();//분
//    int gY = (data.getYear() + 1900);//년도
//    int gMh = (data.getMonth() + 1);//월
//    int gD = data.getDate();//일


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
                                        monthOfYear = monthOfYear1+1;
                                        dayOfMonth = dayOfMonth1;

                                    }
                                }
                                , // 사용자가 날짜설정 후 다이얼로그 빠져나올때
                                //    호출할 리스너 등록
                                (data.getYear() + 1900), data.getMonth(), data.getDate()); // 기본값 연월일
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
                                data.getHours(), data.getMinutes(), false); // 기본값 시분 등록
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
boolean Y;
    public void setAlarm() {

        int gH = data.getHours();//시간
        int gM = data.getMinutes();//분
        int gY = (data.getYear() + 1900);//년도
        int gMh = (data.getMonth() + 1);//월
        int gD = data.getDate();//일
        Toast.makeText(getApplicationContext(),"저장 완료",
                Toast.LENGTH_SHORT).show();
        AudioManager am;
        am= (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
        Toast.makeText(getApplicationContext(),gY+"년 "+gMh+"월 "+gD+"일 "+gH+"시 "+gM+"분",
                Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),year+"년 "+monthOfYear+"월 "+dayOfMonth+"일 "+hOD+"시 "+nue+"분",
                Toast.LENGTH_SHORT).show();
//    int gH = data.getHours();//시간
//    int gM = data.getMinutes();//분
//    int gY = (data.getYear() + 1900);//년도
//    int gMh = (data.getMonth() + 1);//월
//    int gD = data.getDate();//일
        while (year ==  (data.getYear() + 1900) && hOD==data.getHours() && nue == data.getMinutes() && monthOfYear == (data.getMonth() + 1) && dayOfMonth == data.getDate()) {
                if (temp == 0) {
                    Toast.makeText(getApplicationContext(), "소리모드 변경 완료",
                            Toast.LENGTH_SHORT).show();
                    am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

                    break;
                }
                if (temp == 1) {
                    Toast.makeText(getApplicationContext(), "무음모드 변경 완료",
                            Toast.LENGTH_SHORT).show();
                    am.setRingerMode(AudioManager.RINGER_MODE_SILENT);

                    break;
                }
                if (temp == 2) {
                    Toast.makeText(getApplicationContext(), "진동모드 변경 완료",
                            Toast.LENGTH_SHORT).show();
                    am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);

                    break;
                }
            }

    }
}
