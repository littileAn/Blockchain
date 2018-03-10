package com.example.demo;

import org.littlean.block.Blockchain;
import org.littlean.block.BlockchainServer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 01 on 2018/3/10.
 */
@RestController
@RequestMapping("block")
public class TestController {

    private BlockchainServer blockchainServer = new BlockchainServer();

    @PostMapping(value = "/add/{data}")
    public String addNewBlock(@PathVariable("data") String data){
        Blockchain block = blockchainServer.createNewBlock(data);
        boolean flag = blockchainServer.addBlock(block);
        if(flag){
            return "ADD NEWBLOCK SUCCESS";
        }
        return "ADD NEWBLOCK FAILED";
    }

    @GetMapping(value = "/all")
    public List<Blockchain> getAll(){
        return blockchainServer.getBlocks();
    }


}
