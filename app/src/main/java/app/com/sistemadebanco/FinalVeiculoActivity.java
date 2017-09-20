package app.com.sistemadebanco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class FinalVeiculoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_veiculo);

        Intent intent = getIntent();
        double valor = intent.getDoubleExtra("VALOR", 0);
        Toast.makeText(this, String.valueOf(valor), Toast.LENGTH_SHORT).show();
    }
}
