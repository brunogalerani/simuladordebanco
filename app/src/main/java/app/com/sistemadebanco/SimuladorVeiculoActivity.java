package app.com.sistemadebanco;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SimuladorVeiculoActivity extends AppCompatActivity {

    private Button buttonCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulador_veiculo);

        buttonCalcular = (Button) findViewById(R.id.buttonCalcularVeiculo);

        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SimuladorVeiculoActivity.this, FinalVeiculoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
