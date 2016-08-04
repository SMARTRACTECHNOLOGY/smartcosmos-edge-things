package net.smartcosmos.edge.things;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import net.smartcosmos.annotation.EnableSmartCosmosEvents;
import net.smartcosmos.annotation.EnableSmartCosmosExtension;
import net.smartcosmos.annotation.EnableSmartCosmosSecurity;
import net.smartcosmos.edge.things.config.ThingsEdgeConfiguration;

@EnableSmartCosmosExtension
@EnableSmartCosmosEvents
@EnableSmartCosmosSecurity
@EnableSwagger2
@Import(ThingsEdgeConfiguration.class)
@ComponentScan(basePackages = { "net.smartcosmos.edge" }) // required, because @EnableSmartCosmosExtension only scans net.smartcosmos.(dao|extension)
public class ThingEdgeService {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ThingEdgeService.class).web(true).run(args);
    }
}
