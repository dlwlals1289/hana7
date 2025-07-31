package com.hana7.springdemo.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.List;

@Entity
@DynamicInsert
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true, exclude = {"boards"})
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

	@OneToMany(mappedBy = "writer")
	// @OnDelete(action = OnDeleteAction.CASCADE)
	private List<Board> boards;
}
