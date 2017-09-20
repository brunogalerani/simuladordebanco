package app.com.sistemadebanco;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioGroup.OnCheckedChangeListener checkListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupInicial);

        checkListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                Intent intent;
                switch (i) {
                    case R.id.radioButtonResidencia:
                        intent = new Intent(MainActivity.this, SimuladorResidenciaActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.radioButtonVeiculos:
                        intent = new Intent(MainActivity.this, SimuladorVeiculoActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        };

        radioGroup.setOnCheckedChangeListener(checkListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        uncheckAllRadios();
    }

    public void uncheckAllRadios(){

        radioGroup.setOnCheckedChangeListener(null);
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(checkListener);
    }
}
