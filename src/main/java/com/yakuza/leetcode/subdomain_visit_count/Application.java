package com.yakuza.leetcode.subdomain_visit_count;

import com.yakuza.leetcode.subdomain_visit_count.util.Utility;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Solution for task.
 * NOTE.
 * Current solution was done just for positive cases.
 */
public class Application {

    /**
     * CONSTANTS
     */
    static final String ENCODING = "UTF-8";
    static final String COUNT_PAIRED_DOMAIN_DELIMETER = ",";
    static final String COUNT_DOMAIN_DELIMTER = " ";
    static final String DOMAIN_PART_DELIMETER = "\\.";
    public static final String DOUBLE_QUOTES = "\"";


    /**
     * Just solution entry point.
     *
     * @param args
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("The path to text file is expected.");
            return;
        }

        Map<String, Integer> cpDomainsMap = new HashMap<>();
        try {
            Utility.readFile(args[0], ENCODING, lineValue -> handleLine(cpDomainsMap, lineValue));
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    /**
     * Text line handler.
     *
     * @param cpDomainsMap storage of count paired domains
     * @param line         source text line
     */
    private static void handleLine(Map<String, Integer> cpDomainsMap, String line) {
        System.out.println(line);

        String[] values = line.split(COUNT_PAIRED_DOMAIN_DELIMETER);

        for (String value : values) {
            String trimmedValue = value.replace(DOUBLE_QUOTES, "").trim();

            String[] countPairedDomain = trimmedValue.split(COUNT_DOMAIN_DELIMTER);
            Integer counter = Integer.valueOf(countPairedDomain[0], 10);
            String[] domains = countPairedDomain[1].split(DOMAIN_PART_DELIMETER);

            StringBuilder stringToRemove = new StringBuilder();
            for (String domain : domains) {
                if (stringToRemove.length() == 0) {
                    stringToRemove = new StringBuilder(domain);
                } else {
                    stringToRemove.append(".").append(domain);
                }

                String domainValue = countPairedDomain[1].replace(stringToRemove + ".", "");
                cpDomainsMap.put(domainValue, cpDomainsMap.getOrDefault(domainValue, 0) + counter);
            }
        }

        //Here we print results.
        if (!cpDomainsMap.isEmpty()) {

            System.out.print("[");
            cpDomainsMap.keySet().iterator().forEachRemaining(new Consumer<String>() {
                boolean isFirst = true;

                @Override
                public void accept(String s) {
                    if (isFirst) {
                        isFirst = !isFirst;
                    } else {
                        System.out.print(", ");
                    }
                    System.out.print(cpDomainsMap.get(s) + " " + s);
                }
            });
            System.out.print("]");
        }
    }
}
