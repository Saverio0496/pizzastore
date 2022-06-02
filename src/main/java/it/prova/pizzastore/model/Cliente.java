package it.prova.pizzastore.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "indirizzo")
	private String indirizzo;
	@Column(name = "attivo")
	private boolean attivo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	private Set<Ordine> ordini = new HashSet<Ordine>(0);

	@CreationTimestamp
	private LocalDateTime createDateTime;
	@UpdateTimestamp
	private LocalDateTime updateDateTime;

	public Cliente() {
	}

	public Cliente(String nome, String cognome, String indirizzo, boolean attivo, Set<Ordine> ordini) {
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.attivo = attivo;
		this.ordini = ordini;
	}

	public Cliente(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
	}

	public Cliente(String nome, String cognome, String indirizzo) {
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
	}

	public Cliente(String nome, String cognome, String indirizzo, boolean attivo) {
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.attivo = attivo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public boolean isAttivo() {
		return attivo;
	}

	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}

	public Set<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(Set<Ordine> ordini) {
		this.ordini = ordini;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

}
