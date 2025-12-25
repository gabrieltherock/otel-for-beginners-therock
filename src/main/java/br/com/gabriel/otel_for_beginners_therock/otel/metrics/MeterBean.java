package br.com.gabriel.otel_for_beginners_therock.otel.metrics;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.metrics.Meter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MeterBean {

    /**
     * Classe de configuração para métricas personalizadas.
     * Com esse objeto Meter você pode definir contadores, histogramas e outras métricas
     * que serão usadas na aplicação.
     */
    @Bean
    public Meter meter() {
        return GlobalOpenTelemetry.getMeter("business-metrics");
    }
}
