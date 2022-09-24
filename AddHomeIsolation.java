package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miniproject.Class.HomeIsolationPatient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.protobuf.Empty;

import java.util.Calendar;

public class AddHomeIsolation extends AppCompatActivity {
    EditText StartDate ;
    EditText EndDate ;
    Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_homeisolation);
        StartDate = findViewById(R.id.AddStartdate);
        EndDate = findViewById(R.id.AddEnddate) ;


        StartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddHomeIsolation.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1 ;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        StartDate.setText(date);

                    }
                }, year , month , day);
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                datePickerDialog.show();

            }
        });



        EndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddHomeIsolation.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1 ;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        EndDate.setText(date);

                    }
                }, year , month , day);
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                datePickerDialog.show();

            }
        });


    }

    public void onBtnAdd(View view){

        FirebaseApp.initializeApp(this);

        /// else

        HomeIsolationPatient homeisolationPatient = new HomeIsolationPatient();

        EditText txtPatientId = findViewById(R.id.Addpatienid);
        homeisolationPatient.setPatientId(txtPatientId.getText().toString());

        EditText txtPatientName = findViewById(R.id.Addname);
        homeisolationPatient.setName(txtPatientName.getText().toString());

        EditText txtPatientLastName = findViewById(R.id.Addlastname);
        homeisolationPatient.setLastname(txtPatientLastName.getText().toString());

        EditText txtIdCard = findViewById(R.id.Addidcard);
        homeisolationPatient.setIdCard(txtIdCard.getText().toString());

        EditText txtGender = findViewById(R.id.Addgender);
        homeisolationPatient.setGender(txtGender.getText().toString());

        EditText txtAge = findViewById(R.id.Addage);
        homeisolationPatient.setAge(txtAge.getText().toString());

        EditText txtAddress = findViewById(R.id.AddAddress);
        homeisolationPatient.setAddress(txtAddress.getText().toString());

        EditText txtPhoneNumber = findViewById(R.id.Addphonenumber);
        homeisolationPatient.setPhoneNumber(txtPhoneNumber.getText().toString());

        EditText txtCongenitalDisease= findViewById(R.id.Addcongenitaldisease);
        homeisolationPatient.setCongenitalDisease(txtCongenitalDisease.getText().toString());

        EditText txtDrugAllergy= findViewById(R.id.Adddrugallergy);
        homeisolationPatient.setDrugAllergy(txtDrugAllergy.getText().toString());

        EditText txtWeight= findViewById(R.id.Addweight);
        homeisolationPatient.setWeight(txtWeight.getText().toString());

        EditText txtHeight= findViewById(R.id.Addheight);
        homeisolationPatient.setHeight(txtHeight.getText().toString());

        EditText txtBloodPressure= findViewById(R.id.Addbloodpressure);
        homeisolationPatient.setBloodPressure(txtBloodPressure.getText().toString());

        EditText txtPulse= findViewById(R.id.Addpulse);
        homeisolationPatient.setPulse(txtPulse.getText().toString());

        EditText txtBreathing= findViewById(R.id.Addbreathing);
        homeisolationPatient.setBreathing(txtBreathing.getText().toString());

        EditText txtTemperature= findViewById(R.id.Addtempareture);
        homeisolationPatient.setTemperature(txtTemperature.getText().toString());

        EditText txtStartDate = findViewById(R.id.AddStartdate);
        homeisolationPatient.setStartDate(txtStartDate.getText().toString());

        EditText txtEndDate = findViewById(R.id.AddEnddate);
        homeisolationPatient.setEndDate(txtEndDate.getText().toString());

        EditText txtStatus = findViewById(R.id.AddStatus);
        homeisolationPatient.setStatus(txtStatus.getText().toString());


        if (txtPatientId.getText().toString().trim().isEmpty()) {
            Toast.makeText(AddHomeIsolation.this,"กรุณากรอกข้อรหัสผู้ป่วย",Toast.LENGTH_LONG).show();

        }else if (txtPatientName.getText().toString().trim().isEmpty()){
            Toast.makeText(AddHomeIsolation.this,"กรุณากรอกชื่อ",Toast.LENGTH_LONG).show();

        }else if (txtPatientLastName.getText().toString().trim().isEmpty()) {
            Toast.makeText(AddHomeIsolation.this,"กรุณากรอกนามสกุล",Toast.LENGTH_LONG).show();

        }else if (txtIdCard.getText().toString().trim().isEmpty()){
            Toast.makeText(AddHomeIsolation.this,"กรุณากรอกเลขบัตประชาชน",Toast.LENGTH_LONG).show();
        }else if (txtGender.getText().toString().trim().isEmpty()){
            Toast.makeText(AddHomeIsolation.this,"กรุณากรอกเพศ",Toast.LENGTH_LONG).show();

        }else if (txtAge.getText().toString().trim().isEmpty()){
            Toast.makeText(AddHomeIsolation.this,"กรุณากรอกอายุ",Toast.LENGTH_LONG).show();

        }else if (txtAddress.getText().toString().trim().isEmpty()){
            Toast.makeText(AddHomeIsolation.this,"กรุณากรอกที่อยู่",Toast.LENGTH_LONG).show();

        }else if (txtPhoneNumber.getText().toString().trim().isEmpty()){
            Toast.makeText(AddHomeIsolation.this,"กรุณากรอกเบอร์โทร",Toast.LENGTH_LONG).show();

        }else if (txtCongenitalDisease.getText().toString().trim().isEmpty()){
            Toast.makeText(AddHomeIsolation.this,"กรุณากรอกโรคประจำตัว",Toast.LENGTH_LONG).show();

        }else if (txtDrugAllergy.getText().toString().trim().isEmpty()){
            Toast.makeText(AddHomeIsolation.this,"กรุณากรอกประวัติการเเพ้ยา",Toast.LENGTH_LONG).show();

        }else if (txtWeight.getText().toString().trim().isEmpty()){
            Toast.makeText(AddHomeIsolation.this,"กรุณากรอกน้ำหนัก",Toast.LENGTH_LONG).show();

        }else if (txtHeight.getText().toString().trim().isEmpty()){
            Toast.makeText(AddHomeIsolation.this,"กรุณากรอกส่วนสูง",Toast.LENGTH_LONG).show();

        }else if (txtBloodPressure.getText().toString().trim().isEmpty()){
            Toast.makeText(AddHomeIsolation.this,"กรุณากรอกความดัน",Toast.LENGTH_LONG).show();

        }else if (txtPulse.getText().toString().trim().isEmpty()){
            Toast.makeText(AddHomeIsolation.this,"กรุณากรอกอัตราการเต้นของหัวใจ",Toast.LENGTH_LONG).show();

        }else if (txtBreathing.getText().toString().trim().isEmpty()){
            Toast.makeText(AddHomeIsolation.this,"กรุณากรอกอัตราการหายใจ",Toast.LENGTH_LONG).show();

        }else if (txtTemperature.getText().toString().trim().isEmpty()){
            Toast.makeText(AddHomeIsolation.this,"กรุณากรอกอุหณภูมิ",Toast.LENGTH_LONG).show();

        }else if (txtStartDate.getText().toString().trim().isEmpty()){
            Toast.makeText(AddHomeIsolation.this,"กรุณากรอกวันที่ได้รับการรักษา",Toast.LENGTH_LONG).show();

        }else if (txtEndDate.getText().toString().trim().isEmpty()){
            Toast.makeText(AddHomeIsolation.this,"กรุณากรอกวันสิ้นสุดการรักษา",Toast.LENGTH_LONG).show();

        }else if (txtStatus.getText().toString().trim().isEmpty()){
            Toast.makeText(AddHomeIsolation.this,"กรุณากรอกสถานะ",Toast.LENGTH_LONG).show();

        }else {

            FirebaseDatabase database = FirebaseDatabase.getInstance("https://projecthomeisolation-default-rtdb.asia-southeast1.firebasedatabase.app/");
            DatabaseReference myRef = database.getReference("Login/HomeIsolation");


            DatabaseReference myRef2 = myRef.child(homeisolationPatient.getIdCard());
            myRef2.setValue(homeisolationPatient);

            Intent intent = new Intent(AddHomeIsolation.this,ShowListHomeIsolation.class);
            startActivity(intent);


        }





    }

    public  void  btnCancle(View view ) {
        Intent intent = new Intent (AddHomeIsolation.this,Adminpage.class);
        startActivity(intent);


    }


}