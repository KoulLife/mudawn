package koul.mudawn.promotions.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import koul.mudawn.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "promotions_comments")
@Getter
@NoArgsConstructor
@Entity
public class PromotionsComments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "promotions_id", nullable = false)
	private Promotions promotions;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(nullable = false)
	private String content;

	@Builder
	public PromotionsComments(Promotions promotions, User user, String content) {
		this.promotions = promotions;
		this.user = user;
		this.content = content;
	}

	public void setPromotions(Promotions promotions) {
		this.promotions = promotions;
	}

}
