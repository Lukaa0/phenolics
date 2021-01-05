package pt.ipb.phenolic.repos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
import java.net.http.HttpResponse;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import pt.ipb.phenolic.controllers.SourceController;
import pt.ipb.phenolic.dtos.SourceRequest;
import pt.ipb.phenolic.models.Phenolic;
import pt.ipb.phenolic.models.Source;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = SpringBootTest.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles({"integration"})
*/

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc


public class SourceTesting {
    @MockBean
    private PhenolicRepository phenolicRepository;
    @MockBean
    private MoleculeRepository moleculeRepository;
    @MockBean
    private LambdaRepository lambdaRepository;
    @MockBean
    private MSFragmentRepository MSFragmentRepository;
    @MockBean
    private SourceRepository sourceRepository;
/*
    @Value("${local.server.port}")
    private int ports;

    @BeforeAll
    public void setUp(){
        //port = ports;
        //baseURI = "http://localhsot:5000/api";
    }*/
    @Autowired
    private MockMvc mockMvc;

    /*@InjectMocks
    SourceController sourceController ;

    @Mock
    private SourceRepository sourceRepo;
    @Mock
    private PhenolicRepository phenolicRepo;

*/
    //private static final SourceController sc = new SourceController();


/*
    @MockBean
    private PhenolicRepository phenolicRepo;

    @Before
    public void init() {
        Source src = new Source();
        long id = 0;
        src.setName("Source name");
        src.setId(id);


        when(sourceRepo.findById(id)).thenReturn(Optional.of(src));

    }*/

    @Test
    public void Testing_get_Empty() throws Exception{/*
        Source src = new Source();
        src.setName("Source");
        Set<Phenolic> phenolics = ;
        Phenolic ph = new Phenolic();
        ph.setName("name of phenolic");
        phenolics.add(ph);
        

        Phenolic ph = new Phenolic();
        ph.setName("Name of phenolic");
        src.setPhenolics(ph);
*/
        //sourceRepo.save(src);


        //sourceController = new SourceController(sourceRepo,phenolicRepo);

        //HashSet<SourceRequest> has = sourceController.getSources("all");
        //System.out.print(has);


        System.out.print("Test 1");

        mockMvc.perform(get("/api/v1/sources"))
                .andExpect(status().isOk());


        //verify(sourceRepository, times(1)).findById(1L);

    }


}
