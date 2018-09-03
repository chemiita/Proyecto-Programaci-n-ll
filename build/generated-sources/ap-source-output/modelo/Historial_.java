package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Historial.class)
public abstract class Historial_ {

	public static volatile SingularAttribute<Historial, String> accion;
	public static volatile SingularAttribute<Historial, String> descripcion;
	public static volatile SingularAttribute<Historial, String> tipo;
	public static volatile SingularAttribute<Historial, Date> fechaHora;
	public static volatile SingularAttribute<Historial, String> usuario;
	public static volatile SingularAttribute<Historial, Long> id;

}

