package app.com.sistemadebanco;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SimuladorVeiculoActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private EditText editTextValor;
    private EditText editTextPorcentagem;
    private EditText editTextParcelas;
    private EditText editTextRendaMensal;
    private Button buttonCalcular;

    boolean isNovo;
    double valorCarro;
    double porcentagem;
    int parcelas;
    double rendaMensal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulador_veiculo);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupVeiculos);
        editTextValor = (EditText) findViewById(R.id.editTextValorVeiculo);
        editTextPorcentagem = (EditText) findViewById(R.id.editTextEntradaVeiculo);
        editTextParcelas = (EditText) findViewById(R.id.editTextQuantidadeDeParcelasVeiculo);
        editTextRendaMensal = (EditText) findViewById(R.id.editTextRendaMensalVeiculo);
        buttonCalcular = (Button) findViewById(R.id.buttonCalcularVeiculo);

        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validaCampos()) {
                    isNovo = radioGroup.getCheckedRadioButtonId() == R.id.radioButtonVeiculoNovo;
                    valorCarro = Double.parseDouble(editTextValor.getText().toString());
                    porcentagem = Double.parseDouble(editTextPorcentagem.getText().toString());
                    parcelas = Integer.parseInt(editTextParcelas.getText().toString());
                    rendaMensal = Double.parseDouble(editTextRendaMensal.getText().toString());

                    if (valorCarro <= 0) {
                        Toast.makeText(SimuladorVeiculoActivity.this, "Valor do carro não pode ser nulo ou negativo!", Toast.LENGTH_SHORT).show();
                    } else if (porcentagem < 5 || porcentagem >= 100) {
                        Toast.makeText(SimuladorVeiculoActivity.this, "Valor de porcentagem de entrada deve ser entre 5% e 99.9%!", Toast.LENGTH_SHORT).show();
                    } else if (parcelas < 1 || parcelas > 48) {
                        Toast.makeText(SimuladorVeiculoActivity.this, "O número de parcelas deve estar entre 1 e 48!", Toast.LENGTH_SHORT).show();
                    } else if (rendaMensal <= 0) {
                        Toast.makeText(SimuladorVeiculoActivity.this, "Renda mensal não pode ser nula ou negativa!", Toast.LENGTH_SHORT).show();
                    } else {

                        double juros = realizaCalculos();
                        double valorTotal = juros + valorCarro;
                        double entrada = (porcentagem / 100) * valorCarro;

                        double valorParcelas = (valorTotal - entrada) / parcelas;
                        double maxParcela = (0.3 * rendaMensal);

                        boolean podePagar = valorParcelas < maxParcela;

                        Intent intent = new Intent(SimuladorVeiculoActivity.this, FinalVeiculoActivity.class);

                        intent.putExtra("VALOR_TOTAL", valorTotal);
                        intent.putExtra("PARCELAS", valorParcelas);
                        intent.putExtra("PODE_PAGAR", podePagar);

                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(SimuladorVeiculoActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validaCampos() {
        return !(
                radioGroup.getCheckedRadioButtonId() == -1 ||
                        editTextValor.getText().toString().isEmpty() ||
                        editTextPorcentagem.getText().toString().isEmpty() ||
                        editTextParcelas.getText().toString().isEmpty() ||
                        editTextRendaMensal.getText().toString().isEmpty()
        );
    }

    private double realizaCalculos() {
        double jurosEmplacamento = 0;
        double taxaIPVA = 0;
        double jurosPorRenda;
        double jurosTotal;

        if (isNovo) {
            jurosEmplacamento = valorCarro * 0.01;
            taxaIPVA = valorCarro * 0.04;
        }

        if (rendaMensal <= 3500) {
            jurosPorRenda = 0.06;
        } else if (rendaMensal < 5000) {
            jurosPorRenda = 0.05;
        } else {
            jurosPorRenda = 0.04;
        }

        jurosPorRenda *= valorCarro;

        jurosTotal = jurosEmplacamento + taxaIPVA + jurosPorRenda;

        return jurosTotal;
    }
}
