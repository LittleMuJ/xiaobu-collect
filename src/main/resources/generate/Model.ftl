package ${package_name}.entity;
import java.io.Serializable;

import com.xiaobu.common.base.BaseEntity;

/**
* 描述：${table_annotation}模型
* @author ${author}
* @date ${date}
*/

public class ${table_name} extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
    <#if model_column?exists>
        <#list model_column as model>
    /**
    *${model.columnComment!}
    */
    <#if (model.columnType = 'varchar' || model.columnType = 'text')>
    private String ${model.changeColumnName?uncap_first};

    </#if>
    <#if (model.columnType = 'int4' || model.columnType = 'int')>
    private Integer ${model.changeColumnName?uncap_first};

    </#if>
    <#if model.columnType = 'timestamp' >
    private Date ${model.changeColumnName?uncap_first};

    </#if>
    <#if model.columnType = 'timestamptz' >
         private Date ${model.changeColumnName?uncap_first};

    </#if>
     <#if model.columnType = 'float8' >
    private Double ${model.changeColumnName?uncap_first};
     </#if>
        </#list>
    </#if>

<#if model_column?exists>
<#list model_column as model>
<#if (model.columnType = 'varchar' || model.columnType = 'text')>
    public String get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(String ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }

</#if>
<#if (model.columnType = 'int4' || model.columnType = 'int')>
    public Integer get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(Integer ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }

</#if>
<#if model.columnType = 'timestamp' >
    public Date get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(Date ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }

</#if>
<#if model.columnType = 'timestamptz' >
    public Date get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(Date ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
</#if>
<#if model.columnType = 'float8' >
    public Double get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(Double ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
</#if>
</#list>
</#if>

}