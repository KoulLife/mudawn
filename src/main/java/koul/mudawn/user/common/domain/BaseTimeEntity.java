package koul.mudawn.user.common.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

	@Column(name = "created_date", nullable = false)
	@CreatedDate
	private String createdDate;

	@Column(name = "modified_date", nullable = false)
	@LastModifiedDate
	private String modifiedDate;

	/* 엔티티가 insert 작업 전에 호출 */
	@PrePersist
	public void onPrePersist(){
		this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
		this.modifiedDate = this.createdDate;
	}

	/* 엔티티가 update되기 전에 호출 */
	@PreUpdate
	public void onPreUpdate(){
		this.modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
	}
}
