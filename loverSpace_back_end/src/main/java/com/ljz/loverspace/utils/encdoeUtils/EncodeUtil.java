package com.ljz.loverspace.utils.encdoeUtils;

import lombok.Data;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Component;

@Data
@Component
public class EncodeUtil {
    private final String secretKey="Coding@Lover";
    private final String hashAlgorithmName="MD5";
    private final int hashIterations=2;
    public String encode(String password,String salt){
        return new SimpleHash(hashAlgorithmName,password,salt,hashIterations).toString();
    }
}
