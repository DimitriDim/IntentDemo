package fr.m2i.intentdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeechService;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


//implements TextToSpeech.OnInitListener tranforme MainActivity en écouteur
public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    EditText txtDomaine;
    TextView txtResult;
    ImageView imgPhoto;
    private final int REQUEST_CODE_LOGIN = 1;
    private final int REQUEST_CODE_PHOTO = 2;
    private final int REQUEST_CODE_LIRE = 3;
    boolean TTS_OK = false;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtDomaine = findViewById(R.id.txtDomain);

        //TTS = Text To Speach
        // On test si un synthétiseur vocal est présent
        Intent intLire = new Intent (TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        //on pose la question avec startActivityForResult
        startActivityForResult(intLire,REQUEST_CODE_LIRE );
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

        //création de l'intent
        Intent i = new Intent(this, LoginActivity.class);

        // Passer une information à la deuxième activité :
        i.putExtra("nomDomaine", txtDomaine.getText().toString());

        // Lancer la deuxième activité :
        // REQUEST_CODE est un enteir qui identifie cet appel
        startActivityForResult(i, REQUEST_CODE_LOGIN);

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        txtResult = findViewById(R.id.txtLogin);

        if (requestCode == REQUEST_CODE_LOGIN) { // Tester quelle activité a répondu
            if (resultCode == RESULT_OK) {
                String logRecu = data.getStringExtra("Login");
                txtResult.setText(logRecu);
            } else {
                txtResult.setText("Non connecté !!");
            }
        }
        if (requestCode == REQUEST_CODE_PHOTO) {
            imgPhoto = findViewById(R.id.imgPhoto);

            //"data" de .get("data") est le nom donné à la photo par l'appareil photo (fixé)
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgPhoto.setImageBitmap(bitmap);
        }

        if (requestCode == REQUEST_CODE_LIRE) {
            if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
                Toast.makeText(this,"Synthétiseur ok", Toast.LENGTH_LONG).show();
                TTS_OK = true;

                // le 2e this est la classe MainActivity qu'on vient d'implémenter en écouteur, sinon pas possible
                tts = new TextToSpeech(this,this);

            }else{
                Toast.makeText(this,"Synthétiseur KO :(", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void btnPhoto(View v) {
        // création de l'intent photo
        Intent intPhoto = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        //lancement de l'activité "appareil photo" avec une attente de resutat
        startActivityForResult(intPhoto, REQUEST_CODE_PHOTO);

    }

    public void btnLire(View v) {


        if (TTS_OK = true){

            tts.setLanguage(Locale.ENGLISH);
            tts.setPitch(10);
            tts.setSpeechRate(0.5F);
            tts.speak(txtDomaine.getText().toString(),TextToSpeech.QUEUE_FLUSH,null,null);
        }
    }

    @Override
    public void onInit(int status) {

    }
}
