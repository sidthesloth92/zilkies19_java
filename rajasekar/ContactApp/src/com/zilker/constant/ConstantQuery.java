package com.zilker.constant;

public class ConstantQuery {
	public static final String MAIL = "select * from email";
	public static final String insertContact = "insert into contact (fname,lname)values(?,?)";
	public static final String CONTACT_ID = "select c_id from contact";
	public static final String INSERT_EMAIL = "insert into email (c_id,email) values(?,?)";
	public static final String INSERT_OFFICE = "insert into office (c_id,eno,office) values(?,?,?)";
	public static final String INSERT_HOME = "insert into home (c_id,area,cc,home) values(?,?,?,?)";
	public static final String INSERT_MOBILE = "insert into mobile (c_id,cc,mobile) values(?,?,?)";
	public static final String SORT_FIRST_NAME = "select * from contact ORDER BY fname";
	public static final String DISPLAY_CONTACT = "select * from contact where c_id=(?)";
	public static final String DISPLAY_EMAIL = "select * from email where c_id=(?)";
	public static final String DISPLAY_OFFICE = "select * from office where c_id=(?)";
	public static final String DISPLAY_HOME = "select * from home where c_id=(?)";
	public static final String DISPLAY_MOBILE = "select * from mobile where c_id=(?)";
	public static final String SORT_LAST_NAME = "select * from contact ORDER BY lname";
	public static final String CONTACT = "select c_id,email from email where c_id IN (select c_id from contact where fname=? AND lname=?)";
	public static final String UPDATE_MOBILE = "update mobile SET cc=?, mobile=? where m_id=?";
	public static final String UPDATE_OFFICE = "update office SET eno=?, office=? where o_id=?";
	public static final String UPDATEHOME = "update home SET area=?, cc=?,home=? where h_id=?";
	public static final String CONTACTID = "select c_id from contact where c_id IN(select c_id from email where email=?)";
	public static final String UPDATE_FIRST_NAME = "update contact SET fname=? where c_id=?";
	public static final String UPDATE_LAST_NAME = "update contact SET lname=? where c_id=?";
}
