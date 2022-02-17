package cn.com.pro.provider.db.model;

public class MessageConsumeKey {
    private String messageKey;

    private String consumeGroup;

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey == null ? null : messageKey.trim();
    }

    public String getConsumeGroup() {
        return consumeGroup;
    }

    public void setConsumeGroup(String consumeGroup) {
        this.consumeGroup = consumeGroup == null ? null : consumeGroup.trim();
    }
}