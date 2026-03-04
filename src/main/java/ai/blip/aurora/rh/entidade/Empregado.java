package ai.blip.aurora.rh.entidade;

public class Empregado {

	private String cpf;
	
	private String nome;
	
	private double salario;
	
	private String dataAdmissao;

	public Empregado(String cpf, String nome, double salario) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.salario = salario;
	}
	
	public Empregado(String cpf, String nome, double salario, String dataAdmissao) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.salario = salario;
		this.dataAdmissao = dataAdmissao;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public double getSalario() {
		return salario;
	}
	
	public String getDataAdmissao() {
		return dataAdmissao;
	}

	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", nome=" + nome + ", salario=" + salario + "]";
	}
	
}
