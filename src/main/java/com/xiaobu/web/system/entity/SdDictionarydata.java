package com.xiaobu.web.system.entity;
import java.io.Serializable;

import com.xiaobu.common.base.BaseEntity;

/**
* 描述：标注平台用户模型
* @author MuRunSen
* @date 2018-08-21 09:56:42
*/

public class SdDictionarydata extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
    /**
    *主键id
    */
    /**
    *字典名字
    */
    private String dictdataName;

    /**
    *字典值
    */
    private Integer dictdataValue;

    /**
    *字典类型
    */
    private String dictdataType;

    /**
    *
    */


    /**
    *
    */



    public String getDictdataName() {
        return this.dictdataName;
    }

    public void setDictdataName(String dictdataName) {
        this.dictdataName = dictdataName;
    }

    public Integer getDictdataValue() {
        return this.dictdataValue;
    }

    public void setDictdataValue(Integer dictdataValue) {
        this.dictdataValue = dictdataValue;
    }

    public String getDictdataType() {
        return this.dictdataType;
    }

    public void setDictdataType(String dictdataType) {
        this.dictdataType = dictdataType;
    }


}