package br.com.gabriel.otel_for_beginners_therock.controller;

import br.com.gabriel.otel_for_beginners_therock.otel.metrics.BomDiaEnviadosMetricProvider;
import br.com.gabriel.otel_for_beginners_therock.otel.trace.SpanProvider;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

import static io.opentelemetry.api.trace.SpanKind.SERVER;

@RestController
public class BomDiaController {

    private final SpanProvider spanProvider;
    private final BomDiaEnviadosMetricProvider bomDiaEnviadosMetricProvider;

    public BomDiaController(SpanProvider spanProvider,
                            BomDiaEnviadosMetricProvider bomDiaEnviadosMetricProvider) {
        this.spanProvider = spanProvider;
        this.bomDiaEnviadosMetricProvider = bomDiaEnviadosMetricProvider;
    }

    @GetMapping("/bom-dia")
    public String bomDia() {
        return "Bom dia, OpenTelemetry!";
    }

    @GetMapping("/bom-dia-span")
    @WithSpan(value = "bom-dia-span", kind = SERVER)
    public String bomDiaSpan() {
        // Adiciona atributos customizados ao span atual para eu visualizar no backend de tracing
        // E poder usar para buscas e análises depois
        spanProvider.addAttributesToCurrentSpan("que-horas-sao-maisoumenos", System.currentTimeMillis() + "");
        spanProvider.addAttributesToCurrentSpan("id-da-requisicao", UUID.randomUUID().toString());

        // Adiciona um evento customizado ao span atual com atributos personalizados
        spanProvider.addEventWithAttributesToCurrentSpan(
                "evento-bom-dia",
                "evento-atributo-exemplo",
                "valor-do-atributo-exemplo"
        );

        // Adiciona um evento simples ao span atual
        spanProvider.addEventToCurrentSpan("evento-simples-bom-dia-so-pra-avisar-que-foi");

        return "Bom dia com Span personalizado, OpenTelemetry!\nEle vai ter os atributos que eu quiser.";
    }

    @GetMapping("/bom-dia-metric")
    public String bomDiaMetric() {
        // Incrementa a métrica customizada toda vez que esse endpoint for chamado
        var userId = new Random().nextInt(10) + 1 + ""; // Simula um userId entre 1 e 10
        bomDiaEnviadosMetricProvider.increment(userId);
        return "Bom dia com Métrica personalizada, OpenTelemetry!\nEla vai contar quantas vezes esse endpoint foi chamado.";
    }
}
