package com.you.yiban.service;

import com.mchange.util.MEnumeration;
import com.you.yiban.entity.Message;

import java.util.Date;
import java.util.List;

public interface MessageService {

    /**
     * 用户查看自己提供的报料
     * @param username
     * @return
     */
    public List<Message> getListByUsername(String username);

    /**
     * 发布报料
     * @param message
     * @return
     */
    public Boolean addMessage(Message message);

    /**
     * 获取所有的报料信息列表
     * @return
     */
    public List<Message> getAllMessage();

    /**
     * 获取报料总数
     * @return
     */
    public Integer getMessageNumber();

    /**
     * 获取未处理的报料数量
     * @return
     */
    public Integer getWaitDealNumber();

    /**
     * 获取已经处理报料的数量
     * @return
     */
    public Integer isDealNumber();

    /**
     * 管理员回复报料信息
     * @param message
     * @param messageAnswer
     * @param answerImage
     * @param answerUsername
     * @return
     */
    public Message modifyMessage(Message message,
            String messageAnswer, Byte[] answerImage, String answerUsername);


    /**
     * 删除报料
     * @param messageId
     * @return
     */
    public Boolean deleteMessage(Integer messageId);
}
