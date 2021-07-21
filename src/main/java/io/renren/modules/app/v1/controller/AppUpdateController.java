package io.renren.modules.app.v1.controller;

import io.renren.common.enums.ErrorMsgEnum;
import io.renren.common.utils.R;
import io.renren.common.utils.UploadUtils;
import io.renren.modules.app.v1.entity.AppVersionEntity;
import io.renren.modules.app.v1.enums.FileTypeEnum;
import io.renren.modules.app.v1.service.AppNoticeService;
import io.renren.modules.app.v1.service.AppVersionService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.form.AppNoticeForm;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * APP更新
 * @author kang
 */
@RestController
@RequestMapping("/v1/update")
@Api("APP更新使用接口")
public class AppUpdateController extends AbstractController {

    @Autowired
    private AppVersionService appVersionService;

    @Autowired
    private AppNoticeService appNoticeService;

    @Autowired
    private UploadUtils uploadUtils;

    @Value("${yjs.app.apkPath}")
    private String apkPath;

    /**
     * 上传安装包文件
     * @param file 文件
     * @return 返回下载路径
     */
    @PostMapping("/apk")
    @RequiresPermissions("app:publish")
    public R uploadApk(@RequestParam("file") MultipartFile file) {
        String suffix = uploadUtils.getSuffix(file);
        //上传文件
        if (!FileTypeEnum.APK.getType().equalsIgnoreCase(suffix)
                && !FileTypeEnum.WGT.getType().equalsIgnoreCase(suffix)) {
            return ErrorMsgEnum.ERROR_APK.getR();
        }
        String url = uploadUtils.upload(file, apkPath);
        return R.ok().put("url", url);
    }

    /**
     * 获取当前app最新版本号
     * @return 最新版本号
     */
    @GetMapping("/version")
    public R getLatestVersion() {
        return R.ok(appVersionService.getLatestAppVersionNo());
    }

    /**
     * 保存新版本信息，并发送系统通知
     * @return 发送成功通知
     */
    @PostMapping("/publish")
    @RequiresPermissions("app:publish")
    public R publish(@RequestBody AppVersionEntity entity) {
        appVersionService.save(entity);
        AppNoticeForm noticeForm = new AppNoticeForm();
        noticeForm.setTitle("版本更新");
        noticeForm.setContent(entity.getVersionInfo());
        return R.ok(appNoticeService.toApp(noticeForm));
    }
}
