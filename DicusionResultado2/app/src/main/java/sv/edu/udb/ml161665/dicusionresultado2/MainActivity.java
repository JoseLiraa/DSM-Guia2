package sv.edu.udb.ml161665.dicusionresultado2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText eName;
    private EditText eWorkedHours;
    double iss = 0.02;
    double afp = 0.03;
    double rent = 0.04;
    double pricePerHour = 8.5;
    double deductions, netSalary, totalSalary;
    double deductionsiss, deductionsafp, deductionsrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = (EditText)findViewById(R.id.txtName);
        eWorkedHours = (EditText)findViewById(R.id.txtWorkedHours);

    }

    public void calculate (View v){

        int hour = Integer.parseInt(eWorkedHours.getText().toString());
        totalSalary = hour * pricePerHour;
        deductions = (iss + afp + rent) * totalSalary;
        netSalary = totalSalary - deductions;
        deductionsafp = totalSalary * afp;
        deductionsiss = totalSalary * iss;
        deductionsrent = totalSalary * rent;

        Intent i = new Intent(this, secondActivity.class);
        i.putExtra("txtName", eName.getText().toString());
        i.putExtra("deductionsAFP",String.valueOf(deductionsafp));
        i.putExtra("deductionsISS",String.valueOf(deductionsiss));
        i.putExtra("deductionsRent",String.valueOf(deductionsrent));
        i.putExtra("totalSalary", String.valueOf(totalSalary));
        i.putExtra("netSalary", String.valueOf(netSalary));
        i.putExtra("txtHours", eWorkedHours.getText().toString());
        startActivity(i);
    }


}