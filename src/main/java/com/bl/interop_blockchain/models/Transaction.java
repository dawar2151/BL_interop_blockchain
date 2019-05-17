package com.bl.interop_blockchain.models;


import java.security.PrivateKey;
import java.security.PublicKey;

import com.bl.interop.utils.StringUtils;


public class Transaction {
	

	public int _id;//Contains a hash of transaction*
	public String sender;//Senders address/public key.
	public String recipient; //Recipients address/public key.
	public String data = "";
	public byte[] signature; //This is to prevent anybody else from spending data in our wallet.
	private static int sequence = 0; //A rough count of how many transactions have been generated 
	
	public Transaction(String send, String recipient, String data, byte[] signature) {
		super();
		this.sender = send;
		this.recipient = recipient;
		this.data = data;
		this.signature = signature;
	}
	public Transaction() {
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String send) {
		this.sender = send;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public byte[] getSignature() {
		return signature;
	}
	public void setSignature(byte[] signature) {
		this.signature = signature;
	}
	public static int getSequence() {
		return sequence;
	}
	public static void setSequence(int sequence) {
		Transaction.sequence = sequence;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getData();
	}
	//Signs all the data we dont wish to be tampered with.
	public void generateSignature(PrivateKey privateKey) {
		String data = sender + sender + this.data;
		signature = StringUtils.applyECDSASig(privateKey,data);		
	}
	//Verifies the data we signed hasnt been tampered with
	public boolean verifiySignature() {
		String data = sender + sender + this.data	;
		return StringUtils.verifyECDSASig(sender, data, signature);
	}
}

