package cn.kgc.itrip.controller;

import cn.kgc.itrip.common.Dto;
import cn.kgc.itrip.common.DtoUtil;
import cn.kgc.itrip.model.ItripUser;
import cn.kgc.itrip.service.ItripUserService;
import cn.kgc.itrip.service.TokenService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Auther: L.Y.Huang
 * @Date: Creaeted in  12:00 2018/8/28
 */
@Controller
@RequestMapping("/api/user")
public class ItripUserController {

    @Resource
    private ItripUserService itripUserService;

//    @Resource
//    private TokenService tokenService;


    @RequestMapping("/getById/{userId}")
    @ResponseBody
    public Dto getById(@PathVariable("userId") Long userId){
        try {
            ItripUser itripUser = itripUserService.getById(userId);
            return DtoUtil.returnDataSuccess(itripUser);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("查询异常","1001");
        }
    }


}
