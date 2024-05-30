package com.example.demo.service;
//package com.example.demo.service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//
//import com.example.demo.Exception.ExceptionsList;
//import com.example.demo.admin.dto.RoleDto;
//import com.example.demo.admin.mapper.AdminMapper;
//import com.example.demo.admin.model.Role;
//import com.example.demo.jwt.JwtHelper;
//import com.example.demo.mapper.MapperRemark;
//import com.example.demo.model.Remark;
//import com.example.demo.utils.Utils;
//
//public class RemarkService {
//	@Autowired
//	private MapperRemark mapper;
//	@Autowired
//	private JwtHelper jwtHelper;
//
//	@Value("${jwt.access.token.cookie.name}")
//	private String cookieName;
//
//	@Autowired
//	private Utils util;
//
//	
////	public Remark getAllRemarks() {
////		mapper.getAllRemarks()
////	}
//	
//	public List<Remark> getAllRemarks(HttpServletRequest request) {
//
//		try {
//			if (util.isPermitted(request, "User", "get_all_remarks")) {
//				List<Role> roles = mapper.getAllRemarks();
//				util.registerActivity(request, "View Roles", "-");
//				return roles.stream().map(this::mapFromRoleToDto).collect(Collectors.toList());
//			} else {
//				System.out.println("No user does not have permission.");
//				return null;
//			}
//		} catch (Exception e) {
//			throw new ExceptionsList(e);
//		}
//	}
//	
//
//}
