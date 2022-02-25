package cn.com.pro.provider.enums;

/**
 * @author zhoujianbo
 * @Title:
 * @Description:
 * @date Created in 2022/2/25 16:19
 **/
public enum FundEnum {

    DEFAULT("10001");

    private String fundId;

    FundEnum(String fundId) {
        this.fundId = fundId;
    }

    public String getFundId() {
        return fundId;
    }

    public static FundEnum getByCode(String fundId) {

        for(FundEnum fundEnum : FundEnum.values()) {
            if(fundEnum.getFundId().equals(fundId)) {
                return fundEnum;
            }
        }
        return null;
    }
}
