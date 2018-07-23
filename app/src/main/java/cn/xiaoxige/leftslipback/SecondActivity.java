package cn.xiaoxige.leftslipback;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author by zhuxiaoan on 2018/7/20 0020.
 */

public class SecondActivity extends BaseActivity {
    private Button btnClick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btnClick = (Button) findViewById(R.id.btnClick);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this, "点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void leftSlipProgresss(float progress) {
        super.leftSlipProgresss(progress);
        Log.e("TAG", "progress = " + progress);
    }
}
