package net.smartcosmos.edge.things;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("smartcosmos.edge.things")
public class SmartCosmosEdgeThingsProperties {

    private LocalServiceProperties local = new LocalServiceProperties();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LocalServiceProperties {

        private String things = "smartcosmos-ext-things-rdao";
        private String metadata = "smartcosmos-ext-metadata-rdao";
    }
}
