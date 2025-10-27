 package com.univ.msetudiant;

import com.univ.msetudiant.dto.EtudiantReqDto;
import com.univ.msetudiant.dto.EtudiantResDto;
import com.univ.msetudiant.mappers.EtudiantMapper;
import com.univ.msetudiant.repositories.EtudiantRepo;
import com.univ.msetudiant.services.EtudiantService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
 import java.util.Arrays;
 import java.util.List;

 @SpringBootApplication
public class MsEtudiantApplication {

     @Bean
     WebClient webClient() {
         return WebClient.builder().build();
     }
    public static void main(String[] args) {
        SpringApplication.run(MsEtudiantApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(EtudiantRepo etudiantRepo, EtudiantMapper etudiantMapper, EtudiantService etudiantService) {
        return args -> {
            if (etudiantRepo.count() == 0) {
                List<EtudiantReqDto> samples = Arrays.asList(
                        EtudiantReqDto.builder().nom("John").prenom("Doe").email("john.doe@example.com").telephone("0612345678").dateNaissance(LocalDate.of(2001,5,20)).filiereId(1L).build(),
                        EtudiantReqDto.builder().nom("Jane").prenom("Doe").email("jane.doe@example.com").telephone("0699988877").dateNaissance(LocalDate.of(2002,11,15)).filiereId(2L).build(),
                        EtudiantReqDto.builder().nom("Ali").prenom("Karim").email("ali.karim@example.com").telephone("0700011122").dateNaissance(LocalDate.of(2000,1,10)).filiereId(1L).build(),
                        EtudiantReqDto.builder().nom("Sara").prenom("Ben").email("sara.ben@example.com").telephone("0622233344").dateNaissance(LocalDate.of(1999,9,9)).filiereId(3L).build(),
                        EtudiantReqDto.builder().nom("Youssef").prenom("Amine").email("y.amine@example.com").telephone("0655566677").dateNaissance(LocalDate.of(2003,3,3)).filiereId(2L).build()
                );
                etudiantRepo.saveAll(samples.stream().map(etudiantMapper::toEntity).toList());

                // Sanity check fetch (won't fail app if not present)
                try {
                    EtudiantResDto e = etudiantService.getEtudiantById(1L);
                    System.out.println("Seeded student: " + e);
                } catch (Exception ignored) {}
            }

        };

    }

 }
