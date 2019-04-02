package com.lanjiao.gym.wechat.message.req;

import lombok.Data;

/**
 * ClassName: TextMessage
 * @Description: 文本消息
 */
@Data
public class TextMessage extends BaseMessage {
    // 消息内容
    private String Content;
}