package net.smartcosmos.edge.things.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ConfigurationProperties("smartcosmos.edge.things")
public class SmartCosmosEdgeThingsProperties {

    private LocalServiceProperties local = new LocalServiceProperties();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LocalServiceProperties {

        private String things = "smartcosmos-ext-things";
        private String metadata = "smartcosmos-ext-metadata";
    }
}
