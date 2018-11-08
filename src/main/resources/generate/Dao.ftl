package ${package_name}.dao;
import org.apache.ibatis.annotations.Mapper;

import com.xiaobu.common.base.BaseDao;
import ${package_name}.entity.${table_name};

/**
* 描述：${table_annotation}DTO
* @author ${author}
* @date ${date}
*/
@Mapper
public interface ${table_name}Dao extends BaseDao<${table_name}, Integer>{

}