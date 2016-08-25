package net.smartcosmos.edge.things.utility;

import java.io.UnsupportedEncodingException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.util.UriUtils;

/**
 * Utility to URL-encode Strings.
 */
@Slf4j
public class UrlEncodingUtil {

    /**
     * Applies UTF-8 URL encoding to a provided String.
     *
     * @param value the input String
     * @return the encoded value
     */
    public static String encode(String value) {

        try {
            return UriUtils.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("UTF-8 encoding is not supported");
        }

        // If we can't encode with UTF-8, return the original value
        return value;
    }
}
