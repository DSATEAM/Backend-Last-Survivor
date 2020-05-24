package edu.upc.eetac.dsa.managers;

import org.apache.log4j.Logger;

public class StoreManagerImpl implements StoreManager {
    private static StoreManager instance;
    private static final Logger log = Logger.getLogger(StoreManagerImpl.class);
    public static StoreManager getInstance() {
        if (instance == null) {
            instance = new StoreManagerImpl();
        }
        return instance;
    }

}
