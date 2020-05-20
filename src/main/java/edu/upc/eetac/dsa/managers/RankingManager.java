package edu.upc.eetac.dsa.managers;

import edu.upc.eetac.dsa.orm.model.Player;
import edu.upc.eetac.dsa.orm.model.RankingDTO;

import java.util.List;

public interface RankingManager {
    public List<RankingDTO> getPlayers();
}
