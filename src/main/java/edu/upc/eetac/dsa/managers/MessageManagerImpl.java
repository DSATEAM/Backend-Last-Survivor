package edu.upc.eetac.dsa.managers;
import edu.upc.eetac.dsa.orm.dao.MessageDAOImpl;

import edu.upc.eetac.dsa.orm.model.Forum;

import edu.upc.eetac.dsa.orm.model.Message;

import org.apache.log4j.Logger;

import java.util.List;

public class MessageManagerImpl implements MessageManager {
    private static MessageManager instance;
    private static final ForumManager forumManager=ForumManagerImpl.getInstance();
    private static final MessageDAOImpl messageDAO= new MessageDAOImpl();
    private static final Logger log = Logger.getLogger(MessageManagerImpl.class);
    public static MessageManager getInstance() {
        if (instance == null) {
            instance = new MessageManagerImpl();
        }
        return instance;
    }
    @Override
    public void addMessage(Message message) {

        Forum forum= forumManager.getForum(message.getParentId());
        List<Message> messagesList= forum.getListMessages();
        String id = messageDAO.addMessage(message);
        message.setId(id);
        messagesList.add(message);
        forum.setListMessages(messagesList);

    }

    @Override
    public List<Message> getMessages(String parentId) {
        return messageDAO.getListMessages(parentId);
    }
}
