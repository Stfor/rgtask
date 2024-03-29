package com.example.rgtask.utils;

import com.example.rgtask.pojo.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils implements InitializingBean {


    // 过期时间5分钟
    private static  long EXPIRE_TIME = 30*1000;

    //自己定制密钥
    public static  String SECRET;

    //请求头
    public static  String AUTH_HEADER;

    @Value("secret")
    private String secret;
    @Value("auth_header")
    private String authHeader;
    /**
     * 验证token是否正确
     * @param token
     * @param
     * @param
     * @return
     */
    public static boolean verify(String token, User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("userId",user.getId())
                    .withClaim("userName",user.getLoginName())
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception){
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获得token中的自定义信息,一般是获取token的username，无需secret解密也能获得
     * @param token
     * @param filed
     * @return
     */
    public static String getClaimFiled(String token, String filed){
        try{
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(filed).asString();
        } catch (JWTDecodeException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成签名,准确地说是生成token
     * @param
     * @param
     * @return
     */
    public static String sign(User user){
        try{
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //附带username,nickname信息
            return JWT.create()
                    .withClaim("userId",user.getId())
                    .withClaim("userName",user.getLoginName()).sign(algorithm);
//                    .withExpiresAt(date).sign(algorithm);
        } catch (JWTCreationException e){
            e.printStackTrace();
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取token的签发时间
     * @param token
     * @return
     */
    public static Date getIssueAt(String token){
        try{
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getIssuedAt();
        } catch (JWTDecodeException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 验证token是否过期
     * @param token
     * @return
     */
    public static boolean isTokenExpired(String token){
        Date now = Calendar.getInstance().getTime();
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt().before(now);
    }

    /**
     * 刷新token的有效期
     * @param token
     * @param secret
     * @return
     */
    public static String refreshTokenExpired(String token, String secret){
        DecodedJWT jwt = JWT.decode(token); //解析token
        Map<String, Claim> claims = jwt.getClaims(); //获取token的参数信息

        try{
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTCreator.Builder builder = JWT.create().withExpiresAt(date);
            for(Map.Entry<String,Claim> entry : claims.entrySet()){
                builder.withClaim(entry.getKey(),entry.getValue().asString());
            }
            return builder.sign(algorithm);
        } catch (JWTCreationException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成16位随机盐,用在密码加密上面
     * @return
     */
    public static String generateSalt(){
        SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
        String hex = secureRandomNumberGenerator.nextBytes(16).toHex();
        return hex;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SECRET = secret;
        AUTH_HEADER = authHeader;
    }
}
