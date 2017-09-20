package app.com.sistemadebanco;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
                    boolean isNovo = radioGroup.getCheckedRadioButtonId() == R.id.radioButtonVeiculoNovo;
                    double valor = Double.parseDouble(editTextValor.getText().toString());
                    double porcentagem = Double.parseDouble(editTextPorcentagem.getText().toString());
                    int parcelas = Integer.parseInt(editTextParcelas.getText().toString());
                    double rendaMensal = Double.parseDouble(editTextRendaMensal.getText().toString());

                    Intent intent = new Intent(SimuladorVeiculoActivity.this, FinalVeiculoActivity.class);
                    intent.putExtra("IS_NOVO", isNovo);
                    intent.putExtra("VALOR", valor);
                    intent.putExtra("PORCENTAGEM", porcentagem);
                    intent.putExtra("PARCELAS", parcelas);
                    intent.putExtra("RENDA_MENSAL", rendaMensal);

                    startActivity(intent);
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
}
