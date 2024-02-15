package it.fides.val_training_spring.models.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "utente")
@JsonIgnoreProperties({"password", "passwordUtente", "flgCancellatoUtente", "enabled", "accountNonLocked", "credentialsNonExpired", "accountNonExpired"})
public class UtenteEntity implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_utente")
	private Long idUtente;
	
	@Column(name = "nome", nullable=false, length = 32)
	private String nomeUtente;
	
	@Column(name = "cognome", nullable=false, length = 32)
	private String cognomeUtente;
	
	@Column(name = "email", nullable=false, unique = true, length = 100)
	private String emailUtente;
	
	@Column(name = "password", nullable=false, length = 100)
	private String passwordUtente;
	
	@Column(name = "informazioni_generali", nullable=false, length = 500)
	private String informazioniGeneraliUtente;
	
	@Column(name = "data_creazione", nullable=false)
	private LocalDateTime dataCreazioneUtente;
	
	@Column(name = "data_modifica", nullable=false)
	private LocalDateTime dataModificaUtente;
	
	@Column(name = "flg_cancellato", nullable=false)
	private boolean flgCancellatoUtente;
	
    @ManyToOne
    @JoinColumn(name = "id_ruolo")
    private RuoloEntity ruolo;
    
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "utente_gruppo",
            joinColumns = @JoinColumn(name = "id_utente"),
            inverseJoinColumns = @JoinColumn(name = "id_gruppo"))
    private List<GruppoEntity> gruppi;
    
    public UtenteEntity() {}

    public Long getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}
	public String getNomeUtente() {
		return nomeUtente;
	}
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
	public String getCognomeUtente() {
		return cognomeUtente;
	}
	public void setCognomeUtente(String cognomeUtente) {
		this.cognomeUtente = cognomeUtente;
	}
	public String getEmailUtente() {
		return emailUtente;
	}
	public void setEmailUtente(String emailUtente) {
		this.emailUtente = emailUtente;
	}
	public String getPasswordUtente() {
		return passwordUtente;
	}
	public void setPasswordUtente(String passwordUtente) {
		this.passwordUtente = passwordUtente;
	}
	public String getInformazioniGeneraliUtente() {
		return informazioniGeneraliUtente;
	}
	public void setInformazioniGeneraliUtente(String informazioniGeneraliUtente) {
		this.informazioniGeneraliUtente = informazioniGeneraliUtente;
	}
	public LocalDateTime getDataCreazioneUtente() {
		return dataCreazioneUtente;
	}
	public void setDataCreazioneUtente(LocalDateTime dataCreazioneUtente) {
		this.dataCreazioneUtente = dataCreazioneUtente;
	}
	public LocalDateTime getDataModificaUtente() {
		return dataModificaUtente;
	}
	public void setDataModificaUtente(LocalDateTime dataModificaUtente) {
		this.dataModificaUtente = dataModificaUtente;
	}
	public boolean isFlgCancellatoUtente() {
		return flgCancellatoUtente;
	}
	public void setFlgCancellatoUtente(boolean flgCancellatoUtente) {
		this.flgCancellatoUtente = flgCancellatoUtente;
	}
	public RuoloEntity getRuolo() {
		return ruolo;
	}
	public void setRuolo(RuoloEntity ruolo) {
		this.ruolo = ruolo;
	}
	public List<GruppoEntity> getGruppi() {
		return gruppi;
	}
	public void setGruppi(List<GruppoEntity> gruppi) {
		this.gruppi = gruppi;
	}

	@Override
	public String toString() {
			return "UtenteEntity [idUtente=" + idUtente + ", nomeUtente=" + nomeUtente + ", cognomeUtente=" + cognomeUtente
					+ ", emailUtente=" + emailUtente + ", passwordUtente=" + passwordUtente
					+ ", informazioniGeneraliUtente=" + informazioniGeneraliUtente + ", dataCreazioneUtente="
					+ dataCreazioneUtente + ", dataModificaUtente=" + dataModificaUtente + ", flgCancellatoUtente="
					+ flgCancellatoUtente + ", ruolo=" + ruolo + ", gruppi: " + gruppi + "]";
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
       if (this.ruolo != null) {
           authorities.add(new SimpleGrantedAuthority(this.getRuolo().getNomeRuolo()));
       }
        return authorities;
   }

	@Override
	public String getPassword() {
		return passwordUtente;
	}

	@Override
	public String getUsername() {
		return emailUtente;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
       if (this.ruolo != null) {
           authorities.add(new SimpleGrantedAuthority(this.getRuolo().getNomeRuolo()));
       }
        return authorities;
   }

	@Override
	public String getPassword() {
		return passwordUtente;
	}

	@Override
	public String getUsername() {
		return emailUtente;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
