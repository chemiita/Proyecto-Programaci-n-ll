/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import controlador.servicio.CuentaBancariaServicio;
import controlador.servicio.CuentaServicio;
import controlador.servicio.PersonaServicio;
import controlador.servicio.RolServicio;
import controlador.utilidades.Sesion;
import controlador.utilidades.Utilidades;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import javax.swing.JOptionPane;
import modelo.enums.EstadoCuenta;
import vista.tabla.ModeloTablaPersona;
import vista.utilidades.UtilidadesComponente;
import vista.utilidades.Utilitario;

/**
 *
 * @author Usuario
 */
public class FrmServicioRegistros extends javax.swing.JPanel {

    private Utilitario u = new Utilitario();
    private PersonaServicio ps = new PersonaServicio();
    private ModeloTablaPersona modelo = new ModeloTablaPersona();
    private CuentaServicio c = new CuentaServicio();
    private CuentaBancariaServicio cb = new CuentaBancariaServicio();

    /**
     * Creates new form NewJPanel
     */
    public FrmServicioRegistros() {
        initComponents();
        validar();
        cargarTabla();
        limpiar();
        pnlDatosCuenta.setVisible(false);

    }

    private void cargarVista() {
        int fila = tblVerListado.getSelectedRow();
        if (fila >= 0) {
            ps.fijarPersona(modelo.getLista().get(fila));
            txtApellido.setText(ps.getPersona().getApellido());
            txtCedula.setText(ps.getPersona().getCedula());
            txtCedula.setEditable(false);
            txtDireccion.setText(ps.getPersona().getDireccion());
            txtEmail.setText(ps.getPersona().getCorreo());
            txtNacionalidad.setText(ps.getPersona().getNacionalidad());
            txtNombre.setText(ps.getPersona().getNombre());
            txtTelefono.setText(ps.getPersona().getTelefono());

            pnlDatosCuenta.setVisible(true);
            txtUsuario.setText(ps.getPersona().getCuenta().getUsuario());
            txtUsuario.setEditable(false);
            txtClave.setText(ps.getPersona().getCuenta().getClave());
            txtClave.setEditable(false);
            txtCuentaBancaria.setText(ps.getPersona().getCuentaBancaria().getNroCuenta().toString());
            txtCuentaBancaria.setEditable(false);

            txtUsuario.setText(ps.getPersona().getCuenta().getUsuario());
            txtUsuario.setEditable(false);
            txtClave.setText(ps.getPersona().getCuenta().getClave());
            txtClave.setEditable(false);
            //txtCuentaBancaria.setText(ps.getPersona().getCuenta().getClave());
            txtCuentaBancaria.setEditable(false);
            txtNacionalidad.setEditable(false);
            cedula.setVisible(false);
        }
        pnlDatosCuenta.setVisible(true);
    }

    public void validar() {
        Utilidades.valiarSoloLetras(txtApellido);
        Utilidades.valiarSoloLetras(txtNombre);
        Utilidades.valiarSoloLetras(txtNacionalidad);
        //Utilidades.valiarSoloLetras(txtDireccion);
        Utilidades.valiarSoloNumeros(txtCedula);
        Utilidades.valiarSoloNumeros(txtTelefono);
        Utilidades.LimitarCaracterers(txtUsuario,10);
        Utilidades.LimitarCaracterers(txtClave, 10);
        Utilidades.LimitarCaracterers(txtTelefono, 12);
        Utilidades.LimitarCaracterers(txtCedula, 10);
    }

    private void cargarTabla() {
        modelo.setLista(ps.ListarACliente());
        tblVerListado.setModel(modelo);
        tblVerListado.updateUI();
    }

    private void buscar() {
        cargarTabla();
    }

    private void buscarTexto() {
        if (txtBuscar.getText().trim().length() >= 3) {
            String tipo = "Cliente";
            modelo.setLista(ps.listarSinAdministradorTipoLikeCliente(tipo, txtBuscar.getText()));

            tblVerListado.setModel(modelo);
            tblVerListado.updateUI();
        } else {
            buscar();
        }
    }

    private Integer numeroCuenta() {
        Random rnd = new Random();
        String cod = "5270";
        cod += (ps.listado().size() + 10);
        //   cod += (int)99*new Random().nextInt(100)+11;
        cod += (int) 10 * new Random().nextInt(10) + 100;
//        cod += (int)10000000*new Random().nextInt(1000);
//        //JOptionPane.showMessageDialog(null, cod);
        Long as = Long.parseLong(cod);
        Integer a = as.intValue();

        return a;
    }

    public void limpiar() {
        txtApellido.setText("");
        txtCedula.setText("");
        txtDireccion.setText("");
        txtEmail.setText("");
        //txtClave.setText(null);
        txtNacionalidad.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        //txtUsuario.setText(null);
        cargarTabla();
        ps.fijarPersona(null);
        c.fijarCuenta(null);
        cb.fijarCuentaBancaria(null);

        pnlDatosCuenta.setVisible(false);
        txtNacionalidad.setEditable(true);
        txtCedula.setEditable(true);
        cedula.setVisible(true);

    }

    public void cargarObjeto() {
        int cont = 1;
        ps.getPersona().setApellido(txtApellido.getText());
        ps.getPersona().setCedula(txtCedula.getText());
        ps.getPersona().setCorreo(txtEmail.getText());
        ps.getPersona().setDireccion(txtDireccion.getText());
        ps.getPersona().setExternal_id(UUID.randomUUID().toString());
        ps.getPersona().setNacionalidad(txtNacionalidad.getText());
        ps.getPersona().setTelefono(txtTelefono.getText());
        ps.getPersona().setNombre(txtNombre.getText());
        ps.getPersona().setRol(new RolServicio().buscarRolNombre("Cliente"));
        c.getCuenta().setEstado(true);
        if (ps.getPersona().getId() == null) {

            c.getCuenta().setClave(UUID.randomUUID().toString().toUpperCase().substring(0, 7));
            c.getCuenta().setUsuario(txtNombre.getText().substring(0, 3) + txtApellido.getText().substring(0, 3));

            c.getCuenta().setCreated_at(new Date());
            c.getCuenta().setExternal_id(UUID.randomUUID().toString());

            c.getCuenta().setPersona(ps.getPersona());
            ps.getPersona().setCuenta(c.getCuenta());
            cb.getCuentaBancaria().setFechaApertura(new Date());

            /////////////////
            cb.getCuentaBancaria().setNroCuenta(numeroCuenta());
            //////////////////////

            cb.getCuentaBancaria().setSaldo(30.00);
            cb.getCuentaBancaria().setPersona(ps.getPersona());
            ps.getPersona().setCuentaBancaria(cb.getCuentaBancaria());
        } else {
            c.getCuenta().setUpdate_at(new Date());
            cb.getCuentaBancaria().setFechaModificacion(new Date());
        }
        cb.getCuentaBancaria().setEstado(EstadoCuenta.ACTIVADO);
        pnlDatosCuenta.setVisible(true);
        txtUsuario.setText(ps.getPersona().getCuenta().getUsuario());
        txtUsuario.setEditable(false);
        txtClave.setText(ps.getPersona().getCuenta().getClave());
        txtClave.setEditable(false);
        txtCuentaBancaria.setText(ps.getPersona().getCuentaBancaria().getNroCuenta().toString());
        txtCuentaBancaria.setEditable(false);
        cont++;
    }

    public void guardar() {
        String mensaje = "Se reqiere este dato";
        if (!UtilidadesComponente.mostrarError(txtCedula, mensaje, 'r')
                && !UtilidadesComponente.mostrarError(txtNombre, mensaje, 'r')
                && !UtilidadesComponente.mostrarError(txtApellido, mensaje, 'r')
                && !UtilidadesComponente.mostrarError(txtDireccion, mensaje, 'r')
                && !UtilidadesComponente.mostrarError(txtTelefono, mensaje, 'r')) {
            if ((txtApellido.getText().length() > 3) && (txtNombre.getText().length() > 3)) {
                if ((txtCedula.getText().length() == 10)) {
                    if (Utilidades.esEmail(txtEmail.getText())) {
                        if (txtTelefono.getText().length() > 6) {
                            if (Utilidades.validadorDeCedula(txtCedula.getText())) {

                                cargarObjeto();
                                if (ps.getPersona().getId() != null) {
                                    //Modificar
                                    if (ps.guardar()) {
                                        JOptionPane.showMessageDialog(this, "Se ha modificado correctamente");
                                        Utilidades.guardarHistoriAL("Se modifico una cuenta tipo " + "Cliente", "Actor :"
                                                + Sesion.getCuenta().getPersona().nom() + " registro el usuario :" + txtApellido.getText() + " " + txtNombre.getText(), "registro");

                                        limpiar();
                                        pnlDatosCuenta.setVisible(false);
                                        cargarTabla();

                                    } else {
                                        JOptionPane.showMessageDialog(this, "No se pudo modificar");
                                    }
                                } else {
                                    //Guardar

                                    if (ps.getPersonaCedula(txtCedula.getText()) != null) {
                                        JOptionPane.showMessageDialog(this, "Cedula ya registrada");

                                    } else {
                                        //guardar 
                                        if (ps.guardar()) {
                                            JOptionPane.showMessageDialog(this, "Se ha guardado correctamente");
                                            Utilidades.guardarHistoriAL("Se registro una cuenta tipo " + "Cliente", "Actor :"
                                                    + Sesion.getCuenta().getPersona().nom() + " registro el usuario :" + txtApellido.getText() + " " + txtNombre.getText(), "registro");

                                            limpiar();
                                            pnlDatosCuenta.setVisible(true);
                                        } else {
                                            JOptionPane.showMessageDialog(this, "No se pudo guardar");
                                        }
                                    }
                                }
                            } else {
                                UtilidadesComponente.mensajeError("Atencion", "Cedula no valida");
                            }
                        } else {
                            UtilidadesComponente.mensajeError("Atencion", "Su telefono es muy corto");
                        }
                    } else {
                        UtilidadesComponente.mensajeError("Atencion", "Correo no valida");
                    }
                } else {
                    UtilidadesComponente.mensajeError("Atencion", "Cedula no valida");;
                }
            } else {
                UtilidadesComponente.mensajeError("Atencion", "Su Nombre o Apellidos son muy cortos");
            }

        } else {
            UtilidadesComponente.mensajeError("Atencion", "Se requiere completar todos los campos\n"
                    + "para poder registrar personal");
        }
        cargarTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlAsignar = new javax.swing.JPanel();
        labelMetric2 = new org.edisoncor.gui.label.LabelMetric();
        btnBuscar = new rojeru_san.RSButton();
        txtBuscar = new rojeru_san.RSMTextFull();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVerListado = new javax.swing.JTable();
        rSPanelsSlider1 = new rojeru_san.RSPanelsSlider();
        txtNombre = new rojeru_san.RSMTextFull();
        txtDireccion = new rojeru_san.RSMTextFull();
        txtEmail = new rojeru_san.RSMTextFull();
        txtTelefono = new rojeru_san.RSMTextFull();
        jLabel6 = new javax.swing.JLabel();
        pnlDatosCuenta = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnAceptar = new rojeru_san.RSButton();
        jSeparator6 = new javax.swing.JSeparator();
        txtUsuario = new javax.swing.JTextField();
        txtClave = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        txtCuentaBancaria = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        txtNacionalidad = new rojeru_san.RSMTextFull();
        jSeparator9 = new javax.swing.JSeparator();
        cedula = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        txtApellido = new rojeru_san.RSMTextFull();
        jPanel2 = new javax.swing.JPanel();
        btnDarBaja = new rojeru_san.RSButton();
        btnCancelar = new rojeru_san.RSButton();
        btnGuardar = new rojeru_san.RSButton();

        pnlAsignar.setBackground(new java.awt.Color(36, 47, 65));

        labelMetric2.setBackground(new java.awt.Color(40, 153, 255));
        labelMetric2.setText("Listado Clientes");
        labelMetric2.setColorDeSombra(new java.awt.Color(40, 52, 71));
        labelMetric2.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N

        btnBuscar.setBackground(new java.awt.Color(82, 173, 160));
        btnBuscar.setText("Buscar");
        btnBuscar.setColorHover(new java.awt.Color(223, 247, 244));
        btnBuscar.setColorText(new java.awt.Color(51, 44, 54));
        btnBuscar.setColorTextHover(new java.awt.Color(10, 125, 108));
        btnBuscar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtBuscar.setBackground(new java.awt.Color(40, 52, 71));
        txtBuscar.setForeground(new java.awt.Color(255, 255, 255));
        txtBuscar.setBordeColorFocus(new java.awt.Color(97, 212, 195));
        txtBuscar.setBotonColor(new java.awt.Color(97, 212, 195));
        txtBuscar.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        txtBuscar.setPlaceholder("Apellido/Cedula");
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(97, 212, 195));

        tblVerListado.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblVerListado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblVerListado.setForeground(new java.awt.Color(36, 47, 65));
        tblVerListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido", "Nacionalidad", "Cedula/Pasaporte", "Dirección", "Email", "Telefono"
            }
        ));
        tblVerListado.setToolTipText("");
        tblVerListado.setSelectionBackground(new java.awt.Color(223, 247, 244));
        tblVerListado.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblVerListado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVerListadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVerListado);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        rSPanelsSlider1.setBackground(new java.awt.Color(36, 47, 65));
        rSPanelsSlider1.setLayout(null);

        txtNombre.setBackground(new java.awt.Color(36, 47, 65));
        txtNombre.setForeground(new java.awt.Color(255, 255, 255));
        txtNombre.setBordeColorFocus(new java.awt.Color(97, 212, 195));
        txtNombre.setBotonColor(new java.awt.Color(97, 212, 195));
        txtNombre.setPlaceholder("Nombres");
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
        });
        rSPanelsSlider1.add(txtNombre);
        txtNombre.setBounds(10, 30, 180, 42);

        txtDireccion.setBackground(new java.awt.Color(36, 47, 65));
        txtDireccion.setForeground(new java.awt.Color(255, 255, 255));
        txtDireccion.setBordeColorFocus(new java.awt.Color(97, 212, 195));
        txtDireccion.setBotonColor(new java.awt.Color(97, 212, 195));
        txtDireccion.setPlaceholder("Dirección");
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDireccionKeyPressed(evt);
            }
        });
        rSPanelsSlider1.add(txtDireccion);
        txtDireccion.setBounds(210, 0, 190, 42);

        txtEmail.setBackground(new java.awt.Color(36, 47, 65));
        txtEmail.setForeground(new java.awt.Color(255, 255, 255));
        txtEmail.setBordeColorFocus(new java.awt.Color(97, 212, 195));
        txtEmail.setBotonColor(new java.awt.Color(97, 212, 195));
        txtEmail.setPlaceholder("E-mail");
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        rSPanelsSlider1.add(txtEmail);
        txtEmail.setBounds(210, 50, 190, 42);

        txtTelefono.setBackground(new java.awt.Color(36, 47, 65));
        txtTelefono.setForeground(new java.awt.Color(255, 255, 255));
        txtTelefono.setBordeColorFocus(new java.awt.Color(97, 212, 195));
        txtTelefono.setBotonColor(new java.awt.Color(97, 212, 195));
        txtTelefono.setPlaceholder("Telefono");
        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyPressed(evt);
            }
        });
        rSPanelsSlider1.add(txtTelefono);
        txtTelefono.setBounds(210, 100, 190, 42);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Registrar Personal");
        rSPanelsSlider1.add(jLabel6);
        jLabel6.setBounds(10, 0, 180, 40);

        pnlDatosCuenta.setBackground(new java.awt.Color(36, 47, 65));
        pnlDatosCuenta.setForeground(new java.awt.Color(36, 47, 65));
        pnlDatosCuenta.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Datos de cuenta");
        pnlDatosCuenta.add(jLabel2);
        jLabel2.setBounds(50, 0, 160, 30);

        btnAceptar.setBackground(new java.awt.Color(97, 212, 195));
        btnAceptar.setText("Aceptar");
        btnAceptar.setColorHover(new java.awt.Color(223, 247, 244));
        btnAceptar.setColorText(new java.awt.Color(51, 44, 54));
        btnAceptar.setColorTextHover(new java.awt.Color(10, 125, 108));
        btnAceptar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        pnlDatosCuenta.add(btnAceptar);
        btnAceptar.setBounds(30, 170, 200, 40);

        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));
        pnlDatosCuenta.add(jSeparator6);
        jSeparator6.setBounds(10, 60, 220, 10);

        txtUsuario.setBackground(new java.awt.Color(36, 47, 65));
        txtUsuario.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(255, 255, 255));
        txtUsuario.setText("0000000");
        txtUsuario.setBorder(null);
        txtUsuario.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        txtUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusGained(evt);
            }
        });
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        pnlDatosCuenta.add(txtUsuario);
        txtUsuario.setBounds(10, 30, 230, 30);

        txtClave.setBackground(new java.awt.Color(36, 47, 65));
        txtClave.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtClave.setForeground(new java.awt.Color(255, 255, 255));
        txtClave.setText("0000000");
        txtClave.setBorder(null);
        txtClave.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        txtClave.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtClaveFocusGained(evt);
            }
        });
        txtClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveActionPerformed(evt);
            }
        });
        pnlDatosCuenta.add(txtClave);
        txtClave.setBounds(10, 80, 230, 30);

        jSeparator7.setForeground(new java.awt.Color(255, 255, 255));
        pnlDatosCuenta.add(jSeparator7);
        jSeparator7.setBounds(10, 110, 220, 10);

        txtCuentaBancaria.setBackground(new java.awt.Color(36, 47, 65));
        txtCuentaBancaria.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCuentaBancaria.setForeground(new java.awt.Color(255, 255, 255));
        txtCuentaBancaria.setText("0000000");
        txtCuentaBancaria.setBorder(null);
        txtCuentaBancaria.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        txtCuentaBancaria.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCuentaBancariaFocusGained(evt);
            }
        });
        txtCuentaBancaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCuentaBancariaActionPerformed(evt);
            }
        });
        pnlDatosCuenta.add(txtCuentaBancaria);
        txtCuentaBancaria.setBounds(10, 130, 230, 30);

        jSeparator8.setForeground(new java.awt.Color(255, 255, 255));
        pnlDatosCuenta.add(jSeparator8);
        jSeparator8.setBounds(10, 160, 220, 10);

        rSPanelsSlider1.add(pnlDatosCuenta);
        pnlDatosCuenta.setBounds(420, 0, 260, 220);

        txtNacionalidad.setBackground(new java.awt.Color(36, 47, 65));
        txtNacionalidad.setForeground(new java.awt.Color(255, 255, 255));
        txtNacionalidad.setBordeColorFocus(new java.awt.Color(97, 212, 195));
        txtNacionalidad.setBotonColor(new java.awt.Color(97, 212, 195));
        txtNacionalidad.setPlaceholder("Nacionalidad");
        txtNacionalidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNacionalidadKeyPressed(evt);
            }
        });
        rSPanelsSlider1.add(txtNacionalidad);
        txtNacionalidad.setBounds(10, 130, 180, 42);

        jSeparator9.setBackground(new java.awt.Color(82, 173, 160));
        jSeparator9.setForeground(new java.awt.Color(82, 173, 160));
        rSPanelsSlider1.add(jSeparator9);
        jSeparator9.setBounds(10, 210, 180, 10);

        cedula.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cedula.setForeground(new java.awt.Color(109, 109, 109));
        cedula.setText("Cedula");
        rSPanelsSlider1.add(cedula);
        cedula.setBounds(20, 190, 70, 19);

        txtCedula.setBackground(new java.awt.Color(36, 47, 65));
        txtCedula.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtCedula.setForeground(new java.awt.Color(255, 255, 255));
        txtCedula.setBorder(null);
        txtCedula.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        txtCedula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCedulaFocusGained(evt);
            }
        });
        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCedulaKeyPressed(evt);
            }
        });
        rSPanelsSlider1.add(txtCedula);
        txtCedula.setBounds(10, 180, 180, 30);

        txtApellido.setBackground(new java.awt.Color(36, 47, 65));
        txtApellido.setForeground(new java.awt.Color(255, 255, 255));
        txtApellido.setBordeColorFocus(new java.awt.Color(97, 212, 195));
        txtApellido.setBotonColor(new java.awt.Color(97, 212, 195));
        txtApellido.setPlaceholder("Apellidos");
        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtApellidoKeyPressed(evt);
            }
        });
        rSPanelsSlider1.add(txtApellido);
        txtApellido.setBounds(10, 80, 180, 42);

        jPanel2.setBackground(new java.awt.Color(65, 135, 124));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        btnDarBaja.setBackground(new java.awt.Color(82, 173, 160));
        btnDarBaja.setText("Cambiar Estado");
        btnDarBaja.setColorHover(new java.awt.Color(223, 247, 244));
        btnDarBaja.setColorText(new java.awt.Color(51, 44, 54));
        btnDarBaja.setColorTextHover(new java.awt.Color(10, 125, 108));
        btnDarBaja.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnDarBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDarBajaActionPerformed(evt);
            }
        });
        jPanel2.add(btnDarBaja);

        btnCancelar.setBackground(new java.awt.Color(97, 212, 195));
        btnCancelar.setText("Limpiar");
        btnCancelar.setColorHover(new java.awt.Color(223, 247, 244));
        btnCancelar.setColorText(new java.awt.Color(51, 44, 54));
        btnCancelar.setColorTextHover(new java.awt.Color(10, 125, 108));
        btnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelar);

        btnGuardar.setBackground(new java.awt.Color(82, 173, 160));
        btnGuardar.setText("Guardar");
        btnGuardar.setColorHover(new java.awt.Color(223, 247, 244));
        btnGuardar.setColorText(new java.awt.Color(51, 44, 54));
        btnGuardar.setColorTextHover(new java.awt.Color(10, 125, 108));
        btnGuardar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar);

        javax.swing.GroupLayout pnlAsignarLayout = new javax.swing.GroupLayout(pnlAsignar);
        pnlAsignar.setLayout(pnlAsignarLayout);
        pnlAsignarLayout.setHorizontalGroup(
            pnlAsignarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAsignarLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAsignarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelMetric2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(219, 219, 219))
            .addGroup(pnlAsignarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAsignarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAsignarLayout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addComponent(rSPanelsSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlAsignarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlAsignarLayout.setVerticalGroup(
            pnlAsignarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAsignarLayout.createSequentialGroup()
                .addComponent(labelMetric2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(pnlAsignarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSPanelsSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarTexto();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
        buscarTexto();
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        if (evt.getKeyChar() >= 2) {
            buscarTexto();
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void tblVerListadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVerListadoMouseClicked
        if (evt.getClickCount() >= 2) {
            cargarVista();
        }
    }//GEN-LAST:event_tblVerListadoMouseClicked

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        Utilidades.valiarSoloLetras(txtNombre);
    }//GEN-LAST:event_txtNombreKeyPressed

    private void txtDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyPressed

    }//GEN-LAST:event_txtDireccionKeyPressed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        Utilidades.esEmail(txtEmail.getText());       // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtTelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyPressed
        Utilidades.valiarSoloNumeros(txtTelefono);
        Utilidades.LimitarCaracterers(txtTelefono, 11);
    }//GEN-LAST:event_txtTelefonoKeyPressed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        pnlDatosCuenta.setVisible(false);
        txtClave.setText("");// TODO add your handling code here:
        txtUsuario.setText("");
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioFocusGained

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtClaveFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtClaveFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveFocusGained

    private void txtClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveActionPerformed

    private void txtNacionalidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNacionalidadKeyPressed
        // TODO add your handling code here:
        Utilidades.valiarSoloLetras(txtNacionalidad);
    }//GEN-LAST:event_txtNacionalidadKeyPressed

    private void txtCedulaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCedulaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaFocusGained

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaActionPerformed

    private void txtCedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyPressed
        // TODO add your handling code here:
        Utilidades.valiarSoloNumeros(txtCedula);
        Utilidades.LimitarCaracterers(txtCedula, 10);
        if ((txtCedula.getText() != "") || txtCedula.getText() != null) {
            cedula.setVisible(false);
        } else {
            cedula.setVisible(true);
        }
    }//GEN-LAST:event_txtCedulaKeyPressed

    private void txtApellidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyPressed
        Utilidades.valiarSoloLetras(txtApellido);

        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoKeyPressed

    private void btnDarBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDarBajaActionPerformed
        darDeBaja();
    }//GEN-LAST:event_btnDarBajaActionPerformed
    private void darDeBaja() {
        int fila = tblVerListado.getSelectedRow();
        if (fila >= 0) {
            ps.fijarPersona(modelo.getLista().get(fila));
            if (ps.getPersona().getCuenta().getEstado().equals(true)) {
                ps.getPersona().getCuenta().setEstado(Boolean.FALSE);
                if (ps.guardar()) {
                    limpiar();
                    pnlDatosCuenta.setVisible(false);
                    cargarTabla();
                    UtilidadesComponente.mensajeOK("OK","El estado de la cuenta a cambiado a INACTIVA");
                    

                } else {
                    UtilidadesComponente.mensajeError("Atencion", "No se pudo cambiar el estado");
                }
            } else {
                ps.getPersona().getCuenta().setEstado(Boolean.TRUE);
                if (ps.guardar()) {
                    limpiar();
                    pnlDatosCuenta.setVisible(false);
                    cargarTabla();
                    UtilidadesComponente.mensajeOK("OK","El estado de la cuenta a cambiado a ACTIVA");
                    
                } else {
                    UtilidadesComponente.mensajeError("Atencion", "No se pudo cambiar el estado");
                }
            }

            ps.fijarPersona(null);
        }

    }
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtCuentaBancariaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCuentaBancariaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuentaBancariaFocusGained

    private void txtCuentaBancariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCuentaBancariaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuentaBancariaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnAceptar;
    private rojeru_san.RSButton btnBuscar;
    private rojeru_san.RSButton btnCancelar;
    private rojeru_san.RSButton btnDarBaja;
    private rojeru_san.RSButton btnGuardar;
    private javax.swing.JLabel cedula;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private org.edisoncor.gui.label.LabelMetric labelMetric2;
    private javax.swing.JPanel pnlAsignar;
    private javax.swing.JPanel pnlDatosCuenta;
    private rojeru_san.RSPanelsSlider rSPanelsSlider1;
    private javax.swing.JTable tblVerListado;
    private rojeru_san.RSMTextFull txtApellido;
    private rojeru_san.RSMTextFull txtBuscar;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtClave;
    private javax.swing.JTextField txtCuentaBancaria;
    private rojeru_san.RSMTextFull txtDireccion;
    private rojeru_san.RSMTextFull txtEmail;
    private rojeru_san.RSMTextFull txtNacionalidad;
    private rojeru_san.RSMTextFull txtNombre;
    private rojeru_san.RSMTextFull txtTelefono;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
