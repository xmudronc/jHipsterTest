package com.jhipster.test.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Usr.
 */
@Entity
@Table(name = "usr")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Usr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private String userID;

    @NotNull
    @Column(name = "jhi_password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "usr")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Product> usrs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public Usr userID(String userID) {
        this.userID = userID;
        return this;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public Usr password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Product> getUsrs() {
        return usrs;
    }

    public Usr usrs(Set<Product> products) {
        this.usrs = products;
        return this;
    }

    public Usr addUsr(Product product) {
        this.usrs.add(product);
        product.setUsr(this);
        return this;
    }

    public Usr removeUsr(Product product) {
        this.usrs.remove(product);
        product.setUsr(null);
        return this;
    }

    public void setUsrs(Set<Product> products) {
        this.usrs = products;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Usr usr = (Usr) o;
        if (usr.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), usr.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Usr{" +
            "id=" + getId() +
            ", userID='" + getUserID() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }
}
