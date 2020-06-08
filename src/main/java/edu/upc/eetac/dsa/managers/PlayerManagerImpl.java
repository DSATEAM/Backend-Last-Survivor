package edu.upc.eetac.dsa.managers;

import edu.upc.eetac.dsa.orm.dao.PlayerDAOImpl;
import edu.upc.eetac.dsa.orm.model.Player;
import edu.upc.eetac.dsa.orm.model.RankingDTO;
import org.apache.log4j.Logger;

public class PlayerManagerImpl implements PlayerManager {
    private static PlayerManager instance;
    PlayerDAOImpl playerDAO = new PlayerDAOImpl();
    RankingManagerImpl rankingManager= new RankingManagerImpl();
    private static final Logger log = Logger.getLogger(PlayerManagerImpl.class);
    //Singleton implementation for the instance of the GameManager
    private PlayerManagerImpl(){
        //Singleton Private Constructor
    }

    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManagerImpl();
        }
        return instance;
    }

    @Override
    public String signUp(Player player) {
        if(playerDAO.existUsername(player.getUsername())) {
            return null;
        }else{
            player = checkPlayerAvatar(player);
            String playerID = playerDAO.addPlayer(player);
            return playerID;
        }
    }

    @Override
    public String signIn(Player player) {
        //If correct return id, else empty String(Not Null)
        String playerID = playerDAO.getId(player.getUsername(),player.getPassword());
        return playerID;
    }
    @Override
    public Player getPlayer(String playerId) {
        //Returns Player with Items and Materials list included!
        return playerDAO.getPlayer(playerId);
    }

    @Override
    public Player updatePlayer(Player player) {
        //Check avatar,id is null or empty and add basicAvatar than update
        if(checkPlayerId(player)){return null;}
        player = checkPlayerAvatar(player);
        return playerDAO.updatePlayer(player);
    }
    private boolean checkPlayerId(Player player){
        if(player.getId()== null ){
            if(player.getId().equals("")||player.getId().isEmpty())
            {return false;}
            return false;
        }
        return true;
    }
    private Player checkPlayerAvatar(Player player){
        if(player.getAvatar()== null ){
            if(player.getAvatar().equals("")||player.getAvatar().isEmpty())
            {player.setAvatar("basicAvatar");}
            player.setAvatar("basicAvatar");
        }
        return player;
    }
    @Override
    public int deletePlayer(String playerId) {
       return playerDAO.deletePlayer(playerId);
    }
}
