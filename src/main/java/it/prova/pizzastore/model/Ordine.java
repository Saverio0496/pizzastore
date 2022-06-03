package it.prova.pizzastore.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "ordine")
public class Ordine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "data")
	private Date data;
	@Column(name = "closed")
	private boolean closed;
	@Column(name = "codice")
	private String codice;
	@Column(name = "costoTotaleOrdine")
	private Integer costoTotaleOrdine;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utente_id", nullable = false)
	private Utente utente;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "ordine_pizza", joinColumns = @JoinColumn(name = "ordine_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "ID"))
	private Set<Pizza> pizze = new HashSet<Pizza>();

	@CreationTimestamp
	private LocalDateTime createDateTime;
	@UpdateTimestamp
	private LocalDateTime updateDateTime;

	public Ordine() {
	}

	public Ordine(Date data, boolean closed, String codice, Integer costoTotaleOrdine, Cliente cliente, Utente utente,
			Set<Pizza> pizze) {
		this.data = data;
		this.closed = closed;
		this.codice = codice;
		this.costoTotaleOrdine = costoTotaleOrdine;
		this.cliente = cliente;
		this.utente = utente;
		this.pizze = pizze;
	}

	public Ordine(Date data, boolean closed, String codice, Integer costoTotaleOrdine, Cliente cliente, Utente utente) {
		this.data = data;
		this.closed = closed;
		this.codice = codice;
		this.costoTotaleOrdine = costoTotaleOrdine;
		this.cliente = cliente;
		this.utente = utente;
	}

	public Ordine(Date data, boolean closed, String codice, Integer costoTotaleOrdine, Cliente cliente) {
		this.data = data;
		this.closed = closed;
		this.codice = codice;
		this.costoTotaleOrdine = costoTotaleOrdine;
		this.cliente = cliente;
	}

	public Ordine(Date data, boolean closed, String codice, Integer costoTotaleOrdine) {
		this.data = data;
		this.closed = closed;
		this.codice = codice;
		this.costoTotaleOrdine = costoTotaleOrdine;
	}

	public Ordine(String codice, Integer costoTotaleOrdine) {
		this.codice = codice;
		this.costoTotaleOrdine = costoTotaleOrdine;
	}

	public Ordine(String codice) {
		this.codice = codice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Integer getCostoTotaleOrdine() {
		return costoTotaleOrdine;
	}

	public void setCostoTotaleOrdine(Integer costoTotaleOrdine) {
		this.costoTotaleOrdine = costoTotaleOrdine;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Set<Pizza> getPizze() {
		return pizze;
	}

	public void setPizze(Set<Pizza> pizze) {
		this.pizze = pizze;
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
