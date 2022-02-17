package cn.com.pro.provider.db.model;

import java.util.Date;

public class MessageConsume extends MessageConsumeKey {
    private String producerGroup;

    private String topic;

    private String tags;

    private String messageId;

    private String isAllowRepeat;

    private Integer repeatCount;

    private Integer businessStatus;

    private String lastConsumedRemark;

    private Date createTime;

    private Date updateTime;

    private String context;

    public String getProducerGroup() {
        return producerGroup;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup == null ? null : producerGroup.trim();
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic == null ? null : topic.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }

    public String getIsAllowRepeat() {
        return isAllowRepeat;
    }

    public void setIsAllowRepeat(String isAllowRepeat) {
        this.isAllowRepeat = isAllowRepeat == null ? null : isAllowRepeat.trim();
    }

    public Integer getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(Integer repeatCount) {
        this.repeatCount = repeatCount;
    }

    public Integer getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(Integer businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getLastConsumedRemark() {
        return lastConsumedRemark;
    }

    public void setLastConsumedRemark(String lastConsumedRemark) {
        this.lastConsumedRemark = lastConsumedRemark == null ? null : lastConsumedRemark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }
}