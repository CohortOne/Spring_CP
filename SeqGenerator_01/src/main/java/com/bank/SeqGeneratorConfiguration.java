package com.bank;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeqGeneratorConfiguration {

	@Bean
	public SeqGenerator seqGenerator() {
		SeqGenerator seqGen = new SeqGenerator();
		seqGen.setPrefix("4420");
		seqGen.setSuffix("CC");
		seqGen.setInitial(11223344);
		return seqGen;
	}
}
