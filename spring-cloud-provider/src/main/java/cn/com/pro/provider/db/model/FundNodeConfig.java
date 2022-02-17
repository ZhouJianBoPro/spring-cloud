package cn.com.pro.provider.db.model;

public class FundNodeConfig {
    private Long id;

    private String fundId;

    private String nodeCode;

    private Integer maxConcurrentNum;

    private Integer failRetryCount;

    private Integer initDelayLevel;

    private Integer retryDelayLevel;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId == null ? null : fundId.trim();
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode == null ? null : nodeCode.trim();
    }

    public Integer getMaxConcurrentNum() {
        return maxConcurrentNum;
    }

    public void setMaxConcurrentNum(Integer maxConcurrentNum) {
        this.maxConcurrentNum = maxConcurrentNum;
    }

    public Integer getFailRetryCount() {
        return failRetryCount;
    }

    public void setFailRetryCount(Integer failRetryCount) {
        this.failRetryCount = failRetryCount;
    }

    public Integer getInitDelayLevel() {
        return initDelayLevel;
    }

    public void setInitDelayLevel(Integer initDelayLevel) {
        this.initDelayLevel = initDelayLevel;
    }

    public Integer getRetryDelayLevel() {
        return retryDelayLevel;
    }

    public void setRetryDelayLevel(Integer retryDelayLevel) {
        this.retryDelayLevel = retryDelayLevel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}