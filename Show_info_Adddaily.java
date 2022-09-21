package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.example.miniproject.Mobel.DataGoble;

import lombok.val;

public class Show_info_Adddaily extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info_adddaily);

        val homeIsolationPatientTemp = DataGoble.homeIsolationPatient ;
        homeIsolationPatientTemp.getIdCard() ;

        EditText textId = findViewById(R.id.txtIdShow);
        textId.setText(homeIsolationPatientTemp.getPatientId());
        textId.setInputType(InputType.TYPE_NULL);

        EditText textName = findViewById(R.id.txtNameShow);
        textName.setText(homeIsolationPatientTemp.getName());
        textName.setInputType(InputType.TYPE_NULL);

        EditText textLastName = findViewById(R.id.txtLastNameShow);
        textLastName.setText(homeIsolationPatientTemp.getLastname());
        textLastName.setInputType(InputType.TYPE_NULL);

        EditText txtCongenitalDiseaseShow = findViewById(R.id.txtCongenitalDiseaseShow);
        txtCongenitalDiseaseShow.setText(homeIsolationPatientTemp.getCongenitalDisease());
        txtCongenitalDiseaseShow.setInputType(InputType.TYPE_NULL);

        EditText txtDrugAllergyShow = findViewById(R.id.txtDrugAllergyShow);
        txtDrugAllergyShow.setText(homeIsolationPatientTemp.getDrugAllergy());
        txtDrugAllergyShow.setInputType(InputType.TYPE_NULL);

        EditText txtShowAddress = findViewById(R.id.txtShowAddress);
        txtShowAddress.setText(homeIsolationPatientTemp.getAddress());
        txtShowAddress.setInputType(InputType.TYPE_NULL);


    }

    public void btnNextPage (View v ) {
        Intent intent2 = new Intent(Show_info_Adddaily.this, Add_daily_report.class);
        startActivity(intent2);
    }
}