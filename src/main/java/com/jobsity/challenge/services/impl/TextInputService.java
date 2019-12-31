package com.jobsity.challenge.services.impl;

import com.jobsity.challenge.exception.BadFormatLineException;
import com.jobsity.challenge.exception.BadInputException;
import com.jobsity.challenge.exception.BowlingFileException;
import com.jobsity.challenge.exception.InvalidFormatScoreException;
import com.jobsity.challenge.exception.InvalidScoreException;
import com.jobsity.challenge.model.Result;
import com.jobsity.challenge.services.InputService;
import com.jobsity.challenge.utils.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Servicio para obtener resultados cuando se tiene como entrada un archivo de texto
 */
public class TextInputService implements InputService {
    private static TextInputService single_instance = null;

    public static TextInputService getInstance() {
        if (single_instance == null)
            single_instance = new TextInputService();

        return single_instance;
    }

    /**
     * From filename read file and get as List of Results 
     * @param fileName to read in Resources folder
     * @return List<Result> 
     * @throws IOException
     */
    @Override
    public List<Result> getResults(String fileName) throws BowlingFileException, BadInputException {
        // Se pueden generar errores por
        //NO se encontro el archivo

        ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(fileName);
            if(inputStream == null){
                throw new BowlingFileException("Failed to read file "+fileName+": File not found");
            }
             List<Result> results = readFromInputStream(inputStream)
                    .stream().map(line -> {
                        return TextUtils.lineToResult(line);
                    })
                    .collect(Collectors.toList());
             return results;


    }

    /**
     * Get lines(List<String>) from inputStream
     * @param inputStream
     * @return List<String>
     * @throws BowlingFileException 
     * @throws BadInputException
     */
    private List<String> readFromInputStream(InputStream inputStream)
            throws BowlingFileException, BadInputException {
        StringBuilder resultStringBuilder = new StringBuilder();
        List<String> lines = new ArrayList();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                validateLine(line);
                lines.add(line);
            }

        } catch (IOException e) {
            throw new BowlingFileException("Ha ocurrido un error al leer el archivo " + e.getMessage());
        }
        return lines;
    }

    /**
     * Validate format and correct score from a result(line)
     * @param line
     * @throws BadInputException
     */
    public void validateLine(String line) throws BadInputException {
        String[] elements = line.split("\t");
        if (elements.length != 2) {
            throw new BadFormatLineException("Format line is incorrect: " + line);
        }
        try {
            if(!elements[1].equals("F")){
                Integer score = Integer.parseInt(elements[1]);
                if(score < 0 || score > 10){
                    throw  new InvalidScoreException("The score must be between 0 to 10 : Received "+score);
                }
            }
        }catch (NumberFormatException e){
            throw new InvalidFormatScoreException("The score must be F or number(0-10): Received "+elements[1]);
        }
    }
}
