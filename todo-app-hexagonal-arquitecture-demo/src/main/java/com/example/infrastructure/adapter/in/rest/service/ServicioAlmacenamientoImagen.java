package com.example.infrastructure.adapter.in.rest.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ServicioAlmacenamientoImagen {

	private static final String UPLOAD_DIR = "uploads/";

	public String store(MultipartFile file, long taskId) {
		if (file.isEmpty()) {
			throw new IllegalArgumentException("El archivo está vacío");
		}

		try {
			Path uploadPath = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();
			Files.createDirectories(uploadPath);

			String timestamp = LocalDateTime.now()
					.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
			String originalName = file.getOriginalFilename() != null
					? file.getOriginalFilename().replaceAll("\\s+", "_")
					: "image.jpg";
			String fileName = taskId + "_" + timestamp + "_" + originalName;

			Path targetPath = uploadPath.resolve(fileName).normalize();
			file.transferTo(targetPath);

			return targetPath.toString();
		} catch (IOException ex) {
			throw new RuntimeException("No se pudo guardar la imagen", ex);
		}
	}
}