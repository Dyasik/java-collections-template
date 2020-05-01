package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;
import com.epam.izh.rd.online.helper.StringLengthComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        return getWords(text)
            .stream()
            .map(String::length)
            .reduce(Integer::sum)
            .orElse(0);
    }

    @Override
    public int countNumberOfWords(String text) {
        return getWords(text).size();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return getUniqueWords(text).size();
    }

    @Override
    public List<String> getWords(String text) {
        return new ArrayList<>(Arrays.asList(text.split("[ \n.,-;!?'\"]")))
            .stream()
            .filter((word) -> !word.isEmpty())
            .collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return new HashSet<>(getWords(text));
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return getWords(text)
            .stream()
            .collect(
                HashMap::new,
                (map, word) -> map.merge(word, 1, Integer::sum),
                HashMap::putAll
            );
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        return getWords(text)
            .stream()
            .sorted(new StringLengthComparator(direction))
            .collect(Collectors.toList());
    }
}
