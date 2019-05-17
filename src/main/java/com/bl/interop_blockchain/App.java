package com.bl.interop_blockchain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;

import javax.servlet.http.HttpSession;

import com.bl.interop.networks.HTTPService;
import com.bl.interop.networks.P2PService;
import com.bl.interop.utils.Parameters;
import com.bl.interop.utils.PcryptUtils;
import com.bl.interop_blockchain.models.Peer;
import com.bl.interop_blockchain.services.BlockService;

public class App {
    public static void main(String[] args) {
    	Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        if (args != null && (args.length == 3 || args.length == 4)) {
            try {
            	Peer p = new Peer();
                p.setHttpPort(Integer.valueOf(args[0]));
                p.setP2pPort(Integer.valueOf(args[1]));
                BlockService blockService = new BlockService();
                P2PService p2pService = new P2PService(blockService);
                p2pService.initP2PServer(p.getP2pPort());
				if (args.length == 3) {
					p.setName(args[2]);
				 }
                if (args.length == 4 && args[2] != null) {
                    p2pService.connectToPeer(args[3]);
                }
    			KeyPairGenerator keyGen = KeyPairGenerator.getInstance(Parameters.algorithm,"BC");
    			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
    			ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
    			// Initialize the key generator and generate a KeyPair
    			keyGen.initialize(ecSpec, random); //256 
         
				KeyPair generatedKeyPair = keyGen.genKeyPair();
				PcryptUtils.SaveKeyPair(Parameters.path, generatedKeyPair);
                HTTPService httpService = new HTTPService(blockService, p2pService);
                httpService.initHTTPServer(p.getHttpPort());
            } catch (Exception e) {
            	e.printStackTrace();
                System.out.println("startup is error:" + e.getMessage());
            }
        } else {
            System.out.println("usage: java -jar naivechain.jar 8080 6001");
        }
    }
}