package com.hana7.springdemo.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class BoardContent extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Lob
	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;

	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="boardId")
	private Board board;

	public BoardContent(String content) {
		this.content = content;
	}

	public BoardContent(String content, Board board) {
		this(content);
		this.board = board;
	}
}
