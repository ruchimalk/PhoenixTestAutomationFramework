package com.api.utils;

import java.util.Map;

import io.github.jopenlibs.vault.Vault;
import io.github.jopenlibs.vault.VaultConfig;
import io.github.jopenlibs.vault.VaultException;
import io.github.jopenlibs.vault.VaultImpl;
import io.github.jopenlibs.vault.response.LogicalResponse;

public class VaultDemo {
	
	public static void main(String[] args) throws VaultException {
		
		String data= System.getenv("VAULT_token");
		System.out.println(data);
	}
		
		/*VaultConfig vaultConfig= new VaultConfig().address("http://13.60.183.39:8200")
				.token("root").build();
		
		Vault vault= new VaultImpl(vaultConfig);
	LogicalResponse response	=vault.logical().read("secret/phoenix/qa/database");
	Map<String, String> dataMap=response.getData();
	System.out.println(dataMap.get("DB_URL"));
	}*/

}
