package cn.com.pro.util.utils;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

/**
 * @author zhoujianbo
 * @Title: 钉钉推送消息
 * @Description:
 * @date Created in 2022/2/10 13:45
 **/
@Slf4j
public class DingTalkUtil {

    private static final String SERVER_URL = "https://oapi.dingtalk.com/robot/send?access_token=";

    public static boolean sendByMarkdown(String title, String content, String token, List<String> mobileList, boolean isAtAll) {

        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("markdown");

        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setText(content);
        markdown.setTitle(title);
        request.setMarkdown(markdown);

        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setIsAtAll(String.valueOf(isAtAll));
        if (!mobileList.isEmpty()) {
            at.setAtMobiles(mobileList);
        }
        request.setAt(at);

        DefaultDingTalkClient client = new DefaultDingTalkClient(SERVER_URL + token);
        try {
            OapiRobotSendResponse response = client.execute(request);
            log.info("钉钉消息发送结果, response = {}", JSONObject.toJSONString(response));
            return response.isSuccess();
        } catch (ApiException e) {
            log.error("钉钉消息发送失败", e);
            return false;
        }
    }
}
