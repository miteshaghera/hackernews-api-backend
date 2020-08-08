package com.github.miteshaghera.hackernewsapi.util;

import java.time.ZoneId;

public final class Constants {
    public static final ZoneId UTC = ZoneId.of("UTC");

    public static final char SLASH = '/';
    public static final int SECONDS_IN_A_YEAR = 60 * 60 * 24 * 365;

    private Constants() {

    }
}
