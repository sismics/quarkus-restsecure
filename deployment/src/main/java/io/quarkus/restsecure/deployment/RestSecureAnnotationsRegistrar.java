package io.quarkus.restsecure.deployment;

import io.quarkus.arc.processor.InterceptorBindingRegistrar;
import org.jboss.jandex.DotName;

import javax.annotation.security.RolesAllowed;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author jtremeaux
 */
public class RestSecureAnnotationsRegistrar implements InterceptorBindingRegistrar {
    public static final Map<DotName, Set<String>> SECURITY_BINDINGS = new HashMap<>();

    static {
        SECURITY_BINDINGS.put(DotName.createSimple(RolesAllowed.class.getName()), Collections.singleton("value"));
    }

    @Override
    public Map<DotName, Set<String>> registerAdditionalBindings() {
        return SECURITY_BINDINGS;
    }
}
