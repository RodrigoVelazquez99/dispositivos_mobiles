package com.example.pmobiles;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class TimerActivity extends AppCompatActivity {

    private CountDownTimer countDownTimer;
    private TextView timer;
    private TextView ejercicio;
    private Button iniciar;
    private Button terminar;
    private int iteracion = 1;
    private boolean activo;
    private boolean descanso;
    private final static long TIMER_EJERCICIO = 50000;
    private final static long TIMER_DESCANSO = 15000;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.cambio);
        iniciar = (Button) findViewById(R.id.iniciar);
        terminar = (Button) findViewById(R.id.finalizar);
        timer = (TextView) findViewById(R.id.timer);
        ejercicio = (TextView) findViewById(R.id.ejercicio);

        iniciar.setOnClickListener( new View.OnClickListener(){
         @Override
         public void onClick (View view) {
             if (activo) { return; }
             activo = true;
             nuevoTimer(TIMER_EJERCICIO);
         }
        });

        terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                if (!activo) { return; }
                countDownTimer.cancel();
                ejercicio.setText("Iniciar");
                timer.setText("00:00");
                activo = false;
                descanso = false;
                iteracion = 1;
            }
        });
    }

    private void actualizaTimer (long millisUnTilFinished){
        int seg = (int) millisUnTilFinished/1000;
        char seg0 = String.valueOf(seg).charAt(0);
        char seg1 = 0;
        if (Integer.toString(seg).length() > 1) {
            seg1 = String.valueOf(seg).charAt(1);
        }
        int milseg = (int) millisUnTilFinished % 1000;
        char milseg0 = String.valueOf(milseg).charAt(0);
        char milseg1 = 0;
        if (Integer.toString(milseg).length() > 1) {
            milseg1 = String.valueOf(milseg).charAt(1);
        }
        String tiempoRestante = String.format(Locale.getDefault(), "%c%c:%c%c", seg0, seg1, milseg0, milseg1);
        timer.setText(tiempoRestante);
    }

    private void nuevoTimer (long tiempo) {
        mediaPlayer.start();
        if (descanso) {
            ejercicio.setText("Descanso");
        } else {
            ejercicio.setText("Ejercicio : " + Integer.toString(iteracion));
        }

        countDownTimer = new CountDownTimer(tiempo, 10) {
            public void onTick(long millisUnTilFinished) {
                actualizaTimer(millisUnTilFinished);
            }

            public void onFinish() {
                mediaPlayer.start();
                if (!descanso) {
                    iteracion++;
                }
                if (iteracion == 11) {
                    ejercicio.setText("Terminaste");
                    timer.setText("00:00");
                    activo = false;
                    descanso = false;
                    iteracion = 1;
                    return;
                }
                descanso = !descanso;
                if (descanso) {
                 nuevoTimer(TIMER_DESCANSO);
                } else {
                 nuevoTimer(TIMER_EJERCICIO);
                }
            }
        }.start();
    }

}
