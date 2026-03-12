package com.cs.mapper;

import com.cs.dao.user;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest  // 启动 Spring 容器
@Transactional  // 测试后自动回滚，不会污染数据库
public class MapperTest {

    @Autowired  // 自动注入 Mapper
    private UserMapper userMapper;

    /**
     * 测试根据用户名精确查询
     */
    @Test
    public void testSelectAllByName() {
        System.out.println("========== 测试精确查询 ==========");

        // 调用 Mapper 方法
        user user = userMapper.selectAllByName("admin");
        System.out.println(user);
    }
}