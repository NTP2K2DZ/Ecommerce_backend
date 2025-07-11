package com.example.backend.util;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class SlugUtil {

    private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("\\s+");

    private static String generateSlug(String input, String suffix) {
        if (input == null) return "";

        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String slug = WHITESPACE.matcher(normalized).replaceAll("-");
        slug = NON_LATIN.matcher(slug).replaceAll("");
        slug = slug.replaceAll("-{2,}", "-");
        slug = slug.toLowerCase(Locale.ENGLISH).replaceAll("^-|-$", "");

        if (suffix != null && !suffix.isBlank()) {
            return slug + "-" + suffix.toLowerCase();
        }
        return slug;
    }

    public static String toBrandSlug(String name) {
        return generateSlug(name, null);
    }

    public static String toCategorySlug(String name) {
        return generateSlug(name, null);
    }

    public static String toProductSlug(String name) {
        return generateSlug(name, null);
    }
}
