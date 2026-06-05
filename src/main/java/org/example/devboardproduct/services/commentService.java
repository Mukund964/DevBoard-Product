package org.example.devboardproduct.services;

import org.example.devboardproduct.dtos.createNewComment;
import org.example.devboardproduct.entities.Comments;

public interface commentService {
    Comments addNewComment(createNewComment newComment);
}
