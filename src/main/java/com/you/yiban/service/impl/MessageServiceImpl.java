package com.you.yiban.service.impl;

import com.you.yiban.dao.MessageDao;
import com.you.yiban.entity.Message;
import com.you.yiban.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Message> getListByUsername(String username){
        return messageDao.queryListMessageByUsername(username);
    }

    @Override
    @Transactional
    public Boolean addMessage(Message message){
        final boolean exist = message.getMessageType() != null && message.getMessage() != null
                && message.getQq() != null && message.getPhone() !=null;
        if (exist){
            message.setCreateTime(new Date());
            try {
                Integer effectNum = messageDao.insertMessage(message);
                if (effectNum > 0) {
                    return true;
                }else {
                    logger.info("发布报料失败");
                    return false;
                }
            } catch (Exception e){
                logger.info("发布报料失败"+e.toString());
                return false;
            }

        } else {
            logger.info("请完善报料信息");
            return false;
        }
    }

    @Override
    public List<Message> getAllMessage(){
        return messageDao.getAllMessage();
    }

    @Override
    @Transactional
    public Integer getMessageNumber(){
        // 报料总数
        Integer number;
        List<Message> list = messageDao.getAllMessage();
        number = list.size();
        return number;
    }

    @Override
    @Transactional
    public Integer getWaitDealNumber(){
        // 等待处理的报料数量
        Integer waitNumber;
        List<Message> list = messageDao.getWaitDeal();
        waitNumber = list.size();
        return waitNumber;
    }

    @Override
    @Transactional
    public Integer isDealNumber(){
        Integer all,waitNumber,isDealNumber;
        List<Message> list1 = messageDao.getAllMessage();
        List<Message> list2 = messageDao.getWaitDeal();
        all = list1.size();
        waitNumber = list2.size();
        isDealNumber = all - waitNumber;
        return isDealNumber;
    }

    @Override
    @Transactional
    public Message modifyMessage(Message message,
            String messageAnswer,Byte[] answerImage,String answerUsername){
        final boolean exist = messageAnswer != null && message != null
                && answerImage != null && answerUsername !=null;
        if (exist) {
            try {
                message.setMessageAnswer(messageAnswer);
                message.setAnswerImage(answerImage);
                message.setAnswerUsername(answerUsername);
                message.setMessageState(1);
                return message;
            }catch (Exception e) {
                logger.info("回复失败"+toString());
                return null;
            }
        } else {
            logger.info("请输入回复内容");
            return null;
        }
    }


    @Override
    @Transactional
    public Boolean deleteMessage(Integer messageId){
        Boolean flag;
        if(messageId != null){
            try {
               flag = messageDao.delete(messageId);
               return true;
            } catch (Exception e){
                logger.info("删除失败"+toString());
                return false;
            }
        } else {
            logger.info("信息缺失删除失败");
            return  false;
        }
    }


}
