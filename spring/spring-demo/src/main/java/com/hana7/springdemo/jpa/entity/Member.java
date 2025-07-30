package com.hana7.springdemo.jpa.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.List;

@Entity
@DynamicInsert
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)

@EqualsAndHashCode(callSuper = true)
public class Member extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 31, nullable = false)
	@ColumnDefault("'Guest'")
	private String nickname;

	@Column(nullable = false, unique = true)
	@Email
	private String email;

	@Enumerated(EnumType.STRING)
	private BloodType bloodType;

	private String passwd;

	@Transient
	@Builder.Default
	private int auth = 9;

	@OneToMany(
			mappedBy = "writer",
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER,
			orphanRemoval = true
	)
	@JsonManagedReference
	private List<Board> boards;

	@OneToMany(
			mappedBy = "replyer",
//			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER
//			orphanRemoval = true
	)
	@JsonManagedReference
	private List<Reply> replies;
}
