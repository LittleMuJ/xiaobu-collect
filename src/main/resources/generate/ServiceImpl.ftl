package ${package_name}.service.Impl;
import ${package_name}.entity.${table_name};
import ${package_name}.service.${table_name}Service;
import ${package_name}.dao.${table_name}Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.beanutils.BeanUtils;

/**
* 描述：${table_annotation} 服务实现层
* @author ${author}
* @date ${date}
*/
@Service("${table_name?uncap_first}Service")
public class ${table_name}ServiceImpl implements ${table_name}Service {

    @Autowired
    private ${table_name}Dao ${table_name?uncap_first}Dao;


    @Override
    public ${table_name} getById(Integer id) throws Exception {
        ${table_name} ${table_name?uncap_first} = ${table_name?uncap_first}Dao.getById(id);
        return ${table_name?uncap_first};
    }

    @Override
    public void add(${table_name} ${table_name?uncap_first}) {
       ${table_name?uncap_first}Dao.add(${table_name?uncap_first});
    }

    @Override
    public void update(${table_name} ${table_name?uncap_first}){
       ${table_name?uncap_first}Dao.updateNotNull(${table_name?uncap_first});
    }
    
    @Override
	public void delete(Integer id) {

		${table_name?uncap_first}Dao.delete(id);
	}
}