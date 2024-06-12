package com.itwill.spring2.dto;

import java.time.LocalDateTime;

import com.itwill.spring2.repository.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO(Data Transfer Object)
// 뷰 <--> 컨트롤러, 컨트롤러 <--> 서비스 사이에서 데이터를 주고 받을 때 사용하는 객체.
@Data // @Getter + @Setter + @ToString + @EqualsAndHashCode + @RequiredArgsConstructor (@RequiredArgsConstructor는 기본 생성자를 만드는게 아님.)
@NoArgsConstructor @AllArgsConstructor @Builder
public class PostListDto {
	private Integer id;
	private String title;
	private String author;
	private LocalDateTime modifiedTime;
	
	public static PostListDto fromEntity(Post post) { // static (주어진 "Post" 객체를 기반으로 새로운 "PostListDto" 객체를  생성.
		return PostListDto.builder()
				.id(post.getId())
				.title(post.getTitle())
				.author(post.getAuthor())
				.modifiedTime(post.getModifiedTime())
				.build();
		
	}
	
}

