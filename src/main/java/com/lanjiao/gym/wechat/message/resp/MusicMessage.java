package com.lanjiao.gym.wechat.message.resp;

import lombok.Data;

/**
 * ClassName: MusicMessage
 * @Description: 音乐消息
 */
@Data
public class MusicMessage extends BaseMessage {
    // 音乐
    private Music Music;
}

