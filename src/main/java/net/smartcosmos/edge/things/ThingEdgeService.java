package net.smartcosmos.edge.things;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.retry.annotation.EnableRetry;

import net.smartcosmos.annotation.EnableSmartCosmosEvents;
import net.smartcosmos.annotation.EnableSmartCosmosExtension;
import net.smartcosmos.annotation.EnableSmartCosmosMonitoring;
import net.smartcosmos.annotation.EnableSmartCosmosSecurity;
import net.smartcosmos.edge.things.config.ThingsEdgeConfiguration;

@EnableSmartCosmosExtension
@EnableSmartCosmosEvents
@EnableSmartCosmosMonitoring
@EnableSmartCosmosSecurity
@EnableRetry
@Import(ThingsEdgeConfiguration.class)
public class ThingEdgeService {

    public static void main(String[] args) {

        new SpringApplicationBuilder(ThingEdgeService.class).web(true)
            .run(args);
    }
}
