package com.example.apasca.orariolavoro;



import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import java.util.Calendar;



public class Main extends AppCompatActivity {

    private final String CHANNEL_ID = "com.example.apasca.orariolavoro.notify";
    private ConstraintLayout layout;
    private ConstraintSet constraintSetOld = new ConstraintSet();
    private ConstraintSet constraintSetNew = new ConstraintSet();
    private int orariouscitafinaleH;
    private int orariouscitafinaleM;
    public boolean altLayout;
    public boolean avvisami = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button bottoneCalcola = (Button) findViewById(R.id.button);
        final Button bottoneResetta = (Button) findViewById(R.id.btnResetta);


        final EditText orarioEntrataH = (EditText) findViewById(R.id.edittextOraEntrataH);
        final EditText orarioEntrataM = (EditText) findViewById(R.id.edittextOraEntrataM);
        final EditText orarioUscitaPranzoH = (EditText) findViewById(R.id.edittextOraUscitaPranzoH);
        final EditText orarioUscitaPranzoM = (EditText) findViewById(R.id.edittextOraUscitaPranzoM);
        final EditText orarioRientroH = (EditText) findViewById(R.id.edittextOraRientroPranzoH);
        final EditText orarioRientroM = (EditText) findViewById(R.id.edittextOraRientroPranzoM);

        final TextView valoreUscita = (TextView) findViewById(R.id.textViewOrario);
        final TextView valorePausa = (TextView) findViewById( R.id.textViewValorePausa );
        final TextView labelUscita = (TextView) findViewById( R.id.textViewUscita );
        final TextView labelPausa = (TextView) findViewById( R.id.textViewLabelPausa );
        final TextView labelOraUscita = (TextView) findViewById( R.id.textViewOraUscitaP );
        final TextView labelOraRientro = (TextView) findViewById( R.id.textViewOraRientro );
        final TextView duePunti = (TextView) findViewById( R.id.duepunti );
        final TextView duePunti1 = (TextView) findViewById( R.id.duepunti1 );
        final TextView duePunti2 = (TextView) findViewById( R.id.duepunti2 );
        final Switch switchPausa = (Switch) findViewById( R.id.switch1 );
        final Switch switchAvviso = (Switch) findViewById( R.id.switch2 );

        layout = findViewById( R.id.normalLayout );
        constraintSetOld.clone( layout );
        constraintSetNew.clone( this, R.layout.activity_main_alt);

        switchPausa.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    orarioUscitaPranzoH.setVisibility( View.INVISIBLE );
                    orarioUscitaPranzoM.setVisibility( View.INVISIBLE );
                    orarioRientroH.setVisibility( View.INVISIBLE );
                    orarioRientroM.setVisibility( View.INVISIBLE );
                    labelOraUscita.setVisibility( View.INVISIBLE );
                    labelOraRientro.setVisibility( View.INVISIBLE );
                    duePunti1.setVisibility( View.INVISIBLE );
                    duePunti2.setVisibility( View.INVISIBLE );

                    valoreUscita.setText( "" );
                    valorePausa.setText( "" );
                    labelUscita.setText( "" );
                    labelPausa.setText( "" );


                    orarioUscitaPranzoH.setText( "12" , TextView.BufferType.EDITABLE);
                    orarioUscitaPranzoM.setText( "30" , TextView.BufferType.EDITABLE );
                    orarioRientroH.setText( "12" , TextView.BufferType.EDITABLE );
                    orarioRientroM.setText( "31" ,TextView.BufferType.EDITABLE );

                    constraintSetNew.applyTo(layout);
                    orarioEntrataH.requestFocus();
                    altLayout = true;
                }
                else{
                    orarioUscitaPranzoH.setVisibility( View.VISIBLE );
                    orarioUscitaPranzoM.setVisibility( View.VISIBLE );
                    orarioRientroH.setVisibility( View.VISIBLE );
                    orarioRientroM.setVisibility( View.VISIBLE );
                    labelOraUscita.setVisibility( View.VISIBLE );
                    labelOraRientro.setVisibility( View.VISIBLE );
                    duePunti1.setVisibility( View.VISIBLE );
                    duePunti2.setVisibility( View.VISIBLE );

                    valoreUscita.setText( "" );
                    valorePausa.setText( "" );
                    labelUscita.setText( "" );
                    labelPausa.setText( "" );

                    orarioUscitaPranzoH.setText( "" , TextView.BufferType.EDITABLE);
                    orarioUscitaPranzoM.setText( "", TextView.BufferType.EDITABLE );
                    orarioRientroH.setText( "", TextView.BufferType.EDITABLE );
                    orarioRientroM.setText( "",TextView.BufferType.EDITABLE );
                    constraintSetOld.applyTo(layout);
                    altLayout = false;
                }
            }
        } );

        orarioEntrataH.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(orarioEntrataH.getText().length() == 2)
                    orarioEntrataM.requestFocus();
                return false;
            }
        });

        orarioEntrataM.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(orarioEntrataM.getText().length() == 2)
                    orarioUscitaPranzoH.requestFocus();
                return false;
            }
        });

        orarioUscitaPranzoH.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(orarioUscitaPranzoH.getText().length() == 2)
                    orarioUscitaPranzoM.requestFocus();
                return false;
            }
        });

        orarioUscitaPranzoM.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(orarioUscitaPranzoM.getText().length() == 2)
                    orarioRientroH.requestFocus();
                return false;
            }
        });

        orarioRientroH.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(orarioRientroH.getText().length() == 2)
                    orarioRientroM.requestFocus();
                return false;
            }
        });


        bottoneResetta.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {

                orarioEntrataH.setText( "" );
                orarioEntrataM.setText( "" );
                orarioUscitaPranzoH.setText( "" );
                orarioUscitaPranzoM.setText( "" );
                orarioRientroH.setText( "" );
                orarioRientroM.setText( "" );

                valoreUscita.setText( "" );
                valorePausa.setText( "" );
                labelUscita.setText( "" );
                labelPausa.setText( "" );
                switchAvviso.setVisibility( View.INVISIBLE );
                switchAvviso.setChecked( false );

                avvisami = false;
                displayNotifica( avvisami );

                if(altLayout)
                    constraintSetOld.applyTo( layout );
                    switchPausa.setChecked( false );
            }

        });


        bottoneCalcola.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {



                //Nascondo la tastiera nel momento in cui si clicca il tasto "Calcola"
                closeKeyboard();


                if (orarioEntrataH.getText().toString().length() != 2 || orarioEntrataM.getText().toString().length() != 2 || orarioUscitaPranzoH.getText().toString().length() != 2 ||
                        orarioUscitaPranzoM.getText().toString().length() != 2 || orarioRientroH.getText().toString().length() != 2 || orarioRientroM.getText().toString().length() != 2) {
                    new AlertDialog.Builder( Main.this ).setTitle( "Errore orario" ).setMessage( "Controllare che tutti i campi siano validati" ).show();
                }
                else {
                    //Valorizziamo le variabili
                    int orarioentrataH = Integer.parseInt( orarioEntrataH.getText().toString() );
                    int orarioentrataM = Integer.parseInt( orarioEntrataM.getText().toString() );
                    int orariouscitaPranzoH = Integer.parseInt( orarioUscitaPranzoH.getText().toString() );
                    int orariouscitaPranzoM = Integer.parseInt( orarioUscitaPranzoM.getText().toString() );
                    int orariorientroPranzoH = Integer.parseInt( orarioRientroH.getText().toString() );
                    int orariorientroPranzoM = Integer.parseInt( orarioRientroM.getText().toString() );



                    int pausa = ((orariorientroPranzoH * 60) + orariorientroPranzoM) - ((orariouscitaPranzoH * 60) + orariouscitaPranzoM);


                    //CONTROLLI

                    if (orarioentrataH < 8 || orarioentrataH > 9) {
                        new AlertDialog.Builder( Main.this ).setTitle( "Errore orario" ).setMessage( "L'orario di entrata deve essere compreso tra le 8:00 e le 9:30" ).show();
                    }
                    /*else if(orarioentrataH > 23 || orarioentrataH <1 || orariouscitaPranzoH > 23 || orariouscitaPranzoH < 1 || orariorientroPranzoH > 23 || orariorientroPranzoH < 1){
                        new AlertDialog.Builder( Main.this ).setTitle( "Errore orario" ).setMessage( "Formato ore non valido" ).show();
                    }*/
                    else if(orarioentrataM > 59 || orarioentrataM <0 || orariouscitaPranzoM >59 || orariouscitaPranzoM<0 || orariorientroPranzoM > 59 || orariorientroPranzoM < 0){
                        new AlertDialog.Builder( Main.this ).setTitle( "Errore orario" ).setMessage( "Formato minuti non valido" ).show();
                    }
                    else if (orarioentrataH == 9 && orarioentrataM > 30) {
                        new AlertDialog.Builder( Main.this ).setTitle( "Errore orario" ).setMessage( "L'orario di entrata deve essere compreso tra le 8:00 e le 9:30" ).show();

                    } else if ((orariouscitaPranzoH < 12) || (orariouscitaPranzoH > 14)) {
                        new AlertDialog.Builder( Main.this ).setTitle( "Errore orario" ).setMessage( "L'orario di entrata deve essere compreso tra le 12:30 e le 14:30" ).show();
                    } else if ((orariouscitaPranzoH == 12 && orariouscitaPranzoM < 30) || (orariouscitaPranzoH == 14 && orariouscitaPranzoM > 30)) {
                        new AlertDialog.Builder( Main.this ).setTitle( "Errore orario" ).setMessage( "L'orario di entrata deve essere compreso tra le 12:30 e le 14:30" ).show();
                    } else if (pausa > 90) {
                        new AlertDialog.Builder( Main.this ).setTitle( "Errore orario" ).setMessage( "La durata massima della pausa è di 90 min" ).show();
                    } else if (orariouscitaPranzoH > orariorientroPranzoH) {
                        new AlertDialog.Builder( Main.this ).setTitle( "Errore orario" ).setMessage( "L'orario di rientro non può essere precedente all'orario di uscita" ).show();
                    } else if (orariouscitaPranzoH == orariorientroPranzoH && orariouscitaPranzoM > orariorientroPranzoM) {
                        new AlertDialog.Builder( Main.this ).setTitle( "Errore orario" ).setMessage( "L'orario di rientro non può essere precedente all'orario di uscita" ).show();
                    } else {
                        int pausaMinima = 0;
                        if (pausa < 30) {
                            pausa = 30;
                            pausaMinima = 1;
                        }


                        labelUscita.setText( "Uscita" );
                        if (pausaMinima == 1) labelPausa.setText( "Pausa Minima" );
                        else labelPausa.setText( "Pausa" );

                        //LABEL PAUSA STAMPATA
                        if (pausa > 60) {
                            int valoreMin = pausa - 60;
                            valorePausa.setText( "1 ora\n" + valoreMin + " min" );
                        } else {
                            valorePausa.setText( pausa + " min" );
                        }

                        //CALCOLO EFFETTIVO USCITA

                        orariouscitafinaleH = orarioentrataH + 8;
                        orariouscitafinaleM = orarioentrataM + pausa;

                        if (orariouscitafinaleM > 59) {
                            if (orariouscitafinaleM > 119) {
                                orariouscitafinaleH = orariouscitafinaleH + 2;
                                orariouscitafinaleM = orariouscitafinaleM - 120;
                            } else {
                                orariouscitafinaleM = orariouscitafinaleM - 60;
                                orariouscitafinaleH = orariouscitafinaleH + 1;
                            }
                        }
                        String oraUscitaFinale;
                        if (orariouscitafinaleM < 10) {
                            valoreUscita.setText( orariouscitafinaleH + ":0" + orariouscitafinaleM );
                            oraUscitaFinale = Integer.toString( orariouscitafinaleH ) + ":0" + Integer.toString(orariouscitafinaleM);
                        } else {
                            valoreUscita.setText( orariouscitafinaleH + ":" + orariouscitafinaleM );
                            oraUscitaFinale = Integer.toString( orariouscitafinaleH ) + ":" + Integer.toString(orariouscitafinaleM);
                        }

                        avvisami = true;
                        switchAvviso.setChecked( true );
                        switchAvviso.setVisibility( View.VISIBLE );
                        switchAvvisoChange(view);

                    }

                }

            }

        });




    }

    public int getOraFinale(){
        return orariouscitafinaleH;
    }
    public int getMinFinale(){
        return orariouscitafinaleM;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate( R.menu.menu,menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();

        if(id==R.id.itemLink){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://portal.portal.almaviva.it/Login/Login"));
            startActivity(browserIntent);
        }
        return super.onOptionsItemSelected( item );
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void switchAvvisoChange(View view){
        if(avvisami){
            displayNotifica( avvisami );
            avvisami = false;
        }
        else{
            displayNotifica( avvisami );
            avvisami = true;
        }
    }

    public void closeKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService( Context.INPUT_METHOD_SERVICE );
            imm.hideSoftInputFromWindow( view.getWindowToken(), 0 );
        }
    }

    public void displayNotifica(boolean avvisami){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, getOraFinale());
        calendar.set(Calendar.MINUTE, getMinFinale()-5);
        calendar.set(Calendar.SECOND, 00);

        createNotificationChannel();

        Intent notificationmassage = new Intent(getApplicationContext(),NotificationClass.class);
        notificationmassage.putExtra( "orauscitaH", Integer.toString( getOraFinale() ));
        if(getMinFinale()<10)
            notificationmassage.putExtra( "orauscitaM", "0" + Integer.toString( getMinFinale() ));
        else
            notificationmassage.putExtra( "orauscitaM", Integer.toString( getMinFinale() ));
        //This is alarm manager

        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        PendingIntent pi = PendingIntent.getBroadcast(Main.this, 0 , notificationmassage, PendingIntent.FLAG_UPDATE_CURRENT);
        if(avvisami){
            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
            Toast.makeText(Main.this, "Avviso impostato ", Toast.LENGTH_LONG).show();
        }
        else{
            am.cancel( pi );
            Toast.makeText(Main.this, "Avviso rimosso", Toast.LENGTH_LONG).show();
        }
    }
}


