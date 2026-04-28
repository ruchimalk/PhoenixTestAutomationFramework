package com.api.utils;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import io.github.jopenlibs.vault.Vault;
import io.github.jopenlibs.vault.VaultConfig;
import io.github.jopenlibs.vault.VaultException;
import io.github.jopenlibs.vault.VaultImpl;
import io.github.jopenlibs.vault.response.LogicalResponse;

public class VaultDBConfig {
	
	private static VaultConfig vaultConfig;
	private static Vault vault;
	private static final org.apache.logging.log4j.Logger LOGGER= LogManager.getLogger(VaultDBConfig.class);
	
	static {
		
		try {
			vaultConfig= new VaultConfig().address(System.getenv("VAULT_SERVER")).token(System.getenv("VAULT_TOKEN")).build();
			vault = new VaultImpl(vaultConfig); 
		} catch (VaultException e) {
			LOGGER.error("Something went wrong with the Vault Config", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private VaultDBConfig() {
		
	
	}
public static String getSecret(String key) {
	LogicalResponse response = null;
	try {
		 response	=vault.logical().read("secret/phoenix/qa/database");
	} catch (VaultException e) {
		// TODO Auto-generated catch block
		LOGGER.error("Something went wrong with the reading of Vault response", e);

		e.printStackTrace();
		return null;//if something goes wrong
	}
	
	Map<String, String> dataMap= response.getData();
	String secretValue= dataMap.get(key);
	LOGGER.info("Secret found in the vault");
	return secretValue;
	
}
}
