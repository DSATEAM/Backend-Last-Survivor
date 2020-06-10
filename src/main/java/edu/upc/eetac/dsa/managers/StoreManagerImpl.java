package edu.upc.eetac.dsa.managers;

import edu.upc.eetac.dsa.orm.dao.IPlayerDAO;
import edu.upc.eetac.dsa.orm.dao.ItemDAOImpl;
import edu.upc.eetac.dsa.orm.dao.PlayerDAOImpl;
import edu.upc.eetac.dsa.orm.model.Item;
import edu.upc.eetac.dsa.orm.model.Player;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

public class StoreManagerImpl implements StoreManager {
    private static StoreManager instance;
    private static final PlayerManager playerManager=PlayerManagerImpl.getInstance();
    private static final ItemDAOImpl itemDAO= new ItemDAOImpl();
    private static final PlayerDAOImpl playerDAO= new PlayerDAOImpl();
    private static final Logger log = Logger.getLogger(StoreManagerImpl.class);
    public static StoreManager getInstance() {
        if (instance == null) {
            instance = new StoreManagerImpl();
        }
        return instance;
    }


    @Override
    public int checkPurchase(Item item) {
        //Now we can Continue checking if the player contains any credit for the items
            //If items already exist than ignore and go to next
                //If all of the items same than return 0
        Player player= playerManager.getPlayer(item.getParentId());
        Item item1= itemDAO.getItem(item.getId());
        List<Item> itemList= player.getListItems();
        boolean found=false;
        int c=0;
        while((!found)&&(c<itemList.size())){
            if(itemList.get(c).getId()==item.getId()){
                found=true;
            }
            c++;
        }
        if(found){
            return 0;
        }
        else{
            int credit=player.getCredits()-item1.getCredit();
            if(credit>=0){
                player.setCredits(credit);
                playerDAO.updatePlayer(player);
                return 1;
            }
            else{
                return -1;
            }
        }
    }

    @Override
    public void addItem(Item item) {
        //Now we can add to the player items and ignore the ones which already exist
        Player player= playerManager.getPlayer(item.getParentId());
        Item item1 = itemDAO.getItem(item.getId());
        item1.setParentId(item.getParentId());
        List<Item> itemList= player.getListItems();
        itemList.add(item1);
        player.setListItems(itemList);
        itemDAO.addItem(item1);
    }

    @Override
    public List<Item> getItems() {
       return itemDAO.getListStandardItems();
    }

}
