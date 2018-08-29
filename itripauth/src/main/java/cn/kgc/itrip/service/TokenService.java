package cn.kgc.itrip.service;

import cn.kgc.itrip.model.ItripUser;

/**
 * @Auther: L.Y.Huang
 * @Date: Creaeted in  12:07 2018/8/29
 */
public interface TokenService {

    //构建Token
    public String generateToken(ItripUser itripUser,String userAgent);

    //保存Token
    public void saveToken(String token, ItripUser itripUser);
}
