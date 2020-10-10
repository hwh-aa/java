package com.etc.controller;

import com.etc.dao.ClaDao;
import com.etc.entity.Cla;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/article")
public class ClaController {

    @Resource
    private ClaDao claDao;

    @RequestMapping("/claquery")
    public ModelAndView query(){
        ModelAndView mv = new ModelAndView("claquery");
        mv.addObject("list",claDao.query());
        return mv;
    }

    @RequestMapping("/clatoadd")
    public ModelAndView toadd(){
        ModelAndView mv = new ModelAndView("claadd");
        return mv;
    }

    @RequestMapping("/claget/{cid}")
    public ModelAndView get(@PathVariable Integer cid){
        ModelAndView mv = new ModelAndView("claget");
        mv.addObject("c",claDao.get(cid));
        return mv;
    }

    @RequestMapping("/claadd")
    public ModelAndView add(Cla c){
        ModelAndView mv = new ModelAndView(new RedirectView("claget/"+c.getCid()));//重定向到另一个url影射
        claDao.add(c);
        mv.addObject("msg","新加成功");
        return mv;
    }

    @RequestMapping("/clamod")
    public ModelAndView mod(Cla c){
        ModelAndView mv = new ModelAndView(new InternalResourceView("claget/"+c.getCid()));//转发向到另一个url影射
        claDao.mod(c);
        mv.addObject("msg","修改成功");
        return mv;
    }

    @RequestMapping("/cladel/{cid}")
    public ModelAndView del(@PathVariable Integer cid){
        ModelAndView mv = new ModelAndView(new InternalResourceView("/article/claquery"));//转发向到另一个url影射
        claDao.del(cid);
        mv.addObject("msg","删除成功");
        return mv;
    }
}
