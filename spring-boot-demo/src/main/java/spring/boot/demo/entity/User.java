/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package spring.boot.demo.entity;

import lombok.Data;

/**
 * @author yueyi
 * @version : User.java, v 0.1 2021年03月21日 11:29 下午 yueyi Exp $
 */
@Data
public class User {

    private String id;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userInfo;
    private String headImg;
    private String createTime;

}