package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.miniproject.Class.Daily_Report;
import com.example.miniproject.Class.Medicine_Request;
import com.example.miniproject.Mobel.DataGoble;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import lombok.SneakyThrows;
import lombok.val;

public class Order_medicine extends AppCompatActivity {
    String username;
    EditText showDate;

    Calendar calendar = Calendar.getInstance(new Locale("th", "TH"));
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);
    final int time = calendar.get(Calendar.HOUR_OF_DAY);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_medicine);

        val homeIsolationPatientTemp = DataGoble.homeIsolationPatient;
        homeIsolationPatientTemp.getIdCard() ;

        username = homeIsolationPatientTemp.getIdCard();
        showDate = findViewById(R.id.txtDateRequest);
        showDate.setInputType(InputType.TYPE_NULL);

        final Calendar c = Calendar.getInstance();
        int Year = c.get(Calendar.YEAR);
        int Month = c.get(Calendar.MONTH);
        int Day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        String day = (String.valueOf(Day));
        String months = (String.valueOf(Month + 1));

        EditText txtDate = findViewById(R.id.txtDateRequest);
        txtDate.setText(day + "-" + months + "-" + Year + " " + hour + ":" + min);


        showDate.setOnClickListener(new View.OnClickListener() {
            @SneakyThrows
            @Override
            public void onClick(View v) {
                showDateDialog(showDate);
            }

            private void showDateDialog(EditText showDate) throws ParseException {

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
                                showDate.setText(simpleDateFormat.format(notThaiCalendar.getTime()));


                            }
                        };
                        new TimePickerDialog(Order_medicine.this, timeSetListener, notThaiCalendar.get(Calendar.HOUR_OF_DAY), notThaiCalendar.get(Calendar.MINUTE), false).show();

                    }
                };
                new DatePickerDialog(Order_medicine.this, dateSetListener, notThaiCalendar.get(Calendar.YEAR), notThaiCalendar.get(Calendar.MONTH), notThaiCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }


        });
    }



    public void order_medicine (View view ){

        val homeIsolationPatientTemp = DataGoble.homeIsolationPatient;
        homeIsolationPatientTemp.getIdCard() ;

        EditText txtTime = findViewById(R.id.txtDateRequest);
        String timeDate = txtTime.getText().toString();


        EditText txtName_med = findViewById(R.id.txtmedicineName);
        String medicine_rql = txtName_med.getText().toString();

        EditText txt_reason = findViewById(R.id.txtreason);
        String reason = txt_reason.getText().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://projecthomeisolation-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database .getReference("Login/Requst_medicine");
        Medicine_Request medicine_request = new Medicine_Request();
        medicine_request.setDateRequest(timeDate);
        medicine_request.setMedicine_name(medicine_rql);
        medicine_request.setReason(reason);

        /*medicine_request.setStatus(false);*/

        medicine_request.setHomeIsolationPatient(homeIsolationPatientTemp);

        DatabaseReference myRef2 = myRef.push();
        myRef2.setValue(medicine_request);

        Intent intent2 = new Intent(Order_medicine.this,HomeIsolation_Manpage.class);
        startActivity(intent2);

    }

}



