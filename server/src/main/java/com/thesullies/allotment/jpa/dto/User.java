package com.thesullies.allotment.jpa.dto;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Object representing the crew who are authorised to log into the web app.
 * 
 * <p>
 * Notes:
 * <ul>
 * <li>Authentication is done with username when logging into the admin console
 * <li>The username is the user's email
 * </ul>
 * </p>
 * 
 */
@XmlRootElement
@Entity
@Table(name = "Users")
public class User implements UserDetails {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "idUser")
   private Integer idUser;
   /**
    * Core values required for UserDetails
    */
   @Column(name = "userName", length = 60, nullable = false)
   private String userName;
   @Column(name = "password", length = 90, nullable = false)
   private String password;
   @Column(name = "accountNonExpired", nullable = false)
   private Boolean accountNonExpired;
   @Column(name = "accountNonLocked", nullable = false)
   private Boolean accountNonLocked;
   @Column(name = "credentialsNonExpired", nullable = false)
   private Boolean credentialsNonExpired;
   @Column(name = "enabled", nullable = false)
   private Boolean enabled;

   @JoinTable(name = "user_authority", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "idUser") }, inverseJoinColumns = { @JoinColumn(name = "authority_id", referencedColumnName = "idAuthority") })
   @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   private Set<Authorities> authoritySet;

   /**
    * User Values for AtiBeacon
    */
   @Column(name = "firstName", length = 30, nullable = false)
   private String firstName;
   @Column(name = "lastName", length = 30, nullable = false)
   private String lastName;
   @Column(name = "phone", length = 30, nullable = false)
   private String phone;
   /**
    * Stores when this user last logged in (to either the admin console, or to
    * the app)
    */
   @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
   @Column(name = "lastLogin", nullable = true)
   private DateTime lastLogin;

   @Override
   public String toString() {
      return String
            .format("userName: %s, lastLogin %s", userName,  lastLogin);
   }

   @Override
   public Collection<Authorities> getAuthorities() {
      return authoritySet;
   }

   public Set<Authorities> getAuthoritySet() {
      return authoritySet;
   }

   public void setAuthoritySet(Set<Authorities> authoritySet) {
      this.authoritySet = authoritySet;
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return userName;
   }

   @Override
   public boolean isAccountNonExpired() {
      return accountNonExpired;
   }

   @Override
   public boolean isAccountNonLocked() {
      return accountNonLocked;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return credentialsNonExpired;
   }

   @Override
   public boolean isEnabled() {
      return enabled;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public Boolean getAccountNonExpired() {
      return accountNonExpired;
   }

   public void setAccountNonExpired(Boolean accountNonExpired) {
      this.accountNonExpired = accountNonExpired;
   }

   public Boolean getAccountNonLocked() {
      return accountNonLocked;
   }

   public void setAccountNonLocked(Boolean accountNonLocked) {
      this.accountNonLocked = accountNonLocked;
   }

   public Boolean getCredentialsNonExpired() {
      return credentialsNonExpired;
   }

   public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
      this.credentialsNonExpired = credentialsNonExpired;
   }

   public Boolean getEnabled() {
      return enabled;
   }

   public void setEnabled(Boolean enabled) {
      this.enabled = enabled;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   // @XmlJavaTypeAdapter(DateTimeAdapter.class)
   @XmlTransient
   public DateTime getLastLogin() {
      return lastLogin;
   }

   public void setLastLogin(DateTime lastLogin) {
      this.lastLogin = lastLogin;
   }

}