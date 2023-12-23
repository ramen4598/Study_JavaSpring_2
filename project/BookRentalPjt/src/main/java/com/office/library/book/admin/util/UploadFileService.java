package com.office.library.book.admin.util;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {

	final static public String BOOK_THUMBNAIL_UPLOAD_DIR = makeUploadDir();

	static public String makeUploadDir() {
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("win")) {
			return "C:\\library\\upload\\";
        } else if (osName.contains("mac")) {
        	return System.getProperty("user.home") + "/Desktop/Study_JavaSpring_2/download/upload";
        } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
        	return System.getProperty("user.home") + "/Desktop/Study_JavaSpring_2/download/upload";
        } else {
			return null;
    	}
	}

	public String upload(MultipartFile file) {
		
		boolean result = false;
		
		String fileOriName = file.getOriginalFilename();
		String fileExtension = fileOriName.substring(fileOriName.lastIndexOf("."), fileOriName.length());
		if(BOOK_THUMBNAIL_UPLOAD_DIR == null) return null;

		UUID uuid = UUID.randomUUID();
		String uniqueName = uuid.toString().replaceAll("-", "");
		
		File saveFile = new File(BOOK_THUMBNAIL_UPLOAD_DIR + File.separator + uniqueName + fileExtension);
		System.out.println("[UploadFileService]" + " UPLOAD_DIR : " + BOOK_THUMBNAIL_UPLOAD_DIR + " saveFile : " + saveFile.getAbsolutePath());

		if(!saveFile.exists()) saveFile.mkdirs();
		
		try {
			file.transferTo(saveFile);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result) {
			System.out.println("[UploadFileService] FILE UPLOAD SUCCESS!!");
			return uniqueName + fileExtension;
		} else {
			System.out.println("[UploadFileService] FILE UPLOAD FAIL!!");
			return null;
		}
		
	}

}
