package com.example.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.time.ZoneId;
import java.time.chrono.ThaiBuddhistDate;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.TimePicker;

import com.example.miniproject.Class.Daily_Report;

import com.example.miniproject.Mobel.DataGoble;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

import lombok.SneakyThrows;
import lombok.val;

public class Add_daily_report extends AppCompatActivity {
    String username;
    EditText addTime;
    DatabaseReference myRef;
    FirebaseDatabase database;
    int count = 0;


   /* Daily_Report daily_report = new Daily_Report();*/


    Calendar calendar = Calendar.getInstance(new Locale("th", "TH"));
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);
    final int time = calendar.get(Calendar.HOUR_OF_DAY);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_daily_report);


        val homeIsolationPatientTemp = DataGoble.homeIsolationPatient;

        username = homeIsolationPatientTemp.getIdCard();
        addTime = findViewById(R.id.dateTimetoAdd);
        addTime.setInputType(InputType.TYPE_NULL);

        final Calendar c = Calendar.getInstance();
        int Year = c.get(Calendar.YEAR);
        int Month = c.get(Calendar.MONTH);
        int Day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        String day = (String.valueOf(Day));
        String months = (String.valueOf(Month + 1));

        EditText txtDate = findViewById(R.id.dateTimetoAdd);
        txtDate.setText(day + "-" + months + "-" + Year + " " + hour + ":" + min);

        addTime.setOnClickListener(new View.OnClickListener() {
            @SneakyThrows
            @Override
            public void onClick(View v) {
                showDateDialog(addTime);
            }


        });


    }

    private void showDateDialog(EditText addTime) throws ParseException {

        /* Calendar calendar = Calendar.getInstance();*/
        val notThaiCalendar = Calendar.getInstance(new Locale("th", "TH"));

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

//                notThaiCalendar.set(Calendar.YEAR, year);
//                notThaiCalendar.set(Calendar.MONTH, month);
//                notThaiCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        notThaiCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                        notThaiCalendar.set(Calendar.MINUTE, minute);


                        val simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm", new Locale("th", "TH"));
                        addTime.setText(simpleDateFormat.format(notThaiCalendar.getTime()));


                    }
                };
                new TimePickerDialog(Add_daily_report.this, timeSetListener, notThaiCalendar.get(Calendar.HOUR_OF_DAY), notThaiCalendar.get(Calendar.MINUTE), false).show();

            }
        };
        new DatePickerDialog(Add_daily_report.this, dateSetListener, notThaiCalendar.get(Calendar.YEAR), notThaiCalendar.get(Calendar.MONTH), notThaiCalendar.get(Calendar.DAY_OF_MONTH)).show();


    }


    public void addDaily(View view) {

//        Button saveCheck = findViewById(R.id.button_submit);

        EditText txtTime = findViewById(R.id.dateTimetoAdd);
        String timeDate = txtTime.getText().toString();

        EditText txtSymptoms = findViewById(R.id.txtsymtom);
        String abNormalSymptom = txtSymptoms.getText().toString();


        String NormalSymptom1 = "มีไข้";
        String NormalSymptom2 = "มีอาการไอ";
        String NormalSymptom3 = "เจ็บคอ";
        String NormalSymptom4 = "ไม่ได้กลิ่น";
        String NormalSymptom5 = "ไม่รับรสชาติ";
        String NormalSymptom6 = "มีอาการเหนื่อย";
        String NormalSymptom7 = "ปวดศีรษะ";

        CheckBox Box1 = findViewById(R.id.checkBox1);
        CheckBox Box2 = findViewById(R.id.checkBox2);
        CheckBox Box3 = findViewById(R.id.checkBox3);
        CheckBox Box4 = findViewById(R.id.checkBox4);
        CheckBox Box5 = findViewById(R.id.checkBox5);
        CheckBox Box6 = findViewById(R.id.checkBox6);
        CheckBox Box7 = findViewById(R.id.checkBox7);


        myRef = database.getInstance().getReference().child("NormalSymptom");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    int count = (int) snapshot.getChildrenCount();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        saveCheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (Box1.isChecked()) {
//                    daily_report.setNormalSymptom1(NormalSymptom1);
//                    myRef.child(String.valueOf(count + 1)).setValue(daily_report);
//                }
//                if (Box2.isChecked()) {
//                    daily_report.setNormalSymptom2(NormalSymptom2);
//                    myRef.child(String.valueOf(count + 1)).setValue(daily_report);
//                }
//
//                if (Box3.isChecked()) {
//                    daily_report.setNormalSymptom3(NormalSymptom3);
//                    myRef.child(String.valueOf(count + 1)).setValue(daily_report);
//
//                }
//                if (Box4.isChecked()) {
//                    daily_report.setNormalSymptom3(NormalSymptom4);
//                    myRef.child(String.valueOf(count + 1)).setValue(daily_report);
//
//                }
//                if (Box5.isChecked()) {
//                    daily_report.setNormalSymptom3(NormalSymptom5);
//                    myRef.child(String.valueOf(count + 1)).setValue(daily_report);
//
//                }
//                if (Box6.isChecked()) {
//                    daily_report.setNormalSymptom3(NormalSymptom6);
//                    myRef.child(String.valueOf(count + 1)).setValue(daily_report);
//
//                }
//                if (Box7.isChecked()) {
//                    daily_report.setNormalSymptom3(NormalSymptom7);
//                    myRef.child(String.valueOf(count + 1)).setValue(daily_report);
//
//                }
//
//
//            }
//        });


        Daily_Report daily_report = new Daily_Report();
        daily_report.setDaily_Date(timeDate);
        daily_report.setAbNormalSymptom(abNormalSymptom);

        if (Box1.isChecked()) {
            daily_report.setNormalSymptom1(NormalSymptom1);
            myRef.child(String.valueOf(count + 1)).setValue(daily_report);
        }
        if (Box2.isChecked()) {
            daily_report.setNormalSymptom2(NormalSymptom2);
            myRef.child(String.valueOf(count + 1)).setValue(daily_report);
        }

        if (Box3.isChecked()) {
            daily_report.setNormalSymptom3(NormalSymptom3);
            myRef.child(String.valueOf(count + 1)).setValue(daily_report);

        }
        if (Box4.isChecked()) {
            daily_report.setNormalSymptom4(NormalSymptom4);
            myRef.child(String.valueOf(count + 1)).setValue(daily_report);

        }
        if (Box5.isChecked()) {
            daily_report.setNormalSymptom5(NormalSymptom5);
            myRef.child(String.valueOf(count + 1)).setValue(daily_report);

        }
        if (Box6.isChecked()) {
            daily_report.setNormalSymptom6(NormalSymptom6);
            myRef.child(String.valueOf(count + 1)).setValue(daily_report);

        }
        if (Box7.isChecked()) {
            daily_report.setNormalSymptom7(NormalSymptom7);
            myRef.child(String.valueOf(count + 1)).setValue(daily_report);

        }


        database = FirebaseDatabase.getInstance("https://projecthomeisolation-default-rtdb.asia-southeast1.firebasedatabase.app/");
        myRef = database.getReference("Login/HomeIsolation/" + username + "/Add_daily");
        DatabaseReference myRef2 = myRef.push();
        myRef2.setValue(daily_report);

        Intent intent2 = new Intent(Add_daily_report.this, HomeIsolation_Manpage.class);
        startActivity(intent2);

    }
}