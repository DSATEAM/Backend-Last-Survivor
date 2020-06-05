package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.model.Item;
import edu.upc.eetac.dsa.orm.model.Message;

import java.util.List;

public interface IMessageDAO {
    String addMessage(Message message);
    Message getMessage(String id);
    int deleteMessage(Message message);
    List<Message> getListMessages(String parentId);
}
