package cn.com.pro.util.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author zhoujianbo
 * @Title: ip工具类
 * @Description:
 * @date Created in 2022/2/22 10:55
 **/
@Slf4j
public class IpUtil {

    /**
     * 获取内网ip
     * @return
     */
    public static String getIntranetIp() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        return addr.getHostAddress();
    }

}
