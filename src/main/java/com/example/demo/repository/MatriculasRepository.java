package com.example.demo.repository;

import com.example.demo.entity.Matricula;
import com.example.demo.dto.TotalPorEstadoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculasRepository extends JpaRepository<Matricula, Long> {

    @Query("SELECT m.estado AS estado, SUM(m.totalMatriculas) AS total " +
           "FROM Matricula m GROUP BY m.estado ORDER BY total DESC")
    List<TotalPorEstadoDTO> totalPorEstado();
}
