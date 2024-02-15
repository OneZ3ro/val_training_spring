package it.fides.val_training_spring.exceptions;


public class NotFoundException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(long id) {
        super("L'elemento con id: " + id + " non è stato trovato. Riprovare con un id diverso");
    }

    public NotFoundException(String elem) {
        super("L'elemento: " + elem + " non è stato trovato. Riprovare");
    }


}
