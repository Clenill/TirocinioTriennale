package com.tirociniotriennale.sitoeventi;

//import com.tirociniotriennale.sitoeventi.model.Evento;
//import com.tirociniotriennale.sitoeventi.repository.EventoRepository;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class TicketloveApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketloveApplication.class, args);
	}
/*
	@Bean
	CommandLineRunner commandLineRunner(EventoRepository eventoRepository) {

		return args -> {
				Iterable<Evento> eventiPerUser = eventoRepository.findByUser("org");

				for(Evento ev : eventiPerUser){
					System.out.println("Nome evento: "+ ev.getNomeevento());
					System.out.println("Nome user: "+ ev.getUser());
					System.out.println("Nome idtipologia: "+ ev.getIdtipologia());

				}

				Iterable<Evento> eventiPerIdTipologia = eventoRepository.findByIdtipologia(10);

			for(Evento ev : eventiPerIdTipologia){// se la dimensione è zero non entra nel ciclo e non genera eccezzione
				System.out.println("Per ID TIPOLOGIA: nome è: "+ ev.getNomeevento());
				System.out.println("Per ID TIPOLOGIA: user è: "+ ev.getUser());
				System.out.println("Per ID TIPOLOGIA : idtip è: "+ ev.getIdtipologia());

			}

		};

	}

 */

}
