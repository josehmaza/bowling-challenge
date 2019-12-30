package com.jobsity.challenge.services;

import com.jobsity.challenge.exception.BadFormatLineException;
import com.jobsity.challenge.exception.BadInputException;
import com.jobsity.challenge.exception.BowlingFileException;
import com.jobsity.challenge.exception.InvalidFormatScoreException;
import com.jobsity.challenge.exception.InvalidScoreException;
import com.jobsity.challenge.exception.ThrowNumberException;
import com.jobsity.challenge.model.Result;
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
     * @param fileName
     * @return
     * @throws IOException
     */
    @Override
    public List<Result> getResults(String fileName) throws BowlingFileException, BadInputException {
        // Se pueden generar errores por
        //NO se encontro el archivo

        ClassLoader classLoader = getClass().getClassLoader();
        //try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
            InputStream inputStream = classLoader.getResourceAsStream(fileName);
            if(inputStream == null){
                throw new BowlingFileException("Failed to read file "+fileName+": File not found");
            }
             List<Result> results = readFromInputStream(inputStream)
                    .stream().map(line -> {
                        return TextUtils.lineToResult(line);
                    })
                    .collect(Collectors.toList());
             validateThrows(results);
             return results;
        //} catch (IOException e) {
        //}

    }

    private void validateThrows(List<Result> results) throws BadInputException {
        Map<String, Integer> playerDistinct = results.stream()
                .filter(distinctByKey(r -> r.getNamePlayer()))

                .collect(Collectors.toMap(p -> p.getNamePlayer(), p -> 0));
        String lastPlayer = "";
        for (Result result : results) {
            if(!result.getNamePlayer().equals(lastPlayer)){
                lastPlayer = result.getNamePlayer();
                int throwNumber = playerDistinct.get(result.getNamePlayer());
                throwNumber++;
                if(throwNumber > 10){
                    throw new ThrowNumberException("A Player can't have more than ten throws: "+throwNumber);
                }
                playerDistinct.put(result.getNamePlayer(), throwNumber++);
            }
        }

        for (Map.Entry<String, Integer> entry : playerDistinct.entrySet()) {
            if(entry.getValue() < 10){
                throw new ThrowNumberException("A Player can't have less than ten throws: "+entry.getKey()+" "+entry.getValue());
            }
        }

    }

    public static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
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
