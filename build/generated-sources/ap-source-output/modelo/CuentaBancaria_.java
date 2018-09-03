package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.enums.EstadoCuenta;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CuentaBancaria.class)
public abstract class CuentaBancaria_ {

	public static volatile SingularAttribute<CuentaBancaria, Date> fechaCierre;
	public static volatile SingularAttribute<CuentaBancaria, EstadoCuenta> estado;
	public static volatile SingularAttribute<CuentaBancaria, Date> fechaModificacion;
	public static volatile SingularAttribute<CuentaBancaria, Persona> persona;
	public static volatile SingularAttribute<CuentaBancaria, Integer> nroCuenta;
	public static volatile SingularAttribute<CuentaBancaria, Date> fechaApertura;
	public static volatile ListAttribute<CuentaBancaria, Transaccion> listaTransaccion;
	public static volatile SingularAttribute<CuentaBancaria, Long> id;
	public static volatile SingularAttribute<CuentaBancaria, Double> saldo;

}

