package com.bl.interop_blockchain.services;

import java.util.ArrayList;
import java.util.List;

import com.bl.interop_blockchain.models.Block;
import com.bl.interop_blockchain.models.StringUtils;
import com.bl.interop_blockchain.models.Transaction;



public class BlockService {
    private List<Block> blockChain;

    public BlockService() {
        this.blockChain = new ArrayList<Block>();
        blockChain.add(this.getFristBlock());
    }

    private String calculateHash(int index, String previousHash, long timestamp, String data) {
        StringBuilder builder = new StringBuilder(index);
        builder.append(previousHash).append(timestamp).append(data);
        return StringUtils.applySha256(builder.toString());
    }

    public Block getLatestBlock() {
        return blockChain.get(blockChain.size() - 1);
    }

    private Block getFristBlock() {
    	Transaction transaction = new Transaction();
    	transaction.set_id(0);
    	transaction.setData("Hello Block");
    	ArrayList<Transaction> l = new ArrayList<Transaction>();
    	l.add(transaction);
 
        return new Block(1, "0", System.currentTimeMillis(), l);
    }

    public Block generateNextBlock(String blockData) {
        Block previousBlock = this.getLatestBlock();
        int nextIndex = previousBlock.getIndex() + 1;
        long nextTimestamp = System.currentTimeMillis();
        Transaction transaction = new Transaction();
    	transaction.set_id(0);
    	transaction.setData(blockData);
    	ArrayList<Transaction> l = new ArrayList<Transaction>();
    	l.add(transaction);
        return new Block(nextIndex, previousBlock.getHash(), nextTimestamp, l);
    }

    public void addBlock(Block newBlock) {
        if (isValidNewBlock(newBlock, getLatestBlock())) {
            blockChain.add(newBlock);
        }
    }

    private boolean isValidNewBlock(Block newBlock, Block previousBlock) {
        if (previousBlock.getIndex() + 1 != newBlock.getIndex()) {
            System.out.println("invalid index");
            return false;
        } else if (!previousBlock.getHash().equals(newBlock.getPreviousHash())) {
            System.out.println("invalid previoushash");
            return false;
        } else {
            String hash =newBlock.calculateHash();
            if (!hash.equals(newBlock.getHash())) {
                System.out.println("invalid hash: " + hash + " " + newBlock.getHash());
                return false;
            }
        }
        return true;
    }

    public void replaceChain(List<Block> newBlocks) {
        if (isValidBlocks(newBlocks) && newBlocks.size() > blockChain.size()) {
            blockChain = newBlocks;
        } else {
            System.out.println("Received blockchain invalid");
        }
    }

    private boolean isValidBlocks(List<Block> newBlocks) {
        Block fristBlock = newBlocks.get(0);
        if (fristBlock.equals(getFristBlock())) {
            return false;
        }

        for (int i = 1; i < newBlocks.size(); i++) {
            if (isValidNewBlock(newBlocks.get(i), fristBlock)) {
                fristBlock = newBlocks.get(i);
            } else {
                return false;
            }
        }
        return true;
    }

    public List<Block> getBlockChain() {
        return blockChain;
    }
}
