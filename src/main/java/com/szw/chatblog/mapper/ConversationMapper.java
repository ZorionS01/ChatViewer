package com.szw.chatblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szw.chatblog.dto.ConversationDto;
import com.szw.chatblog.pojo.Conversation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author ChatViewer
 */
@Mapper
public interface ConversationMapper extends BaseMapper<Conversation> {

    /**
     * 获取用户与ChatGPT的所有会话列表，每个会话项包括conversationId、firstMessage
     * @param userId 待查询用户id
     * @return List<ConversationDto>
     */
    List<ConversationDto> conversationsOf(Long userId);

}
