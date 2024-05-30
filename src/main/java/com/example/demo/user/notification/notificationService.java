package com.example.demo.user.notification;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class notificationService {
	@Autowired
	private notificationMapper notificationMapper;

	public long notification(notificationModel notificationModel) {

		notificationModel.setDate(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		notificationModel.setDescription("Replay remark");
		notificationModel.setTitle("Remark");
		notificationModel.setView_status("0");
		long notificationId = notificationMapper.Notification(notificationModel);
		return notificationId;

	}

	public void userNotification(long userId, long notificationId) {
		notificationMapper.UserNotifiaction(userId, notificationId);
	}

	public void notificationOwner(long notificationId, long referenceId) {
		notificationMapper.notificationOwner(notificationId, referenceId);
		;
	}

	public void notificationReceiver(long notificationId, long receiverId) {
		notificationMapper.notificationReceiver(notificationId, receiverId);
	}
}
