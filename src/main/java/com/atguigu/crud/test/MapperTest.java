package com.atguigu.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.dao.DepartmentMapper;
import com.atguigu.crud.dao.EmployeeMapper;

/**
 * 	测试dao层的工作
 * 	推荐Spring的项目就可以使用Spring的单元测试
 * 	1、导入SpringTest模块
 * 	2、@ContextConfiguration指定Spring配置文件的位置
 * 	3、直接autowired要使用的组件即可
 * @author wangyu
 *
 */

public class MapperTest {
	
	
	/**
	 * 	测试DepartmentMapper
	 * 
	 */
	@Test
	public void testCRUD() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		DepartmentMapper departmentMapper = (DepartmentMapper) ctx.getBean(DepartmentMapper.class);
		
//		System.out.println(departmentMapper);
		
		EmployeeMapper employeeMapper = ctx.getBean(EmployeeMapper.class);
		
		//1.插入几个部门
//		departmentMapper.insertSelective(new Department(1,"开发部"));
//		departmentMapper.insertSelective(new Department(2, "测试部"));
		
		//2、生成员工数据，测试员工插入
//		employeeMapper.insertSelective(new Employee(null, "Jerry", "M", "Jerry@atguigu.com", 1));
		
		SqlSession sqlSession = ctx.getBean(SqlSession.class);
		
		//3、批量插入多个员工；批量，使用可以执行批量操作的SqlSession
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i = 0;i < 1000;i++) {
			String uid = UUID.randomUUID().toString().substring(0, 5) + i;
			employeeMapper.insertSelective(new Employee(null, uid, "M", uid+"@atguigu.com", 1));
		}
		System.out.println("批量插入完成！");
	}
	
}
