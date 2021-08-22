package com.example.restservice.repositories;

import com.example.restservice.models.Ranking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class RankingRepositoryImp implements RankingRepository {
    @Autowired
    private Sql2o sql2o;

    public int biggestIdRanking() {
        try (Connection conn = sql2o.open()) {
            Ranking temp = conn
                    .createQuery(
                            "SELECT id_Ranking FROM Ranking WHERE id_Ranking = (SELECT MAX(id_Ranking) FROM Ranking)")
                    .executeAndFetchFirst(Ranking.class);
            return temp.getId_Ranking();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public int countRanking() {
        int total = 0;
        try (Connection conn = sql2o.open()) {
            total = conn.createQuery("select count(*) from Ranking").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Ranking> getAllRanking() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("select * from Ranking").executeAndFetch(Ranking.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Ranking getRankingById(Integer id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Ranking WHERE id_Ranking = :v_id_Ranking")
                    .addParameter("v_id_Ranking", id).executeAndFetchFirst(Ranking.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Ranking createRanking(Ranking ranking) {
        int id = this.biggestIdRanking() + 1;
        try (Connection conn = sql2o.open()) {
            conn.createQuery(
                    "INSERT INTO Ranking(ID_Ranking, Nombre_Ranking, FK_Voluntario, FK_Tarea) values (:id_Ranking, :nombreRanking, :id_Voluntario, :id_Tarea)",
                    true).addParameter("id_Ranking", id).addParameter("nombreRanking", ranking.getNombreRanking())
                    .addParameter("id_Voluntario", ranking.getId_Voluntario()).executeUpdate().getKey();
            ranking.setId_Ranking(id);
            return ranking;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateRanking(int id, Ranking ranking) {
        String updateSql = "update Ranking set nombreRanking=:rankingNombre,id_Voluntario=:id_Voluntario,id_Tarea=:id_Tarea where id_Ranking = :idParam";

        try (Connection con = sql2o.open()) {
            Ranking rankingAntiguo = con.createQuery("SELECT * FROM Ranking where id_Ranking = :idP")
                    .addParameter("idP", id).executeAndFetchFirst(Ranking.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if (ranking.getId_Ranking() != null) {
                consulta.addParameter("rankingNombre", ranking.getId_Ranking());
            } else {
                consulta.addParameter("rankingNombre", rankingAntiguo.getId_Ranking());
            }
            if (ranking.getId_Voluntario() != null) {
                consulta.addParameter("id_Voluntario", ranking.getId_Voluntario());
            } else {
                consulta.addParameter("id_Voluntario", rankingAntiguo.getId_Voluntario());
            }
            if (ranking.getId_Tarea() != null) {
                consulta.addParameter("id_Tarea", ranking.getId_Tarea());
            } else {
                consulta.addParameter("id_Tarea", rankingAntiguo.getId_Tarea());
            }
            consulta.executeUpdate();
            System.out.println("El ranking se actualizo correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteRanking(int id, Ranking ranking){
        String deleteSql = "update ranking set borrado=:borrado where id_Ranking=:idParam";

        try (Connection con = sql2o.open()) {
            Ranking valorAntiguo = con.createQuery("SELECT * FROM Ranking where id_Ranking=:idP").addParameter("idP", id).executeAndFetchFirst(Ranking.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(ranking.getBorrado() != null){
                consulta.addParameter("borrado", ranking.getBorrado());
            }else{
                consulta.addParameter("borrado", valorAntiguo.getBorrado());
            }
            consulta.executeUpdate();
            System.out.println("El Ranking se elimino correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}