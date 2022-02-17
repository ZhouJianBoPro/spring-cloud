package cn.com.pro.common.id;

/**
 * @author zhoujianbo
 * @Title: 雪花算法
 * @Description: 生成id最大15位
 * 为降低生成id长度, 时间戳精确到秒，占用32bit
 * 机器id占用3bit 数据中心占用3bit 自增序列占用10bit, 每个节点每秒产生1024个ID序号
 * @date Created in 2022/02/17 16:02
 */
public class SnowFlake {

    /** 起始的时间戳(秒), 2021-01-01 00:00:00, 占用32bit，最高可支持到2106年(1970 + 2^32 / 3600 / 24 / 365 ≈ 2106)*/
    private final static long START_STMP = 1609430400L;
    /** 序列号占用的位数 */
    private final static long SEQUENCE_BIT = 10;
    /** 机器标识占用的位数 */
    private final static long MACHINE_BIT = 3;
    /** 数据中心占用的位数 */
    private final static long DATACENTER_BIT = 3;
    /** 支持的最大机器id，结果是7 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    /** 支持的最大数据标识id，结果是7 */
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    /** 生成序列的掩码，这里为1023 */
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);
    /** 机器ID向左移10位 */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    /** 数据标识id向左移13位(10+3) */
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    /** 时间截向左移15位(10+3+2) */
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;
    /** 数据中心ID(0~7) */
    private long datacenterId;
    /** 工作机器ID(0~7) */
    private long machineId;
    /** 秒内序列(0~1023) */
    private long sequence = 0L;
    /** 上次生成ID的时间截 */
    private long lastStmp = -1L;

    public SnowFlake(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastStmp) {
            //相同秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = getNextSeconds();
            }
        } else {
            //不同秒内，序列号置为0
            sequence = 0L;
        }

        lastStmp = currStmp;

        return (currStmp - START_STMP) << TIMESTMP_LEFT //时间戳部分
                | datacenterId << DATACENTER_LEFT       //数据中心部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    private long getNextSeconds() {
        long seconds = getNewstmp();
        while (seconds <= lastStmp) {
            seconds = getNewstmp();
        }
        return seconds;
    }

    private long getNewstmp() {
        return System.currentTimeMillis() / 1000;
    }

    public static void main(String[] args) {

        SnowFlake snowFlake = new SnowFlake(0L, 0L);
        for(int i = 0; i < 100; i++) {
            long s = snowFlake.nextId();
            System.out.println(s + "    " + String.valueOf(s).length());
        }
    }
}