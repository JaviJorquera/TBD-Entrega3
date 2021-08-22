package com.example.restservice.repositories;

import com.example.restservice.models.Voluntario;
//import org.postgis.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class VoluntarioRepositoryImp implements VoluntarioRepository {
    @Autowired
    private Sql2o sql2o;

    public int biggestIdVoluntario() {
        try (Connection conn = sql2o.open()) {
            Voluntario temp = conn.createQuery(
                    "SELECT id_Voluntario FROM Voluntario WHERE id_Voluntario = (SELECT MAX(id_Voluntario) FROM Voluntario)")
                    .executeAndFetchFirst(Voluntario.class);
            return temp.getId_Voluntario();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public int countVoluntario() {
        int total = 0;
        try (Connection conn = sql2o.open()) {
            total = conn.createQuery("SELECT count(*) FROM Voluntario").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Voluntario> getAllVoluntario() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery(
                    "select v.id_Voluntario, v.nombreVoluntario, v.flg_participa , v.borrado , v.id_Tarea , st_x(st_astext( v.ubicacion)) AS longitud, st_y(st_astext(v.ubicacion)) AS latitud from Voluntario as v")
                    .executeAndFetch(Voluntario.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Voluntario getVoluntarioById(Integer id_Voluntario) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery(
                    "SELECT v.id_Voluntario, v.nombreVoluntario, v.flg_participa , v.borrado , v.id_Tarea , st_x(st_astext( v.ubicacion)) AS longitud, st_y(st_astext(v.ubicacion)) AS latitud FROM Voluntario as v WHERE id_Voluntario = :v_id_Voluntario")
                    .addParameter("v_id_Voluntario", id_Voluntario).executeAndFetchFirst(Voluntario.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Voluntario> getVoluntarioById_Tarea(Integer id_Tarea) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery(
                    "SELECT v.id_Voluntario, v.nombreVoluntario, v.flg_participa , v.borrado , v.id_Tarea , st_x(st_astext( v.ubicacion)) AS longitud, st_y(st_astext(v.ubicacion)) AS latitud FROM Voluntario as v WHERE id_Tarea = :v_id_Tarea")
                    .addParameter("v_id_Tarea", id_Tarea).executeAndFetch(Voluntario.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Voluntario> getVoluntariosByID_Emergencia(Integer id_Emergencia) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery(
                    "SELECT v.id_Voluntario, v.nombreVoluntario, v.flg_participa , v.borrado , v.id_Tarea , st_x(st_astext( v.ubicacion)) AS longitud, st_y(st_astext(v.ubicacion)) AS latitud FROM Voluntario as v, Tarea as t, Emergencia as e WHERE v.ID_Tarea = t.ID_Tarea AND t.ID_Emergencia = e.ID_Emergencia AND e.ID_Emergencia = :id_Emergencia")
                    .addParameter("id_Emergencia", id_Emergencia).executeAndFetch(Voluntario.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Voluntario createVoluntario(Voluntario voluntario) {
        int id = this.biggestIdVoluntario() + 1;
        try (Connection conn = sql2o.open()) {
            conn.createQuery(
                    "INSERT INTO Voluntario(ID_Voluntario, Nombre_Voluntario, Latitud_Vol, Longitud_Vol Disponibilidad) values (:id_Voluntario, :nombreVoluntario, :latitud_Vol, :longitud_Vol, :disponibilidad)",
                    true).addParameter("id_Voluntario", id)
                    .addParameter("nombreVoluntario", voluntario.getNombreVoluntario())
                    .addParameter("latitud", voluntario.getLatitud()).addParameter("longitud", voluntario.getLongitud())
                    .addParameter("disponibilidad", voluntario.getFlg_participa()).executeUpdate().getKey();
            voluntario.setId_Voluntario(id);
            return voluntario;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateVoluntario(int id, Voluntario voluntario) {
        String updateSql = "update voluntario set nombreVoluntario=:nombreVoluntario, flg_participa=:flg_participa , id_Tarea=:id_Tarea WHERE id_Voluntario = :idParam";

        try (Connection con = sql2o.open()) {
            Voluntario valorAntiguo = con.createQuery("SELECT * FROM voluntario WHERE id_Voluntario = :idP")
                    .addParameter("idP", id).executeAndFetchFirst(Voluntario.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if (voluntario.getNombreVoluntario() != null) {
                consulta.addParameter("nombreVoluntario", voluntario.getNombreVoluntario());
            } else {
                consulta.addParameter("nombreVoluntario", valorAntiguo.getNombreVoluntario());
            }
            if (voluntario.getFlg_participa() != null) {
                consulta.addParameter("flg_participa", voluntario.getFlg_participa());
            } else {
                consulta.addParameter("flg_participa", valorAntiguo.getFlg_participa());
            }
            if (voluntario.getId_Tarea() != null) {
                consulta.addParameter("id_Tarea", voluntario.getId_Tarea());
            } else {
                consulta.addParameter("id_Tarea", valorAntiguo.getId_Tarea());
            }
            consulta.executeUpdate();
            System.out.println("El voluntario se actualizo correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteVoluntario(int id, Voluntario voluntario) {
        String deleteSql = "update voluntario set borrado=:borrado where id_Voluntario=:idParam";

        try (Connection con = sql2o.open()) {
            Voluntario valorAntiguo = con.createQuery("SELECT * FROM Voluntario where id_Voluntario=:idP")
                    .addParameter("idP", id).executeAndFetchFirst(Voluntario.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if (voluntario.getBorrado() != null) {
                consulta.addParameter("borrado", voluntario.getBorrado());
            } else {
                consulta.addParameter("borrado", valorAntiguo.getBorrado());
            }
            consulta.executeUpdate();
            System.out.println("El Voluntario se elimino correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}