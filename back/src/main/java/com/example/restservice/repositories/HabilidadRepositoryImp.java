package com.example.restservice.repositories;

import com.example.restservice.models.Habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class HabilidadRepositoryImp implements HabilidadRepository {
    @Autowired
    private Sql2o sql2o;

    public int biggestIdHabilidad() {
        try (Connection conn = sql2o.open()) {
            Habilidad temp = conn.createQuery(
                    "SELECT id_Habilidad FROM Habilidad WHERE id_Habilidad = (SELECT MAX(id_Habilidad) FROM Habilidad)")
                    .executeAndFetchFirst(Habilidad.class);
            return temp.getId_Habilidad();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public int countHabilidad() {
        int total = 0;
        try (Connection conn = sql2o.open()) {
            total = conn.createQuery("select count(*) from Habilidad").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Habilidad> getAllHabilidad() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("select * from Habilidad").executeAndFetch(Habilidad.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Habilidad getHabilidadById(Integer id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Habilidad WHERE id_Habilidad = :v_id_Habilidad")
                    .addParameter("v_id_Habilidad", id).executeAndFetchFirst(Habilidad.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Habilidad createHabilidad(Habilidad habilidad) {
        int id = this.biggestIdHabilidad() + 1;
        try (Connection conn = sql2o.open()) {
            conn.createQuery(
                    "INSERT INTO Habilidad(ID_Habilidad, Nombre_Habilidad, Tipo) values (:id_Habilidad, :nombreHabilidad, :tipo)",
                    true).addParameter("id_Habilidad", id)
                    .addParameter("nombreHabilidad", habilidad.getNombreHabilidad())
                    .addParameter("tipo", habilidad.getTipo()).executeUpdate().getKey();
            habilidad.setId_Habilidad(id);
            return habilidad;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateHabilidad(int id, Habilidad habilidad) {
        String updateSql = "update Habilidad set nombreHabilidad=:habilidadNombre,tipo=:habilidadTipo where id_Habilidad = :idParam";

        try (Connection con = sql2o.open()) {
            Habilidad habilidadAntigua = con.createQuery("SELECT * FROM Habilidad where id_Habilidad = :idP")
                    .addParameter("idP", id).executeAndFetchFirst(Habilidad.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if (habilidad.getNombreHabilidad() != null) {
                consulta.addParameter("habilidadNombre", habilidad.getNombreHabilidad());
            } else {
                consulta.addParameter("habilidadNombre", habilidadAntigua.getNombreHabilidad());
            }
            if (habilidad.getTipo() != null) {
                consulta.addParameter("habilidadTipo", habilidad.getTipo());
            } else {
                consulta.addParameter("habilidadTipo", habilidadAntigua.getTipo());
            }
            consulta.executeUpdate();
            System.out.println("La habilidad se actualizo correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteHabilidad(int id, Habilidad habilidad){
        String deleteSql = "update habilidad set borrado=:borrado where id_Habilidad = :idParam";

        try (Connection con = sql2o.open()) {
            Habilidad valorAntiguo = con.createQuery("SELECT * FROM Habilidad where id_Habilidad = :idP").addParameter("idP", id).executeAndFetchFirst(Habilidad.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(habilidad.getBorrado() != null){
                consulta.addParameter("borrado", habilidad.getBorrado());
            }else{
                consulta.addParameter("borrado", valorAntiguo.getBorrado());
            }
            consulta.executeUpdate();
            System.out.println("La Habilidad se elimino correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}