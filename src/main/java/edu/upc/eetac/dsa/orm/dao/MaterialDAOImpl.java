package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.FactorySession;
import edu.upc.eetac.dsa.orm.Session;
import edu.upc.eetac.dsa.orm.model.Material;
import edu.upc.eetac.dsa.orm.model.Player;

import java.util.List;

public class MaterialDAOImpl implements IMaterialDAO{
    @Override
    public String addMaterial(String name, String description, int quantity) {
            Session session = null;
            Material mat = new Material(name, description, quantity);
            String IDen=null;
            try {
                session = FactorySession.openSession();
                IDen=session.save(mat);
            }
            catch (Exception e) {
                // LOG
                return null;
            }
            finally {
                session.close();
            }
            return IDen;
    }
    @Override
    public Material getMaterial(String ID) {
        Session session = null;
        Material material = null;
        try {
            session = FactorySession.openSession();
            material = (Material) session.get(Material.class, ID);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return material;
    }

    @Override
    public void updateMaterial(String materialID, String name, String description, int quantity) {
        Material material = this.getMaterial(materialID);
        material.setName(name);
        material.setDescription(description);
        material.setQuantity(quantity);
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.update(material);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
    }

    @Override
    public void deleteMaterial(String materialID) {
        Material material = this.getMaterial(materialID);
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.delete(material);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }

    }

    @Override
    public List<Material> getAllMaterials() {
        Session session = null;
        List<Material> materialList=null;
        try {
            session = FactorySession.openSession();
            materialList = session.findAll(Material.class);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return materialList;
    }


}
