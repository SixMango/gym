package com.lanjiao.gym.wechat.message.resp;

import lombok.Data;

import java.util.List;

/**
 * ClassName: NewsMessage
 * @Description: 多图文消息
 */
@Data
public class NewsMessage extends BaseMessage {
    // 图文消息个数，限制为 10 条以内
    private int ArticleCount;
    // 多条图文消息信息，默认第一个 item 为大图
    private List<Article> Articles;
}