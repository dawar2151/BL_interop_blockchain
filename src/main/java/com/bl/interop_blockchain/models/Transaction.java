package com.bl.interop_blockchain.models;


import java.security.PublicKey;


public class Transaction {
	

	public int _id;//Contains a hash of transaction*
	public PublicKey send;//Senders address/public key.
	public PublicKey recipient; //Recipients address/public key.
	public String data;
	public byte[] signature; //This is to prevent anybody else from spending data in our wallet.
	private static int sequence = 0; //A rough count of how many transactions have been generated 
	
	public Transaction(PublicKey send, PublicKey recipient, String data, byte[] signature) {
		super();
		this.send = send;
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
	public PublicKey getSend() {
		return send;
	}
	public void setSend(PublicKey send) {
		this.send = send;
	}
	public PublicKey getRecipient() {
		return recipient;
	}
	public void setRecipient(PublicKey recipient) {
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
}

