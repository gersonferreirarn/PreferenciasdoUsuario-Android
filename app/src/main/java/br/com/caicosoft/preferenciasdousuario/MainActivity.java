package br.com.caicosoft.preferenciasdousuario;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etNome;
    Button btnSalvar;
    TextView tvResultado;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia"; // VALOR SEMPRE O MESMO APESAR DAS INTANCIAS, VALOR IMUTAVEL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = findViewById(R.id.etNome);
        btnSalvar = findViewById(R.id.btnSalvar);
        tvResultado = findViewById(R.id.tvResultado);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0); // MODE = 0 EH PRIVADO, OU SEJA, SO O NOSSO APP TEM ACESSO. Basicamente cria arquivo XML e salva as informações. RECOMENDADO PARA SALVAR PEQUENAS INFORMAÇÕES DO USUARIO
                SharedPreferences.Editor editor = preferences.edit();

                // validar o nome
                if(etNome.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Preencha o nome!", Toast.LENGTH_LONG).show();
                }else{
                    String nome = etNome.getText().toString();
                    editor.putString("nome", nome);
                    editor.commit(); // salva os dados
                    tvResultado.setText(nome);
                }
            }
        });

        // recupera dados salvos
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0); // MODE = 0 EH PRIVADO, OU SEJA, SO O NOSSO APP TEM ACESSO. Basicamente cria arquivo XML e salva as informações. RECOMENDADO PARA SALVAR PEQUENAS INFORMAÇÕES DO USUARIO

        // valida se existe nome em preferencias
        if(preferences.contains("nome")){
            String nome = preferences.getString("nome","Não encontrado");
            tvResultado.setText("Olá, "+nome);
        }
    }
}
