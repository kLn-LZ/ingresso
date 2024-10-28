package br.edu.fateczl.ingresso;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.edu.fateczl.ingresso.Model.Ingresso;
import br.edu.fateczl.ingresso.Model.IngressoVIP;

public class MainActivity extends AppCompatActivity {

    /*
     *@author: Kelvin Santos Guimarães
     */

    private EditText etCodigoIdentificador, etValorIngresso, etTaxaConveniencia;
    private RadioGroup rgTipoIngresso;
    private RadioButton rbIngressoComum, rbIngressoVIP;
    private Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etCodigoIdentificador = findViewById(R.id.etCodigoIdentificador);
        etValorIngresso = findViewById(R.id.etValorIngresso);
        etTaxaConveniencia = findViewById(R.id.etTaxaConveniencia);
        rgTipoIngresso = findViewById(R.id.rgTipoIngresso);
        rbIngressoComum = findViewById(R.id.rbIngressoComum);
        rbIngressoVIP = findViewById(R.id.rbIngressoVIP);
        btnCalcular = findViewById(R.id.btnCalcular);

        rgTipoIngresso.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.rbIngressoComum) {
                etValorIngresso.setHint("Valor do Ingresso Comum");
            } else if (id == R.id.rbIngressoVIP) {
                etValorIngresso.setHint("Valor do Ingresso VIP");
            }
        });

        btnCalcular.setOnClickListener(op -> {
            String codigoIdentificador = etCodigoIdentificador.getText().toString();
            float valorIngresso = Float.parseFloat(etValorIngresso.getText().toString());
            float taxaConveniencia = Float.parseFloat(etTaxaConveniencia.getText().toString());
            float valorFinal;

            if (rbIngressoComum.isChecked()) {
                Ingresso ingressoComum = new Ingresso(codigoIdentificador, valorIngresso);
                valorFinal = ingressoComum.valorFinal(taxaConveniencia);
            } else {
                IngressoVIP ingressoVIP = new IngressoVIP(codigoIdentificador, valorIngresso, "Acesso VIP");
                valorFinal = ingressoVIP.valorFinal(taxaConveniencia);
            }

            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putFloat("valorFinal", valorFinal);
            bundle.putString("codigoIdentificador", codigoIdentificador);
            intent.putExtras(bundle);
            startActivity(intent);
            this.finish();
        });

    }
}