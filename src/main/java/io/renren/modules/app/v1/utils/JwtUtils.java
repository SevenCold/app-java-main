package io.renren.modules.app.v1.utils;

import cn.hutool.json.JSONUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.renren.common.enums.ErrorMsgEnum;
import io.renren.common.enums.UserStatusEnum;
import io.renren.common.exception.RRException;
import io.renren.common.utils.RedisKeys;
import io.renren.common.utils.RedisUtils;
import io.renren.modules.app.v1.entity.AppUserEntity;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * jwt工具类
 *
 * @author Mark sunlightcs@gmail.com
 */
@ConfigurationProperties(prefix = "renren.jwt")
@Component
public class JwtUtils {


    @Autowired
    private RedisUtils redisUtils;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String secret;
    private long expire;
    private String header;

    /**
     * 生成jwt token
     */
    public String generateToken(long userId) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId+"")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims getClaimByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            logger.debug("validate is token error ", e);
            return null;
        }
    }

    public Long validateToken(HttpServletRequest request) {
        //获取用户凭证
        String token = request.getHeader(header);
        if(StringUtils.isBlank(token)){
            token = request.getParameter(header);
        }
        //凭证为空
        if(StringUtils.isBlank(token)){
            throw new RRException(ErrorMsgEnum.UNAUTHORIZED.getMsg(), HttpStatus.UNAUTHORIZED.value());
        }

        Claims claims = getClaimByToken(token);
        if(claims == null || isTokenExpired(claims.getExpiration())){
            throw new RRException(ErrorMsgEnum.UNAUTHORIZED.getMsg(), HttpStatus.UNAUTHORIZED.value());
        }
        //设置userId到request里，后续根据userId，获取用户信息
        long userId = Long.parseLong(claims.getSubject());
        // 查询redis 账号状态
        String redisInfo = redisUtils.get(RedisKeys.getUserInfoKey(userId));
        if (StringUtils.isEmpty(redisInfo)) {
            throw new RRException(ErrorMsgEnum.UNAUTHORIZED.getMsg(), HttpStatus.UNAUTHORIZED.value());
        }
        AppUserEntity userEntity = JSONUtil.toBean(redisInfo, AppUserEntity.class);
        if (userEntity != null) {
            // 账号被禁
            if (UserStatusEnum.DISABLE.getValue() == userEntity.getStatus()) {
                throw new RRException(ErrorMsgEnum.ACCOUNT_DIS.getMsg(), HttpStatus.UNAUTHORIZED.value());
            }
            // 查看是否被踢下线
            String oldToken = userEntity.getOldToken();
            if (StringUtils.isNotEmpty(oldToken)) {
                // 清空重置oldToken
                userEntity.setOldToken("");
                redisUtils.set(RedisKeys.getUserInfoKey(userId), userEntity);
                // 删除oldToken key
                redisUtils.delete(RedisKeys.getSysUserTokenKey(oldToken));
                throw new RRException(ErrorMsgEnum.KICKED_OFF.getMsg(), HttpStatus.UNAUTHORIZED.value());
            }
        }
        return userId;
    }

    /**
     * token是否过期
     * @return  true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
