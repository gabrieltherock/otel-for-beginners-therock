package br.com.gabriel.otel_for_beginners_therock.otel.metrics;

import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;
import org.springframework.stereotype.Component;

import static io.opentelemetry.api.common.AttributeKey.stringKey;

@Component
public class BomDiaEnviadosMetricProvider {

    private final LongCounter messagesSent;

    /**
     * Construtor que inicializa o contador de métricas.
     * Como as métricas do OpenTelemetry funcionam: <a href="https://opentelemetry.io/docs/specs/otel/metrics/">...</a>
     */
    public BomDiaEnviadosMetricProvider(Meter meter) {
        this.messagesSent = meter
                .counterBuilder("bom_dia_enviados") // Aqui definimos o nome da métrica que usaremos
                .setDescription("Total de bom dia enviados por usuario (1 a 10 aleatórios)")
                .setUnit("1") // Unidade da métrica, "1" significa contagem simples
                .build();
    }

    public void increment(String userId) {
        messagesSent.add(
                1,
                Attributes.of(
                        stringKey("user_id"), userId
                )
        );
    }
}
