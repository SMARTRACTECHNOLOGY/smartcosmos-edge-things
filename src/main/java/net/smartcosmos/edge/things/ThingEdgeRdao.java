package net.smartcosmos.edge.things;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import net.smartcosmos.spring.EnableSmartCosmos;
import net.smartcosmos.spring.EnableSmartCosmosExtension;
import net.smartcosmos.spring.EnableSmartCosmosSecurity;

@EnableSmartCosmosExtension
@EnableSmartCosmos
@EnableSmartCosmosSecurity
@EnableSwagger2
@ComponentScan(basePackages={"net.smartcosmos.edge"}) // required, because @EnableSmartCosmosExtension only scans net.smartcosmos.(dao|extension)
//@EnabledSmartCosmosEdgeService // could be the annotation that enables the component scan for net.smartcosmos.edge from above
public class ThingEdgeRdao extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ThingEdgeRdao.class).web(true).run(args);
    }

    //    @Autowired
    //    Map<String, FormatterRegistrar> formatterRegistrarMap;
    //
    //    @Override
    //    public void addFormatters(FormatterRegistry registry) {
    //        for (FormatterRegistrar registrar : formatterRegistrarMap.values()) {
    //            registrar.registerFormatters(registry);
    //        }
    //    }

}
