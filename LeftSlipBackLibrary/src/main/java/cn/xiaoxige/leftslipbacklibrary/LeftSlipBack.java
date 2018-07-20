package cn.xiaoxige.leftslipbacklibrary;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import cn.xiaoxige.leftslipbacklibrary.view.LeftSlipAgentLayout;
import cn.xiaoxige.leftslipbacklibrary.viewinterface.ILeftSlipBack;

/**
 * @author by zhuxiaoan on 2018/7/20 0020.
 */

public class LeftSlipBack {


    public static void takeEffect(Activity activity, ILeftSlipBack leftSlipBack) {
        View decorView = activity.getWindow().getDecorView();
        if (decorView == null) {
            throw new RuntimeException("Please confirm whether the layout is set up...");
        }

        ViewGroup rootView = (ViewGroup) decorView;

        reorganize(rootView, new LeftSlipAgentLayout(activity, leftSlipBack), breakTheRelationship(rootView));

    }

    /**
     * Break the relationship
     *
     * @param rootView
     * @return
     */
    private static View breakTheRelationship(ViewGroup rootView) {
        View view = rootView.getChildAt(0);
        rootView.removeView(view);
        return view;
    }

    /**
     * Reorganize the relationship
     *
     * @param rootView
     * @param leftSlipAgentLayout
     * @param contentView
     */
    private static void reorganize(ViewGroup rootView, LeftSlipAgentLayout leftSlipAgentLayout, View contentView) {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        leftSlipAgentLayout.addView(contentView, params);
        rootView.addView(leftSlipAgentLayout, params);
    }

}
