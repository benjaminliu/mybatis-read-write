package com.ben.test.controller;

import com.ben.test.dao.PostMapper;
import com.ben.test.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * — <br>
 *
 * @author: 刘恒 <br>
 * @date: 2018/11/12 <br>
 */
@RestController
@RequestMapping("/post")
public class PostController {

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

    @RequestMapping("/select")
    public ResponseEntity<?> query() {
        List<Post> res = postMapper.selectAll();
        return ResponseEntity.ok(res);
    }
}
