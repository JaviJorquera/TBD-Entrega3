package com.example.restservice.repositories;

import com.example.restservice.models.Eme_Habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class Eme_HabilidadRepositoryImp implements Eme_HabilidadRepository {
    @Autowired
    private Sql2o sql2o;

    public int biggestIdEme_Habilidad() {
        try (Connection conn = sql2o.open()) {
            Eme_Habilidad temp = conn.createQuery(
                    "SELECT id_Eme_Habilidad FROM Eme_Habilidad WHERE id_Eme_Habilidad = (SELECT MAX(id_Eme_Habilidad) FROM id_Eme_Habilidad)")
                    .executeAndFetchFirst(Eme_Habilidad.class);
            return temp.getId_Emergencia();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public int countEme_Habilidad() {
        int total = 0;
        try (Connection conn = sql2o.open()) {
            total = conn.createQuery("select count(*) from Eme_Habilidad").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Eme_Habilidad> getAllEme_Habilidad() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("select * from Eme_Habilidad").executeAndFetch(Eme_Habilidad.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Eme_Habilidad getEme_HabilidadById(Integer id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Eme_Habilidad WHERE id_Eme_Habilidad = :v_id_Eme_Habilidad")
                    .addParameter("v_id_Eme_Habilidad", id).executeAndFetchFirst(Eme_Habilidad.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Eme_Habilidad createEme_Habilidad( Eme_Habilidad eme_Habilidad) {
        int id = this.biggestIdEme_Habilidad() + 1;
        try (Connection conn = sql2o.open()) {
            conn.createQuery(
                    "INSERT INTO Eme_Habilidad(ID_Eme_Habilidad, FK_Emergencia, FK_Habilidad) values (:id_Eme_Habilidad, :id_Habilidad, :id_Emergencia)",
                    true).addParameter("id", id).addParameter("id_Eme_Habilidad", eme_Habilidad.getId_Eme_Habilidad())
                    .addParameter("id_Habilidad", eme_Habilidad.getId_Emergencia())
                    .addParameter("id_Emergencia", eme_Habilidad.getid_Habilidad()).executeUpdate().getKey();
            eme_Habilidad.setId_Eme_Habilidad(id);
            return eme_Habilidad;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateEme_Habilidad(int id, Eme_Habilidad eme_Habilidad) {
        
        String updateSql = "update Eme_Habilidad set id_Habilidad=:id_Habilidad,id_Emergencia=:id_Emergencia where id_Eme_Habilidad = :idParam";

        try (Connection con = sql2o.open()) {
            Eme_Habilidad eme_HabilidadAntigua = con
                    .createQuery("SELECT * FROM Eme_Habilidad where id_Emergencia = :idP").addParameter("idP", id)
                    .executeAndFetchFirst(Eme_Habilidad.class);

            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if (eme_Habilidad.getid_Habilidad() != null) {
                consulta.addParameter("id_Habilidad", eme_Habilidad.getId_Emergencia());
            } else {
                consulta.addParameter("id_Habilidad", eme_HabilidadAntigua.getId_Emergencia());
            }
            if (eme_Habilidad.getId_Emergencia() != null) {
                consulta.addParameter("id_Emergencia", eme_Habilidad.getId_Emergencia());
            } else {
                consulta.addParameter("id_Emergencia", eme_HabilidadAntigua.getId_Emergencia());
            }
            consulta.executeUpdate();
            System.out.println("La eme_Habilidad se actualizo correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteEme_Habilidad(int id, Eme_Habilidad eme_habilidad){
        String deleteSql = "update eme_habilidad set borrado=:borrado where id_Eme_Habilidad=:idParam";

        try (Connection con = sql2o.open()) {
            Eme_Habilidad valorAntiguo = con.createQuery("SELECT * FROM Eme_Habilidad where id_Eme_Habilidad=:idP").addParameter("idP", id).executeAndFetchFirst(Eme_Habilidad.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(eme_habilidad.getBorrado() != null){
                consulta.addParameter("borrado", eme_habilidad.getBorrado());
            }else{
                consulta.addParameter("borrado", valorAntiguo.getBorrado());
            }
            consulta.executeUpdate();
            System.out.println("La Eme_Habilidad se elimino correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}