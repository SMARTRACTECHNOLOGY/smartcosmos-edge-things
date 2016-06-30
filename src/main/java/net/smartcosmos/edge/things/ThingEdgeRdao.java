package net.smartcosmos.edge.things;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

import net.smartcosmos.spring.EnableSmartCosmos;
import net.smartcosmos.spring.EnableSmartCosmosExtension;
import net.smartcosmos.spring.EnableSmartCosmosSecurity;

@EnableSmartCosmosExtension
@EnableSmartCosmos
@EnableSmartCosmosSecurity
@EnableSwagger2
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
