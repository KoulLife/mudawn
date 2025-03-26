package koul.mudawn.promotions.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import koul.mudawn.user.common.domain.BaseTimeEntity;
import koul.mudawn.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Promotions extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;

	// 소프트 Delete를 위한 용도
	private boolean deleted = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "view_count")
	private Long viewCount = 0L;

	@Column(name = "like_count")
	private Long likeCount = 0L;

	@OneToMany(mappedBy = "promotions", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PromotionsComments> comments = new ArrayList<>();

	@Builder
	public Promotions(User user, String title, String content) {
		this.user = user;
		this.title = title;
		this.content = content;
	}

	// 조회수 증가
	public void incrementViewCount() {
		this.viewCount++;
	}

	// 추천수 증가
	public void incrementLikeCount() {
		this.likeCount++;
	}

	// 추천수 감소
	public void decrementLikeCount() {
		this.likeCount--;
	}

	// 소프트 Delete 메서드
	public void softDelete() {
		this.deleted = true;
	}

}
