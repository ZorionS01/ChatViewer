package com.szw.chatblog.dto;

import lombok.Data;
import java.util.HashMap;

/**
 * @author ChatViewer
 */
@Data
public class CommentDto{

    Long id;

    Long parentId;

    Long uid;

    String content;

    Integer likes;

    String createTime;

    UserDtoForComment user;

    HashMap<String, Object> reply;

}
