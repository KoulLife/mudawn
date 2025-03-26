package koul.mudawn.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import koul.mudawn.user.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class User extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 30, unique = true)
	private String username;

	@Column(nullable = false, unique = true)
	private String nickname;

	@Column(length = 100)
	private String password;

	@Column(nullable = false, unique = true)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	// 회원 정보 수정
	public void modify(String nickname, String password) {
		this.nickname = nickname;
		this.password = password;
	}

	// 소셜로그인의 경우 이미 등록된 회원이라면
	// 수정 날짜만 업데이트 하여 기존 데이터 보존
	public User updateModifiedDate() {
		this.onPreUpdate();
		return this;
	}

	// 유저의 role 반환
	public String getRoleValue() {
		return this.role.getValue();
	}
}
