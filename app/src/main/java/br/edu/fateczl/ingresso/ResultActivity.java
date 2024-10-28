package br.edu.fateczl.ingresso;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {
    /*
     *@author: Kelvin Santos Guimarães
     */
    private TextView tvDetalhesIngresso;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvDetalhesIngresso = findViewById(R.id.tvDetalhesIngresso);
        btnVoltar = findViewById(R.id.btnVoltar);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            float valorFinal = bundle.getFloat("valorFinal");
            String codigoIdentificador = bundle.getString("codigoIdentificador");
            tvDetalhesIngresso.setText("Código: " + codigoIdentificador + "\nValor Final do Ingresso: R$ " + valorFinal);
        }

        btnVoltar.setOnClickListener(op -> {
            Intent i = new Intent(this, MainActivity.class);
            this.startActivity(i);
            this.finish();
        });


    }
}