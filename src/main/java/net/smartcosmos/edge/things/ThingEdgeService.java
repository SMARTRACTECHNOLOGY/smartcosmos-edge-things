package net.smartcosmos.edge.things;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

import net.smartcosmos.annotation.EnableSmartCosmosEvents;
import net.smartcosmos.annotation.EnableSmartCosmosExtension;
import net.smartcosmos.annotation.EnableSmartCosmosSecurity;
import net.smartcosmos.edge.things.config.ThingsEdgeConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSmartCosmosExtension
@EnableSmartCosmosEvents
@EnableSmartCosmosSecurity
@EnableSwagger2
@Import(ThingsEdgeConfiguration.class)
public class ThingEdgeService {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ThingEdgeService.class).web(true).run(args);
    }
}
