package com.bl.interop_blockchain.models;

import java.security.PrivateKey;
import java.security.PublicKey;

public class Peer {
	private int index;
	private String name;
	private int p2pPort;
	private int httpPort;
	private PrivateKey privateKey;
	private PublicKey publicKey;
	
	public Peer() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Peer ["
					+ "index=" + index + ", "
					+ "name=" + name + ", "
					+ "p2pPort=" + p2pPort + ","
					+ " httpPort=" + httpPort
					+ ", privateKey=" + privateKey + ", "
					+ "publicKey=" + publicKey + 
				"]";
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Peer(int p2pPort, int httpPort, PrivateKey privateKey, PublicKey publicKey) {
		super();
		this.p2pPort = p2pPort;
		this.httpPort = httpPort;
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}

	public int getP2pPort() {
		return p2pPort;
	}
	public void setP2pPort(int p2pPort) {
		this.p2pPort = p2pPort;
	}
	public int getHttpPort() {
		return httpPort;
	}
	public void setHttpPort(int httpPort) {
		this.httpPort = httpPort;
	}
	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}
	public PublicKey getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}
	
}
