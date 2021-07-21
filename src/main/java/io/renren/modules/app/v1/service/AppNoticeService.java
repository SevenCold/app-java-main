package io.renren.modules.app.v1.service;

import com.gexin.rp.sdk.base.IPushResult;
import io.renren.modules.sys.form.AppNoticeForm;

import java.util.List;

/** 使用个推实现APP推送服务，包括离线和在线推送
 * @author 汪少
 * @date 2021/4/6 10:18
 */
public interface AppNoticeService {

    /**
     * 对所有用户群发推送消息
     * 此接口频次限制100次/天，每分钟不能超过5次（个推限制）
     * @param noticeForm
     * @return
     */
    IPushResult toApp(AppNoticeForm noticeForm);

    /**
     * 向单个用户发送消息，可根据cid或别名指定用户
     * @param cid
     * @param noticeForm
     * @return
     */
    IPushResult toSingle(String cid, AppNoticeForm noticeForm);

    /**
     * 对指定多个用户群发推送消息
     * @param cidList
     * @param noticeForm
     * @return
     */
    IPushResult toList(List<String> cidList, AppNoticeForm noticeForm);
}
