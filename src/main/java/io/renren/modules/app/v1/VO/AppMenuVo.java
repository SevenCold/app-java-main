package io.renren.modules.app.v1.VO;

import lombok.Data;

import java.util.List;

/**
 * APP菜单树组件数据
 */
@Data
public class AppMenuVo{

    private long menuId;
    private String menuName;
    private List<AppMenuVo> children;
}
