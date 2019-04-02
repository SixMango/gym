package com.lanjiao.gym.wechat.message.resp;

import lombok.Data;

/**
 * ClassName: VideoMessage
 * @Description: 视频消息
 */
@Data
public class VideoMessage extends BaseMessage {

    private Video Video;
}

