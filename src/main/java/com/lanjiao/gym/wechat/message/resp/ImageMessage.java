package com.lanjiao.gym.wechat.message.resp;

import lombok.Data;

/**
 * ClassName: ImageMessage
 * @Description: 图片消息
 */
@Data
public class ImageMessage extends BaseMessage {
    private Image Image;
}
