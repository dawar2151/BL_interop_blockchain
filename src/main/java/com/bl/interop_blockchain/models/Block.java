package com.bl.interop_blockchain.models;


import java.util.ArrayList;
import java.util.Date;

public class Block {
	
	public String hash;
	public String previousHash; 
	public String merkleRoot;
	public ArrayList<Transaction> messages = new ArrayList<Transaction>(); //our data will be a simple message.
	public long timeStamp;
	public int index;
	
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getPreviousHash() {
		return previousHash;
	}
	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}
	public String getMerkleRoot() {
		return merkleRoot;
	}
	public void setMerkleRoot(String merkleRoot) {
		this.merkleRoot = merkleRoot;
	}
	public ArrayList<Transaction> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<Transaction> messages) {
		this.messages = messages;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int nonce) {
		this.index = nonce;
	}
	public Block() {
		// TODO Auto-generated constructor stub
	}

	public Block(int index, String previousHash, long timeStamp, ArrayList<Transaction> messages) {
		super();
		this.previousHash = previousHash;
		this.messages = messages;
		this.timeStamp = timeStamp;
		this.index = index;
		this.hash = calculateHash(); //Making sure we do this after we set the other values.
	}
		//Block Constructor.  
		public Block(String previousHash ) {
			this.previousHash = previousHash;
			this.timeStamp = new Date().getTime();
			
			this.hash = calculateHash(); //Making sure we do this after we set the other values.
		}
		
		//Calculate new hash based on blocks contents
		public String calculateHash() {
			String calculatedhash = StringUtils.applySha256( 
					previousHash +
					Long.toString(timeStamp) +
					Integer.toString(index) + 
					merkleRoot
					);
			return calculatedhash;
		}
		
		//Increases nonce value until hash target is reached.
		public void mineBlock(int difficulty) {
			merkleRoot = StringUtils.getMerkleRoot(messages);
			String target = StringUtils.getDificultyString(difficulty); //Create a string with difficulty * "0" 
			while(!hash.substring( 0, difficulty).equals(target)) {
				index ++;
				hash = calculateHash();
			}
			System.out.println("Block Mined!!! : " + hash);
		}
		
		//Add transactions to this block
		public boolean addTransaction(Transaction message) {
			//process transaction and check if valid, unless block is genesis block then ignore.
			if(message == null) return false;
			/*
			if((previousHash != "0")) {
				if((message.processTransaction() != true)) {
					System.out.println("Transaction failed to process. Discarded.");
					return false;
				}
			}
			*/
			messages.add(message);
			System.out.println("Transaction Successfully added to Block");
			return true;
		}
		
	}
