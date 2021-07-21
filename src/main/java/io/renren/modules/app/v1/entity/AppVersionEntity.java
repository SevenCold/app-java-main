package io.renren.modules.app.v1.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("app_version")
public class AppVersionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    @TableId
    private Long id;

    /**
     * 版本号
     */
    private String versionCode;

    /**
     * 版本名称
     */
    private String versionName;

    private String versionInfo;

    private boolean forceUpdate;

    private String downloadUrl;

    private Date createTime;

}
