package com.cs.mapper;

import com.cs.dao.user;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    user selectAllByName (String userName);
}
