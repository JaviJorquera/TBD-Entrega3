package com.example.restservice.repositories;

import com.example.restservice.models.Emergencia;
//import org.postgis.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class EmergenciaRepositoryImp implements EmergenciaRepository {
    @Autowired
    private Sql2o sql2o;

    public int biggestIdEme() {
        try (Connection conn = sql2o.open()) {
            Emergencia temp = conn.createQuery(
                    "SELECT id_Emergencia FROM Emergencia WHERE id_Emergencia = (SELECT MAX(id_Emergencia) FROM Emergencia)")
                    .executeAndFetchFirst(Emergencia.class);
            return temp.getId_Emergencia();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public int countEmergencias() {
        int total = 0;
        try (Connection conn = sql2o.open()) {
            total = conn.createQuery("select count(*) from Emergencia").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Emergencia> getAllEmergencias() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("select * from Emergencia").executeAndFetch(Emergencia.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Emergencia getEmergenciaById(Integer id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Emergencia WHERE id_Emergencia = :v_id_Emergencia")
                    .addParameter("v_id_Emergencia", id).executeAndFetchFirst(Emergencia.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // se obtiene la emergencia por la latitud y longitud
    @Override
    public Emergencia getEmergenciaByPoint(Double latitud_Eme, Double longitud_Eme) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery(
                    "SELECT * FROM Emergencia WHERE latitud_Eme = :v_latitud_Eme AND longitud_Eme = :v_longitud_Eme")
                    .addParameter("v_latitud_Eme", latitud_Eme).addParameter("v_longitud_Eme", longitud_Eme)
                    .executeAndFetchFirst(Emergencia.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Emergencia createEmergencia(Emergencia emergencia) {
        // Se obtiene el ID nuevo.
        int id = this.biggestIdEme() + 1;
        try (Connection conn = sql2o.open()) {
            conn.createQuery(
                    "INSERT INTO Emergencia (ID_Emergencia, Nombre_Emergencia, Requerimientos, Latitud_Eme, Longitud_Eme, FK_Institucion) values (:id_Emergencia, :nombreEmergencia, :requerimientos, :latitud_Eme, :longitud_Eme, :id_Institucion)",
                    true).addParameter("id_Emergencia", id)
                    .addParameter("nombreEmergencia", emergencia.getNombreEmergencia())
                    .addParameter("requerimientos", emergencia.getRequerimientos())
                    //.addParameter("latitud_Eme", emergencia.getLatitud_Eme())
                    //.addParameter("longitud_Eme", emergencia.getLongitud_Eme())
                    .addParameter("id_Institucion", emergencia.getId_Institucion()).executeUpdate().getKey();
            emergencia.setId_Emergencia(id);
            return emergencia;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateEmergencia(int id, Emergencia emergencia) {
        String updateSql = "update emergencia set nombreEmergencia=:emergenciaNombre,requerimientos=:emergenciaRequerimientos,latitud_Eme=:emergenciaLatitud,longitud_Eme=:emergenciaLongitud,id_Institucion=:id_Institucion,id_Estado=:id_Estado where id_Emergencia = :idParam";

        try (Connection con = sql2o.open()) {
            Emergencia emergenciaAntigua = con.createQuery("SELECT * FROM Emergencia where id_Emergencia = :idP")
                    .addParameter("idP", id).executeAndFetchFirst(Emergencia.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if (emergencia.getNombreEmergencia() != null) {
                consulta.addParameter("emergenciaNombre", emergencia.getNombreEmergencia());
            } else {
                consulta.addParameter("emergenciaNombre", emergenciaAntigua.getNombreEmergencia());
            }
            if (emergencia.getRequerimientos() != null) {
                consulta.addParameter("emergenciaRequerimientos", emergencia.getRequerimientos());
            } else {
                consulta.addParameter("emergenciaRequerimientos", emergenciaAntigua.getRequerimientos());
            }
            if (emergencia.getLatitud_Eme() != null) {
                consulta.addParameter("emergenciaLatitud", emergencia.getLatitud_Eme());
            } else {
                consulta.addParameter("emergenciaLatitud", emergenciaAntigua.getLatitud_Eme());
            }
            if (emergencia.getLongitud_Eme() != null) {
                consulta.addParameter("emergenciaLongitud", emergencia.getLongitud_Eme());
            } else {
                consulta.addParameter("emergenciaLongitud", emergenciaAntigua.getLongitud_Eme());
            }
            if (emergencia.getId_Institucion() != null) {
                consulta.addParameter("id_Institucion", emergencia.getId_Institucion());
            } else {
                consulta.addParameter("id_Institucion", emergenciaAntigua.getId_Institucion());
            }
            if (emergencia.getId_Estado() != null) {
                consulta.addParameter("id_Estado", emergencia.getId_Estado());
            } else {
                consulta.addParameter("id_Estado", emergenciaAntigua.getId_Estado());
            }
            consulta.executeUpdate();
            System.out.println("La emergencia se actualizo correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteEmergencia(int id, Emergencia emergencia) {
        String deleteSql = "update emergencia set borrado=:borrado where id_Emergencia = :idParam";

        try (Connection con = sql2o.open()) {
            Emergencia valorAntiguo = con.createQuery("SELECT * FROM Emergencia where id_Emergencia = :idP")
                    .addParameter("idP", id).executeAndFetchFirst(Emergencia.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if (emergencia.getBorrado() != null) {
                consulta.addParameter("borrado", emergencia.getBorrado());
            } else {
                consulta.addParameter("borrado", valorAntiguo.getBorrado());
            }
            consulta.executeUpdate();
            System.out.println("La Emergencia se elimino correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}