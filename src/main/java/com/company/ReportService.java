package com.company;

/**
 * Responsible for creating a report on the difference between objects.
 */
public class ReportService {

    /**
     * Creates a report based on the difference between fields of passed objects.
     * @param sourceData source PersonalData object.
     * @param targetData PersonalData object with which to compare source.
     * @return Report object with information about the difference between the transmitted objects
     */
    public Report createReport(PersonalData sourceData, PersonalData targetData) {
        if (sourceData == null || targetData == null) {
            throw new IllegalArgumentException("Parameters must not be null");
        }
        int errorCounter = foundDifference(sourceData.getFirstName(), targetData.getFirstName()) +
                foundDifference(sourceData.getLastName(), targetData.getLastName()) +
                foundDifference(sourceData.getPatronymicName(), targetData.getPatronymicName());
        return new Report(errorCounter);
    }

    /**
     * Finds the edit distance between the transmitted strings using the Damerau-Levenshtein algorithm.
     * The calculation does not include characters that are not letters (Cyrillic and Latin) or numbers.
     * Also ignored case.
     * @param sourceString source string.
     * @param targetString string with which to compare source.
     * @return edit distance (count of mismatch).
     */
    public int foundDifference (String sourceString, String targetString) {
        if (sourceString == null || targetString == null) {
            throw new IllegalArgumentException("Parameter must not be null");
        }

        sourceString = removeNonValidSymbols(sourceString).toUpperCase();
        targetString = removeNonValidSymbols(targetString).toUpperCase();

        int sourceLength = sourceString.length();
        int targetLength = targetString.length();

        if (sourceLength == 0) return targetLength;
        if (targetLength == 0) return sourceLength;

        int[][] dist = new int[sourceLength + 1][targetLength + 1];
        for (int i = 0; i < sourceLength + 1; i++) {
            dist[i][0] = i;
        }
        for (int j = 0; j < targetLength + 1; j++) {
            dist[0][j] = j;
        }
        for (int i = 1; i < sourceLength + 1; i++) {
            for (int j = 1; j < targetLength + 1; j++) {
                int cost = sourceString.charAt(i - 1) == targetString.charAt(j - 1) ? 0 : 1;
                dist[i][j] = Math.min(Math.min(dist[i - 1][j] + 1, dist[i][j - 1] + 1), dist[i - 1][j - 1] + cost);
                if (i > 1 &&
                        j > 1 &&
                        sourceString.charAt(i - 1) == targetString.charAt(j - 2) &&
                        sourceString.charAt(i - 2) == targetString.charAt(j - 1)) {
                    dist[i][j] = Math.min(dist[i][j], dist[i - 2][j - 2] + cost);
                }
            }
        }
        return dist[sourceLength][targetLength];
    }

    /**
     * Removes characters from a string that are not letters (Cyrillic or Latin) or numbers.
     * @param inputString input string;
     * @return string with only letters or numbers
     */
    public String removeNonValidSymbols(String inputString) {
        if (inputString == null) {
            throw new IllegalArgumentException("Parameter must not be null");
        }
        // Regexp: only numbers and letters (cyrillic and latin)
        return inputString.replaceAll("[^\\da-zA-Zа-яёА-ЯЁ]", "");
    }
}
