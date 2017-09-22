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

public class SimuladorResidenciaActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private EditText editTextValor;
    private EditText editTextPorcentagem;
    private EditText editTextParcelas;
    private EditText editTextRendaMensal;
    private Button buttonCalcular;

    boolean isNovo;
    double valorImóvel;
    double porcentagem;
    int parcelas;
    double rendaMensal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulador_residencia);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupResidencia);
        editTextValor = (EditText) findViewById(R.id.editTextValorResidencia);
        editTextPorcentagem = (EditText) findViewById(R.id.editTextEntradaResidencia);
        editTextParcelas = (EditText) findViewById(R.id.editTextQuantidadeDeParcelasResidencia);
        editTextRendaMensal = (EditText) findViewById(R.id.editTextRendaMensalResidencia);
        buttonCalcular = (Button) findViewById(R.id.buttonCalcularResidencia);

        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validaCampos()) {
                    isNovo = radioGroup.getCheckedRadioButtonId() == R.id.radioButtonResidenciaNovo;
                    valorImóvel = Double.parseDouble(editTextValor.getText().toString());
                    porcentagem = Double.parseDouble(editTextPorcentagem.getText().toString());
                    parcelas = Integer.parseInt(editTextParcelas.getText().toString());
                    rendaMensal = Double.parseDouble(editTextRendaMensal.getText().toString());

                    if (valorImóvel <= 0) {
                        Toast.makeText(SimuladorResidenciaActivity.this, "Valor do imóvel não pode ser nulo ou negativo!", Toast.LENGTH_SHORT).show();
                    } else if (porcentagem < 20 || porcentagem >= 100) {
                        Toast.makeText(SimuladorResidenciaActivity.this, "Valor de porcentagem de entrada deve ser entre 20% e 99.9%!", Toast.LENGTH_SHORT).show();
                    } else if (parcelas < 1 || parcelas > 48) {
                        Toast.makeText(SimuladorResidenciaActivity.this, "O número de parcelas deve estar entre 1 e 48!", Toast.LENGTH_SHORT).show();
                    } else if (rendaMensal <= 0) {
                        Toast.makeText(SimuladorResidenciaActivity.this, "Renda mensal não pode ser nula ou negativa!", Toast.LENGTH_SHORT).show();
                    } else {

                        double juros = realizaCalculos();
                        double valorTotal = juros + valorImóvel;
                        double entrada = (porcentagem / 100) * valorImóvel;

                        double valorParcelas = (valorTotal - entrada) / parcelas;
                        double maxParcela = (0.3 * rendaMensal);

                        boolean podePagar = valorParcelas < maxParcela;

                        Intent intent = new Intent(SimuladorResidenciaActivity.this, FinalResidenciaActivity.class);

                        intent.putExtra("VALOR_TOTAL", valorTotal);
                        intent.putExtra("PARCELAS", valorParcelas);
                        intent.putExtra("PODE_PAGAR", podePagar);

                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(SimuladorResidenciaActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
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
        double habite = 0;
        double transferencia = 0;
        double jurosPorRenda;
        double jurosTotal;

        if (isNovo) {
            habite = valorImóvel * 0.05;
        }else{
            transferencia = valorImóvel * 0.02;
        }

        if (rendaMensal <= 3500) {
            jurosPorRenda = 0.03;
        } else if (rendaMensal < 5000) {
            jurosPorRenda = 0.025;
        } else {
            jurosPorRenda = 0.020;
        }

        jurosPorRenda *= valorImóvel;

        jurosTotal = habite + transferencia + jurosPorRenda;

        return jurosTotal;
    }
}
