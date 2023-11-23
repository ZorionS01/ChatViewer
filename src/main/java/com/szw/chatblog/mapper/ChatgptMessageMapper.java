package com.szw.chatblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szw.chatblog.pojo.ChatgptMessage;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author ChatViewer
 */
@Mapper
public interface ChatgptMessageMapper extends BaseMapper<ChatgptMessage> {
}
