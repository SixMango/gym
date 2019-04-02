package com.lanjiao.gym.wechat.message.resp;

import lombok.Data;

/**
 * ClassName: VoiceMessage
 * @Description: 语音消息
 * @author dapengniao
 * @date 2016 年 3 月 8 日 下午 6:02:13
 */
@Data
public class VoiceMessage extends BaseMessage {

    private Voice Voice;
}
