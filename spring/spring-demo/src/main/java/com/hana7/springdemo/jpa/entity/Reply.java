package com.hana7.springdemo.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reply extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 1000, nullable = false)
	private String reply;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	@JoinColumn(name = "replyer", foreignKey = @ForeignKey(name = "fk_Reply_member"))
	@ToString.Exclude
	private Member replyer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	@JoinColumn(name = "board", foreignKey = @ForeignKey(name = "fk_Reply_board"))
	@ToString.Exclude
	private Board board;

}
