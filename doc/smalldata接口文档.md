<font face="微软雅黑" size="6">**smalldata接口文档**</font>  
<font face="微软雅黑" size="3">当前版本V1.0</font>

----------
##### 1\.注册：
> ###### 1\.1管理员用注册接口
> 说明：标注数据管理后台的管理员用户注册  
> 接口地址：/api/signup/signupManager  
> 请求方式：POST  
> ##### 请求参数： 
>                名称           说明         数据类型         必选
>              username        用户名        String           Y
>              password        密码          String           Y
>              phone           电话号码      String           Y
>              vCode           验证码        String           Y
> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳       
> ##### 返回信息示例：
>               
				{
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
##### 2\.登录
> ###### 2\.1系统管理员登录接口
> 说明：  
> 接口地址：/api/login/checkManagerLogin  
> 请求方式：POST
> ##### 请求参数：
>                名称           说明         数据类型         必选
>              username        用户名        String           Y
>              password        密码          String           Y
> ##### 应答参数：
>
> ##### 返回消息示例：
>            <!DOCTYPE html>
             <html xmlns="http://www.w3.org/1999/xhtml">
			    <head>
			        <meta charset="utf-8">
			        <meta http-equiv="X-UA-Compatible" content="IE=edge">
			        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
			        <link rel="stylesheet" href="/statics/plugin/bootstrap-3.3.7/css/bootstrap.min.css">
			        <link rel="stylesheet" href="/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css">
			        <link rel="stylesheet" href="/statics/plugin/AdminLTE-2.4.3/css/AdminLTE.min.css">
			        <link rel="stylesheet" href="/statics/plugin/layer/theme/default/layer.css">
			    </head>
			    <body class="hold-transition login-page">
			        <div class="login-box">
					</div>
					        <script src="/statics/plugin/jquery-3.2.1.min.js"></script>
					        <script src="/statics/plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>
					        <script src="/statics/plugin/layer/layer.js"></script>
					        <script src="/js/common/login.js"></script>
					<script>
					    var myData=smalldata;
					    var indexData ={'userName':smalldata,'userEmail':,'userType':Manager,'userPhone':15185175127}
					</script>
					    </body>
					</html>  
> ##### 2\.2甲方人员登录接口
> 说明：  
> 接口地址：/api/login/checkConsumerLogin  
> 请求方式：POST
> ##### 请求参数：
>                名称           说明         数据类型         必选
>              username        用户名        String           Y
>              password        密码          String           Y
> ##### 应答参数：
>
> ##### 返回消息示例：
>            <!DOCTYPE html>
             <html xmlns="http://www.w3.org/1999/xhtml">
			    <head>
			        <meta charset="utf-8">
			        <meta http-equiv="X-UA-Compatible" content="IE=edge">
			        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
			        <link rel="stylesheet" href="/statics/plugin/bootstrap-3.3.7/css/bootstrap.min.css">
			        <link rel="stylesheet" href="/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css">
			        <link rel="stylesheet" href="/statics/plugin/AdminLTE-2.4.3/css/AdminLTE.min.css">
			        <link rel="stylesheet" href="/statics/plugin/layer/theme/default/layer.css">
			    </head>
			    <body class="hold-transition login-page">
			        <div class="login-box">
					</div>
					        <script src="/statics/plugin/jquery-3.2.1.min.js"></script>
					        <script src="/statics/plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>
					        <script src="/statics/plugin/layer/layer.js"></script>
					        <script src="/js/common/login.js"></script>
					<script>
					    var myData=smalldata;
					    var indexData ={'userName':smalldata,'userEmail':,'userType':Manager,'userPhone':15185175127}
					</script>
					    </body>
					</html>  
> ##### 2\.3团队管理员登录接口
> 说明：  
> 接口地址：/api/login/checkOrganizationLogin  
> 请求方式：POST
> ##### 请求参数：
>                名称           说明         数据类型         必选
>              username        用户名        String           Y
>              password        密码          String           Y
> ##### 应答参数：
>
> ##### 返回消息示例：
>            <!DOCTYPE html>
             <html xmlns="http://www.w3.org/1999/xhtml">
			    <head>
			        <meta charset="utf-8">
			        <meta http-equiv="X-UA-Compatible" content="IE=edge">
			        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
			        <link rel="stylesheet" href="/statics/plugin/bootstrap-3.3.7/css/bootstrap.min.css">
			        <link rel="stylesheet" href="/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css">
			        <link rel="stylesheet" href="/statics/plugin/AdminLTE-2.4.3/css/AdminLTE.min.css">
			        <link rel="stylesheet" href="/statics/plugin/layer/theme/default/layer.css">
			    </head>
			    <body class="hold-transition login-page">
			        <div class="login-box">
					</div>
					        <script src="/statics/plugin/jquery-3.2.1.min.js"></script>
					        <script src="/statics/plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>
					        <script src="/statics/plugin/layer/layer.js"></script>
					        <script src="/js/common/login.js"></script>
					<script>
					    var myData=smalldata;
					    var indexData ={'userName':smalldata,'userEmail':,'userType':Manager,'userPhone':15185175127}
					</script>
					    </body>
					</html>  
##### 3\.获取短信验证码接口
>说明：注册时获取手机短信验证码  
>请求方式：POST  
>请求地址：/api/baseinterface/obtainSms
> ##### 请求参数： 
>                名称           说明         数据类型         必选
>              number        电话号码        String             Y
> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳       
> ##### 返回信息示例：
>               
				{
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
##### 4\.平台管理员相关接口：/api/manager
>##### 4.1 发布采集任务接口及拆分接口
>>说明：当发布公网任务时organizationId不传，当直接发布给团体时以及拆分给团体时需传organizationId  
>>请求方式：POST  
>>请求地址：/api/manager/issueCollectTask  
>>权限：Manager  
>> ##### 请求参数： 
>         名称                 说明             数据类型                        必选
>      SdCollectTask         实体类对象          Object                         Y  
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
>##### 4.2 平台管理员采集任务列表分页
>>说明：  
>>请求方式：POST  
>>请求地址：/api/manager/collectTaskList  
>>权限：Manager  
>> ##### 请求参数： 
>         名称                 说明             数据类型                        必选
>      SdCollectTask         实体类对象          Object                         Y  
>      start                 起始行             Integer                         Y
>      length                每页展示数据条数    Integer                         Y
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
>##### 4.3 平台管理员查询审核页面List分页（）
>>说明：平台管理员查询审核List，查询organization_id为空的公网任务以及状态值为3的团队管理员提交的待审核状态  
>>请求方式：POST  
>>请求地址：/api/manager/examineCollectList  
>>权限：Manager  
>> ##### 请求参数： 
>         名称                 说明             数据类型                        必选
>      SdCollectTask    实体类对象（作为筛选）     Object                         Y  
>      start                 起始行             Integer                         Y
>      length                每页展示数据条数    Integer                         Y
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
>##### 4.4 平台管理员审核详情接口
>>说明：平台管理员审核详情接口  
>>请求方式：POST  
>>请求地址：/api/manager/examineCollectInfo  
>>权限：Manager  
>> ##### 请求参数： 
>         名称                 说明             数据类型                        必选
>      taskId                任务id             Integer                         Y  
>      start                 起始行             Integer                         Y
>      length                每页展示数据条数    Integer                         Y
>      examineLen        审核长度，此参数是在审核 Integer                         N
>                         团队提交任务时传入
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
>##### 4.5 平台管理员审核接口(因平台管理员审核的单表sd_collect数据，只是状态值传值不同平台管理员传6或7
>>说明：平台管理员审核接口(因平台管理员审核的单表sd_collect数据，只是状态值传值不同平台管理员传6或7  
>>请求方式：POST  
>>请求地址：/api/manager/examineCollect  
>>权限：Manager  
>> ##### 请求参数： 
>         名称                 说明                数据类型                        必选
>      id                    单条任务id             Integer                         Y  
>      status                状态值                 Integer                         Y
>      taskId                任务id                 Integer                         Y
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
>##### 4.6 此接口是在填写任务表单时用做查询团队、甲方信息，权限为平台管理员
>>说明：此接口是在填写任务表单时用做查询团队、甲方信息，权限为平台管理员  
>>请求方式：POST  
>>请求地址：/api/manager/orgAndConInfo  
>>权限：Manager  
>> ##### 请求参数： 
>         名称                 说明                数据类型                        必选
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
>##### 4.7 平台管理员更新采集任务接口，权限为只有平台管理员可访问
>>说明：平台管理员更新采集任务接口，权限为只有平台管理员可访问  
>>请求方式：POST  
>>请求地址：/api/manager/updateCollectTask  
>>权限：Manager  
>> ##### 请求参数： 
>         名称                 说明                数据类型                        必选
>      sdCollectTask        实体类对象             Object                         Y  
>      restart            认识重启标识             String
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
>##### 4.8 根据任务Id撤销采集任务接口
>>说明：平台管理员更新采集任务接口，权限为只有平台管理员可访问  
>>请求方式：POST  
>>请求地址：/api/manager/revocationCollectTask  
>>权限：Manager  
>> ##### 请求参数： 
>         名称                 说明                数据类型                        必选
>         id                 采集任务id             Object                         Y  
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
>##### 4.9 提交采集任务至甲方管理员验收接口
>>说明：平台管理员更新采集任务接口，权限为只有平台管理员可访问  
>>请求方式：POST  
>>请求地址：/api/manager/submitCollectTask  
>>权限：Manager  
>> ##### 请求参数： 
>         名称                 说明                数据类型                        必选
>        taskId             整体任务id             Integer                         Y  
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String    
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
>##### 4.10 甲方信息列表List接口（分页）
 >>说明：甲方信息列表List接口（分页）
 >>请求方式：POST  
 >>请求地址：/api/manager/queryConsumerList
 >>权限：Manager  
 >> ##### 请求参数： 
 >         名称                 说明             数据类型                        必选
 >          start            起始行             Integer                         Y
 >          length           每页展示数据条数    Integer                         Y
 >> ##### 应答参数：
 >                名称           说明         数据类型
 >                code          请求状态      String
 >                message       返回信息      String
 >                result        返回数据      
 >                timestamp     时间戳 
 >> ##### 返回信息示例：
 >               {
 			    "timestamp": 1524041560641,
 			    "message":返回提示信息（一般为成功、失败等）,
 			    "result":,
 			    "code": 200
                 }  
  >##### 4.11 新增甲方信息接口
   >>说明：新增甲方信息接口
   >>请求方式：POST  
   >>请求地址：/api/manager/addConsumerInfo 
   >>权限：Manager  
   >> ##### 请求参数： 
   >         名称                 说明             数据类型                        必选
   >          name                姓名             String                        
   >          phone               电话号码         String                          
   >          email               邮箱             String
   >          username            账号             String                           Y
   >          password            密码             String                           Y
   >> ##### 应答参数：
   >                名称           说明         数据类型
   >                code          请求状态      String
   >                message       返回信息      String
   >                timestamp     时间戳 
   >> ##### 返回信息示例：
   >               {
   			    "timestamp": 1524041560641,
   			    "message":返回提示信息（一般为成功、失败等）,
   			    "code": 200
                   }   
   >##### 4.12 修改甲方信息接口
   >>说明：修改甲方信息接口
   >>请求方式：POST  
   >>请求地址：/api/manager/modifyConsumerInfo
   >>权限：Manager  
   >> ##### 请求参数： 
   >         名称                 说明             数据类型                        必选
   >          id                  甲方id              int                             Y
   >          name                姓名             String                        
   >          phone               电话号码         String                          
   >          email               邮箱             String
   >> ##### 应答参数：
   >                名称           说明         数据类型
   >                code          请求状态      String
   >                message       返回信息      String
   >                timestamp     时间戳 
   >> ##### 返回信息示例：
   >               {
      			    "timestamp": 1524041560641,
      			    "message":返回提示信息（一般为成功、失败等）,
      			    "code": 200
                      }   
                      
  >##### 4.13 删除甲方信息接口
  >>说明：删除甲方信息接口
  >>请求方式：GET  
  >>请求地址：/api/manager/removeConsumerInfo
  >>权限：Manager  
  >> ##### 请求参数： 
  >         名称                 说明             数据类型                        必选
  >          id                  甲方id                int                             Y
  >> ##### 应答参数：
  >                名称           说明         数据类型
  >                code          请求状态      String
  >                message       返回信息      String
  >                timestamp     时间戳 
  >> ##### 返回信息示例：
  >               {
       			    "timestamp": 1524041560641,
       			    "message":返回提示信息（一般为成功、失败等）,
       			    "code": 200
                       }
>##### 4.14 团体组织信息List接口（分页）
>>说明：团体组织信息List接口（分页） 
>>请求方式：POST  
>>请求地址：/api/manager/queryOrganizationList  
>>权限：Manager  
>> ##### 请求参数： 
>         名称                 说明                数据类型                        必选
>         start                起始行              Integer                         Y  
>         length               当页显示长度        Integer                         Y
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
>##### 4.15 新增团体组织信息接口
>>说明：新增团体组织信息接口 
>>请求方式：POST  
>>请求地址：/api/manager/addOrganizationInfo  
>>权限：Manager  
>> ##### 请求参数： 
>         名称                 说明                数据类型                        必选
>         name                 姓名                String                            
>         intro                介绍                String                          
>         username             账号                String                           Y
>         password             密码                String                           Y
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "code": 200
                }
>##### 4.16 修改团体组织信息接口
>>说明：修改团体组织信息接口 
>>请求方式：POST  
>>请求地址：/api/manager/modifyOrganizationInfo  
>>权限：Manager  
>> ##### 请求参数： 
>         名称                 说明                数据类型                        必选
>         id                   团队id                  Integer                         Y  
>         name                 姓名                String                            
>         intro                介绍                String                          
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "code": 200
                }
>##### 4.17 根据id删除团体组织信息接口
>>说明：根据id删除团体组织信息接口 
>>请求方式：POST  
>>请求地址：/api/manager/removeOrganizationInfo  
>>权限：Manager  
>> ##### 请求参数： 
>         名称                 说明                数据类型                        必选
>         id                   团队id                  Integer                         Y  
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "code": 200
                }
>##### 4.18 系统用户List分页（待审核用户分页）
>>说明：系统用户List分页（待审核用户分页）
>>请求方式：POST  
>>请求地址：/api/manager/userList  
>>权限：Manager  
>> ##### 请求参数： 
>         名称                 说明                数据类型                        必选
>         status              审核状态              Integer                          Y 
>         start               分页开始行            Integer                          Y
>         length              每页长度              Integer                          Y
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "code": 200
                }
>##### 4.19 审核成为系统成员
>>说明：审核成为系统成员 
>>请求方式：POST  
>>请求地址：/api/manager/auditUser  
>>权限：Manager  
>> ##### 请求参数： 
>         名称                 说明                数据类型                        必选
>         id                  用户id              Integer                          Y 
>         status       审核状态 2通过 3不通过       Integer                         Y
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "code": 200
                }
>##### 4.20 平台管理员审核整条任务的通过与不通过 状态值是4审核失败(平台管理员审核)->5审核成功(平台管理员审核)
>>说明：平台管理员审核整条任务的通过与不通过 状态值是4审核失败(平台管理员审核)->5审核成功(平台管理员审核) 
>>请求方式：POST  
>>请求地址：/api/manager/examineCollectTask  
>>权限：Manager  
>> ##### 请求参数： 
>         名称                 说明                数据类型                        必选
>         taskId              任务id              Integer                          Y 
>         status       审核状态 4通过 5不通过       Integer                         Y
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "code": 200
                }
>##### 4.21 获取平台管理员首页数据
>>说明：获取平台管理员首页数据 
>>请求方式：POST  
>>请求地址：/api/manager/findSysInfo  
>>权限：Manager  
>> ##### 请求参数： 
>         名称                 说明                数据类型                        必选
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "code": 200
                }

>##### 4.22 获取平台管理员审核的总情况
>>说明：获取平台管理员首页数据 
>>请求方式：POST  
>>请求地址：/api/manager/findExamineInfo  
>>权限：Manager  
>> ##### 请求参数： 
>         名称                 说明                数据类型                        必选
>         taskId             任务id               Integer                         Y
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "code": 200
                }

##### 5\.团队管理员相关接口
>##### 5.1 团队管理员采集任务列表（已接收）分页接口，团队id即可查询
>>说明：团队管理员采集任务列表（已接收）分页接口，团队id即可查询  
>>请求方式：POST  
>>请求地址：/api/organization/collectTaskList  
>>权限：Organization  
>> ##### 请求参数： 
>         名称                 说明             数据类型                        必选
>      organizationId        团队id            Integer                           Y  
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
>##### 5.2 团队管理员采集任务待领取（接收）分页List
>>说明：团队管理员采集任务待领取（接收）分页List  
>>请求方式：POST  
>>请求地址：/api/organization/readyCollectList  
>>权限：Organization  
>> ##### 请求参数： 
>         名称                 说明             数据类型                        必选
>      organizationId        团队id            Integer                           Y  
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
>##### 5.3 团队管理员采集任务领取（接收）接口
>>说明：团队管理员采集任务领取（接收）接口  
>>请求方式：POST  
>>请求地址：/api/organization/receptionCollect  
>>权限：Organization  
>> ##### 请求参数： 
>         名称                 说明             数据类型                        必选
>          id                 任务id            Integer                           Y  
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
>##### 5.4 团队管理员查询质检页面List分页（）
>>说明：团队管理员查询质检页面List分页（）  
>>请求方式：POST  
>>请求地址：/api/organization/examineCollectList  
>>权限：Organization  
>> ##### 请求参数： 
>         名称                 说明             数据类型                        必选
>     organizationId          团队id            Integer                         Y  
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
>##### 5.5 团队管理员质检成员提交的采集任务详情接口
>>说明：团队管理员质检成员提交的采集任务详情接口  
>>请求方式：POST  
>>请求地址：/api/organization/examineCollectInfo  
>>权限：Organization  
>> ##### 请求参数： 
>         名称                  说明             数据类型                        必选
>       taskId                 任务id            Integer                         Y  
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
>##### 5.6 团队管理员检验单条担任接口
>>说明：团队管理员检验单条担任接口  
>>请求方式：POST  
>>请求地址：/api/organization/examineCollect  
>>权限：Organization  
>> ##### 请求参数： 
>         名称                 说明                数据类型                        必选
>         taskId             任务id               Integer                         Y
>         id                 单条任务id            Integer                         Y  
>         status       状态值 3检验失败，4检验成功   Integer                         Y
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
                

>##### 5.11 团队管理员提交任务至平台管理员审核接口
>>说明：团队管理员提交任务至平台管理员审核接口 
>>请求方式：POST  
>>请求地址：/api/organization/submitCollectTask  
>>权限：Organization  
>> ##### 请求参数： 
>         名称                 说明                    数据类型                        必选
>         taskId            整体任务id                 Integer                         Y
>         orgId               团队id                  Integer                         Y  
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "code": 200
                }
>##### 5.12 根据组织id查询审核通过的组织成员信息接口
>>说明：根据组织id查询审核通过的组织成员信息接口 
>>请求方式：POST  
>>请求地址：/api/organization/queryOrgRoleInfoById  
>>权限：Manager、Organization  
>> ##### 请求参数： 
>         名称                 说明             数据类型                        必选
>      organizationId          团队id            Integer                         Y  
>      start                   起始行            Integer                         Y
>      length                  每页显示长度      Integer                         Y
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
>##### 5.13 根据组织id查询待审核的组织成员信息接口
>>说明：根据组织id查询待审核的组织成员信息接口 
>>请求方式：POST  
>>请求地址：/api/organization/queryOrgRoleInfoByStatus  
>>权限：Manager、Organization   
>> ##### 请求参数： 
>         名称                 说明             数据类型                        必选
>      organizationId          团队id            Integer                         Y  
>      start                   起始行            Integer                         Y
>      length                  每页显示长度      Integer                         Y
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
                
>##### 5.14 审核成员信息接口
>>说明：审核成员信息接口
>>请求方式：POST  
>>请求地址：/api/organization/modifyOrgRoleInfoByStatus  
>>权限：Manager、Organization  
>> ##### 请求参数： 
>         名称                 说明             数据类型                        必选
>          id                  团队成员id        Integer                        Y  
>          status              审核状态          Integer                        Y
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }

>##### 5.15 获取团队用户信息（团队成员，团队采集任务数）
>>说明：获取团队用户信息（团队成员，团队采集任务数）
>>请求方式：POST  
>>请求地址：/api/organization/findOrgInfo  
>>权限：Organization  
>> ##### 请求参数： 
>         名称                 说明             数据类型                        必选
>        orgId                团队id          Integer                        Y  
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }

>##### 5.16 获取团队管理员首页的表格中已提交审核的数据
>>说明：获取团队管理员首页的表格中已提交审核的数据
>>请求方式：POST  
>>请求地址：/api/organization/findOrgTaskInfo  
>>权限：Organization  
>> ##### 请求参数： 
>         名称                 说明             数据类型                        必选
>        orgId                团队id          Integer                        Y  
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }

>##### 5.17 获取某任务的饼状图信息
>>说明：获取某任务的饼状图信息
>>请求方式：POST  
>>请求地址：/api/organization/orgPiechartInfo  
>>权限：Organization  
>> ##### 请求参数： 
>         名称                 说明             数据类型                        必选
>        orgId                团队id           Integer                          Y  
>        taskId               任务id           Integer                          Y
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
##### 6\.甲方管理员相关接口
>##### 6.1 甲方管理员采集任务列表分页接口，甲方id即可查询
>>说明：甲方管理员采集任务列表分页接口，甲方id即可查询 
>>请求方式：POST  
>>请求地址：/api/consumer/collectTaskList  
>>权限：Consumer  
>> ##### 请求参数： 
>         名称                 说明             数据类型                        必选
>      consumerId            甲方id            Integer                           Y  
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
>##### 6.2 甲方验收页面List分页
>>说明：甲方验收页面List分页 
>>请求方式：POST  
>>请求地址：/api/consumer/examineCollectList  
>>权限：Consumer  
>> ##### 请求参数： 
>         名称                 说明             数据类型                        必选
>      consumerId            甲方id            Integer                           Y  
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
>##### 6.3 甲方管理员验收详情接口
>>说明：甲方管理员验收详情接口 
>>请求方式：POST  
>>请求地址：/api/consumer/examineCollectInfo  
>>权限：Consumer  
>> ##### 请求参数： 
>         名称                 说明             数据类型                        必选
>          id                任务id            Integer                           Y 
>          examineLen       验收抽样条数        Integer                           Y
>          start            起始行             Integer                         Y
>          length           每页展示数据条数    Integer                         Y
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
>##### 6.4 甲方管理员验收接口，甲方管理管理员验收的平台管理员提交验收的任务
>>说明：甲方管理员验收接口，甲方管理管理员验收的平台管理员提交验收的任务  验收状态值 8（验收失败），9（验收成功）
>>请求方式：POST  
>>请求地址：/api/consumer/examineCollect  
>>权限：Consumer  
>> ##### 请求参数： 
>         名称                 说明             数据类型                        必选
>          id           collect表单条记录id     Integer                         Y 
>          status           验收状态值          Integer                         Y
>          taskId            所属任务id         Integer                         Y
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
>##### 6.5 甲方管理员账号登录查看自己的信息，根据id
>>说明：甲方管理员账号登录查看自己的信息，根据id 
>>请求方式：POST  
>>请求地址：/api/consumer/findConsumerInfoById  
>>权限：Consumer  
>> ##### 请求参数： 
>         名称                 说明             数据类型                        必选
>          id              甲方的用户id         Integer                           Y 
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                } 

>##### 6.6 甲方管理员验收整条任务的通过与不通过 状态值是7验收失败(甲方管理员验收)->8验收成功(甲方管理员验收)
>>说明：甲方管理员验收整条任务的通过与不通过 状态值是7验收失败(甲方管理员验收)->8验收成功(甲方管理员验收) 
>>请求方式：POST  
>>请求地址：/api/consumer/examineCollectTask  
>>权限：Consumer  
>> ##### 请求参数： 
>         名称                 说明             数据类型                        必选
>         taskId              任务id            Integer                         Y 
>         status          审核状态，7失败，8成功  Integer                         Y
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }

>##### 6.7 获取甲方管理员首页信息
>>说明：获取甲方管理员首页信息 
>>请求方式：POST  
>>请求地址：/api/consumer/findConInfo  
>>权限：Consumer  
>> ##### 请求参数： 
>         名称                 说明             数据类型                        必选
>         ConId           甲方管理员用户id       Integer                         Y
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }
>##### 6.8 获取甲方管理员审核的总情况
>>说明：获取甲方管理员审核的总情况 
>>请求方式：POST  
>>请求地址：/api/consumer/findExamineInfo  
>>权限：Consumer  
>> ##### 请求参数： 
>         名称                 说明             数据类型                        必选
>         taskId              任务id            Integer                         Y
>> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳 
>> ##### 返回信息示例：
>               {
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }         

>##### 7\.根据任务id获取采集任务详情,平台、团队、甲方管理员同用一个接口(此为公用接口)
>说明：根据任务id获取采集任务详情,平台、团队、甲方管理员同用一个接口  
>请求方式：POST  
>请求地址：/api/baseinterface/findTaskInfoById
> ##### 请求参数： 
>                名称           说明         数据类型              必选
>                 id           任务id          Integer             Y
> ##### 应答参数：
>                名称           说明         数据类型
>                code          请求状态      String
>                message       返回信息      String
>                result        返回数据      
>                timestamp     时间戳       
> ##### 返回信息示例：
>               
				{
			    "timestamp": 1524041560641,
			    "message":返回提示信息（一般为成功、失败等）,
			    "result":,
			    "code": 200
                }

