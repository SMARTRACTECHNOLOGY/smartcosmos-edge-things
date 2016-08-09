package net.smartcosmos.edge.things.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ SmartCosmosEdgeThingsProperties.class })
public class ThingsEdgeConfiguration {

}
