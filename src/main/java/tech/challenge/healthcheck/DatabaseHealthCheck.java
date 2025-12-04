package tech.challenge.healthcheck;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class DatabaseHealthCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        try {
            var result = io.quarkus.hibernate.orm.panache.Panache
                    .getEntityManager()
                    .createNativeQuery("SELECT 1")
                    .getSingleResult();

            return HealthCheckResponse.up("Database connection");
        } catch (Exception e) {
            return HealthCheckResponse.down("Database connection");
        }
    }
}
