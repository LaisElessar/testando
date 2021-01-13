package Model.Bean;

import java.util.Calendar;

public class Projeto {
	
	private Integer codigo; //integer
	private String nome; //text
	private Calendar dataInicio; //date
	private Calendar dataFinal; //date
	private Double valor; //float
	private Integer risco; //integer
	private String participantes; //text
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Calendar getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Calendar getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Calendar dataFinal) {
		this.dataFinal = dataFinal;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Integer getRisco() {
		return risco;
	}
	public void setRisco(Integer risco) {
		this.risco = risco;
	}
	public String getParticipantes() {
		return participantes;
	}
	public void setParticipantes(String participantes) {
		this.participantes = participantes;
	}
}
