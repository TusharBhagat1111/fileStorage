package com.javatechie.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javatechie.entity.FileData;
import com.javatechie.entity.ImageData;
import com.javatechie.respository.FileDataRepository;
import com.javatechie.respository.StorageRepository;
import com.javatechie.util.ImageUtils;
import com.javatechie.util.TusharException;

@Service
public class StorageService {

	@Autowired
	private StorageRepository repository;

	@Autowired
	private FileDataRepository fileDataRepository;

//	private final String FOLDER_PATH = "/Users/javatechie/Desktop/MyFIles/";
//	private final String FOLDER_PATH = "/Users/tusha/OneDrive/Desktop/MyFiles/";
	private final String FOLDER_PATH = "C:\\Users\\tusha\\OneDrive\\Desktop\\MyFiles";

	public String uploadImage(MultipartFile file) throws IOException {
		ImageData imageData = repository.save(ImageData.builder().name(file.getOriginalFilename())
				.type(file.getContentType()).imageData(ImageUtils.compressImage(file.getBytes())).build());
		if (imageData != null) {
			return "file uploaded successfully : " + file.getOriginalFilename();
		}
		return null;
	}

	public byte[] downloadImage(String fileName) {

		Optional<ImageData> dbImageData = repository.findByName(fileName);
		byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());

		return images;
	}

	public String uploadImageToFileSystem(MultipartFile file) throws IOException {
		String filePath = FOLDER_PATH + file.getOriginalFilename();

		FileData fileData = fileDataRepository.save(FileData.builder().name(file.getOriginalFilename())
				.type(file.getContentType()).filePath(filePath).build());

		file.transferTo(new File(filePath));

		if (fileData != null) {
			return "file uploaded successfully : " + filePath;
		}
		return null;
	}

	public byte[] downloadImageFromFileSystem(Integer id) throws IOException, TusharException {
//		Optional<FileData> fileData = fileDataRepository.findByName(fileName);
//		String filePath = fileData.get().getFilePath();
//		byte[] images = Files.readAllBytes(new File(filePath).toPath());
//		return images;
		
		
//		FileData fileData = fileDataRepository.findByName(fileName).orElseThrow(() -> new TusharException(fileName, fileName));
//		String filePath = fileData.getFilePath();
//	    return Files.readAllBytes(Paths.get(filePath));
		
		FileData fileData = fileDataRepository.findById(id).orElseThrow(() -> new TusharException(id, id));
		String filePath = fileData.getFilePath();
	    return Files.readAllBytes(Paths.get(filePath));
		
	}

}
