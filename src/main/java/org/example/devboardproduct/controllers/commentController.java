package org.example.devboardproduct.controllers;

import org.example.devboardproduct.dtos.createNewComment;
import org.example.devboardproduct.entities.Comments;
import org.example.devboardproduct.services.commentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class commentController {

    @Autowired
    commentService commentService;

    @MutationMapping
    Comments addComment(@Argument createNewComment newComment){
        return commentService.addNewComment(newComment);
    }
}
