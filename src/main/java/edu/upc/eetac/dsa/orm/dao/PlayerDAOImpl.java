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
    public void deletePlayer(String id){
        Player player = this.getPlayer(id);
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.delete(player);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }


    }
    public void updatePlayer(String ID, String username, String password, int gamesPlayed, int kills, int deaths, int experience, int wins, List<Item> listItems, List<Material> listMaterial) {
        Player pl = this.getPlayer(ID);
        pl.setUsername(username);
        pl.setPassword(password);
        pl.setGamesPlayed(gamesPlayed);
        pl.setKills(kills);
        pl.setDeaths(deaths);
        pl.setExperience(experience);
        pl.setWins(wins);
        pl.setListItems(listItems);
        pl.setListMaterials(listMaterial);
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.update(pl);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
    }
    public List<Player> getPlayers() {
        Session session = null;
        List<Player> playerList=null;
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
