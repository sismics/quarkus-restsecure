package io.quarkus.restsecure.deployment;

import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.deployment.InterceptorBindingRegistrarBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.restsecure.RestSecureInterceptor;

/**
 * @author jtremeaux
 */
class RestSecureProcessor {
    private static final String FEATURE = "restsecure";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    void registerSecurityInterceptors(BuildProducer<InterceptorBindingRegistrarBuildItem> registrars,
            BuildProducer<AdditionalBeanBuildItem> beans) {
        registrars.produce(new InterceptorBindingRegistrarBuildItem(new RestSecureAnnotationsRegistrar()));
        Class[] interceptors = { RestSecureInterceptor.class };
        beans.produce(new AdditionalBeanBuildItem(interceptors));
    }

}
