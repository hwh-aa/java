package com.etc.dao;

import com.etc.entity.Cla;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClaDao {

    private static List<Cla> list = new ArrayList<>();
    //静态代码块
    static {
        Cla c = new Cla(10,"SJ806");
        Cla c2 = new Cla(20,"SJ807");
        Cla c3 = new Cla(30,"SJ808");
        list.add(c);
        list.add(c2);
        list.add(c3);
    }

    public List<Cla> query(){
        return list;
    }

    public void add(Cla c){
        list.add(c);
    }

    //根据主键来加载一条记录
    public Cla get(Integer cid){
        for(Cla c:list){
            if(c.getCid().equals(cid))return c;
        }
        return null;
    }

    public void del(Integer cid){
        for(Cla c:list){
            if(c.getCid().equals(cid)){
                list.remove(c);
                break;
            }
        }
    }

    public void mod(Cla c){
        int index = 0;//序号
        for(Cla ci:list){
            if(ci.getCid().equals(c.getCid())){//查找到要修改的班级
                list.set(index,c);
                break;
            }
            index++;
        }
    }
}
