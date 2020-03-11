package io.quarkus.restsecure;

import java.util.UUID;

/**
 * @author jtremeaux
 */
public interface SecurityService {
    /**
     * Authenticate the user.
     *
     * @param username The user name
     * @param password The password
     * @return The user ID, or null if the user doesn't exist / the password doesn't match
     */
    UUID authenticate(String username, String password);

    /**
     * Check that the user has a permission.
     *
     * @param permission The permission to check
     * @return The condition to check
     */
    boolean check(String permission);
}
