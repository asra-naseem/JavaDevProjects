package com.springboot.project.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		//file name
		
		String name = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		
		String filePath = path+File.separator+name;
		
		File f =new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		Files.copy(file.getInputStream(),Paths.get(filePath));
		
		
		return name;
	}

	@Override
	public InputStream getSource(String path, String file) throws FileNotFoundException {
		String fullPath = path+File.separator+file;
		InputStream is = new FileInputStream(fullPath);
		return is;
	}
	

}
