package sv.edu.udb.ml161665.pemiisosapp;

import static android.Manifest.permission.READ_CONTACTS;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView lblPemiso;
    private TextView lblContacto;
    private final int MY_PERMISSONS = 100; //Codigo que utilizamos para solicitar los permisos, puede ser cualquier numero

    private final int OPEN_CONTACT = 200; //Codigo que utilizamos para abrir la lista de contactos, puede ser cualquier numero

    private final String str_permitido = "PERMITIDO";
    private final String str_denegado = "DENEGADO";
    private String estado;

    public boolean verificarPermiso(){
        /* Comprobando la version del dispositivo, en este caso que sea la version de Android 6.0 */
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true;

        /* Comprobando que los permisos ya hayan sido aceptados por el usuario*/
        if(checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED)
            return true;

        //los permisos nos fueron aceptados por el ususario
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        estado = "Estado del permiso:";

        lblPemiso = (TextView) findViewById(R.id.lblPermiso);
        lblContacto = (TextView) findViewById(R.id.lblContacto);

        if(verificarPermiso())
            lblPemiso.setText(estado + " " + str_permitido);
        else
            lblPemiso.setText(estado + " " + str_denegado);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void on_Click(View view){
        if(verificarPermiso()){
            lblPemiso.setText(estado + " " + str_permitido);
            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI); //Creamos un Intent para abrir los contactos
            startActivityForResult(intent, OPEN_CONTACT); //Lanzamos el Intent y se espera a que se seleccione un contacto
        } else{
            requestPermissions(new String[]{READ_CONTACTS}, MY_PERMISSONS);//Solicitamos los permisos para abrir los contactos
        }
    }

    @SuppressLint("Range")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            switch (requestCode){
                case OPEN_CONTACT:
                    Uri contactUri = data.getData();
                    //Con el URI consultamos la informacion del contacto seleccionado
                    Cursor cursor = getContentResolver().query(contactUri, null, null, null, null);

                    String nombre = "\n" + "Contacto Seleccionado: " + "\n";
                    //Verificacion si el contacto existe
                    if(cursor.moveToFirst()){
                        //Obtenemos el nombre del contacto
                        nombre = nombre + cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    }
                    lblContacto.setText(nombre);
                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_PERMISSONS){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                lblPemiso.setText(estado + " " + str_permitido);
                //Creamos el Intent para abrir los contactos
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                //Abrimos los contactos
                startActivityForResult(intent, OPEN_CONTACT);
            } else{
                lblPemiso.setText(estado + " " + str_denegado );
            }
        }
    }
}