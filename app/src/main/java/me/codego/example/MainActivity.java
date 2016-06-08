package me.codego.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import me.codego.library.OnEffectiveClickListener;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    private Button testBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testBtn = (Button) findViewById(R.id.btn_test);
        testBtn.setOnClickListener(new OnEffectiveClickListener(1000) {
            @Override
            public void onEffectiveClick(View view) {
                Log.d("MainActivity", "click button");
            }
        });
    }
}
