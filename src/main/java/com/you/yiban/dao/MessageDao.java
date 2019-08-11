package com.you.yiban.dao;

import com.you.yiban.entity.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageDao {

    /**
     * 创建报料
     * @param
     * @return
     */
    Integer insertMessage(Message message);

    /**
     * 根据用户名查询该用户所有报料
     * @param username
     * @return
     */
    List<Message> queryListMessageByUsername(String username);


    /**
     * 获得该所有的报料信息
     * @return
     */
    List<Message> getAllMessage();


    /**
     * 获得已处理的报料信息
     * @param
     * @return
     */
    List<Message> getMessageWithAnswer();

    /**
     * 获得未处理的报料信息
     * @return
     */
    List<Message> getWaitDeal();

    /**
     * 处理报料
     * @param message
     * @return
     */
    Message dealMessage(Message message);

    /**
     * 根据id删除报料信息
     * @param messageId
     * @return
     */
    Boolean delete(Integer messageId);

}
