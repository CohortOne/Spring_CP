package com.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SeqGeneratorApplication {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(SeqGeneratorConfiguration.class);

		SeqGenerator seqG = context.getBean(SeqGenerator.class);

		for(int i = 0 ; i <= 10000 ; i ++ )
			System.out.println( " Seq Generator @ Work :: " + seqG.getSequence());
	}

}
