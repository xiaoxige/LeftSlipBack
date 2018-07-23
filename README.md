# LeftSlipBack(简单侧滑关闭)

# 注意：
    1. 程序的主题请集成LeftSlipBack.theme
    2. 如果需要使用到了windowBackground， 请直接在color中定义colorDefaultBg的color即可（暂只支持颜色值）
# 使用：
    
## 1.初始化
    在Android的BaseActivity里初始化即可。eg:
    public class BaseActivity extends Activity implements ILeftSlipBack {
    
        @CallSuper
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            LeftSlipBack.takeEffect(this, this);
        }
    
        @Override
        public boolean isLeftSlipBackOpen() {
            return true;
        }
    
        @Override
        public void leftSlipProgresss(float progress) {
        }
    }
    
    其中 isLeftSlipBackOpen： 是否开启侧滑
         leftSlipProgresss： 界面滑动的进度（0~1）
         
## 2.使用
    在Activity中如果不需要侧滑的界面请重写 isLeftSlipBackOpen 并返回false即可
    需要监听进度并做一些操作的话， 请重写 leftSlipProgresss 即可