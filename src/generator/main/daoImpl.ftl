package ${pac}.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import ${vo};
import seentao.xhsn.common.DaoUtil;
import ${pac}.dao.${clazzName?cap_first}DAO;


@Repository
@Scope("prototype")
public class ${clazzName?cap_first}DAOImpl extends SqlSessionDaoSupport implements ${clazzName?cap_first}DAO{

	private SqlSession sqlSession;
	private DaoUtil daoUtil;
	public void initDao(){
		if(daoUtil==null){
			sqlSession=sqlSession==null?getSqlSession():sqlSession;
			daoUtil=new DaoUtil(sqlSession);
		}
	}
	
	@Override
	public void insert(${vo} bo) {
		daoUtil.insertBO("seentao.xhsn.bean.mappers.${vo}Mapper.insert", bo);
	}

	@Override
	public void update(${vo} bo) {
		daoUtil.updateByPK("seentao.xhsn.bean.mappers.${vo}Mapper.updateByPrimaryKey", bo);
	}

	@Override
	public void delete(String id) {
		daoUtil.deleteByPK("seentao.xhsn.bean.mappers.${vo}Mapper.deleteByPrimaryKey", id);
	}

	@Override
	public ${vo} selectByPrimaryKey(String id) {
		return (${vo}) daoUtil.selectByPK("seentao.xhsn.bean.mappers.${vo}Mapper.selectByPrimaryKey", id);
	}

	@Override
	public List<${vo}> getAllByParam(${vo} bo) {
		List<${vo}> list=daoUtil.selectByBOParamT("seentao.xhsn.bean.mappers.${vo}Mapper.selectByParam", bo);
		return list;
	}
	
}