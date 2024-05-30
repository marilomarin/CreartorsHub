package modelo;

/**
 * La clase Creador representa a un tipo de usuario específico de la aplicación.
 * Los Creadores ofrecen sus servicios y pueden apicar a Proyectos dentro de la
 * aplicación. Su perfil está asociado a una categoría, portfolio y nivel
 * determinados.
 * 
 * @author: Mariló Marín
 * @version: 2024 V1.0
 */

public class Creador extends Usuario {

	private String categoria;
	private String portfolio;
	private String nivel;

	// Constructores

	public Creador() {
	}
}