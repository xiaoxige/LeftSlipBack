package cn.xiaoxige.leftslipback;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    private Button btnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSkip = (Button) findViewById(R.id.btnSkip);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TAG", "MainActivity onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("TAG", "MainActivity onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("TAG", "MainActivity onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("TAG", "MainActivity onRestart()");

    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("TAG", "MainActivity onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("TAG", "MainActivity onDestroy()");
    }

    @Override
    public boolean isLeftSlipBackOpen() {
        return false;
    }
}
