package cn.com.pro.common.id;

import cn.com.pro.common.redis.RedisManager;
import cn.com.pro.util.utils.DateTimeUtil;
import cn.com.pro.util.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @author zhoujianbo
 * @Title: id生成器
 * @Description:
 * @date Created in 2022/2/17 11:20
 **/
@Slf4j
@Component
@Scope("singleton")
public class IdGenerate {

    @Value("${server.port}")
    private String serverPort;

    private SnowFlake snowFlake;

    @Autowired
    private RedisManager redisManager;

    @PostConstruct
    public void init() throws UnknownHostException {

        String val = getConfVal(serverPort);
        String[] arr = val.split(":");

        long dataCenterId = Long.parseLong(arr[0]);
        long machineId = Long.parseLong(arr[1]);
        log.info("初始化SnowFlake dataCenterId:{}, machineId:{}", dataCenterId, machineId);
        snowFlake = new SnowFlake(dataCenterId, machineId);
    }

    /**
     * tag + 14位日期时间 + 15位序列号
     * @param tag
     * @return
     */
    public String genSerialNo(String tag) {
        return tag + DateTimeUtil.getCurrentYYYYMMDDHHMMSS_T() + getSnowflakeNo15();
    }

    /**
     * tag + 15位序列号
     * @param tag
     * @return
     */
    public String getSerialNo(String tag) {
        return tag + getSnowflakeNo15();
    }

    /**
     * 32位流水号: 17位日期时间 + 15位序列号
     * @return
     */
    public String genSerialNo32() {
        return DateTimeUtil.DateToString(new Date(), DateTimeUtil.DEFAULT_DATETIME_FORMAT_MSEL) + getSnowflakeNo15();
    }

    /**
     * 生成15位序列号
     * @return
     */
    public String getSnowflakeNo15() {
        long snowflakeNo = snowFlake.nextId();
        if (String.valueOf(snowflakeNo).length() < 15) {
            return String.format("%015d", snowflakeNo);
        }
        return String.valueOf(snowflakeNo);
    }

    private String getConfVal(String serverPort) throws UnknownHostException {

        String intranetIp = IpUtil.getIntranetIp();
        String key = intranetIp + ":" + serverPort;
        String val = redisManager.get(key);
        if(StringUtils.isEmpty(val)) {
            throw new RuntimeException("应用IP和端口配置不存在");
        }
        return val;
    }

}
