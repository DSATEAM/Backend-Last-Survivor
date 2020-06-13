package edu.upc.eetac.dsa.orm.dao;
import edu.upc.eetac.dsa.RandomUtils;
import edu.upc.eetac.dsa.orm.FactorySession;
import edu.upc.eetac.dsa.orm.Session;
import edu.upc.eetac.dsa.orm.model.Item;
import edu.upc.eetac.dsa.orm.model.Player;
import edu.upc.eetac.dsa.orm.model.RankingDTO;

import java.util.LinkedList;
import java.util.List;

public class PlayerDAOImpl implements IPlayerDAO {

    public String addPlayer(Player player) {
        Session session = null;

        String id = null;
        try {
            session = FactorySession.openSession();
            id=session.save(player);
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return id;
    }
    public Boolean existUsername(String username){
        Session session = null;
        List ids = new LinkedList<>();
        try {
            session = FactorySession.openSession();
            String query = "SELECT * FROM player WHERE username = ?"; List<String> paramsList = new LinkedList<>();
            paramsList.add(username);
            ids = session.queryExecute(String.class, query,paramsList);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return !ids.isEmpty();
    }
    //TODO getID of the User given username and password
    public String getId(String username,String password){
        Session session = null;
        String playerId = null; List ids;
        try {
            session = FactorySession.openSession();
            String query = "SELECT id FROM player WHERE username = ? AND password = AES_ENCRYPT(?,'SALTED_CHARACTER_SECRET_KEY')"; List<String> paramsList = new LinkedList<>();
            paramsList.add(username);paramsList.add(password);paramsList.add(password);
            ids = session.queryExecute(String.class, query,paramsList);
            if(ids ==null) return null;
            if(ids.isEmpty()) return null;
            for(Object id : ids){
                playerId = (String) id;
            }
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
        }
        finally {
            if(session!=null)
                session.close();
        }
        return playerId;
    }
    public Player getPlayer(String playerId){
        Session session = null;
        Player player = null;

        List ids;
        try {
            session = FactorySession.openSession();
            player = (Player)session.get(Player.class, playerId);
            //Now that we have the player we must add the items and materials of the player
            String queryTransaction = "SELECT itemId FROM Transaction WHERE playerId = ?";
            List<String> paramsList = new LinkedList<>();
            List<Item> resultItemList = new LinkedList<>();
            paramsList.add(playerId);
            ids = session.queryExecute(String.class, queryTransaction,paramsList);
            paramsList.clear();
            String query = "SELECT * FROM Item WHERE id=?";
            Item item;
            List<Item> itemList = new LinkedList<>();
            if(!ids.isEmpty()){

                for (int i = 0;i<ids.size();i++){
                    paramsList.add((String) ids.get(i));
                    //Get the Item from Item table for each id found in transaction for the playerId
                    resultItemList = session.queryExecuteGetObject(Item.class,query,paramsList);
                    paramsList.clear();
                    if(resultItemList!=null) {
                        if(!resultItemList.isEmpty()){
                            itemList.add(resultItemList.get(0));
                        }
                    }
                }
                player.setListItems(itemList);
            }

        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
        }
        finally {
            if(session!=null)
                session.close();
        }
        return player;
    }
    public int deletePlayer(String id){
        Player player = this.getPlayer(id);
        int res;
        Session session = null;
        try {
            session = FactorySession.openSession();
           res =  session.delete(player);
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
            res = -1;
        }
        finally {
            if(session!=null)
                session.close();
        }
        return res;
    }
    public Player updatePlayer(Player player) {
        Session session = null;
        try {
            session = FactorySession.openSession();
          if(session.update(player)<=0) //Player Wasn't updated!
          {
              player =  null;
          }
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
        }
        finally {
            if(session!=null)
                session.close();
        }
        return player;
    }
    public List<Player> getPlayers() {
        Session session = null;
        List playerList=null;
        try {
            session = FactorySession.openSession();
            playerList = session.findAll(Player.class);
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
        }
        finally {
            if(session!=null)
                session.close();
        }
        return playerList;
    }
}
