package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {

	@Autowired
	private SqlSession sqlSession;
	
	//전체리스트 가져오기
	public List<PersonVo> getPersonList(){
		System.out.println("dao: getPersonList()");
		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList2");
		
		System.out.println(personList.toString());
		return personList;
	}
	
	
	//전화번호 저장
	public int  personInsert(PersonVo personVo) {
		System.out.println(personVo.toString());
		int count = sqlSession.insert("phonebook.insert", personVo);
		
		return count;
	}
	
	//전화번호 삭제
	public int personDelete(int personId) {
		System.out.println("dao: personDelete() " +  personId);
		
		int count = sqlSession.delete("phonebook.delete", personId);
		System.out.println(count);
		
		return count;
	}
	
	
	//1명 데이터 가졍오기
	public PersonVo getPerson(int personId) {
		System.out.println("dao: getPerson()" + personId);
		
		PersonVo personVo = sqlSession.selectOne("phonebook.selectOne", personId);
		System.out.println(personVo.toString());
		
		return personVo;
	}
	
	//수정
	

	
}
