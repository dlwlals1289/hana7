package com.hana7.springdemo.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicInsert
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
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

	@OneToMany(mappedBy = "writer",  cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Board> boards = new ArrayList<>();

}
