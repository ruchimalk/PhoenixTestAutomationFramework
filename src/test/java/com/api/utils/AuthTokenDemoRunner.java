package com.api.utils;

import java.time.Duration;

import com.api.constants.Roles;

public class AuthTokenDemoRunner {
	
	public static void main(String[] args) throws InterruptedException {
		
		for(int i=0; i<=100;i++) {
		String token=AuthTokenProvider.getToken(Roles.FD);
		Thread.sleep(2000);
		System.out.println(token);
		
	}
	}
}
