package com.example.demo.admin.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.admin.model.Functionality;
import com.example.demo.admin.model.Logs;
import com.example.demo.admin.model.Role;
import com.example.demo.admin.model.UserActivity;
import com.example.demo.admin.model.UserName;
import com.example.demo.admin.model.Users;
import com.example.demo.model.User;
import com.example.demo.user.model.Currency;

@Mapper
public interface AdminMapper {

	@Select("select * from roles where  availability=1")
	public List<Role> getAllRoles();

	@Select("select 1 from users where email = #{email} and status = 1 and availability = 1")
	public Boolean check_email(String email);

	@Select("insert into users(firstname, middlename, lastname, password, gender, email, phonenumber, reg_date, status, availability, firsttime) OUTPUT Inserted.id "
			+ "values(#{firstname}, #{middlename}, #{lastname}, #{password}, #{gender}, #{email}, #{phonenumber},#{reg_date}, #{status}, #{availability}, #{firsttime});")
	public Long register_user(User user);

	@Update("update users set firstname = #{firstname}, middlename = #{middlename}, lastname = #{lastname}, gender = #{gender}, email = #{email}, phonenumber = #{phonenumber} where id = #{id}")
	public void update_user(User user);

	@Select("select top 1 id from user_role where user_id = #{user_id}")
	public Long getUserRoleId(@Param("user_id") Long user_id);

	@Update("update user_role set user_id = #{user_id}, role_id = #{role_id} where id = #{user_role_id}")
	public void updateUserRole(@Param("user_id") Long user_id, @Param("role_id") Long role_id,
			@Param("user_role_id") Long user_role_id);

	@Insert("insert into user_role (user_id, role_id, status, availability) values (#{user_id}, #{role_id}, #{status}, #{availability});")
	public void addUserRole(@Param("user_id") Long user_id, @Param("role_id") Long role_id,
			@Param("status") String status, @Param("availability") String availability);

	@Update("update roles set status = '0' where id = #{role_id}")
	public void deactivate_role(String role_id);

	@Update("update roles set status = '1' where id = #{role_id}")
	public void activate_role(String role_id);

	@Select("select u.id,u.firstname, u.middlename, u.lastname, u.gender, u.email, l.id as role, l.activity, l.browser_type, l.browser_version, l.ip, "
			+ "l.date as date_all, l.status, l.availability from users u, log l, user_log ul"
			+ " where l.id = ul.log_id and ul.user_id = u.id and l.date like #{date} + '%';")
	@Results(value = { @Result(property = "id", column = "id"),
			@Result(property = "roles", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.MapperAuth.getRolesByUserId")),
//			@Result(property = "rights", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.MapperAuth.getUserRights")),
//			@Result(property = "subject", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.MapperAuth.getSubjectsByUserId")) 
	})
	public List<Logs> getAllLogs(@Param("date") String act_date);

	@Select("select f.id, f.name, f.description, rf.status, f.status as status_all from functionalities f, role_functionality rf where rf.role_id = #{role_id} and rf.functionality_id = f.id  and f.availability=1 and rf.availability = 1")
	public List<Functionality> getFunctionality(String role_id);

	@Update("update role_functionality set status = #{status} where role_id = #{role_id} and functionality_id = #{functionality_id}; ")
	public void updateFunctionalityStatus(@Param("role_id") String role_id,
			@Param("functionality_id") String functionality_id, @Param("status") String status);

	@Select("select u.id, u.firstname, u.middlename, u.lastname, u.gender, u.email, a.id as role, a.name as activity, a.description, \r\n"
			+ "	a.date as date_all, a.status, a.availability from users u, activities a, user_activity ua\r\n"
			+ "	where a.id = ua.activity_id and ua.user_id = u.id and a.date like #{date} + '%' ")
	@Results(value = { @Result(property = "id", column = "id"),
			@Result(property = "roles", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.MapperAuth.getRolesByUserId")),
//			@Result(property = "rights", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.MapperAuth.getUserRights")),
//			@Result(property = "subject", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.MapperAuth.getSubjectsByUserId")) 
	})
	public List<UserActivity> getAllUserActivities(@Param("date") String act_date);

//	@Select("select distinct u.id, u.firstname, u.middlename, u.lastname, u.gender, u.email, u.phonenumber, u.reg_date, u.status, u.availability "
//			+ ",r.name as role from users u, user_role ur, roles r where u.availability = '1' and u.id = ur.user_id and ur.role_id = r.id and u.availability = '1'; ")
//	public List<Users> getAllUserUsers();

	@Select("select distinct u.id, u.firstname, u.middlename, u.lastname, u.gender, u.email, u.phonenumber, u.reg_date, u.status, u.availability "
			+ " from users u where u.availability = '1';")
	@Results(value = { @Result(property = "id", column = "id"),
			@Result(property = "roles", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.MapperAuth.getRolesByUserId")),
//			@Result(property = "rights", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.MapperAuth.getUserRights")),
//			@Result(property = "subject", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.MapperAuth.getSubjectsByUserId")) 
	})
	public List<User> getAllUserUsers();

	@Select("select distinct u.id, u.firstname, u.middlename, u.lastname, u.gender, u.email, u.phonenumber, u.reg_date, u.status, u.availability "
			+ " from users_tempo u where u.availability = 1 and u.status=1; ")
	public List<Users> getPendingUsers();

	@Select("select distinct u.id, u.firstname, u.middlename, u.lastname, u.gender, u.email, u.phonenumber, u.reg_date, u.status, u.availability "
			+ " from users_tempo u where u.availability = 1 and u.status IN (0,2); ")
	public List<Users> getRejectedApprovedUsers();
//	@Select("select top 1 u.firstname, u.middlename, u.lastname, u.gender, u.email, u.phonenumber, r.name as role from users u "
//			+ ",user_role ur, roles r where u.id = #{user_id} and u.id = ur.user_id and ur.role_id = r.id;")
	@Select("select distinct u.id, u.firstname, u.middlename, u.lastname, u.gender, u.email, u.phonenumber, u.reg_date, u.status, u.availability "
			+ " from users u where u.availability = '1' and u.id = #{user_id};")
	@Results(value = { @Result(property = "id", column = "id"),
			@Result(property = "roles", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.MapperAuth.getRolesByUserId")),
//			@Result(property = "rights", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.MapperAuth.getUserRights")),
//			@Result(property = "subject", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.MapperAuth.getSubjectsByUserId")) 
	})
	public User getUser(@Param("user_id") String user_id);

	@Select("select distinct u.id, u.firstname, u.middlename, u.lastname, u.gender, u.email, u.phonenumber, u.reg_date, u.status, u.availability "
			+ " from users_tempo u where u.availability = '1' and u.id = #{user_id};")
//	@Results(value = { @Result(property = "id", column = "id"),
//			@Result(property = "roles", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.MapperAuth.getRolesByUserId")),
//			@Result(property = "rights", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.MapperAuth.getUserRights")),
//			@Result(property = "subject", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.MapperAuth.getSubjectsByUserId")) 
//	})
	public User getUser_pending_byId(@Param("user_id") String user_id);

	@Update("update users set availability = '0' where id = #{user_id}")
//	@Update("delete from users where id = #{user_id}")
	public Boolean delete_user(@Param("user_id") String user_id);

	@Update("update  users_tempo set status = '0' where id = #{user_id}")
	public Boolean reject_pending_user(@Param("user_id") long user_id);

	@Update("update  users_tempo set status = '2' where id = #{user_id}")
	public Boolean approve_pending_user(@Param("user_id") long user_id);
	/*
	 * @Update("update roles set status = '0' where id = #{role_id}") public void
	 * deactivate_role(String role_id);
	 * 
	 * @Update("update roles set status = '1' where id = #{role_id}") public void
	 * activate_role(String role_id);
	 */

	@Update("update users set status = '1' where id = #{user_id}")
	public void activate_user(@Param("user_id") String user_id);

	@Update("update users set status = '0' where id = #{user_id}")
	public void deactivate_user(@Param("user_id") String user_id);

	@Select("select CONCAT(firstname, ' ', middlename) as name FROM users where id = #{user_id}")
	public UserName get_user_name(@Param("user_id") Long user_id);

	@Select("select CONCAT(firstname, ' ', middlename) as name FROM users where id = #{user_id}")
	public String getUserName(@Param("user_id") Long user_id);

	@Select("select CONCAT(firstname, ' ', middlename) as name FROM users_tempo where id = #{user_id}")
	public String getPendingUser(@Param("user_id") Long user_id);

	@Select("select password from users where id = #{user_id}")
	public String get_password(@Param("user_id") Long user_id);

	@Update("update users set password = #{password}, password_change_date=#{password_change_date} where id = #{user_id}")
	public void updatePassword(@Param("password") String confirmpassword, @Param("user_id") Long user_id, @Param("password_change_date") String password_change_date);

	@Update("update users set firsttime = '0' where id = #{user_id}")
	public void changeFirstTimeStatus(@Param("user_id") Long user_id);

	@Update("update users set password = #{password}, firsttime = '1' where id = #{user_id}")
	public Boolean reset_password_by_admin(@Param("password") String password, @Param("user_id") String user_id);

	@Select("select name from roles where id = #{role_id} and availability=1 and status=1")
	public String getRole(@Param("role_id") Long role_id);

//	@Select("select f.id, f.name, f.description, rf.status, f.status as status_all from functionalities f, role_functionality rf where rf.role_id = #{role_id} and rf.functionality_id = f.id")
//	public List<Functionality> getFunctionality(String role_id);

//	@Update("update role_functionality set status = #{status} where role_id = #{role_id} and functionality_id = #{functionality_id}; ")
//	public void updateFunctionalityStatus(@Param("role_id") String role_id, @Param("functionality_id") String functionality_id,@Param("status") String status);

	@Select("select * from functionalities where availability=1")
	public List<Functionality> getFunctionality_no_role();

	@Update("update functionalities set status = #{status} where id = #{functionality_id}; ")
	public void updateFunctionalityStatus_no_role(@Param("functionality_id") String functionality_id,
			@Param("status") String status);

	@Select("select top 1 1 from refresh_token where email = #{email}")
	public Optional<String> check_userLoggedIn(@Param("email") String email);

	@Select("select email from users where id = #{user_id};")
	public String getEmailFromId(@Param("user_id") Long user_id);

	@Select("select TOP 1 id from account")
	public Long getTopOneAccountId();

//	@Insert("insert into user_role (user_id, role_id, status, availability) values (#{user_id}, #{role_id}, #{status}, #{availability});")
//	public void addUserRole(@Param("user_id") Long user_id, @Param("role_id") Long role_id, @Param("status") String status, @Param("availability") String availability);

	@Insert("insert into user_selected_account (user_id, account_id, status, availability) values (#{user_id}, #{account_id}, #{status}, #{availability});")
	public void addUserSelectedAccount(@Param("user_id") Long user_id, @Param("account_id") Long role_id,
			@Param("status") String status, @Param("availability") String availability);

	@Delete("delete from user_role where user_id = #{id}")
	public void deleteUserRoles(@Param("id") Long id);

	@Insert("insert into roles (name, description, status, availability) values (#{name}, #{description}, '1', '1');")
	public void addUserCurrency(Currency role);

}
