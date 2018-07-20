package cn.xiaoxige.leftslipbacklibrary.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ViewDragHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import cn.xiaoxige.leftslipbacklibrary.R;
import cn.xiaoxige.leftslipbacklibrary.viewinterface.ILeftSlipBack;

/**
 * @author by zhuxiaoan on 2018/7/20 0020.
 *         Left slip agent layer
 */

public class LeftSlipAgentLayout extends FrameLayout {

    private Context mContext;

    private ViewDragHelper mDragHelp;
    private int mTouchSlop;
    private float mTouchX;
    private int mWidth;

    private ILeftSlipBack mLeftSlipBack;

    public LeftSlipAgentLayout(@NonNull Context context, ILeftSlipBack leftSlipBack) {
        super(context);
        this.mContext = context;
        this.mLeftSlipBack = leftSlipBack;
        setBackgroundColor(ContextCompat.getColor(context, R.color.colorTransparent));

        mDragHelp = ViewDragHelper.create(this, 1.0f, new ViewDragHelperCallBack());
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
    }

    private class ViewDragHelperCallBack extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return mWidth;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            left = Math.max(0, left);
            return left;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top,
                                          int dx, int dy) {

        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {

        }

    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchX = getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float disX = Math.abs(getX() - mTouchX);
                if (disX < mTouchSlop) {
                    return super.onInterceptTouchEvent(ev);
                }
                break;
            default:
                break;
        }
        return mDragHelp.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            mDragHelp.processTouchEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mLeftSlipBack.isOpen();
    }
}
