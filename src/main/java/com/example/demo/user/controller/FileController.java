package com.example.demo.user.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.response.APIResponse;
import com.example.demo.user.model.FileUpload;
import com.example.demo.user.service.FileService;

@RestController
@RequestMapping("/api/user/files/")
public class FileController {
	@Autowired
	private FileService fileService;

	@PostMapping("upload")
	public ResponseEntity<Boolean> uploadAndExtractFile(@RequestParam("file") MultipartFile multipartFile,
			@RequestParam("transaction_date") String transaction_date,
			@RequestParam("nbe_core") String nbe_core, HttpServletRequest request) throws IOException {
		return APIResponse.response(fileService.uploadAndExtractFile(multipartFile, transaction_date, nbe_core, request));
	}
	@RequestMapping(value = "get_uploaded_files", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getUploadedFiles(HttpServletRequest request) {
		return APIResponse.response(fileService.getAllUploadedFiles(request));
	}
	@RequestMapping(value = "get_uploaded_file_by_id/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getUploadedFile( HttpServletRequest request,@PathVariable("id") Long id) {
		return APIResponse.response(fileService.getfilebyid(request,id));
	}
	
 	@RequestMapping(value = "download/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Resource> downloadFiles(HttpServletRequest request, @PathVariable Long id) throws ParseException {
		System.out.println("this is controller class...");
		return fileService.downloadFiles(request, id);
	}
 	
//	@RequestMapping(value = "download_second/{id}", method = RequestMethod.GET, produces = "application/json")
//	public ResponseEntity<Resource> downloadFiles(HttpServletRequest request, @PathVariable Long id, String type) throws ParseException {
//		System.out.println("this is controller class...");
//		return fileService.downloadFiles(request, id, type);
//	}
	
 	@RequestMapping(value = "downloadReportFiles/{date_type}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Resource> downloadReportFiles(HttpServletRequest request, @PathVariable("date_type") String date_type) throws ParseException {
 		System.out.println("this is the file download controllerrrrrrrrrrrrr");
		return fileService.downloadReportFiles(request, date_type);
	}
//	@RequestMapping(value = "downloadReportFilesIssue/{date_type}", method = RequestMethod.GET, produces = "application/json")
//	public ResponseEntity<Resource> downloadReportFilesIssue(HttpServletRequest request, @PathVariable("date_type") String date_type) throws ParseException {
//		return fileService.downloadReportFilesIssue(request, date_type);
//	}
 	
 	
 	@RequestMapping(value = "true", method = RequestMethod.POST, produces = "application/json")
	public boolean doesNotExpridedTokenForDownloadUploadedFile(HttpServletRequest request){
		fileService.doesNotExpridedToken(request);
 		return true;
	}
 	
// 	@RequestMapping(value = "true", method = RequestMethod.POST, produces = "application/json")
//	public boolean doesNotExpridedTokenForDownloadUploadedFile(HttpServletRequest request){
//		fileService.doesNotExpridedToken(request);
// 		return true;
//	}
 	
// 	@RequestMapping(value = "report/downlod/{date_type}", method = RequestMethod.GET, produces = "application/json")
//	public ResponseEntity<Resource> downloadReportFiles(HttpServletRequest request, @PathVariable("date_type") String date_type) throws ParseException {
//		return fileService.downloadReportFiles(request, date_type);
//	}
 	
 	@RequestMapping(value = "deletefile", method = RequestMethod.POST, produces = "application/json")
	public boolean deleteFiles(@RequestParam("id") List<Long> id,HttpServletRequest request){
 		for( int i=0;i<id.size();i++) {
		System.out.println("id..."+id.get(i));
		fileService.deleteFile(request,id.get(i));
		
 		}
 		return true;
	}
	@RequestMapping(value = "rolebackfile", method = RequestMethod.POST, produces = "application/json")
//	@RequestMapping(value = "download/{id}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> rolebackfile(@RequestParam("id") List<Long> id,@RequestParam("type") List<String> type,HttpServletRequest request){
	 return APIResponse.response(fileService.rolebackFile(request, id, type));
	}
	@RequestMapping(value = "updatefile/{id}", method = RequestMethod.POST, produces = "application/json")
	public boolean updateFiles(@PathVariable("id") long id,@Validated @RequestBody FileUpload fileUpload) throws ParseException {
		System.out.println("controlerrrr"+fileUpload.getFile_name());
		fileService.FileUpload(id,fileUpload);
		return true;
	}
//	
//	@RequestMapping(value = "/get_uploaded_files", method = RequestMethod.GET, produces = "application/json")
//	public ResponseEntity<Object> getUploadedFiles(HttpServletRequest request) {
//		return APIResponse.response(fileService.getAllUploadedFiles(request));
//	}
//	
// 	@RequestMapping(value = "download/{filename}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
////	@RequestMapping(value = "download/{id}", method = RequestMethod.GET)
//	public void downloadFiles(HttpServletRequest request, @PathVariable Long id) throws ParseException {
//		System.out.println("controllerrrrrrrrrrrrrrrrnewwwww...");
//		fileService.downloadFiles(request, id);
//	}
}