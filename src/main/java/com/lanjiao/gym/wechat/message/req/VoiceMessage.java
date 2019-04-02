package com.lanjiao.gym.wechat.message.req;

import lombok.Data;

/**
 * ClassName: VoiceMessage
 * @Description: 语音消息
 */
@Data
public class VoiceMessage extends BaseMessage {
    // 媒体 ID
    private String MediaId;
    // 语音格式
    private String Format;
}