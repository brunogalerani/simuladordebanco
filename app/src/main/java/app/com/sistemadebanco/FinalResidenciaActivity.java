package app.com.sistemadebanco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalResidenciaActivity extends AppCompatActivity {

    private TextView textViewValorTotal;
    private TextView textViewValorParcelas;
    private Button buttonNovaSimulacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_residencia);

        textViewValorTotal = (TextView) findViewById(R.id.TextViewValorFinalResidencia);
        textViewValorParcelas = (TextView) findViewById(R.id.TextViewValorParcelasResidencia);
        buttonNovaSimulacao = (Button) findViewById(R.id.buttonNovaSimulacaoResidencia);

        Intent intent = getIntent();
        double valor = intent.getDoubleExtra("VALOR_TOTAL", 0);
        double valorParcelas = intent.getDoubleExtra("PARCELAS", 0);
        boolean podePagar = intent.getBooleanExtra("PODE_PAGAR", true);

        if (!podePagar) {
            textViewValorTotal.setText("Valor de parcelas superior a 30% da renda mensal!");
            textViewValorParcelas.setText("");
        } else {
            textViewValorTotal.setText(textViewValorTotal.getText().toString() + String.valueOf(valor));
            textViewValorParcelas.setText(textViewValorParcelas.getText().toString() + String.valueOf(valorParcelas));
        }

        buttonNovaSimulacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinalResidenciaActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
