package com.xiaobu.web.pro.controller;
import com.xiaobu.web.pro.service.SdLabelTaskService;
import com.xiaobu.web.pro.entity.SdLabelTask;
import com.xiaobu.common.base.BaseController;
import com.xiaobu.common.config.Code;
import com.xiaobu.common.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

/**
* 描述：标注平台用户controller
* @author MuRunSen
* @date 2018-08-09 15:02:18
*/
@Controller
@RequestMapping("/sdLabelTask")
public class SdLabelTaskController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(SdLabelTaskController.class);

    @Autowired
    private SdLabelTaskService sdLabelTaskService;

    /**
    * 描述：根据Id 查询
    * @param id  标注平台用户id
    */
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Object findById(@PathVariable("id") Integer id)throws Exception {
        SdLabelTask sdLabelTask = sdLabelTaskService.getById(id);
        
        return actionResult(Code.OK,sdLabelTask);
    }

    /**
    * 描述:创建标注平台用户
    * 保存
    * @param sdLabelTaskDTO  标注平台用户DTO
    */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object create(@RequestBody SdLabelTask sdLabelTask) throws Exception {
    try {
        // 修改
		if(ValidateUtil.isNotEmpty(sdLabelTask.getId())){
			
			sdLabelTaskService.update(sdLabelTask);
		}
		// 新增
			else{
				sdLabelTaskService.add(sdLabelTask);
			}
		} catch (Exception e) {
			
			logger.error(e.getMessage(), e);
		}
        return actionResult(Code.OK);
    }

    /**
    * 描述：删除标注平台用户
    * @param id 标注平台用户id
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object deleteById(@PathVariable("id") Integer id) throws Exception {
    try {
        	sdLabelTaskService.delete(id);
    	} catch (Exception e) {
		
		logger.error(e.getMessage(), e);
		return actionResult(Code.OK);
	}
	return actionResult(Code.INTERNAL_SERVER_ERROR);
  }
}