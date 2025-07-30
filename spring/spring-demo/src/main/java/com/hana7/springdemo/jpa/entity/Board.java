package com.hana7.springdemo.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Builder
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Board extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 40, nullable = false)
	private String title;

	@ManyToOne()
	@JsonBackReference
	@JoinColumn(name = "writer", foreignKey = @ForeignKey(name = "fk_Board_member"))
	@ToString.Exclude
	private Member writer;

	@Column(nullable = false)
	@ColumnDefault("0")
	private int hit;

	@JsonManagedReference
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Reply> replies;

	@OneToOne(mappedBy = "board", cascade = CascadeType.ALL)
	private BoardContent content;

	public void setContent(BoardContent content) {
		this.content = content;
		if (content != null) content.setBoard(this);
	}
}
