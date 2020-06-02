package ormtests;
import edu.upc.eetac.dsa.orm.dao.*;
import edu.upc.eetac.dsa.orm.model.Player;
//Junit 4.13
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.management.counter.perf.PerfLongArrayCounter;

import javax.print.event.PrintJobAttributeListener;

public class PlayerDAOImplTest{
    PlayerDAOImpl playerDAO ;
    Player player = null;
    @Before
    public void setUp() {
        player = null;
        playerDAO = new PlayerDAOImpl();
    }
    @Test
    public void addPlayerTest(){
        Player player = new Player("Marc", "pass", 5, 4, 2, 23);
        player.setAvatar("iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAIAAAADnC86AAAAA3NCSVQICAjb4U/gAAAO10lEQVRY\n" +
                "    hS1WaZCcZ3Hu7vf9vm/u2ZnZ2UPa1bHataTVaR22ZUvW+ixj8C0SqBwEiipCqDhJBcKP5EdIJaQS\n" +
                "    UiYJhGBEMGACCYZKYiNT2AbfdixjnbvSSqvVarWX9piZnfs73rc7PzZVXf2vf3Q//RzYt/VeIbKk\n" +
                "    BEAjIiAIgCZBQiBAICRSKIAEQKSYUECUUsCMIorRCgAiK0QSsoIGLSBKiCKAwqDYGgKyYJERAAUg\n" +
                "    wkhbyyKADETIoKxiFCRmJERkYUQSMQIIiMgsoAgAAEAARMCCILMCUswsICAAgAAIEiGSgLEREoFl\n" +
                "    BBQBASuMqFClioNIJMAIggLEoJFQAIxoIAAhYAQEWesCAsAMISMzCcRIvvi7d9carRuVGgEKW2Fk\n" +
                "    y0ZYRICFAMAKIjIzIAsoIhExKpnfIECIWgSBSBABDYogiCAiCwKigIgQIltAFEQGEARkBM1hlvi1\n" +
                "    0XlmAAEGYQFRiALIa/sLIAgLADADkVjDZOsaxBWwAgyo0AqgCJEAIloURI38/8MoIkBgEYGJWEAB\n" +
                "    R9aSmW8IGyuKWCwwEAJasMJEyMaikCAqxVZEBIWBNEhoSdCIFQSNAiKGORSOhH0AEuZIQrYsVtYK\n" +
                "    rFgWCyIiVpgNxD3n2MceA9TEQoJr0LNYNsyRECnUzGKiCMSCVkxojTXMrJK5LYQgIggCZBBRGAER\n" +
                "    AQCJAEAIUBCREAQBBcQSAoCN7ju6tb8nsXPH0M17dp384FQUiQgAACASAaJYCwBApAhFwLAVK4wk\n" +
                "    Ilqlcv2sGIEVIbCDohAUEQEAMwMJAFp0O9dtrNdrIoxA2k3eebD/4fuGtmzaxDYIA/PpT37sQ/fd\n" +
                "    m055vz4zKsJsAYQQAVGEmSGSNeYAI4KIWI5UIrcZRIFQMp0J/EDECkSKBMAiEqJSboacpB8EWsXE\n" +
                "    hsCKw5XdQ8k77rhdgX/s2Mez6Vgu36sU792748SLrzQaASEQAduQrQgyoSCuIYxGLBKJAAEbhBA4\n" +
                "    bFTLAAwAhDqyihlZwApH7boN2hK2jfFVPCcSfP0rf3T7LdsTLol1urp7L45fcRyVyiSB8dUXv8Ps\n" +
                "    G46MDQCFlAVgFmBhEDSEiIqNERsRAAiDAFpAIyLKY2HiQMQCRiQW0KKrvGSG2Dft1WKH+sG//ygy\n" +
                "    2Go3hwa3vPP2W/Pzy35kYq7qyLjnz11OJzzEtRcTI2sygwLEyGCsmECAgZRKdvSj1khIhA5ItqPD\n" +
                "    D42IZQGkGDMIIwMbQTERm1ZXIbmxJz23MF9aWY2n0m+/e8ZGdmz06sDWmyavTL333gc9PevPjl2y\n" +
                "    a/xERCAAK2LEWiEEJK3RmogsoRGxoi1rA1Qul9CLMaBSik1bQLSnlSICCyBAHrJfWa01W+H24X3f\n" +
                "    +Mb32W9351IH9m177633l5cqfsCR8QWJSIMYNhFzIJZZUEiLCFhj2iFYpbzsZqViJIAKmV2EEAwD\n" +
                "    AohFFUNC0S5Hxo0lmA0ovaknVSrXVyt+tVIZGBiIOT3LpWlE9eIvXk8l0xNTU4169crVGRELSGuH\n" +
                "    ZrAoKGyBLdCaGIJK5bYCgDCTEhAGIBBGclB7bJrCoFCJMGoHUAswt2qZXHZlZWVTf4/rFmNua3np\n" +
                "    hhKoNWrvvXvKNzI+NVdt+YQEwAIWmURYhJEASAugGGOtISuW2TB5xrqWNVsRUKhdGzUAINPRbS2A\n" +
                "    crSomHKTXjaMbBQEXcVcRzIxfenU7OzU4ObN6Uz2wuh4PJ0cHt6+tFJBNhyFHBlkYmFSCpUWRokY\n" +
                "    2SgSR6EGZlQOWN+KQRAkEDEmNIgxIDRiE8kOx9WClE97lYZx4sml5VUT+Rt6uw2F8UTcRn4ym3v0\n" +
                "    oY8YL3tubMKGoZBCRQyy5mY2YCJWwECAyCEggCJBx7IvIkiCKAAMQGAjFEFwona77dfq9XpttVxa\n" +
                "    DRziRlvZyOYS8fdOnlu8Ud68efNr737QtNDw/W8+fXx5ZZWVAwhiBdnhSIhcrQ0qYYJIMDAajEgU\n" +
                "    qFi6iOCwMCAIOAYIgNDLax1HBNAugfR3xjlooFKDG9at6ynML8wQQrGjo1jI/vTF1xZKjbdPno5p\n" +
                "    5+6Rw+OTiyasiokQIoIAFQNa3ypSGRu0CZCYUQkwYGHjrQJaQARjAsASQ+sDusr1CMNbdw/ef+e+\n" +
                "    Q4cO1iqV06Ojjhf/x+M/JhtqWyskU6Rp/66dz7386uP3HKlWq4FO+sZ59EP3dHYV5+aufemp42FE\n" +
                "    bNFRoQAQOOQ6NmwJGRtG2oAHIiq5UYS5XSYIRCuS0NPwuU8+9plPPZFOZoDM6NkP8jnn2edOKDBR\n" +
                "    2CCFbdNa19Fx+dIFh6CnpycCmpurrZSWLl+5mstmD+49+MxT/Z/9wt9G4IVBEyliEXS1CZgio5kI\n" +
                "    bcsyce2Sqc+TXbVRJJT67WMPPf13n3/y938rn89qV9rNyuT01OnRCTeeabcbjBJa2wxYk1eqN5Ry\n" +
                "    oiCsNvz5uXkT1menry3duN7bm3U1/cs//AVz0yJTPGPZgN8mgjXnI2DQ0gJggjaIVRj0ZcJPffwB\n" +
                "    rdlxCRFe+OmzE1fGbRRu7ssvzs84YNAasMw2KpXKqXRSE7R8PzBgbfQnf/DpLTdtcD0IwvbwzsHe\n" +
                "    fOov//gTccdxKZbIdDieI2IByKJVbqYoiEAorIig2OF96YufWb++eNuhA8BBu93aun17Kt3x+ptv\n" +
                "    fe9HL4WBbTaboTFKOQRAiIExEcNNW4amF5fjjj32+INhu25FCul0saugHT0ycvum3sLQlo1jY2PV\n" +
                "    SkVEjDUEouKZdQgCzCCiyf7eRx/s6c4dvftoFDROPP8/YxcvPvXU16euz546M8Hanbo+hwrR0aHv\n" +
                "    I5G1EnN0aAwhzixUEjHn1Ml3/uwLf5jJpHOdhVKpNDC46eQ7bxw6cnsqntjS35lNeeOXp5ABCVQ8\n" +
                "    3SWAAKSJnvzEQ5//088O79rSqCwuLc03mrUzZ8fffPvkjcXGhr7ek++PRWHkaIVaOaRMEGoia8UC\n" +
                "    FXJd80tLmqJ7Rg4GYXR9dioR04D08iu/OnjgYCzmxRw6/u2n7xs5EobB5PU5AFYq0cMCGs2uwf4v\n" +
                "    /9WT2XymUa//7MSJ7z373LmxqUTcYWO9eGpq+ho5XssPSDhVyEdRSMCBSCjCIPOLc6mYd3Dv0N69\n" +
                "    26Kw/eGHnjj1/ulqvfno44/Um7Wv/P0/b+jrdePefz3/MnC4sFyNjFWJVAEQNMGBXUNnR89vWl98\n" +
                "    /mcvPfvDFy9cnm3U20g8NNAXWVaOOz271PZDHfP8VlORNkGEqNYCs0t0c3//I0883FEo7tqzB0Dy\n" +
                "    nfl33nl35/DO+fnZW27dX29EV6+MHti35/zYdHcxP3djSTmpYiqmbtpYqLdMIeMeuu3g1NU5pV0N\n" +
                "    LEBjF8aDMDpzbqK/b71GTiJVfP+OB4/EXCrdWGYGpUjYOsgrtfLExNSRI4eOP338u9/7z8ce/kh/\n" +
                "    //pGrW5NGE8kX3v9jT27hhmx3Wy+8tpbAqBUspB0E2j9P//i5x544N7zo+dXKtXJiYuoJJ2Kp9Pp\n" +
                "    fK4jm83s3Lb5f399PvTbzba/NH1j5cYiIZCyxgT5TDIZ8zxPA8oPfviTQ7fccW16ev/+3alkslRa\n" +
                "    6u7q/vnPX/WDZq1amZu5ft/InWHonx+/qmLxQiam9u7oiyULlXL1/LnTnksXLk46nuc4eNfR28+e\n" +
                "    n6hUqvVae8/w1pPnrxzeUiz7TU/pxx+5/wtP/s7NOweyqURff29nITs3X+5bv67Z8r/2T19eXFyM\n" +
                "    aefN117/6teeWV0tjdx5y9BNQw642kv88tU3duzYrjDe6Sm+b+ROseHYpYmObKrZbLXbjf6N/cuL\n" +
                "    lYnJ6VJp1Ubh1PRyqVId3jpQX5z70MMjR44cPnpod7tZL5XLL/zklXg6tm3rcLVSdbVzz123Aat6\n" +
                "    vbK4sHB45MjeHYNxT+3Zva\n" +
                "2020-05-29 18:42:27.324 16043-16043/edu.upc.eetac.dsa.lastsurvivorfrontend W/IInputConnectionWrapper: finishComposingText on inactive InputConnection\n" +
                "2020-05-29 18:42:27.325 16043-16043/edu.upc.eetac.dsa.lastsurvivorfrontend W/IInputConnectionWrapper: reportFullscreenMode on inexistent InputConnection\n" +
                "2020-05-29 18:42:27.325 16043-16043/edu.upc.eetac.dsa.lastsurvivorfrontend W/IInputConnectionWrapper: finishComposingText on inactive InputConnection\n");
        String playerID = playerDAO.addPlayer(player);
        Assert.assertNotNull(playerID);
        playerDAO.deletePlayer(playerID);
    }
    @Test
    public void getPlayerTest(){
        Player player = new Player("Marc", "pass", 5, 4, 2, 23);
        player.setAvatar("sadfojhsafdlijusdalfiu");
       String playerId = playerDAO.addPlayer(player);
       Object obj = playerDAO.getId("Yo","pass");
       Assert.assertNotNull(playerDAO.getPlayer(playerId));
       playerDAO.deletePlayer(playerId);
    }
    @After
    public void tearDown() {
    }

}