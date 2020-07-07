package br.com.example.farmacia.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {

	public static String fileJson(final String filePath) throws IOException {
		try {
			final File input = new ClassPathResource(filePath).getFile();
			return new String(Files.readAllBytes(Paths.get(input.getPath())));
		} catch (Exception e) {
			throw e;
		}
	}

	public static String Json(Object object) throws JsonProcessingException {
		return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(object);
	}
}
