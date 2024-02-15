package it.fides.val_training_spring.models.dto;

public class Person {
    private String nome;
    private String cognome;

    public Person() {}

    public Person(String nome, String cognome) {
        //super();
        this.nome = nome;
        this.cognome = cognome;
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

	@Override
	public String toString() {
		return "Person [nome=" + nome + ", cognome=" + cognome + "]";
	}
    
}
