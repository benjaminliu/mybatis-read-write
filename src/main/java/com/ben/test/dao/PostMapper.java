package com.ben.test.dao;

import com.ben.test.entity.Post;

import java.util.List;

public interface PostMapper {
    int insert(Post record);

    int insertSelective(Post record);

    List<Post> selectAll();
}