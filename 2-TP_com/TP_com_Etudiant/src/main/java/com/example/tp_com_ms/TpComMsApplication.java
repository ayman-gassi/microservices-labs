package com.example.tp_com_ms;

import com.example.tp_com_ms.dto.EtudiantReqDto;
import com.example.tp_com_ms.mapper.EtudiantMapper;
import com.example.tp_com_ms.repository.EtudiantRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TpComMsApplication {
    public static void main(String[] args) {
        SpringApplication.run(TpComMsApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(EtudiantRepo etudiantRepo , EtudiantMapper etudiantMapper){
        return args -> {
            EtudiantReqDto etudiantReqDto = EtudiantReqDto.builder()
                    .nom("gassi")
                    .prenom("ayman")
                    .email("ayman@gmail.com")
                    .telephone("+212680598310")
                    .build();
            etudiantRepo.save(etudiantMapper.toEntity(etudiantReqDto));
        };
    }
}
