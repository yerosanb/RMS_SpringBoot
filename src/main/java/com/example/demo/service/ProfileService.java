package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.mapper.MapperAuth;
import com.example.demo.model.Files_;

@Service
public class ProfileService {

	@Autowired
	private MapperAuth mapper;

	@Autowired
	ServletContext servletContext;

	public void updateProfilePicture(MultipartFile file, Long id) throws IOException {

		String rootPath = System.getProperty("user.dir");
		File file_path = new File(
				StringUtils.join(rootPath + "/src/main/resources/static/files/images/profile_images/"));

		if (!file_path.exists()) {
			file_path.mkdir();
		}
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		String file_location_with_name = file_path.getAbsolutePath() + "/" + generateUniqueFileName() + "." + extension;

		Files_ newfile = new Files_();
		newfile.setName(file.getOriginalFilename());
		newfile.setPath(file_location_with_name);
		newfile.setFile_type(file.getContentType());
//		newfile.setStatus(1);
		newfile.setUsage_type("profile");

		file.transferTo(new File(file_location_with_name));

		if (changeCurrentProfileStatus(id)) {
//			mapper.updateProfileId(new UpdateProfileRequest(id, mapper.inesrtProfilePictureInfo(newfile)));
		}
	}

	private boolean changeCurrentProfileStatus(Long userId) {
		Long profileId = mapper.getCurrentProfileId(userId);
		mapper.changeProfileStatus(profileId);
		return true;
	}

	String generateUniqueFileName() {
		String filename = "";
		long millis = System.currentTimeMillis();
		String datetime = new Date().toGMTString();
		datetime = datetime.replace(" ", "");
		datetime = datetime.replace(":", "");
		String rndchars = RandomStringUtils.randomAlphanumeric(16);
		filename = rndchars + "_" + datetime + "_" + millis;
		return filename;
	}

}
