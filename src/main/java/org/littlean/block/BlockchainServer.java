package org.littlean.block;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 01 on 2018/3/10.
 */
public class BlockchainServer {
    private List<Blockchain> blocks;

    public BlockchainServer() {
        this.blocks = new ArrayList<>();
        blocks.add(this.CreationBlock());
    }

    private Blockchain CreationBlock() {
        return new Blockchain(1, "0", System.currentTimeMillis(), "CreationBlock", "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3");
    }

    private boolean isAddBlock(Blockchain blockchain, Blockchain lastBlock) {
        if(lastBlock.getIndex() + 1 != blockchain.getIndex()){
            return false;
        }else if(!blockchain.getPreviousHash().equals(lastBlock.getHash())){
            return false;
        }else {
            String hash = Util.getNextHash(blockchain.getIndex(),blockchain.getPreviousHash(),blockchain.getTimestamp(),blockchain.getData());
            if(!hash.equals(blockchain.getHash())){
                return false;
            }
        }
        return true;
    }


    public Blockchain getLastBlock() {
        int index = blocks.size();
        return blocks.get(index - 1);
    }

    public Blockchain createNewBlock(String blockData){
        Blockchain block = getLastBlock();
        int newIndex  = block.getIndex() + 1;
        long newTimeStamp = System.currentTimeMillis();
        String newHash = Util.getNextHash(newIndex,block.getHash(),newTimeStamp,blockData);
        return new Blockchain(newIndex,block.getHash(),newTimeStamp,blockData,newHash);
    }

    public boolean addBlock(Blockchain blockchain) {
        if(isAddBlock(blockchain, getLastBlock())){
            blocks.add(blockchain);
            return true;
        }
        return false;
    }



    public List<Blockchain> getBlocks() {
        return blocks;
    }
}
