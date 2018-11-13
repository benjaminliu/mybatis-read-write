package com.ben.test.controller;

import com.alibaba.fastjson.JSON;
import com.ben.test.dao.PostMapper;
import com.ben.test.entity.Post;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * — <br>
 *
 * @author: 刘恒 <br>
 * @date: 2018/11/12 <br>
 */
@RestController
@RequestMapping("/post")
public class PostController {
    private static Logger logger = LoggerFactory.getLogger(PostController.class);

    private static Cache<String, String> cache = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.HOURS)
            .build();

    @Autowired
    private PostMapper postMapper;

    @RequestMapping("/add")
    public ResponseEntity<?> add(String title, String desc) {
        Post post = new Post();
        post.setTitle(title);
        post.setDescription(desc);
        int res = postMapper.insert(post);
        return ResponseEntity.ok().build();
    }

    private static final String selectKey = "select";

    @RequestMapping("/select")
    public ResponseEntity<?> query() {
//
//        String json = "[{\"title\":\"d1\",\"description\":\"d1\"},{\"title\":\"d2\",\"description\":\"d2\"}]";
//        return ResponseEntity.ok(json);

        logger.info("enter");

        String value = cache.asMap().get(selectKey);
        List<Post> res = null;
        if (value == null) {
            res = postMapper.selectAll();
            value = JSON.toJSONString(res);
            cache.asMap().put(selectKey, value);
        } else {
            res = JSON.parseArray(value, Post.class);
        }
        logger.info("exit");
        return ResponseEntity.ok(res);
    }
}
