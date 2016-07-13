package net.smartcosmos.edge.things.resource;

import java.util.Arrays;

import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import net.smartcosmos.edge.things.ThingEdgeRdao;
import net.smartcosmos.edge.things.config.ThingsEdgeTestConfig;
import net.smartcosmos.edge.things.domain.RestEdgeThingCreateDto;
import net.smartcosmos.edge.things.domain.local.metadata.RestMetadataCreateResponseDto;
import net.smartcosmos.edge.things.domain.local.things.RestThingCreateResponseDto;
import net.smartcosmos.edge.things.service.CreateThingEdgeService;
import net.smartcosmos.edge.things.service.GetThingEdgeService;
import net.smartcosmos.edge.things.service.local.metadata.CreateMetadataRestService;
import net.smartcosmos.edge.things.service.local.things.CreateThingRestService;
import net.smartcosmos.edge.things.testutil.Testutility;
import net.smartcosmos.security.user.SmartCosmosUser;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = { ThingEdgeRdao.class, ThingsEdgeTestConfig.class })
@ActiveProfiles("test")
public class CreateThingsResourceTest {

    @Autowired
    private CreateThingEdgeService createThingEdgeService;

    @Autowired
    private GetThingEdgeService getThingEdgeService;

    @Autowired
    private CreateThingRestService createThingRestService;

    @Autowired
    private CreateMetadataRestService createMetadataRestService;

    @Autowired
    protected WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

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

    /**
     * Test that creating a Thing is successful.
     *
     * @throws Exception
     */
    @Test
    public void thatCreateThingSucceeds() throws Exception {

        final String expectedUrn = "urn";
        final String expectedType = "someType";
        final String expectedTenantUrn = "tenantUrn";
        final Boolean expectedActive = false;

        final RestThingCreateResponseDto thingResponseBody = RestThingCreateResponseDto.builder()
            .urn(expectedUrn)
            .type(expectedType)
            .tenantUrn(expectedTenantUrn)
            .active(expectedActive)
            .build();
        final ResponseEntity<?> thingResponseEntity = new ResponseEntity<>(thingResponseBody, HttpStatus.CREATED);

        final RestMetadataCreateResponseDto metadataResponseBody = RestMetadataCreateResponseDto.builder()
            .uri(expectedType + "/" + expectedUrn)
            .build();
        final ResponseEntity<?> metadataResponseEntity = new ResponseEntity<>(metadataResponseBody, HttpStatus.OK);

        willReturn(thingResponseEntity).given(createThingRestService).create(anyString(), anyObject(), anyObject());
        willReturn(metadataResponseEntity).given(createMetadataRestService).create(anyString(), anyString(), anyBoolean(), anyMap(), anyObject());

        byte[] jsonDto = Testutility.convertObjectToJsonBytes(RestEdgeThingCreateDto.builder().urn(expectedUrn).type(expectedType).build());
        MvcResult mvcResult = this.mockMvc.perform(
            post("/someType").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.urn", is(expectedUrn)))
            .andExpect(jsonPath("$.type", is(expectedType)))
            .andExpect(jsonPath("$.tenantUrn", is(expectedTenantUrn)))
            .andExpect(jsonPath("$.active", is(expectedActive)));
    }
}
