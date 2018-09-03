package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.enums.AccionMovimiento;
import modelo.enums.TipoMovimiento;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Movimientos.class)
public abstract class Movimientos_ {

	public static volatile SingularAttribute<Movimientos, AccionMovimiento> accion;
	public static volatile SingularAttribute<Movimientos, TipoMovimiento> tipo;
	public static volatile SingularAttribute<Movimientos, Transaccion> transaccion;
	public static volatile SingularAttribute<Movimientos, String> siglas;
	public static volatile SingularAttribute<Movimientos, Long> id;

}

