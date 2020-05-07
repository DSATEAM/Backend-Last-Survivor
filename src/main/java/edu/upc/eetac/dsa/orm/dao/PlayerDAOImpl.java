package edu.upc.eetac.dsa.orm.dao;
import edu.upc.eetac.dsa.RandomUtils;
import edu.upc.eetac.dsa.orm.FactorySession;
import edu.upc.eetac.dsa.orm.Session;
import edu.upc.eetac.dsa.orm.model.Item;
import edu.upc.eetac.dsa.orm.model.Material;
import edu.upc.eetac.dsa.orm.model.Player;

import java.util.LinkedList;
import java.util.List;

public class PlayerDAOImpl implements IPlayerDAO {

    public String addPlayer(String username, String password, int gamesPlayed, int kills, int deaths, int experience, int wins) {
        Session session = null;
        Player pl = new Player(username, password, gamesPlayed, kills, deaths, experience, wins);
        String id=null;
        try {
            session = FactorySession.openSession();
            id=session.save(pl);
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
            String query = "SELECT COUNT(player.username) FROM player WHERE username = ?"; List<String> paramsList = new LinkedList<>();
            paramsList.add(username);
            ids = (List) session.queryExecute(String.class, query,paramsList);
            if(ids.isEmpty()) return null;
            for(Object id : ids){
                return true;
            }
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
            String query = "SELECT ID FROM player WHERE username = ? AND password = ?"; List<String> paramsList = new LinkedList<>();
            paramsList.add(username);paramsList.add(password);
            ids = (List) session.queryExecute(String.class, query,paramsList);
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
        List<Material> listMaterials = null;
        try {
            session = FactorySession.openSession();
            player = (Player)session.get(Player.class, playerId);
            //Now that we have the player we must add the items and materials of the player
            listItems =(List) session.getList(Item.class, playerId);
            player.setListItems(listItems);
            //Materials of player
            listMaterials =(List) session.getList(Material.class, playerId);
            player.setListMaterials(listMaterials);
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
            session.delete(player);
            res = 1;
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
    public int updatePlayer(Player player) {
        int res;
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.update(player);
            res = 1;
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
            res = -1;
        }
        finally {
            session.close();
        }
        return res;
    }
    public List<Player> getPlayers() {
        Session session = null;
        List playerList=null;
        try {
            session = FactorySession.openSession();
            playerList = (List) session.findAll(Player.class);
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
