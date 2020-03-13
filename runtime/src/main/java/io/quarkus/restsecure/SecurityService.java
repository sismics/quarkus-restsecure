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

    /**
     * This method is called before after authentication.
     *
     * @param userId The user ID
     */
    default void afterAuthenticate(UUID userId) {
    }

    /**
     * This method is called before a user tries to sign off.
     * You need to override this method if you wish to perform specific actions (eg. Record the name of the user who signed
     * off).
     */
    default void onDisconnect() {
    }

    /**
     * This method is called after a successful sign off.
     * You need to override this method if you wish to perform specific actions (eg. Record the time the user signed off).
     */
    default void onDisconnected() {
    }

}
