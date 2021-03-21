/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package spring.boot.demo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import spring.boot.demo.entity.User;

import java.util.List;

/**
 * @author yueyi
 * @version : UserMapper.java, v 0.1 2021年03月21日 11:29 下午 yueyi Exp $
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM sys_user ")
    List<User> findUsers();

}