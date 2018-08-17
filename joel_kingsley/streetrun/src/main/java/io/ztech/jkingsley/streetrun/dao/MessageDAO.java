package io.ztech.jkingsley.streetrun.dao;

import java.util.ArrayList;

import io.ztech.jkingsley.streetrun.beans.Group;
import io.ztech.jkingsley.streetrun.beans.Message;
import io.ztech.jkingsley.streetrun.beans.User;

public interface MessageDAO {
	/*BigInteger message_id;
	String message;
	BigInteger owner_id;
	BigInteger receiver_id;
	Boolean deleted;*/
	
	public ArrayList<Message> getAllMessages();
	public ArrayList<Message> getBySenderAndReceiver(User sender,User receiver);
	public ArrayList<Message> getByGroup(Group group);
	
	public boolean insertGroupMessage(Message message, Group group);
	public boolean insertDirectMessage(Message message);
	
	public boolean deleteGroupMessage(Message message, Group group);
}
