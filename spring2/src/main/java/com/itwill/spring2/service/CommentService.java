package com.itwill.spring2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.spring2.dto.CommentCreateDto;
import com.itwill.spring2.dto.CommentListDto;
import com.itwill.spring2.dto.CommentUpdateDto;

import com.itwill.spring2.repository.Comment;
import com.itwill.spring2.repository.CommentDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor

@Service
public class CommentService {

	private final CommentDao commentDao;
	
	public List<CommentListDto> read(Integer postId) {
		log.debug("read(postId = {})", postId);
		List<Comment> list = commentDao.selectByPostId(postId);
		
		return list.stream()
              .map(CommentListDto::fromEntity) // map((x) -> CommentListDto.fromEntity(x))
              .toList();
	}
	
	public int create(CommentCreateDto dto) {
		log.debug("create({})", dto);
		
		int result = commentDao.insert(dto.toEntity());
		log.debug("insert 결과 = {}", result);
		
		return result;
	}
	
	public int update(CommentUpdateDto dto) {
		log.debug("update({})", dto);
		
		int result = commentDao.update(dto.toEntity());
		log.debug("update 결과 = {}", result);
		
		return result;
	}
	
	public int deleteById(Integer id) {
		log.debug("deleteById(id = {})", id);
		
		int result = commentDao.deleteById(id);
		log.debug("deleteById 결과 = {}", result);
		
		return result;
		
	}
	
	public int deleteByPostId(Integer postId) {
		log.debug("deleteByPostId(postId = {})", postId);
		
		int result = commentDao.deleteByPostId(postId);
		log.debug("deleteByPostId 결과 = {}", result);
		
		return result;
		
	}
	
    public CommentListDto readById(Integer id) {
        log.debug("readById(id={})", id);
        
        Comment comment = commentDao.selectById(id);
        log.debug(comment.toString());
        
        return CommentListDto.fromEntity(comment);
    }
	
//	public Comment readById(Integer id) {
//		log.debug("readById(id = {})", id);
//		return commentDao.selectById(id);
//		
//	}

}

