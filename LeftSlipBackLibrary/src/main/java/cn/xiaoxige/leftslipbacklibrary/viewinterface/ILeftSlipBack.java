package cn.xiaoxige.leftslipbacklibrary.viewinterface;

/**
 * @author by zhuxiaoan on 2018/7/20 0020.
 *         Left slip closed monitor
 */

public interface ILeftSlipBack {

    /**
     * Whether to open the left slip and turn off
     *
     * @return
     */
    boolean isOpen();

    /**
     * Speed of progress
     *
     * @param progress
     */
    void leftSlipProgresss(float progress);


}
