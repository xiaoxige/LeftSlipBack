package cn.xiaoxige.leftslipback;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import cn.xiaoxige.leftslipbacklibrary.LeftSlipBack;
import cn.xiaoxige.leftslipbacklibrary.viewinterface.ILeftSlipBack;

/**
 * @author by zhuxiaoan on 2018/7/20 0020.
 */

public class BaseActivity extends Activity implements ILeftSlipBack {

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LeftSlipBack.takeEffect(this, this);

    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public void leftSlipProgresss(float progress) {
    }
}
