package com.example.application.port.in;

import com.example.domain.model.Task;

public interface UploadTaskImageUseCase {
	Task uploadImage(long id, String imagePath);
}