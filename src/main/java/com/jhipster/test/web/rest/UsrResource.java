package com.jhipster.test.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jhipster.test.domain.Usr;
import com.jhipster.test.repository.UsrRepository;
import com.jhipster.test.web.rest.errors.BadRequestAlertException;
import com.jhipster.test.web.rest.util.HeaderUtil;
import com.jhipster.test.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Usr.
 */
@RestController
@RequestMapping("/api")
public class UsrResource {

    private final Logger log = LoggerFactory.getLogger(UsrResource.class);

    private static final String ENTITY_NAME = "usr";

    private final UsrRepository usrRepository;

    public UsrResource(UsrRepository usrRepository) {
        this.usrRepository = usrRepository;
    }

    /**
     * POST  /usrs : Create a new usr.
     *
     * @param usr the usr to create
     * @return the ResponseEntity with status 201 (Created) and with body the new usr, or with status 400 (Bad Request) if the usr has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/usrs")
    @Timed
    public ResponseEntity<Usr> createUsr(@Valid @RequestBody Usr usr) throws URISyntaxException {
        log.debug("REST request to save Usr : {}", usr);
        if (usr.getId() != null) {
            throw new BadRequestAlertException("A new usr cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Usr result = usrRepository.save(usr);
        return ResponseEntity.created(new URI("/api/usrs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /usrs : Updates an existing usr.
     *
     * @param usr the usr to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated usr,
     * or with status 400 (Bad Request) if the usr is not valid,
     * or with status 500 (Internal Server Error) if the usr couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/usrs")
    @Timed
    public ResponseEntity<Usr> updateUsr(@Valid @RequestBody Usr usr) throws URISyntaxException {
        log.debug("REST request to update Usr : {}", usr);
        if (usr.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Usr result = usrRepository.save(usr);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, usr.getId().toString()))
            .body(result);
    }

    /**
     * GET  /usrs : get all the usrs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of usrs in body
     */
    @GetMapping("/usrs")
    @Timed
    public ResponseEntity<List<Usr>> getAllUsrs(Pageable pageable) {
        log.debug("REST request to get a page of Usrs");
        Page<Usr> page = usrRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/usrs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /usrs/:id : get the "id" usr.
     *
     * @param id the id of the usr to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the usr, or with status 404 (Not Found)
     */
    @GetMapping("/usrs/{id}")
    @Timed
    public ResponseEntity<Usr> getUsr(@PathVariable Long id) {
        log.debug("REST request to get Usr : {}", id);
        Optional<Usr> usr = usrRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(usr);
    }

    /**
     * DELETE  /usrs/:id : delete the "id" usr.
     *
     * @param id the id of the usr to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/usrs/{id}")
    @Timed
    public ResponseEntity<Void> deleteUsr(@PathVariable Long id) {
        log.debug("REST request to delete Usr : {}", id);

        usrRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
