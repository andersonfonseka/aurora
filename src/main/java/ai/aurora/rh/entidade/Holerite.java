package ai.aurora.rh.entidade;

public class Holerite {
	
	private String mes;
	
	private double salario;
	
	public Holerite(String mes, double salario) {
		super();
		this.mes = mes;
		this.salario = salario;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Holerite [mes=" + mes + ", salario=" + salario + "]\n";
	}
	
	
}
