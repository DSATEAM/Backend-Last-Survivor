package edu.upc.eetac.dsa.managers;

import edu.upc.eetac.dsa.orm.model.Item;
import edu.upc.eetac.dsa.orm.model.Message;

import java.util.List;

public interface MessageManager {
    public void addMessage(Message message);
    public List<Message> getMessages(String parentId);
}
