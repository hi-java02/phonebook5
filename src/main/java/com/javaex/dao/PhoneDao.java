package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {

	@Autowired
	private SqlSession sqlSession;

	// 전체리스트 가져오기
	public List<PersonVo> getPersonList() {
		System.out.println("dao: getPersonList()");
		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList2");

		System.out.println(personList.toString());
		return personList;
	}

	// 전화번호 저장
	public int personInsert(PersonVo personVo) {
		System.out.println(personVo.toString());
		int count = sqlSession.insert("phonebook.insert", personVo);

		return count;
	}

	// 전화번호 삭제
	public int personDelete(int personId) {
		System.out.println("dao: personDelete() " + personId);

		int count = sqlSession.delete("phonebook.delete", personId);
		System.out.println(count);

		return count;
	}

	// 1명 데이터 가졍오기
	public PersonVo getPerson(int personId) {
		System.out.println("dao: getPerson()" + personId);
		
		PersonVo personVo = sqlSession.selectOne("phonebook.selectOne", personId);
		System.out.println(personVo.toString());
		
		return personVo;
	}
	
	// 1명 데이터 가졍오기2
	public Map<String, Object> getPerson2(int personId) {
		System.out.println("dao: getPerson2()" + personId);
		
		Map<String, Object> personMap = sqlSession.selectOne("phonebook.selectOne2", personId);
		System.out.println(personMap.toString());
		
		/*
		 * int id = (Integer)personMap.get("PERSONID"); System.out.println(id);
		 * 
		 * String name = (String)personMap.get("NAME"); System.out.println(name);
		 */
		
		return personMap;
	}
	
	

	// 수정
	public int personUpdate(PersonVo personVo) {
		System.out.println("dao: personUpdate()" + personVo.toString());
		
		int count = sqlSession.update("phonebook.update", personVo);
		System.out.println("dao: count = " + count );
		
		return count;
	}

	
	//수정2
	public int personUpdate2(int personId, String name, String hp, String company) {
		System.out.println("dao: personUpdate2()" + personId + "," + name + "," + hp + "," + company);
		
		//vo 대신 --> map
		Map<String, Object> personMap = new HashMap<String, Object>();
		personMap.put("id", personId);
		personMap.put("name", name);
		personMap.put("hp", hp);
		personMap.put("company", company);
		
		System.out.println(personMap.toString());
		
		return sqlSession.update("phonebook.update2", personMap);
	}
	
	
	
	
}
