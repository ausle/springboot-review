package com.asule.springbootreview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ReduceStockController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/deduct_stock")
    public String deductStock() {
        String lockStock="lock_product:100";
        UUID randomUUID = UUID.randomUUID();
        String randomStr=randomUUID.toString();
        // redis的单个命令具备原子性。
        Boolean isGetLock = stringRedisTemplate.opsForValue().setIfAbsent(lockStock, randomStr,10, TimeUnit.SECONDS);

        if (!isGetLock){
            return "system error";
        }
        if (isGetLock){
            try {
                int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
                if (stock > 0) {
                    int realStock = stock - 1;
                    stringRedisTemplate.opsForValue().set("stock", realStock + "");
                    System.out.println("扣减成功，剩余库存:" + realStock);
                } else {
                    System.out.println("扣减失败，库存不足");
                }
            }finally {
                if (randomStr.equals(stringRedisTemplate.opsForValue().get(lockStock))){
                    stringRedisTemplate.delete(lockStock);
                }
            }
        }
        return "end";
    }
}
