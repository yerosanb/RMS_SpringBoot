package com.example.demo.user.notification;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface notificationMapper {
	@Select("insert into notification(title, description, date,view_status) OUTPUT Inserted.id "
			+ "values(#{title}, #{description}, #{date},#{view_status});")
	public Long Notification(notificationModel notificationModel);

	@Insert("insert into user_notification(user_id, notification_id, view_status) "
			+ "values(#{user_id}, #{notification_id}, '0');")
	public void UserNotifiaction(@Param("user_id") long user_id, @Param("notification_id") long notification_id);

	@Insert("insert into notification_owner( notification_id, reference_id) "
			+ "values(#{notification_id}, #{reference_id});")
	public void notificationOwner(@Param("notification_id") long notification_id,
			@Param("reference_id") long reference_id);

	@Insert("insert into notification_reciver( notification_id, reciver_id) "
			+ "values(#{notification_id}, #{reciver_id});")
	public void notificationReceiver(@Param("notification_id") long notification_id,
			@Param("reciver_id") long reciver_id);

}
