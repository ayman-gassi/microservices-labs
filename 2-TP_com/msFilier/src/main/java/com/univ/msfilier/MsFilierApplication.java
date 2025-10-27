package com.univ.msfilier;

import com.univ.msfilier.dto.FiliereReq;
import com.univ.msfilier.dto.FiliereResp;
import com.univ.msfilier.entities.Cycle;
import com.univ.msfilier.entities.Filiere;
import com.univ.msfilier.mappers.FiliereMapper;
import com.univ.msfilier.repositories.FiliereRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MsFilierApplication {

    @Bean
    WebClient webClient() {
        return WebClient.builder().build();
    }

    public static void main(String[] args) {
        SpringApplication.run(MsFilierApplication.class, args);
    }
//-------------
    @Bean
    CommandLineRunner commandLineRunner(FiliereRepo filiereRepo, FiliereMapper filiereMapper) {
        return args -> {
            if (filiereRepo.count() == 0) {
                List<FiliereReq> seeds = Arrays.asList(
                        FiliereReq.builder().titre("Informatique").cycle(Cycle.INGENIERIE).build(),
                        FiliereReq.builder().titre("Gestion").cycle(Cycle.LICENCE).build(),
                        FiliereReq.builder().titre("Data Science").cycle(Cycle.MASTER).build(),
                        FiliereReq.builder().titre("Recherche Avancée").cycle(Cycle.DOCTORAT).build()
                );
                filiereRepo.saveAll(seeds.stream().map(filiereMapper::toEntity).toList());
                Optional<Filiere> filiere = filiereRepo.findById(1L);
                filiere.ifPresent(value -> System.out.println("Seeded filière: " + filiereMapper.toResp(value)));
            }


        };
    }
}
