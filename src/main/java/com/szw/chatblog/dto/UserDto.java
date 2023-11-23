package com.szw.chatblog.dto;

import lombok.Data;

/**
 * @author ChatViewer
 */
@Data
public class UserDto {

    private Long userId;

    private String userName;

    private String userAvatar;

    private Integer userTokenCount;

    private String userApiKey;

}