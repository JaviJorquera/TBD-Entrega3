package com.example.restservice.repositories;

import com.example.restservice.models.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class EstadoRepositoryImp implements EstadoRepository {
    @Autowired
    private Sql2o sql2o;

    public int biggestIdEstado() {
        try (Connection conn = sql2o.open()) {
            Estado temp = conn
                .createQuery("SELECT id_Estado FROM Estado WHERE id_Estado = (SELECT MAX(id_Estado) FROM Estado)")
                .executeAndFetchFirst(Estado.class);
            return temp.getId_Estado();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public int countEstados() {
        int total = 0;
        try (Connection conn = sql2o.open()) {
            total = conn.createQuery("select count(*) from Estado").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Estado> getAllEstados() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("select * from Estado").executeAndFetch(Estado.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Estado getEstadoById(Integer id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Estado WHERE id_Estado = :v_id_Estado")
                    .addParameter("v_id_Estado", id).executeAndFetchFirst(Estado.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Estado createEstado(Estado estado) {
        // Se obtiene el ID nuevo.
        int id = this.biggestIdEstado() + 1;
        try (Connection conn = sql2o.open()) {
            conn.createQuery("INSERT INTO Estado(ID_Estado, Nombre_Estado) values (:id_Estado, :nombreEstado)", true)
                    .addParameter("id_Estado", id).addParameter("nombreEstado", estado.getNombreEstado())
                    .executeUpdate().getKey();
            estado.setId_Estado(id);
            return estado;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateEstado(int id, Estado estado) {
        String updateSql = "update estado set nombreEstado=:nombreEstado where id_Estado=:id";

        try (Connection con = sql2o.open()) {
            Estado estadoAntiguo = con.createQuery("SELECT * FROM estado where id_Estado = :id").addParameter("id", id).executeAndFetchFirst(Estado.class);
            Query consulta = con.createQuery(updateSql);
            
            consulta.addParameter("id", id);

            System.out.println(estado.getNombreEstado());
            
            if (estado.getNombreEstado() != null) {
                consulta.addParameter("nombreEstado", estado.getNombreEstado());
            } else {
                consulta.addParameter("nombreEstado", estadoAntiguo.getNombreEstado());
            }
            consulta.executeUpdate();
            System.out.println("El estado se actualizo correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteEstado(int id, Estado estado){
        String deleteSql = "update estado set borrado=:borrado where id_Estado = :idParam";

        try (Connection con = sql2o.open()) {
            Estado valorAntiguo = con.createQuery("SELECT * FROM Estado where id_Estado = :idP").addParameter("idP", id).executeAndFetchFirst(Estado.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(estado.getBorrado() != null){
                consulta.addParameter("borrado", estado.getBorrado());
            }else{
                consulta.addParameter("borrado", valorAntiguo.getBorrado());
            }
            consulta.executeUpdate();
            System.out.println("El Estado se elimino correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}