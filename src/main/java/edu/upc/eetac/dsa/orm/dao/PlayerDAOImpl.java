package edu.upc.eetac.dsa.orm.dao;
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

        String id;
        try {
            session = FactorySession.openSession();
            id=session.save(player);
        }
        catch (Exception e) {
            // LOG
            return null;
        }
        finally {
            session.close();
        }
        return id;
    }
    public Boolean existUsername(String username){
        Session session = null;
        int users; List ids;
        try {
            session = FactorySession.openSession();
            String query = "SELECT * FROM player WHERE username = ?"; List<String> paramsList = new LinkedList<>();
            paramsList.add(username);
            ids = session.queryExecute(String.class, query,paramsList);
            return !ids.isEmpty();
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return false;
    }
    //TODO getID of the User given username and password
    public String getId(String username,String password){
        Session session = null;
        String playerId = null; List ids;
        try {
            session = FactorySession.openSession();
            String query = "SELECT id FROM player WHERE username = ? AND password = ?"; List<String> paramsList = new LinkedList<>();
            paramsList.add(username);paramsList.add(password);
            ids = session.queryExecute(String.class, query,paramsList);
            if(ids.isEmpty()) return null;
            for(Object id : ids){
                playerId = (String) id;
            }
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return playerId;
    }
    public Player getPlayer(String playerId){
        Session session = null;
        Player player = null;
        List<Item> listItems = null;
        try {
            session = FactorySession.openSession();
            player = (Player)session.get(Player.class, playerId);
            //Now that we have the player we must add the items and materials of the player
            listItems =(List) session.getList(Item.class, playerId);
            player.setListItems(listItems);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
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
            res = -1;
        }
        finally {
            session.close();
        }
        return res;
    }
    public Player updatePlayer(Player player) {
        Session session = null;
        try {
            session = FactorySession.openSession();
          if(session.update(player)>0)
          {
              //Player was updated return player
              return player;
          }else{
              return null;
          }
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
            player = null;
        }
        finally {
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
        }
        finally {
            session.close();
        }
        return playerList;
    }

}
