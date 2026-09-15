package com.example.infrastructure.adapter.out.persistence;

import java.time.LocalDateTime;

import com.example.domain.model.EstadoTarea;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EntidadTareaJpa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private EstadoTarea status;
	
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "completed_at")
	private LocalDateTime completedAt;
	
	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
		
		if (this.status == null)
			this.status = EstadoTarea.PENDING;
	}
}