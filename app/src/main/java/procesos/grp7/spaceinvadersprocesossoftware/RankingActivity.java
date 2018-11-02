package procesos.grp7.spaceinvadersprocesossoftware;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class RankingActivity extends AppCompatActivity {

    private String puntos;
    private String name;

    private Usuario[] puntuaciones;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        Intent intent = getIntent();
        name = intent.getStringExtra("EXTRA_MESSAGE");
        puntos = intent.getStringExtra("EXTRA_MESSAGE2");
        puntuaciones = new Usuario[10];
        Usuario user = new Usuario(name, puntos);
        sacarPuntuaciones();
        leerFile();
        colocarUsuario(user);
        writeFile();
        puntuar();


    }

    public void sacarPuntuaciones() {
        for (int i = 0; i < 10; i++) {
            puntuaciones[i] = new Usuario("Vacío", 0 + "");
        }
    }

    public void colocarUsuario(Usuario user) {
        int i = 9;
        while ((i >= 0) && user.compareTo(puntuaciones[i]) >= 0) {
            i--;
        }
        i++;
        if (i >= 0) {

            if (i != 9)
                for (int j = 8; j >= i; j--) {
                    puntuaciones[j + 1] = puntuaciones[j];
                }
            puntuaciones[i] = user;
        }
    }

    public void puntuar() {
        TextView puntos = findViewById(R.id.punt);
        puntos.setText(("1- "+puntuaciones[0].getMessage()));
        TextView puntos0 = findViewById(R.id.punt2);
        puntos0.setText(("2- "+puntuaciones[1].getMessage()));
        TextView puntos1 = findViewById(R.id.punt3);
        puntos1.setText(("3- "+puntuaciones[2].getMessage()));
        TextView puntos2 = findViewById(R.id.punt4);
        puntos2.setText(("4- "+puntuaciones[3].getMessage()));
        TextView puntos3 = findViewById(R.id.punt5);
        puntos3.setText(("5- "+puntuaciones[4].getMessage()));
        TextView puntos4 = findViewById(R.id.punt6);
        puntos4.setText(("6- "+puntuaciones[5].getMessage()));
        TextView puntos5 = findViewById(R.id.punt7);
        puntos5.setText(("7- "+puntuaciones[6].getMessage()));
        TextView puntos6 = findViewById(R.id.punt8);
        puntos6.setText(("8- "+puntuaciones[7].getMessage()));
        TextView puntos7 = findViewById(R.id.punt9);
        puntos7.setText(("9- "+puntuaciones[8].getMessage()));
        TextView puntos8 = findViewById(R.id.punt10);
        puntos8.setText(("10- "+puntuaciones[9].getMessage()));
    }


    public void leerFile() {
        try {
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    openFileInput("puntos.txt")));

            for (int i = 0; i < 10; i++) {
                puntuaciones[i] = new Usuario(fin.readLine(), fin.readLine());
            }


            fin.close();
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al leer fichero desde recurso raw");
        }
    }

    public void writeFile() {
        try {
            OutputStreamWriter fout =
                    new OutputStreamWriter(
                            openFileOutput("puntos.txt", Context.MODE_PRIVATE));

            for (int i = 0; i < 10; i++) {
                fout.write((puntuaciones[i].getNombre() + "\n"));
                fout.write((puntuaciones[i].getPunts() + "\n"));
            }
            fout.close();
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero a memoria interna");
        }


    }
    public void GoTOMenu(View view) {

        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);

        finish();
    }

    public void RestartGame(View view) {

        Intent intent = new Intent(this, GameActivity.class);
        String extra = "0";
        intent.putExtra("EXTRA_MESSAGE", extra);
        startActivityForResult(intent, 1);
        finish();
    }
}
