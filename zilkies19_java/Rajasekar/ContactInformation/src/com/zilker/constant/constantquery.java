package com.zilker.constant;

public class constantquery {
	public static final String mail = "select * from email";
	public static final String insertcontact = "insert into contact (fname,lname)values(?,?)";
	public static final String cid = "select c_id from contact";
	public static final String insertemail = "insert into email (c_id,email) values(?,?)";
	public static final String insertoffice = "insert into office (c_id,eno,office) values(?,?,?)";
	public static final String inserthome = "insert into home (c_id,area,cc,home) values(?,?,?,?)";
	public static final String insertmobile = "insert into mobile (c_id,cc,mobile) values(?,?,?)";
	public static final String sortfname = "select * from contact ORDER BY fname";
	public static final String displaycontact = "select * from contact where c_id=(?)";
	public static final String displayemail = "select * from email where c_id=(?)";
	public static final String displayoffice = "select * from office where c_id=(?)";
	public static final String displayhome = "select * from home where c_id=(?)";
	public static final String displaymobile = "select * from mobile where c_id=(?)";
	public static final String sortlname = "select * from contact ORDER BY lname";
	public static final String contact = "select c_id,email from email where c_id IN (select c_id from contact where fname=? AND lname=?)";
	public static final String updatemobile = "update mobile SET cc=?, mobile=? where m_id=?";
	public static final String updateoffice = "update office SET eno=?, office=? where o_id=?";
	public static final String updatehome = "update home SET area=?, cc=?,home=? where h_id=?";
	public static final String contid = "select c_id from contact where c_id IN(select c_id from email where email=?)";
	public static final String updatefname = "update contact SET fname=? where c_id=?";
	public static final String updatelname = "update contact SET lname=? where c_id=?";
}
