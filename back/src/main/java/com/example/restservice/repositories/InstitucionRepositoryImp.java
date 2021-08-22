package com.example.restservice.repositories;

import com.example.restservice.models.Institucion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class InstitucionRepositoryImp implements InstitucionRepository {
    @Autowired
    private Sql2o sql2o;

    public int biggestIdInstitucion() {
        try (Connection conn = sql2o.open()) {
            Institucion temp = conn.createQuery(
                    "SELECT id_Institucion FROM Institucion WHERE id_Institucion = (SELECT MAX(id_Institucion) FROM Institucion)")
                    .executeAndFetchFirst(Institucion.class);
            return temp.getId_Institucion();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public int countInstitucion() {
        int total = 0;
        try (Connection conn = sql2o.open()) {
            total = conn.createQuery("select count(*) from Institucion").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Institucion> getAllInstitucion() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("select * from Institucion").executeAndFetch(Institucion.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Institucion getInstitucionById(Integer id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Institucion WHERE id_Institucion = :v_id_Institucion")
                    .addParameter("v_id_Institucion", id).executeAndFetchFirst(Institucion.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Institucion createInstitucion(Institucion institucion) {
        int id = this.biggestIdInstitucion() + 1;
        try (Connection conn = sql2o.open()) {
            conn.createQuery(
                    "INSERT INTO Institucion(ID_Institucion, Nombre_Institucion, Coordinador) values (:id_Institucion, :nombreInstitucion, :coordinador)",
                    true).addParameter("id_Institucion", id)
                    .addParameter("nombreInstitucion", institucion.getNombreInstitucion())
                    .addParameter("coordinador", institucion.getCoordinador()).executeUpdate().getKey();
            institucion.setId_Institucion(id);
            return institucion;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateInstitucion(int id, Institucion institucion) {
        String updateSql = "update Institucion set nombreInstitucion=:institucionNombre,coordinador=:institucionCoordinador where id_Institucion = :idParam";

        try (Connection con = sql2o.open()) {
            Institucion institucionAntigua = con.createQuery("SELECT * FROM Institucion where id_Institucion = :idP")
                    .addParameter("idP", id).executeAndFetchFirst(Institucion.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if (institucion.getNombreInstitucion() != null) {
                consulta.addParameter("institucionNombre", institucion.getId_Institucion());
            } else {
                consulta.addParameter("institucionNombre", institucionAntigua.getNombreInstitucion());
            }
            if (institucion.getCoordinador() != null) {
                consulta.addParameter("institucionCoordinador", institucion.getCoordinador());
            } else {
                consulta.addParameter("institucionCoordinador", institucionAntigua.getCoordinador());
            }
            consulta.executeUpdate();
            System.out.println("La institucion se actualizo correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteInstitucion(int id, Institucion institucion){
        String deleteSql = "update institucion set borrado=:borrado where id_Institucion=:idParam";

        try (Connection con = sql2o.open()) {
            Institucion valorAntiguo = con.createQuery("SELECT * FROM Institucion where id_Institucion=:idP").addParameter("idP", id).executeAndFetchFirst(Institucion.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(institucion.getBorrado() != null){
                consulta.addParameter("borrado", institucion.getBorrado());
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