package io.renren.modules.sys.VO;

import lombok.Data;

import java.util.List;

@Data
public class SysUserVo {
    private Long userId;
    private String username;
    private String email;
    private String mobile;
    private String token;

    private List<String> permList;
}
