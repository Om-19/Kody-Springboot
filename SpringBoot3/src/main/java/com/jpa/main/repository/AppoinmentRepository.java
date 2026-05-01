package com.jpa.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jpa.main.entity.Appoinment;

public interface AppoinmentRepository extends JpaRepository<Appoinment, Long> {

    // Entity Grapg ex
    @EntityGraph(attributePaths = {
            "patient",
            "doctor",
            "doctor.departments"
    })
    List<Appoinment> findAll();

    /*
     * types
     * Load Graph
     * Fetch Graph
     */

    /*
     * Load graph
     * > Specified fields → eagerly fetched
     * > Others → follow default fetch type
     * | patient | eagerly fetched |
     * | doctor | follows default (LAZY likely) |
     * | department | LAZY
     */
    @EntityGraph(attributePaths = { "patient" }, type = EntityGraph.EntityGraphType.LOAD)
    List<Appoinment> findByReason(String reason);

    /*
     * 3) FETCH GRAPH Example
     * > Only specified attributes fetched
     * > ALL others treated as LAZY (even if EAGER)
     * ## 🎯 Use Case:
     * > “Get only appointment + doctor, NOTHING else”
     * 
     * | doctor | fetched |
     * | patient | NOT fetched (forced LAZY) |
     * | department | NOT fetched |
     * 
     */
    @EntityGraph(attributePaths = { "doctor" }, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT a FROM Appoinment a") // custom query needed to give custom name
    List<Appoinment> findWithLoadGraph();

}
