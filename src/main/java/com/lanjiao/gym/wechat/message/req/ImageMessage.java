package com.lanjiao.gym.wechat.message.req;

import lombok.Data;

/**
 * ClassName: ImageMessage
 * @Description: 图片消息
 */
@Data
public class ImageMessage extends BaseMessage {
    // 图片链接
    private String PicUrl;

}