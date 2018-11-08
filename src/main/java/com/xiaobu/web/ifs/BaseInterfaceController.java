package com.xiaobu.web.ifs;

import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.xiaobu.common.base.BaseController;
import com.xiaobu.common.config.Code;
import com.xiaobu.common.constant.SysMessage;

import com.xiaobu.common.sms.SmsContentUtil;
import com.xiaobu.common.sms.SmsSingleSender;
import com.xiaobu.web.pro.entity.SdCollectTask;
import com.xiaobu.web.pro.service.SdCollectService;
import com.xiaobu.web.pro.service.SdCollectTaskService;
import com.xiaobu.web.redis.service.RedisInfoService;

import com.xiaobu.web.system.service.SdConsumerService;
import com.xiaobu.web.system.service.SdDictionarydataService;
import com.xiaobu.web.system.service.SdUserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


@Controller
@RequestMapping("/api/baseinterface")
public class BaseInterfaceController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(BaseInterfaceController.class);

	@Autowired
	private RedisInfoService redisService;

    @Autowired
    private SdCollectTaskService sdCollectTaskService;

    @Autowired
    private SdConsumerService sdConsumerService;

    @Autowired
    private SdDictionarydataService sdDictionarydataService;

    @Autowired
    private SdUserService sdUserService;

    @Autowired
    private SdCollectService sdCollectService;

    /***********************************************************************公用接口********************************************************************************/

    /**
     * 3.短信验证码接口
     */
    @RequestMapping(value={"/obtainSms"},method=RequestMethod.POST )
    @ResponseBody
    public Object obtainSms(String number){
        SmsSingleSender ssender = new SmsSingleSender(SmsContentUtil.APPID, SmsContentUtil.APPKEY);

        try {
            
            SmsSingleSenderResult result = ssender.sendWithParam(SmsContentUtil.nationCode,number,SmsContentUtil.TTEMPLATEID,SmsContentUtil.list,SmsContentUtil.SMSSIGN,"","");
            if(result.errMsg.equals("OK")) {
            	logger.info("电话"+number+",验证码："+SmsContentUtil.list.get(0));
            	//如果发送成功存入redis,设置过期时间为120秒
            	redisService.set(number, SmsContentUtil.list.get(0),120,TimeUnit.SECONDS);
            	//redisService.setString(number, SmsContentUtil.list.get(0));
            	return actionResult(Code.OK,result.errMsg);
            }else {
            	return actionResult(Code.BAD_REQUEST,result.errMsg);
            }
        } catch (HTTPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 7.获取数据字典
     * @param types
     * @return
     */
    /*@RequestMapping(value = "/selectDictionaryType",method = RequestMethod.GET)
    @ResponseBody
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER, SysMessage.ORGANIZATION,SysMessage.CONSUMER})
    @RequiresAuthentication
    public Object selectDictionaryType(String types){
        Map<String,Object> map = new HashMap<>();
        try {
            if(StringUtils.isNotEmpty(types)){
                map  = sdDictionarydataService.selectDictionaryType(types);
            }
            return actionResult(Code.OK,"获取成功",map);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR);
        }
    }*/

    /**
     * 7.根据任务id获取采集任务详情,平台、团队、甲方管理员同用一个接口
     * @param id
     * @return
     */
    @RequestMapping(value = "/findTaskInfoById",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.ORGANIZATION,SysMessage.CONSUMER,SysMessage.MANAGER})
    @RequiresAuthentication
    public Object findTaskInfoById(Integer id){
        try {
            SdCollectTask sdCollectTask = sdCollectTaskService.getById(id);
            return actionResult(Code.OK,"获取成功",sdCollectTask);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取失败");
        }
    }
}