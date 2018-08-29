package cn.kgc.itrip.service.impl;

import cn.kgc.itrip.common.DigestUtil;
import cn.kgc.itrip.common.RedisAPI;
import cn.kgc.itrip.common.UserAgentUtil;
import cn.kgc.itrip.model.ItripUser;
import cn.kgc.itrip.service.TokenService;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: L.Y.Huang
 * @Date: Creaeted in  12:09 2018/8/29
 */
@Service("tokenService")
public class TokenServiceImpl implements TokenService {
    @Resource
    private RedisAPI redisAPI;

    @Override
    public String generateToken(ItripUser itripUser, String userAgent) {
        StringBuffer sbToken = new StringBuffer("token:");
        //客户端标识 PC  MOBILE
        if(UserAgentUtil.CheckAgent(userAgent))
            sbToken.append("MOBILE-");
        else
            sbToken.append("PC-");

        sbToken.append(DigestUtil.hmacSign(itripUser.getUserCode())+"-");
        sbToken.append(itripUser.getId()+"-");
        String dateTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        sbToken.append(dateTime+"-");
        String md5UserAgent = DigestUtil.hmacSign(userAgent).substring(0,6);
        sbToken.append(md5UserAgent);
        return sbToken.toString();
    }

    @Override
    public void saveToken(String token, ItripUser itripUser) {
        String itripUserJSON = JSON.toJSONString(itripUser);
        if (token.indexOf("PC") != -1){
            redisAPI.set(token , itripUserJSON , 60*60*2);
        }else {
            redisAPI.set(token, itripUserJSON);
        }
    }
}
