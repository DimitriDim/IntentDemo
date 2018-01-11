package fr.m2i.intentdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText edTextDomLog, saisieLogin, saisieMdP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //récupération de l'intent de l'activite MainActivity
        Intent intentRecup = getIntent();
        String nomDomaineRecup = intentRecup.getStringExtra("nomDomaine");

        //envoi vers la zone de text de cette activité
        edTextDomLog = findViewById(R.id.txtDomain);
        saisieLogin = findViewById(R.id.txtLogin);
        saisieMdP = findViewById(R.id.txtPwd);
        edTextDomLog.setText(nomDomaineRecup);

    }

    public void boutonOk(View v) {

        //création de l'intent
        Intent iLog = new Intent();
        // test si saisie login = saisie mot de passe
        if (saisieLogin.getText().toString().equals(saisieMdP.getText().toString())) {
            // resultat OK
            setResult(RESULT_OK,iLog);
            //renvoi le login avec l'intent
            iLog.putExtra("Login", saisieLogin.getText().toString());

        } else {
            setResult(RESULT_CANCELED,iLog);
        }
        //relance l'activité principale (MainActivité), on termien l'activité LoginActivity
        finish();
    }

    public void boutonCancel(View v) {

        Intent iCancel = new Intent();
        setResult(RESULT_CANCELED,iCancel);
        finish();

    }

}
