package cn.com.pro.provider.db.model;

import java.math.BigDecimal;
import java.util.Date;

public class TLoanSucceedExtraInfo {
    private Integer id;

    private String fundId;

    private String loanNo;

    private String iouNo;

    private BigDecimal penaltyYearRate;

    private BigDecimal compoundYearRate;

    private BigDecimal penaltyDayRate;

    private BigDecimal compoundDayRate;

    private String remark;

    private String creator;

    private Date createTime;

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

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo == null ? null : loanNo.trim();
    }

    public String getIouNo() {
        return iouNo;
    }

    public void setIouNo(String iouNo) {
        this.iouNo = iouNo == null ? null : iouNo.trim();
    }

    public BigDecimal getPenaltyYearRate() {
        return penaltyYearRate;
    }

    public void setPenaltyYearRate(BigDecimal penaltyYearRate) {
        this.penaltyYearRate = penaltyYearRate;
    }

    public BigDecimal getCompoundYearRate() {
        return compoundYearRate;
    }

    public void setCompoundYearRate(BigDecimal compoundYearRate) {
        this.compoundYearRate = compoundYearRate;
    }

    public BigDecimal getPenaltyDayRate() {
        return penaltyDayRate;
    }

    public void setPenaltyDayRate(BigDecimal penaltyDayRate) {
        this.penaltyDayRate = penaltyDayRate;
    }

    public BigDecimal getCompoundDayRate() {
        return compoundDayRate;
    }

    public void setCompoundDayRate(BigDecimal compoundDayRate) {
        this.compoundDayRate = compoundDayRate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}