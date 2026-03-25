package com.dataproviders.api.bean;

import com.opencsv.bean.CsvBindByName;
import com.poiji.annotation.ExcelCellName;

public class UserBean {
	@ExcelCellName("username")
	@CsvBindByName(column = "username")
	private String username;
	@ExcelCellName("password")

	@CsvBindByName(column = "password")

	private String password;

	public UserBean() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "UserPOJO [username=" + username + ", password=" + password + "]";
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserBean(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

}
