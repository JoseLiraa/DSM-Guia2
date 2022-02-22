package sv.edu.udb.ml161665.dicusionresultado2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class secondActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvHours;
    private TextView tvDiscountISS;
    private TextView tvDiscountAFP;
    private TextView tvDiscountRent;
    private TextView tvTotalSalary;
    private TextView tvNetSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvName = (TextView) findViewById(R.id.textViewName);
        tvHours = (TextView) findViewById(R.id.textViewHours);
        tvDiscountISS = (TextView) findViewById(R.id.textViewAFP);
        tvDiscountAFP = (TextView) findViewById(R.id.textViewISS);
        tvDiscountRent = (TextView) findViewById(R.id.textViewRent);
        tvTotalSalary = (TextView) findViewById(R.id.textViewTotalSalary);
        tvNetSalary = (TextView) findViewById(R.id.textViewNetSalary);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("txtName");
        String deductionsafp = bundle.getString("deductionsAFP");
        String deductionsiss = bundle.getString("deductionsISS");
        String deductionsrent = bundle.getString("deductionsRent");
        String totalSalary = bundle.getString("totalSalary");
        String netSalary = bundle.getString("netSalary");
        String hours = bundle.getString("txtHours");

        tvName.setText(getResources().getString(R.string.Name, name));
        tvHours.setText(getResources().getString(R.string.Hours, hours));
        tvDiscountRent.setText(getResources().getString(R.string.DiscountRent, deductionsrent));
        tvDiscountISS.setText(getResources().getString(R.string.DiscountISS,deductionsiss));
        tvDiscountAFP.setText(getResources().getString(R.string.DiscountAFP, deductionsafp));
        tvTotalSalary.setText(getResources().getString(R.string.TotalSalary, totalSalary));
        tvNetSalary.setText(getResources().getString(R.string.NetSalary,  netSalary));
    }

    public void conclude (View v){finish();}
}