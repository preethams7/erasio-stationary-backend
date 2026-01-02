package com.hospital.base.core.quartz;


import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hospital.base.core.notifications.NotificationsAction;
import com.hospital.base.user.subscriptions.AccountSubscriptionEntity;
import com.hospital.base.user.subscriptions.AccountSubscriptionService;
import com.hospital.base.utils.DateTimeUtils;

public class QuartzJob implements Job {
	
	@Autowired
	AccountSubscriptionService subSrvc;
	
	@Autowired DateTimeUtils dtu;
	
	@Autowired
	NotificationsAction notif;
	
	Logger logger = LoggerFactory.getLogger(QuartzJob.class);
	
	@Override
	public void execute(JobExecutionContext jobExecutionContext) {
		logger.info("Evique Digital Cron Job");
		
		List<AccountSubscriptionEntity> subs = subSrvc.getAllSubscrptions();
		for(int i=0;i<subs.size();i++) {
			if (dtu.dateCompare(dtu.getCurrentDate(), subs.get(i).getExpires())>0) {
				notif.createNotification("SUBSCRIPTION RENEWAL DUE", subs.get(i).getUsername());
			}else if (dtu.dateCompare(dtu.getCurrentDate(), subs.get(i).getExpires())<-7) {
				notif.createNotification("SUBSCRIPTION RENEWAL DUE", subs.get(i).getUsername());
			}else if (dtu.dateCompare(dtu.getCurrentDate(), subs.get(i).getExpires())<-3) {
				notif.createNotification("SUBSCRIPTION RENEWAL DUE", subs.get(i).getUsername());
			} else if (dtu.dateCompare(dtu.getCurrentDate(), subs.get(i).getExpires())<-1) {
				notif.createNotification("SUBSCRIPTION RENEWAL DUE", subs.get(i).getUsername());
			}else {
				//NOTHING
			}
		}
		
	}
}