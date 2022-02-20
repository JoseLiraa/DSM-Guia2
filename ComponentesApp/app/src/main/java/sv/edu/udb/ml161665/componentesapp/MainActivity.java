package sv.edu.udb.ml161665.componentesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etEdad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = (EditText)findViewById(R.id.txtNombre);
        etEdad = (EditText)findViewById(R.id.txtEdad);
    }

    public void segundaActividad (View v){
        Intent i = new Intent( this, segundaActividad.class);
        i.putExtra( "txtNombre", etNombre.getText().toString());
        i.putExtra( "txtEdad", etEdad.getText().toString());
        startActivity(i);
    }
}