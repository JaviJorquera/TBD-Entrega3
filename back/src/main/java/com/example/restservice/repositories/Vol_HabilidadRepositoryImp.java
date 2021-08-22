package com.example.restservice.repositories;

import com.example.restservice.models.Vol_Habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class Vol_HabilidadRepositoryImp implements Vol_HabilidadRepository {
    @Autowired
    private Sql2o sql2o;

    public int biggestIdVol_Habilidad() {
        try (Connection conn = sql2o.open()) {
            Vol_Habilidad temp = conn.createQuery(
                    "SELECT id_Vol_Habilidad FROM Vol_Habilidad WHERE id_Vol_Habilidad = (SELECT MAX(id_Vol_Habilidad) FROM Vol_Habilidad)")
                    .executeAndFetchFirst(Vol_Habilidad.class);
            return temp.getId_Vol_Habilidad();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public int countVol_Habilidad() {
        int total = 0;
        try (Connection conn = sql2o.open()) {
            total = conn.createQuery("select count(*) from Vol_Habilidad").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Vol_Habilidad> getAllVol_Habilidad() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("select * from Vol_Habilidad").executeAndFetch(Vol_Habilidad.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Vol_Habilidad getVol_HabilidadById(Integer id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Vol_Habilidad WHERE id_Vol_Habilidad = :v_id_Vol_Habilidad")
                    .addParameter("v_id_Vol_Habilidad", id).executeAndFetchFirst(Vol_Habilidad.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Vol_Habilidad createVol_Habilidad(Vol_Habilidad vol_Habilidad) {
        // Se obtiene el ID nuevo.
        int id = this.biggestIdVol_Habilidad() + 1;
        try (Connection conn = sql2o.open()) {
            conn.createQuery(
                    "INSERT INTO Vol_Habilidad(ID_Vol_Habilidad, FK_Voluntario, FK_Habilidad) values (:id_Vol_Habilidad, :id_Voluntario, :id_Habilidad)",
                    true).addParameter("id_Vol_Habilidad", id)
                    .addParameter("id_Voluntario", vol_Habilidad.getId_Voluntario())
                    .addParameter("id_Habilidad", vol_Habilidad.getId_Habilidad()).executeUpdate().getKey();
            vol_Habilidad.setId_Vol_Habilidad(id);
            return vol_Habilidad;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateVol_Habilidad(int id, Vol_Habilidad vol_Habilidad) {
        String updateSql = "update Vol_Habilidad set id_Voluntario=:id_Voluntario,id_Habilidad=:id_Habilidad where id_Vol_Habilidad = :idParam";

        try (Connection con = sql2o.open()) {
            Vol_Habilidad vol_HabilidadAntigua = con
                    .createQuery("SELECT * FROM Vol_Habilidad where id_Vol_Habilidad = :idP").addParameter("idP", id)
                    .executeAndFetchFirst(Vol_Habilidad.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if (vol_Habilidad.getId_Vol_Habilidad() != null) {
                consulta.addParameter("id_Voluntario", vol_Habilidad.getId_Vol_Habilidad());
            } else {
                consulta.addParameter("id_Voluntario", vol_HabilidadAntigua.getId_Vol_Habilidad());
            }
            if (vol_Habilidad.getId_Habilidad() != null) {
                consulta.addParameter("id_Habilidad", vol_Habilidad.getId_Habilidad());
            } else {
                consulta.addParameter("id_Habilidad", vol_HabilidadAntigua.getId_Habilidad());
            }
            consulta.executeUpdate();
            System.out.println("La vol_Habilidad se actualizo correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteVol_Habilidad(int id, Vol_Habilidad vol_Habilidad){
        String deleteSql = "update vol_Habilidad set borrado=:borrado where id_Vol_Habilidad=:idParam";

        try (Connection con = sql2o.open()) {
            Vol_Habilidad valorAntiguo = con.createQuery("SELECT * FROM Vol_Habilidad where id_Vol_Habilidad=:idP").addParameter("idP", id).executeAndFetchFirst(Vol_Habilidad.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(vol_Habilidad.getBorrado() != null){
                consulta.addParameter("borrado", vol_Habilidad.getBorrado());
            }else{
                consulta.addParameter("borrado", valorAntiguo.getBorrado());
            }
            consulta.executeUpdate();
            System.out.println("La Vol_Habilidad se elimino correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}