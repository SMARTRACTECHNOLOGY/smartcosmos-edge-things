package net.smartcosmos.edge.things;

import java.util.List;
import java.util.Map;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import net.smartcosmos.extension.things.ThingRdao;
import net.smartcosmos.spring.EnableSmartCosmos;
import net.smartcosmos.spring.EnableSmartCosmosExtension;
import net.smartcosmos.spring.EnableSmartCosmosSecurity;

@EnableSmartCosmosExtension
@EnableSmartCosmos
@EnableSmartCosmosSecurity
@EnableSwagger2
public class ThingEdgeRdao extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ThingRdao.class).web(true).run(args);
    }

    @Autowired
    Map<String, FormatterRegistrar> formatterRegistrarMap;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        for (FormatterRegistrar registrar : formatterRegistrarMap.values()) {
            registrar.registerFormatters(registry);
        }
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setOneIndexedParameters(true);
        argumentResolvers.add(resolver);
        super.addArgumentResolvers(argumentResolvers);
    }
}
