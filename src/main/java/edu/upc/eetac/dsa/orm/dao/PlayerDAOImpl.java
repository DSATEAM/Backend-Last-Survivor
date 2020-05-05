package edu.upc.eetac.dsa.orm.dao;
import edu.upc.eetac.dsa.RandomUtils;
import edu.upc.eetac.dsa.orm.FactorySession;
import edu.upc.eetac.dsa.orm.Session;
import edu.upc.eetac.dsa.orm.model.Item;
import edu.upc.eetac.dsa.orm.model.Material;
import edu.upc.eetac.dsa.orm.model.Player;

import java.util.List;

public class PlayerDAOImpl implements IPlayerDAO {

    public String addPlayer(String username, String password, int gamesPlayed, int kills, int deaths, int experience, int wins) {
        Session session = null;
        Player pl = new Player(username, password, gamesPlayed, kills, deaths, experience, wins);
        String ID=null;
        try {
            session = FactorySession.openSession();
            ID=session.save(pl);
        }
        catch (Exception e) {
            // LOG
            return null;
        }
        finally {
            session.close();
        }
        return ID;
    }
    //TODO getID of the User given username and password
    public String getID(String username,String password){

        return null;
    }
    public Player getPlayer(String id){
        Session session = null;
        Player player = null;
        try {
            session = FactorySession.openSession();
            player = (Player)session.get(Player.class, id);
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
