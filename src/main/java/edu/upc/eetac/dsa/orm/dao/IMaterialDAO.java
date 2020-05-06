package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.model.Material;

import java.util.List;

public interface IMaterialDAO {
    String addMaterial(String name, String description, int quantity);
    Material getMaterial(String id);
    void updateMaterial(String materialID, String name, String description, int quantity);


    void deleteMaterial(String materialId);
    List<Material> getAllMaterials();
}
