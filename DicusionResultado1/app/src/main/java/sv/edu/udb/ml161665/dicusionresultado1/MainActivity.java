package sv.edu.udb.ml161665.dicusionresultado1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtResult;
    private Button btnSum;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = (TextView)findViewById(R.id.txtResult);
        btnSum = (Button)findViewById(R.id.btnSum);

        if(savedInstanceState != null){
            counter = savedInstanceState.getInt("Counter");
            txtResult.setText(String.valueOf(counter));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstaceState){
        super.onSaveInstanceState(savedInstaceState);
        savedInstaceState.putInt("Counter", counter);
    }


    public void addOne(View v){
        counter++;
        if(counter > 9){
            counter=0;
        }
        String result = String.valueOf(counter);
        txtResult.setText(result);
    }
}