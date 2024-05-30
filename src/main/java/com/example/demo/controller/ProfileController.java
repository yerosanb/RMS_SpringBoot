package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.ProfileService;

@RestController
@RequestMapping("/api/profile/")
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json", consumes = "multipart/form-data")
	public String updateProfile(@RequestParam("file") MultipartFile file, @RequestParam("id") String id)
			throws IOException {
		profileService.updateProfilePicture(file,Long.parseLong(id));
		return "Update success...";
	}
}