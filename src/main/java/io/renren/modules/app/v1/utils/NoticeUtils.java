package io.renren.modules.app.v1.utils;

import io.renren.modules.sys.form.AppNoticeForm;

/**
 * @author 汪少
 * @date 2021/4/7 16:25
 */
public class NoticeUtils {

    /**
     * 通知管理员审批新用户注册
     * @return
     */
    public static AppNoticeForm noticeToApprove() {
        AppNoticeForm noticeForm = new AppNoticeForm();
        noticeForm.setTitle("用户审批");
        noticeForm.setContent("有新用户注册，请尽快审批！");
        return noticeForm;
    }

    /**
     * 通知新注册用户，账号已审批通过
     * @return
     */
    public static AppNoticeForm noticeApproveResult() {
        AppNoticeForm noticeForm = new AppNoticeForm();
        noticeForm.setTitle("注册结果");
        noticeForm.setContent("恭喜您，您的账号已审批通过！");
        return noticeForm;
    }
}
