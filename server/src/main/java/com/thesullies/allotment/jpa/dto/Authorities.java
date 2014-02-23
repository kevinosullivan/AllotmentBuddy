package com.thesullies.allotment.jpa.dto;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

@Entity
@Table(name = "Authorities", uniqueConstraints = {  @UniqueConstraint(columnNames = { "username", "authority" })  })
public class Authorities implements GrantedAuthority, Serializable {
   // ~ Instance fields
   // ================================================================================================

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "idAuthority", nullable = false)
   private Integer idAuthority;

   @Column(name = "username", nullable = false)
   private String username;

   @Column(name = "authority", nullable = false)
   private String authority;
   
   @ManyToMany(mappedBy = "authoritySet", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
   private Set<User> userSet;

   // ~ Constructors
   // ===================================================================================================
   public Authorities(String username, String authority) {
      Assert.hasText(authority, "A granted authority textual representation is required");
      this.username = username;
      this.authority = authority;
   }

   public Authorities() {
   }

   @Override
   public String getAuthority() {
      return authority;
   }
}
