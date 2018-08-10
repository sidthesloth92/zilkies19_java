package io.ztech.contact.constants;

public class SqlConstantQueries {

	public static String SELECTPERSON="select person_id from person where firstname = ?";
	public static String UPDATECONTACT="UPDATE contact set number=? where person_id= ? and contact_id=?";
	public static String INSERTPERSON="insert into person(firstname,lastname) values(?,?)";
	public static String MAX="select max(person_id) as max from person";
	public static String INSERTOFFICE="insert into contact(person_id,contact_id,number) values(?,?,?)";
	public static String INSERTHOME="insert into contact(person_id,contact_id,number) values(?,?,?)";
	public static String INSERTMOBILE="insert into contact(person_id,contact_id,number) values(?,?,?)";
	public static String INSERTEMAIL="insert into email(person_id,email) values(?,?)";
	public static String SORTASC="SELECT * FROM person order by firstname";
	public static String SELCONTACTID="select * from contact where person_id=?";
	public static String SELEMAIL="select email from email where person_id=?";
	public static String SORTDESC="SELECT * FROM person order by firstname desc";
	public static String SELDETAILPERSON="SELECT * FROM person where firstname=?  order by firstname ";
	public static String DELETE="delete from person where person_id=?";
	public static String UPDATEEMAIL="update email set email=? where person_id=?";
}
