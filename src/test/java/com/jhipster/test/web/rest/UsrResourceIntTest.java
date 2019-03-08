package com.jhipster.test.web.rest;

import com.jhipster.test.JHipsterTestApp;

import com.jhipster.test.domain.Usr;
import com.jhipster.test.repository.UsrRepository;
import com.jhipster.test.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.jhipster.test.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UsrResource REST controller.
 *
 * @see UsrResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JHipsterTestApp.class)
public class UsrResourceIntTest {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    @Autowired
    private UsrRepository usrRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUsrMockMvc;

    private Usr usr;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UsrResource usrResource = new UsrResource(usrRepository);
        this.restUsrMockMvc = MockMvcBuilders.standaloneSetup(usrResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Usr createEntity(EntityManager em) {
        Usr usr = new Usr()
            .userID(DEFAULT_USER_ID)
            .password(DEFAULT_PASSWORD);
        return usr;
    }

    @Before
    public void initTest() {
        usr = createEntity(em);
    }

    @Test
    @Transactional
    public void createUsr() throws Exception {
        int databaseSizeBeforeCreate = usrRepository.findAll().size();

        // Create the Usr
        restUsrMockMvc.perform(post("/api/usrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usr)))
            .andExpect(status().isCreated());

        // Validate the Usr in the database
        List<Usr> usrList = usrRepository.findAll();
        assertThat(usrList).hasSize(databaseSizeBeforeCreate + 1);
        Usr testUsr = usrList.get(usrList.size() - 1);
        assertThat(testUsr.getUserID()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testUsr.getPassword()).isEqualTo(DEFAULT_PASSWORD);
    }

    @Test
    @Transactional
    public void createUsrWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = usrRepository.findAll().size();

        // Create the Usr with an existing ID
        usr.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUsrMockMvc.perform(post("/api/usrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usr)))
            .andExpect(status().isBadRequest());

        // Validate the Usr in the database
        List<Usr> usrList = usrRepository.findAll();
        assertThat(usrList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUserIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = usrRepository.findAll().size();
        // set the field null
        usr.setUserID(null);

        // Create the Usr, which fails.

        restUsrMockMvc.perform(post("/api/usrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usr)))
            .andExpect(status().isBadRequest());

        List<Usr> usrList = usrRepository.findAll();
        assertThat(usrList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPasswordIsRequired() throws Exception {
        int databaseSizeBeforeTest = usrRepository.findAll().size();
        // set the field null
        usr.setPassword(null);

        // Create the Usr, which fails.

        restUsrMockMvc.perform(post("/api/usrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usr)))
            .andExpect(status().isBadRequest());

        List<Usr> usrList = usrRepository.findAll();
        assertThat(usrList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUsrs() throws Exception {
        // Initialize the database
        usrRepository.saveAndFlush(usr);

        // Get all the usrList
        restUsrMockMvc.perform(get("/api/usrs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(usr.getId().intValue())))
            .andExpect(jsonPath("$.[*].userID").value(hasItem(DEFAULT_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())));
    }
    

    @Test
    @Transactional
    public void getUsr() throws Exception {
        // Initialize the database
        usrRepository.saveAndFlush(usr);

        // Get the usr
        restUsrMockMvc.perform(get("/api/usrs/{id}", usr.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(usr.getId().intValue()))
            .andExpect(jsonPath("$.userID").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingUsr() throws Exception {
        // Get the usr
        restUsrMockMvc.perform(get("/api/usrs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUsr() throws Exception {
        // Initialize the database
        usrRepository.saveAndFlush(usr);

        int databaseSizeBeforeUpdate = usrRepository.findAll().size();

        // Update the usr
        Usr updatedUsr = usrRepository.findById(usr.getId()).get();
        // Disconnect from session so that the updates on updatedUsr are not directly saved in db
        em.detach(updatedUsr);
        updatedUsr
            .userID(UPDATED_USER_ID)
            .password(UPDATED_PASSWORD);

        restUsrMockMvc.perform(put("/api/usrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUsr)))
            .andExpect(status().isOk());

        // Validate the Usr in the database
        List<Usr> usrList = usrRepository.findAll();
        assertThat(usrList).hasSize(databaseSizeBeforeUpdate);
        Usr testUsr = usrList.get(usrList.size() - 1);
        assertThat(testUsr.getUserID()).isEqualTo(UPDATED_USER_ID);
        assertThat(testUsr.getPassword()).isEqualTo(UPDATED_PASSWORD);
    }

    @Test
    @Transactional
    public void updateNonExistingUsr() throws Exception {
        int databaseSizeBeforeUpdate = usrRepository.findAll().size();

        // Create the Usr

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUsrMockMvc.perform(put("/api/usrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usr)))
            .andExpect(status().isBadRequest());

        // Validate the Usr in the database
        List<Usr> usrList = usrRepository.findAll();
        assertThat(usrList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUsr() throws Exception {
        // Initialize the database
        usrRepository.saveAndFlush(usr);

        int databaseSizeBeforeDelete = usrRepository.findAll().size();

        // Get the usr
        restUsrMockMvc.perform(delete("/api/usrs/{id}", usr.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Usr> usrList = usrRepository.findAll();
        assertThat(usrList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Usr.class);
        Usr usr1 = new Usr();
        usr1.setId(1L);
        Usr usr2 = new Usr();
        usr2.setId(usr1.getId());
        assertThat(usr1).isEqualTo(usr2);
        usr2.setId(2L);
        assertThat(usr1).isNotEqualTo(usr2);
        usr1.setId(null);
        assertThat(usr1).isNotEqualTo(usr2);
    }
}
