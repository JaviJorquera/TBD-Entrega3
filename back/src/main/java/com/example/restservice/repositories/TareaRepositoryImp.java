package com.example.restservice.repositories;

import com.example.restservice.models.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class TareaRepositoryImp implements TareaRepository {
    @Autowired
    private Sql2o sql2o;

    public int biggestIdTarea() {
        try (Connection conn = sql2o.open()) {
            Tarea temp = conn
                    .createQuery("SELECT id_Tarea FROM Tarea WHERE id_Tarea = (SELECT MAX(id_Tarea) FROM Tarea)")
                    .executeAndFetchFirst(Tarea.class);
            return temp.getId_Tarea();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public int countTarea() {
        int total = 0;
        try (Connection conn = sql2o.open()) {
            total = conn.createQuery("SELECT count(*) FROM Tarea").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Tarea> getAllTarea() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Tarea").executeAndFetch(Tarea.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Tarea getTareaById(Integer id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Tarea WHERE id_Tarea = :v_id_Tarea").addParameter("v_id_Tarea", id)
                    .executeAndFetchFirst(Tarea.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Tarea> getTareaById_Emergencia(Integer id_Emergencia) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Tarea WHERE id_Emergencia = :v_id_Emergencia ORDER BY Id_Estado")
                    .addParameter("v_id_Emergencia", id_Emergencia).executeAndFetch(Tarea.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Tarea createTarea(Tarea tarea) {
        // Se obtiene el ID nuevo.
        int id = this.biggestIdTarea() + 1;
        try (Connection conn = sql2o.open()) {
            conn.createQuery(
                    "INSERT INTO Tarea(ID_Tarea, nombreTarea, Detalle, ID_Estado, ID_Emergencia) values (:id_Tarea, :nombreTarea, :detalle, :id_Estado, :id_Emergencia)",
                    true).addParameter("id_Tarea", id).addParameter("nombreTarea", tarea.getNombreTarea())
                    .addParameter("detalle", tarea.getDetalle()).addParameter("id_Estado", tarea.getId_Estado())
                    .addParameter("id_Emergencia", tarea.getId_Emergencia()).executeUpdate().getKey();
            tarea.setId_Tarea(id);
            return tarea;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateTarea(int id, Tarea tarea) {
        String updateSql = "update tarea set nombreTarea=:nombreTarea,detalle=:detalle,vol_requeridos=:vol_requeridos,id_Estado=:id_Estado,id_Emergencia=:id_Emergencia where id_Tarea = :id";

        try (Connection con = sql2o.open()) {
            Tarea tareaAntigua = con.createQuery("SELECT * FROM tarea where id_Tarea = :id").addParameter("id", id)
                    .executeAndFetchFirst(Tarea.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("id", id);
            if (tarea.getNombreTarea() != null) {
                consulta.addParameter("nombreTarea", tarea.getNombreTarea());
            } else {
                consulta.addParameter("nombreTarea", tareaAntigua.getNombreTarea());
            }
            if (tarea.getDetalle() != null) {
                consulta.addParameter("detalle", tarea.getDetalle());
            } else {
                consulta.addParameter("detalle", tareaAntigua.getDetalle());
            }
            if (tarea.getId_Estado() != null) {
                consulta.addParameter("id_Estado", tarea.getId_Estado());
            } else {
                consulta.addParameter("id_Estado", tareaAntigua.getId_Estado());
            }
            if (tarea.getId_Emergencia() != null) {
                consulta.addParameter("id_Emergencia", tarea.getId_Emergencia());
            } else {
                consulta.addParameter("id_Emergencia", tareaAntigua.getId_Emergencia());
            }
            if (tarea.getVol_requeridos() != null) {
                consulta.addParameter("vol_requeridos", tarea.getVol_requeridos());
            } else {
                consulta.addParameter("vol_requeridos", tareaAntigua.getVol_requeridos());
            }
            consulta.executeUpdate();
            System.out.println("La tarea se actualizo correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteTarea(int id, Tarea tarea) {
        String deleteSql = "update tarea set borrado=:borrado where id_Tarea_Habilidad=:idParam";

        try (Connection con = sql2o.open()) {
            Tarea valorAntiguo = con.createQuery("SELECT * FROM Tarea where id_Tarea_Habilidad=:idP")
                    .addParameter("idP", id).executeAndFetchFirst(Tarea.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if (tarea.getBorrado() != null) {
                consulta.addParameter("borrado", tarea.getBorrado());
            } else {
                consulta.addParameter("borrado", valorAntiguo.getBorrado());
            }
            consulta.executeUpdate();
            System.out.println("La Tarea se elimino correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}