package br.com.example.farmacia.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.core.io.ClassPathResource;

public class Util {

	public static String fileJson(final String filePath) throws IOException{
		try {
			final File input = new ClassPathResource(filePath).getFile();
			return new String(Files.readAllBytes(Paths.get(input.getPath())));
		} catch (Exception e) {
			throw e;
		}
	}
}
