package com.hana7.springdemo.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@ToString(exclude = {"writer", "replies"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Board extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 40, nullable = false)
	private String title;

	// @Column(length = 30, nullable = false)
	// private String writer;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "writer",
			foreignKey = @ForeignKey(
					name = "fk_Board_writer_Member",
					foreignKeyDefinition = """
								foreign key (writer)
								   references Member(id)
								    on DELETE cascade on UPDATE set null
							"""
			)
	)
	private Member writer;

	@Column(nullable = false)
	@ColumnDefault("0")
	private int hit;

	@OneToOne(mappedBy = "board")
	private BoardContent content;

	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
	private List<Reply> replies;

	public void setContent(BoardContent content) {
		this.content = content;
		if (content != null)
			content.setBoard(this);
	}
}
