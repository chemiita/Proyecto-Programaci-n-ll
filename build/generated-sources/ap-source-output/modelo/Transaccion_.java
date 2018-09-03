package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Transaccion.class)
public abstract class Transaccion_ {

	public static volatile SingularAttribute<Transaccion, CuentaBancaria> cuentaBancaria;
	public static volatile SingularAttribute<Transaccion, Date> lugarFecha;
	public static volatile SingularAttribute<Transaccion, Movimientos> movimiento;
	public static volatile SingularAttribute<Transaccion, Integer> nroCuenta;
	public static volatile SingularAttribute<Transaccion, Double> lugar;
	public static volatile SingularAttribute<Transaccion, Double> valor;
	public static volatile SingularAttribute<Transaccion, Long> id;

}

