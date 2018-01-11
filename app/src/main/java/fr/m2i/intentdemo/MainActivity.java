package fr.m2i.intentdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText txtDomaine;
    TextView txtResult;
    ImageView imgPhoto;
    int REQUEST_CODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDomaine = findViewById(R.id.txtDomain);
    }

    public void btnDemo1(View v) {

        //création de l'intent
        Intent i = new Intent(this, LoginActivity.class);

        // Passer une information à la deuxième activité :
        i.putExtra("nomDomaine", txtDomaine.getText().toString());

        // Lancer la deuxième activité :
        startActivity(i);

    }


    public void btnDemo2(View v) {
        REQUEST_CODE = 1;
        //création de l'intent
        Intent i = new Intent(this, LoginActivity.class);

        // Passer une information à la deuxième activité :
        i.putExtra("nomDomaine", txtDomaine.getText().toString());

        // Lancer la deuxième activité :
        // REQUEST_CODE est un enteir qui identifie cet appel
        startActivityForResult(i, REQUEST_CODE);

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        txtResult = findViewById(R.id.txtLogin);

        if (requestCode == 1) { // Tester quelle activité a répondu
            if (resultCode == RESULT_OK) {
                String logRecu = data.getStringExtra("Login");
                txtResult.setText(logRecu);
            } else {
                txtResult.setText("Non connecté !!");
            }
        }
        if (requestCode == 2) {
            imgPhoto = findViewById(R.id.imgPhoto);

            //"data" de .get("data") est le nom donné à la photo par l'appareil photo (fixé)
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgPhoto.setImageBitmap(bitmap);
        }
    }

    public void btnPhoto(View v) {
        // création de l'intent photo
        Intent intPhoto = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        REQUEST_CODE = 2;

        //lancement de l'activité avec une attente de resutat
        startActivityForResult(intPhoto, REQUEST_CODE);

        //envoi du resultat vers onActivityResult car activité lancé avec startActivityForResult
        setResult(RESULT_OK, intPhoto);
    }

}
