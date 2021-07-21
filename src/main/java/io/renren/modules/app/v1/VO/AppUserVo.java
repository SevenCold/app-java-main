package io.renren.modules.app.v1.VO;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class AppUserVo {
    private Long userId;
    private String username;
    private String nickName;
    private String email;
    private String mobile;
    private String token;
    /**
     * app推送设备id
     */
    private String cid;

    /**
     * 用户头像
     */
    private String avatar;

    private Set<String> permList;

    private List<Long> roleIdList;
}
