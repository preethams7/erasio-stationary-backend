package com.hospital.base.business.auth.data;

import java.util.List;

import org.springframework.validation.BindingResult;

public class DataObject {

	private Object obj;
	
	private boolean status;
	
	private BindingResult bindingResult;
	
	private List<MessageObject> messages;

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public BindingResult getBindingResult() {
		return bindingResult;
	}

	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}

	public List<MessageObject> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageObject> messages) {
		this.messages = messages;
	}
}
