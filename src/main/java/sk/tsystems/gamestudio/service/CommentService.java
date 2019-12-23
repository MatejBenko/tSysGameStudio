package sk.tsystems.gamestudio.service;

import java.util.List;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Score;

public interface CommentService {
	void addComment(Comment c);

	List<Comment> getComments(String game);

}
