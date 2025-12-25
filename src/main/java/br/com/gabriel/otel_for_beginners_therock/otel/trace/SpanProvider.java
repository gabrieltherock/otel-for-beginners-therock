package br.com.gabriel.otel_for_beginners_therock.otel.trace;

import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.Span;
import org.springframework.stereotype.Component;

@Component
public class SpanProvider {

    /**
     * Adiciona um atributo customizado ao span atual.
     * Como os spans do OpenTelemetry funcionam: <a href="https://opentelemetry.io/docs/specs/otel/trace/">...</a>
     * @param key   A chave do atributo.
     * @param value O valor do atributo.
     */
    public void addAttributesToCurrentSpan(String key, String value) {
        var span = Span.current();
        span.setAttribute(key, value);
    }

    public void addEventToCurrentSpan(String eventName) {
        var span = Span.current();
        span.addEvent(eventName);
    }

    public void addEventWithAttributesToCurrentSpan(String eventName, String key, String value) {
        var span = Span.current();
        span.addEvent(eventName, Attributes.of(AttributeKey.stringKey(key), value));
    }
}
