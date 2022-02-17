package cn.com.pro.provider.db.model;

public class FundNodeRoute {
    private Integer id;

    private String fundId;

    private String nodeCode;

    private Integer status;

    private String nextNodeCodes;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNextNodeCodes() {
        return nextNodeCodes;
    }

    public void setNextNodeCodes(String nextNodeCodes) {
        this.nextNodeCodes = nextNodeCodes == null ? null : nextNodeCodes.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}