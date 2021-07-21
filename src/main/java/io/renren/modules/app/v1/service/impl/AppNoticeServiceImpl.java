package io.renren.modules.app.v1.service.impl;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.AbstractNotifyStyle;
import com.gexin.rp.sdk.template.style.Style0;
import io.renren.modules.app.v1.service.AppNoticeService;
import io.renren.modules.sys.form.AppNoticeForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 汪少
 * @date 2021/4/6 10:19
 */
@Slf4j
@Service("AppNoticeService")
public class AppNoticeServiceImpl implements AppNoticeService {


    @Value("${yjs.app.appId}")
    private String appId;
    @Value("${yjs.app.appKey}")
    private String appKey;
    @Value("${yjs.app.masterSecret}")
    private String masterSecret;
    @Value("${yjs.app.url}")
    private String url;
    @Value("${yjs.app.pushOffTime}")
    private String pushOffTime;

    @Override
    public IPushResult toApp(AppNoticeForm noticeForm) {
        Style0 style = getStyle0(noticeForm);
        List<String> appIds = new ArrayList<>();
        appIds.add(appId);
        AppMessage message = new AppMessage();
        NotificationTemplate template = getTemplate(style);
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(Integer.valueOf(pushOffTime) * 3600 * 1000);
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        IPushResult ret = push.pushMessageToApp(message);
        log.info("【APP】发送系统通知, 标题为【{}】, 内容为【{}】。",
                noticeForm.getTitle(), noticeForm.getContent());
        return ret;
    }

    @Override
    public IPushResult toSingle(String cid, AppNoticeForm noticeForm) {
        Style0 style = getStyle0(noticeForm);
        List<String> appIds = new ArrayList<>();
        appIds.add(appId);
        SingleMessage singleMessage = new SingleMessage();
        NotificationTemplate template = getTemplate(style);
        singleMessage.setData(template);
        singleMessage.setOffline(true);
        singleMessage.setOfflineExpireTime(Integer.valueOf(pushOffTime) * 3600 * 1000);
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        Target target = new Target();
        target.setClientId(cid);
        target.setAppId(appId);
        IPushResult ret;
        try {
            ret = push.pushMessageToSingle(singleMessage, target);
        } catch (RequestException e) {
            e.printStackTrace();
            log.info("【APP】发送单个系统通知错误, 标题为【{}】, 内容为【{}】， exception为【{}】。",
                    noticeForm.getTitle(), noticeForm.getContent(), e.getMessage());
            ret = push.pushMessageToSingle(singleMessage, target, e.getRequestId());
        }
        log.info("【APP】发送系统通知, 标题为【{}】, 内容为【{}】。",
                noticeForm.getTitle(), noticeForm.getContent());
        return ret;
    }

    @Override
    public IPushResult toList(List<String> cidList, AppNoticeForm noticeForm) {
        if (CollectionUtils.isEmpty(cidList)) {
            return null;
        }
        List<Target> targetList = cidList.stream().map(e -> {
            Target target = new Target();
            target.setClientId(e);
            target.setAppId(appId);
            return target;
        }).collect(Collectors.toList());
        ListMessage listMessage = new ListMessage();
        NotificationTemplate template = getTemplate(getStyle0(noticeForm));
        listMessage.setData(template);
        listMessage.setOffline(true);
        listMessage.setOfflineExpireTime(Integer.valueOf(pushOffTime) * 3600 * 1000);
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        // taskId用于在推送时去查找对应的message
        String taskId = push.getContentId(listMessage);
        IPushResult ret = push.pushMessageToList(taskId, targetList);
        return ret;
    }

    private Style0 getStyle0(AppNoticeForm noticeForm) {
        Style0 style = new Style0();
        style.setTitle(noticeForm.getTitle());
        style.setText(noticeForm.getContent());
        // 设置推送图标
        //style.setLogo("push.png");
        // 设置响铃
        style.setRing(noticeForm.isRing());
        // 设置震动
        style.setVibrate(noticeForm.isVibrate());
        style.setClearable(true);
        return style;
    }

    private NotificationTemplate getTemplate(AbstractNotifyStyle style) {
        NotificationTemplate template = new NotificationTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setStyle(style);
        return template;
    }
}
