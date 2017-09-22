package app.com.sistemadebanco;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalVeiculoActivity extends AppCompatActivity {

    private TextView textViewValorTotal;
    private TextView textViewValorParcelas;
    private Button buttonNovaSimulacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_veiculo);

        textViewValorTotal = (TextView) findViewById(R.id.TextViewValorFinalVeiculo);
        textViewValorParcelas = (TextView) findViewById(R.id.TextViewValorParcelasVeiculo);
        buttonNovaSimulacao = (Button) findViewById(R.id.buttonNovaSimulacaoVeiculo);

        Intent intent = getIntent();
        double renda = intent.getDoubleExtra("RENDA", 0);
        double valor = intent.getDoubleExtra("VALOR_TOTAL", 0);
        int parcelas = intent.getIntExtra("N_PARCELAS", 0);
        double entrada = intent.getDoubleExtra("ENTRADA", 0);

        double valorParcelas = (valor - entrada) / parcelas;
        double maxParcela = (0.3 * renda) + renda;

        if (valorParcelas > maxParcela) {
            textViewValorTotal.setText("Valor de parcelas superior a 30% da renda mensal!");
            textViewValorParcelas.setText("");
        } else {
            textViewValorTotal.setText(textViewValorTotal.getText().toString() + String.valueOf(valor));
            textViewValorParcelas.setText(textViewValorParcelas.getText().toString() + String.valueOf(valorParcelas));
        }

        buttonNovaSimulacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinalVeiculoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
