package net.smartcosmos.edge.things.resource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import io.github.robwin.markup.builder.MarkupLanguage;
import springfox.documentation.staticdocs.Swagger2MarkupResultHandler;
import springfox.documentation.staticdocs.SwaggerResultHandler;
import springfox.documentation.swagger2.web.Swagger2Controller;

import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import net.smartcosmos.edge.things.ThingEdgeService;
import net.smartcosmos.edge.things.config.SpringfoxDocumentationConfig;
import net.smartcosmos.security.user.SmartCosmosUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Generate the documentation.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = { ThingEdgeService.class, SpringfoxDocumentationConfig.class })
@ActiveProfiles("test")
public class GenerateDocumentationTest {

    public static final String GENERATED_DOCS_ROOT_LOCATION = "target/generated/api-docs";
    public static final String GENERATED_ASCIIDOCS_LOCATION = GENERATED_DOCS_ROOT_LOCATION + "/asciidoc";
    public static final String GENERATED_JSON_LOCATION = GENERATED_DOCS_ROOT_LOCATION + "/json";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        // Need to mock out user for `conversion service.
        // Might be a good candidate for a test package util.
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getPrincipal())
            .thenReturn(new SmartCosmosUser("accountUrn", "urn:userUrn", "username",
                                            "password", Arrays.asList(new SimpleGrantedAuthority("USER"))));
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication())
            .thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        MockitoAnnotations.initMocks(getClass());

        mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .build();
    }

    @Test
    public void convertSwaggerToAsciiDoc() throws Exception {

        mockMvc.perform(get(Swagger2Controller.DEFAULT_URL).accept(MediaType.APPLICATION_JSON))
            .andDo(Swagger2MarkupResultHandler.outputDirectory(getDocOutputLocation())
                       .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                       .build())
            .andDo(SwaggerResultHandler.outputDirectory(getJsonOutputLocation())
                       .build())
            .andExpect(status().isOk());
    }

    private String getJsonOutputLocation() {

        return new File(getTargetFileLocation(), GENERATED_JSON_LOCATION).getAbsolutePath();
    }

    private String getDocOutputLocation() {

        return new File(getTargetFileLocation(), GENERATED_ASCIIDOCS_LOCATION).getAbsolutePath();
    }

    private File getTargetFileLocation() {

        ClassPathResource pathfileRes = new ClassPathResource("application-test.yml");
        try {
            return pathfileRes.getFile()
                .getParentFile()
                .getParentFile()
                .getParentFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
