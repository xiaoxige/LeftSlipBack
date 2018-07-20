package cn.xiaoxige.leftslipbacklibrary.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import cn.xiaoxige.leftslipbacklibrary.R;
import cn.xiaoxige.leftslipbacklibrary.viewinterface.ILeftSlipBack;

/**
 * @author by zhuxiaoan on 2018/7/20 0020.
 *         Left slip agent layer
 */

public class LeftSlipAgentLayout extends FrameLayout {

    private Context mContext;

    private View mContentView;
    private ViewDragHelper mDragHelp;
    private int mTouchSlop;
    private float mTouchX;
    boolean mIsStartLegitimate = false;
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

    @CallSuper
    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        super.addView(child, params);
        mContentView = child;
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
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            int left = mContentView.getLeft();
            if (left > mWidth >> 1) {
                handlerFinish();
            } else {
                handlerCancle();
            }
        }

    }

    @Override
    public void computeScroll() {
        if (mDragHelp.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        if (mContext != null && mContentView.getLeft() >= mWidth) {
            ((Activity) mContext).finish();
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mLeftSlipBack.isLeftSlipBackOpen() ? mDragHelp.shouldInterceptTouchEvent(ev) : super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchX = event.getRawX();
                mIsStartLegitimate = false;
                break;
            case MotionEvent.ACTION_MOVE:

                if (!mLeftSlipBack.isLeftSlipBackOpen()) {
                    return super.onTouchEvent(event);
                }

                //noinspection ConstantConditions
                mIsStartLegitimate = mIsStartLegitimate || Math.abs(event.getRawX() - mTouchX) > mTouchSlop && Math.abs(mTouchX - mContentView.getLeft()) < mTouchSlop;

                if (!mIsStartLegitimate) {
                    return super.onTouchEvent(event);
                }

                break;
            default:
                break;
        }

        try {
            mDragHelp.processTouchEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mLeftSlipBack.isLeftSlipBackOpen() || super.onTouchEvent(event);
    }


    private void handlerCancle() {
        if (mDragHelp.smoothSlideViewTo(mContentView, 0, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private void handlerFinish() {
        if (mDragHelp.smoothSlideViewTo(mContentView, mWidth, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
