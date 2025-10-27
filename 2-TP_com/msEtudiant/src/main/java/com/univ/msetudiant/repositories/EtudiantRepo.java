package com.univ.msetudiant.repositories;

import com.univ.msetudiant.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtudiantRepo extends JpaRepository<Etudiant, Long> {

    List<Etudiant> findAllByFiliereId(Long idFiliere);

    Page<Etudiant> findAllByFiliereId(Long idFiliere, Pageable pageable);

    interface FiliereCount {
        Long getFiliereId();
        Long getCount();
    }

    @Query("select e.filiereId as filiereId, count(e) as count from Etudiant e group by e.filiereId")
    List<FiliereCount> countEtudiantsByFiliere();

}
