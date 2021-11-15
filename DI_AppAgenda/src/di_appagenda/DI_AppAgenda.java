/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di_appagenda;

import entidades.Provincia;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class DI_AppAgenda {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf=
                Persistence.createEntityManagerFactory("DI_AppAgendaPU");
        EntityManager em=emf.createEntityManager();
        
        Provincia provinciaSevilla = new Provincia();
        provinciaSevilla.setNombre("Sevilla");
        
        em.getTransaction().begin();
        //Añadir aquí las operaciones de modificacion de la base de datos
        em.persist(provinciaSevilla);
        em.getTransaction().commit();
       
       Query queryProvincias = em.createNamedQuery("Provincia.findAll");
       List<Provincia> listProvincias = queryProvincias.getResultList();
       
       for(int i=0;i<listProvincias.size();i++){
           Provincia provincia = listProvincias.get(i);
           System.out.println(provincia.getNombre());
       }
        
        em.close();
        emf.close();
        try{
        DriverManager.getConnection("jdbc:derby:BDAgenda;shutdown=true");
        } catch (SQLException ex){
        }
    }
}
