package com.szw.chatblog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.szw.chatblog.dto.UserDtoForComment;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author ChatViewer
 */
@Data
@TableName(value = "blog_user")
public class User {

    /**
     * @TableId 用于定义实体类中的主键属性以及主键生成策略。
     * @JsonSerialize 用于控制主键属性在 JSON 序列化时的行为，将其转换为字符串以避免数值溢出问题。
     *
     *  //指定主键生成策略使用雪花算法（默认策略）
     */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    private String userName;

    private String userPhone;

    private String userPassword;

    private String userAvatar;

    private Integer userTokenCount;

    private String userApiKey;


    public UserDtoForComment toUseDtoForComment() {
        UserDtoForComment userDtoForComment = new UserDtoForComment();
        userDtoForComment.setUsername(userName);
        userDtoForComment.setAvatar(userAvatar);
        return userDtoForComment;
    }


}
