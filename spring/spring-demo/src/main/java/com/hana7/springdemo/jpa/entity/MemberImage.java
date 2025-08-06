package com.hana7.springdemo.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberImage extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(
			name = "member",
			foreignKey = @ForeignKey(
					name = "fk_MemberImage_member",
					foreignKeyDefinition = """
								foreign key (member)
								   references Member(id)
								    on DELETE cascade on UPDATE cascade on
							"""
			)
	)
	private Member member;

	@Column(nullable = false)
	private String orgName;

	@Column(nullable = false, unique = true)
	private String saveName;

	@Column(nullable = false)
	private String saveDir;
}
