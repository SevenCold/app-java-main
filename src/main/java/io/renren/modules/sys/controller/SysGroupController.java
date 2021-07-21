package io.renren.modules.sys.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.SysGroupEntity;
import io.renren.modules.sys.service.SysGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;



/**
 * 分组管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-24 14:22:39
 */
@RestController
@RequestMapping("/sys/group")
public class SysGroupController extends AbstractController {
    @Autowired
    private SysGroupService sysGroupService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){

        PageUtils page = sysGroupService.queryPage(params, getUserId());

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{groupId}")
    public R info(@PathVariable("groupId") Long groupId){
		SysGroupEntity sysGroup = sysGroupService.getById(groupId);

        return R.ok().put("sysGroup", sysGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SysGroupEntity sysGroup){
        Long userId = getUserId();
        sysGroup.setUserId(userId);
        sysGroup.setCreateUserId(userId);
        sysGroup.setCreateTime(new Date());
		sysGroupService.save(sysGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SysGroupEntity sysGroup){
		sysGroupService.updateById(sysGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] groupIds){
		sysGroupService.removeByIds(Arrays.asList(groupIds));

        return R.ok();
    }

}
