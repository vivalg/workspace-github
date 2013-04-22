package com.nic.mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisExample {
	public static void main(String[] args) throws Exception{
	    
		Reader reader=Resources.getResourceAsReader("mybatis/Configuration.xml");
		SqlSessionFactory factory= new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();
		
		//查询
		ContactMapper mapper = session.getMapper(ContactMapper.class);
		Contact contact = mapper.getContact("li");
		System.out.println(contact.getFirstName() + ", " + contact.getLastName());
		
		
		
		//插入1
//		System.out.println("*------insert Contact Table------------*");
//		Contact contact=new Contact("Amit","Kumar","amit@yes.com");
//		sqlMap.insert("insert",contact);
		//插入2
//		System.out.println("*------insert Contact Table------------*");
//		Contact contact=new Contact("firstName","lastName","tang@yes.com");
//		sqlMap.insert("insertContact",contact);
		//删除
//		int i=sqlMap.delete("deleteContactById", 1);
//		System.out.println("i="+i);
		
		//更新
//		sqlMap.update("updateById", 3);
		
		//根据id查询
//		Contact contact=(Contact) sqlMap.queryForObject("getById", new Integer(3));
//		System.out.println(contact.toString());
		
		//调用存储过程
//		List<Contact> contacts=(List<Contact>)sqlMap.queryForList("storedInfo", null);
//		Contact contact=null;
//		for(Contact c:contacts){
//			System.out.println(c.toString());
//		}
		
		//模糊查询
//		List<Contact> contacts=(List<Contact>)sqlMap.queryForList("selectByName","t");
//		Contact contact=null;
//		for(Contact c:contacts){
//			System.out.println(c.toString());
//		}
		//分页1
//		Map<String,Object> map=new HashMap<String,Object>();
//		map.put("orederById"," id asc");
//		map.put("limitClauseStart",3);
//		map.put("limitClauseCount",2);
//		List<Contact> result=(List<Contact>)sqlMap.queryForList("getByPage", map);
//		for(Contact c:result){
//			System.out.println(c.toString());
//		}
		
		//分页2
//		int skip=5;
//		int max=4;
//		String sql="getByPage1";
//		@SuppressWarnings("unchecked")
//		List<Contact> contacts=(List<Contact>)sqlMap.queryForList(sql,skip,max);
//		for(Contact c:contacts){
//			System.out.println(c.toString());
//		}
	}
}
