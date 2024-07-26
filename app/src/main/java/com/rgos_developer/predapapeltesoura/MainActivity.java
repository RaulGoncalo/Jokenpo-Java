package com.rgos_developer.predapapeltesoura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int contadorUsuario = 0;
    private int contadorComputador = 0;

    private ImageView selectedImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selecionarPedra(View view){
        setSelectedImage(view);
        verificarGanhador("pedra");
    }

    public void selecionarPapel(View view){
        setSelectedImage(view);
        verificarGanhador("papel");
    }

    public void selecionarTesoura(View view){
        setSelectedImage(view);
        verificarGanhador("tesoura");
    }

    public void reiniciarJogo(View view){
        this.contadorUsuario = 0;
        this.contadorComputador = 0;

        TextView textoResultado = findViewById(R.id.text_view_resultado);
        TextView placarUsuario = findViewById(R.id.pontuacao_usuario);
        TextView placarComputador = findViewById(R.id.pontuacao_computador);
        ImageView imageApp = findViewById(R.id.imageResult);

        imageApp.setImageResource(R.drawable.padrao);
        textoResultado.setText(R.string.result);
        placarUsuario.setText(R.string.scoreboard);
        placarComputador.setText(R.string.scoreboard);

        if (selectedImageView != null) {
            selectedImageView.clearColorFilter();
            selectedImageView = null;
        }
    }

    private void verificarGanhador(String escolhadoUsuario){


        String escolhaDoApp = gerarEscolhaAleatoriaApp();

        TextView textoResultado = findViewById(R.id.text_view_resultado);
        TextView placarUsuario = findViewById(R.id.pontuacao_usuario);
        TextView placarComputador = findViewById(R.id.pontuacao_computador);

        //App ganhador
        if(
                (escolhaDoApp.equals("pedra") && escolhadoUsuario.equals("tesoura")) ||
                (escolhaDoApp.equals("papel") && escolhadoUsuario.equals("pedra")) ||
                (escolhaDoApp.equals("tesoura") && escolhadoUsuario.equals("papel"))
        ){
            placarComputador.setText(String.valueOf(this.contadorComputador += 1));
            textoResultado.setText("Você perdeu! :(");
        }else if(
                (escolhadoUsuario.equals("pedra") && escolhaDoApp.equals("tesoura")) ||
                (escolhadoUsuario.equals("papel") && escolhaDoApp.equals("pedra")) ||
                (escolhadoUsuario.equals("tesoura") && escolhaDoApp.equals("papel"))
        ){ // Usuario ganhador
            placarUsuario.setText(String.valueOf(this.contadorUsuario += 1));
            textoResultado.setText("Você ganhou! :)");

        }else{
            textoResultado.setText("Empatou! ;)");
        }
    }

    private String gerarEscolhaAleatoriaApp() {
        String[] opcoes = {"pedra", "papel", "tesoura"};
        int numeroAleatorio = new Random().nextInt(3);

        String escolhaDoApp = opcoes[numeroAleatorio];

        ImageView imageApp = findViewById(R.id.imageResult);

        switch(escolhaDoApp){
            case "pedra":
                imageApp.setImageResource(R.drawable.pedra);
                break;
            case "papel":
                imageApp.setImageResource(R.drawable.papel);
                break;
            case "tesoura":
                imageApp.setImageResource(R.drawable.tesoura);
                break;
        }

        return escolhaDoApp;
    }

    private void setSelectedImage(View view) {
        if (selectedImageView != null) {
            selectedImageView.clearColorFilter();
        }
        selectedImageView = (ImageView) view;
        selectedImageView.setColorFilter(getColor(R.color.selected_color));
    }
}