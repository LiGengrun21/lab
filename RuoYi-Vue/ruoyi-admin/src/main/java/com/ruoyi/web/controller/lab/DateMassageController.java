package com.ruoyi.web.controller.lab;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.lab.service.IDateMassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lab/showdate")
public class DateMassageController extends BaseController {
    @Autowired
    private IDateMassageService dateMassageService;

    @GetMapping("/thisweek")
    public AjaxResult showThisWeek(){
        return AjaxResult.success(dateMassageService.ShowThisWeek());
    }

    @GetMapping("/24Hours")
    public AjaxResult show24Hours(){
        return AjaxResult.success(dateMassageService.Show24Hours());
    }
}
