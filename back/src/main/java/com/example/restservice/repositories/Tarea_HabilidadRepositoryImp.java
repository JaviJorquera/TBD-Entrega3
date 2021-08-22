package com.example.restservice.repositories;

import com.example.restservice.models.Tarea_Habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class Tarea_HabilidadRepositoryImp implements Tarea_HabilidadRepository {
    @Autowired
    private Sql2o sql2o;

    public int biggestIdTarea_Habilidad() {
        try (Connection conn = sql2o.open()) {
            Tarea_Habilidad temp = conn.createQuery(
                    "SELECT id_Tarea_Habilidad FROM Tarea_Habilidad WHERE id_Tarea_Habilidad = (SELECT MAX(id_Tarea_Habilidad) FROM Tarea_Habilidad)")
                    .executeAndFetchFirst(Tarea_Habilidad.class);
            return temp.getId_Tarea_Habilidad();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public int countTarea_Habilidad() {
        int total = 0;
        try (Connection conn = sql2o.open()) {
            total = conn.createQuery("select count(*) from Tarea_Habilidad").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Tarea_Habilidad> getAllTarea_Habilidad() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("select * from Tarea_Habilidad").executeAndFetch(Tarea_Habilidad.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Tarea_Habilidad getTarea_HabilidadById(Integer id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Tarea_Habilidad WHERE id_Tarea_Habilidad = :v_id_Tarea_Habilidad")
                    .addParameter("v_id_Tarea_Habilidad", id).executeAndFetchFirst(Tarea_Habilidad.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Tarea_Habilidad createTarea_Habilidad(Tarea_Habilidad tarea_Habilidad) {
        // Se obtiene el ID nuevo.
        int id = this.biggestIdTarea_Habilidad() + 1;
        try (Connection conn = sql2o.open()) {
            conn.createQuery(
                    "INSERT INTO Tarea_Habilidad(ID_Tarea_Habilidad, ID_Tarea, ID_Habilidad) values (:id_Tarea_Habilidad, :id_Habilidad, :id_Tarea)",
                    true).addParameter("id_Tarea_Habilidad", id)
                    .addParameter("id_Habilidad", tarea_Habilidad.getId_Habilidad())
                    .addParameter("id_Tarea", tarea_Habilidad.getId_Tarea()).executeUpdate().getKey();
            tarea_Habilidad.setId_Tarea_Habilidad(id);
            return tarea_Habilidad;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateTarea_Habilidad(int id, Tarea_Habilidad tarea_Habilidad) {
        String updateSql = "update Tarea_Habilidad set id_Habilidad=:id_Habilidad,id_Tarea=:id_Tarea where id_Tarea_Habilidad = :idParam";

        try (Connection con = sql2o.open()) {
            Tarea_Habilidad tarea_HabilidadAntigua = con
                    .createQuery("SELECT * FROM Tarea_Habilidad where id_Tarea_Habilidad = :idP")
                    .addParameter("idP", id).executeAndFetchFirst(Tarea_Habilidad.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if (tarea_Habilidad.getId_Habilidad() != null) {
                consulta.addParameter("id_Habilidad", tarea_Habilidad.getId_Habilidad());
            } else {
                consulta.addParameter("id_Habilidad", tarea_HabilidadAntigua.getId_Habilidad());
            }
            if (tarea_Habilidad.getId_Tarea() != null) {
                consulta.addParameter("id_Tarea", tarea_Habilidad.getId_Tarea());
            } else {
                consulta.addParameter("id_Tarea", tarea_HabilidadAntigua.getId_Tarea());
            }
            consulta.executeUpdate();
            System.out.println("La tarea_Habilidad se actualizo correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteTarea_Habilidad(int id, Tarea_Habilidad tarea_Habilidad){
        String deleteSql = "update tarea_Habilidad set borrado=:borrado where id_Tarea_Habilidad=:idParam";

        try (Connection con = sql2o.open()) {
            Tarea_Habilidad valorAntiguo = con.createQuery("SELECT * FROM Tarea_Habilidad where id_Tarea_Habilidad=:idP").addParameter("idP", id).executeAndFetchFirst(Tarea_Habilidad.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(tarea_Habilidad.getBorrado() != null){
                consulta.addParameter("borrado", tarea_Habilidad.getBorrado());
            }else{
                consulta.addParameter("borrado", valorAntiguo.getBorrado());
            }
            consulta.executeUpdate();
            System.out.println("La Tarea_Habilidad se elimino correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}