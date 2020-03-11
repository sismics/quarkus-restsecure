package io.quarkus.restsecure;

import io.quarkus.security.ForbiddenException;

import javax.annotation.Priority;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * @author jtremeaux
 */
@Interceptor
@RestSecure
@Priority(Interceptor.Priority.LIBRARY_BEFORE)
public class RestSecureInterceptor {
    @Inject
    SecurityService securityService;

    @AroundInvoke
    public Object intercept(InvocationContext ic) throws Exception {
        RolesAllowed rolesAllowed = ic.getMethod().getAnnotation(RolesAllowed.class);
        if (rolesAllowed != null) {
            check(rolesAllowed.value());
        }
        rolesAllowed = ic.getMethod().getDeclaringClass().getAnnotation(RolesAllowed.class);
        if (rolesAllowed != null) {
            check(rolesAllowed.value());
        }

        return ic.proceed();
    }

    public void check(String... privileges) {
        for (String profile : privileges) {
            boolean hasProfile = securityService.check(profile);
            if (!hasProfile) {
                throw new ForbiddenException(profile);
            }
        }
    }
}
