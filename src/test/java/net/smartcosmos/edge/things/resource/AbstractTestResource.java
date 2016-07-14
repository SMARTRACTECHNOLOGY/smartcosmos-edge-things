package net.smartcosmos.edge.things.resource;

import java.util.Arrays;

import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import net.smartcosmos.edge.things.ThingEdgeRdao;
import net.smartcosmos.edge.things.config.ThingsEdgeTestConfig;
import net.smartcosmos.edge.things.rest.template.metadata.MetadataRestTemplate;
import net.smartcosmos.edge.things.rest.template.thing.ThingRestTemplate;
import net.smartcosmos.security.user.SmartCosmosUser;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = { ThingEdgeRdao.class, ThingsEdgeTestConfig.class })
@ActiveProfiles("test")
public abstract class AbstractTestResource {

    @Autowired
    protected ThingRestTemplate thingRestTemplate;

    @Autowired
    protected MetadataRestTemplate metadataRestTemplate;

    @Autowired
    protected WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(getClass());

        // Need to mock out user for conversion service.
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal())
            .thenReturn(new SmartCosmosUser("accountUrn", "urn:userUrn", "username",
                "password", Arrays.asList(new SimpleGrantedAuthority("USER"))));
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() {
        Mockito.reset(metadataRestTemplate);
        Mockito.reset(thingRestTemplate);
    }
}
