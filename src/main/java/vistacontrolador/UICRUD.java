package vistacontrolador;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.FlowLayout;
import dao.JoyaDAO;
import impl.JoyaImpl;
import modelo.Joya;
import java.util.List;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

public class UICRUD extends JFrame {

    private JTable table;
    private JTextField txtTipo;
    private javax.swing.JComboBox<String> comboMaterial;
    private JTextField txtAleacion;
    private JTextField txtPrecio;
    private JTextField txtMedida;
    private JTextField txtBuscar;

    private JoyaDAO dao = new JoyaImpl();
    private final JPanel panel_1 = new JPanel();
    
 // Interfaz generada inicialmente con WindowBuilder y posteriormente ajustada manualmente
    @SuppressWarnings("serial")
 // @wbp.parser.entryPoint

    public UICRUD() {

        setTitle("JOYAS");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245,245,245));
        //============================================
       // ===== PANEL SUPERIOR (logo + buscador) =====   
      //==============================================
     // ===== LOGO =====
        ImageIcon icon = new ImageIcon(getClass().getResource("logo.png"));
        Image original = icon.getImage();
        

        BufferedImage resized = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resized.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.drawImage(original, 0, 0, 100, 100, null);
        g2.dispose();
        
        
        JLabel lblLogo = new JLabel(new ImageIcon(resized));
        lblLogo.setPreferredSize(new Dimension(100, 100));
        lblLogo.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 10, 20));
        
     // ===== BUSCADOR =====
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        txtBuscar = new JTextField(15);
        JButton btnBuscar = new JButton("BUSCAR");
        JButton btnReset = new JButton("MOSTRAR TODO");
        
     // estilos botones
        JButton[] botoness = {btnBuscar, btnReset};
        for (JButton b : botoness) {
            b.setBackground(new Color(60, 60, 60));
            b.setForeground(Color.WHITE);
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            b.setFocusPainted(false);
            b.setBorderPainted(false);
        }
        

        panelBusqueda.add(new JLabel("BUSCAR TIPO:"));
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);
        panelBusqueda.add(btnReset);
        
        JPanel panelTop = new JPanel(new BorderLayout());
        panelTop.add(lblLogo, BorderLayout.CENTER);
        panelTop.add(panelBusqueda, BorderLayout.SOUTH);

        // ===== TABLA =====
        JScrollPane scrollPane = new JScrollPane();
        table = new JTable();
        
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(230,230,230));
        table.setRowHeight(25);
        table.setGridColor(new Color(220,220,220));
        table.setSelectionBackground(new Color(160, 160, 160));
        table.setSelectionForeground(Color.BLACK);
        
        //bold al seleccionar
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                java.awt.Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (isSelected) {
                    c.setFont(new Font("Segoe UI", Font.BOLD, 13)); 
                } else {
                    c.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                }

                setHorizontalAlignment(JLabel.CENTER); 

                return c;
            }
        });

        scrollPane.setViewportView(table);

        // ===== CLICK TABLA =====
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int fila = table.getSelectedRow();

                txtTipo.setText(table.getValueAt(fila, 1).toString());
                comboMaterial.setSelectedItem(table.getValueAt(fila, 2).toString());
                txtAleacion.setText(table.getValueAt(fila, 3).toString());
                txtPrecio.setText(table.getValueAt(fila, 4).toString());
                txtMedida.setText(table.getValueAt(fila, 5).toString());
            }
        });

        // ===== FORMULARIO =====
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 10));
        panelForm.setPreferredSize(new Dimension(450, 180));
        
        

        txtTipo = new JTextField();
        txtAleacion = new JTextField();
        txtPrecio = new JTextField();
        txtMedida = new JTextField();
        
        txtPrecio.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { validarNumero(txtPrecio); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { validarNumero(txtPrecio); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { validarNumero(txtPrecio); }
        });

        txtMedida.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { validarNumero(txtMedida); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { validarNumero(txtMedida); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { validarNumero(txtMedida); }
        });
        
        txtTipo.setColumns(15);
        txtAleacion.setColumns(15);
        txtPrecio.setColumns(15);
        txtMedida.setColumns(15);

        comboMaterial = new javax.swing.JComboBox<>();
        comboMaterial.addItem("Oro");
        comboMaterial.addItem("Plata");
        comboMaterial.addItem("Acero");
        comboMaterial.addItem("Platino");

        JLabel lblTipo = new JLabel("TIPO:");
        lblTipo.setHorizontalAlignment(JLabel.RIGHT);
        panelForm.add(lblTipo);
        panelForm.add(txtTipo);

        JLabel lblMaterial = new JLabel("MATERIAL:");
        lblMaterial.setHorizontalAlignment(JLabel.RIGHT);
        panelForm.add(lblMaterial);
        panelForm.add(comboMaterial);

        JLabel lblAleacion = new JLabel("ALEACION:");
        lblAleacion.setHorizontalAlignment(JLabel.RIGHT);
        panelForm.add(lblAleacion);
        panelForm.add(txtAleacion);

        JLabel lblPrecio = new JLabel("PRECIO:");
        lblPrecio.setHorizontalAlignment(JLabel.RIGHT);
        panelForm.add(lblPrecio);
        panelForm.add(txtPrecio);

        JLabel lblMedida = new JLabel("MEDIDA:");
        lblMedida.setHorizontalAlignment(JLabel.RIGHT);
        panelForm.add(lblMedida);
        panelForm.add(txtMedida);
        //centrarlo
       

        // ===== PANEL CENTRAL =====
        
        panelForm.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 40, 10, 40));
       
        JPanel panelFormLogo = new JPanel(new BorderLayout());

        panelFormLogo.add(panelForm, BorderLayout.CENTER);
        
        
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.add(panelFormLogo, BorderLayout.NORTH);
        panelCentro.add(scrollPane, BorderLayout.CENTER);
        
        JPanel panelLogo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelLogo.add(lblLogo);
        panelFormLogo.add(panelLogo, BorderLayout.EAST);
        panelLogo.setBorder(BorderFactory.createEmptyBorder(40, 20, 0, 20));

        // ===== BOTONES =====
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel_1.setBackground(new Color(240,240,240));

        JButton btnNew = new JButton("NUEVO");
        JButton btnEdit = new JButton("EDITAR");
        JButton btnDelete = new JButton("BORRAR");
        JButton btnClear = new JButton("LIMPIAR");

        panel_1.add(btnNew);
        panel_1.add(btnEdit);
        panel_1.add(btnDelete);
        panel_1.add(btnClear);

        // estilos botones
        JButton[] botones = {btnNew, btnEdit, btnDelete, btnClear};
        for (JButton b : botones) {
            b.setBackground(new Color(60, 60, 60));
            b.setForeground(Color.WHITE);
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            b.setFocusPainted(false);
            b.setBorderPainted(false);
        }
        btnClear.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // ===== AÑADIR AL FRAME =====
        getContentPane().add(panelTop, BorderLayout.NORTH);
        getContentPane().add(panelCentro, BorderLayout.CENTER);
        getContentPane().add(panel_1, BorderLayout.SOUTH);
        
        // LIMPIAR 
        btnClear.addActionListener(e -> {
            txtTipo.setText("");
            comboMaterial.setSelectedIndex(0);
            txtAleacion.setText("");
            txtPrecio.setText("");
            txtMedida.setText("");
        });
        //NUEVO
        btnNew.addActionListener(e -> {
        	if (txtTipo.getText().trim().isEmpty()) {
        	    JOptionPane.showMessageDialog(null, "El tipo es obligatorio");
        	    return;
        	}
        	if (txtTipo.getText().length() > 50) {
        	    JOptionPane.showMessageDialog(null, "Máximo 50 caracteres en tipo");
        	    return;
        	}

        	if (txtAleacion.getText().trim().isEmpty()) {
        	    JOptionPane.showMessageDialog(null, "La aleación es obligatoria");
        	    return;
        	}
        	if (txtAleacion.getText().length() > 50) {
        	    JOptionPane.showMessageDialog(null, "Máximo 50 caracteres en Aleacion");
        	    return;
        	}

            if (txtPrecio.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El precio es obligatorio");
                return;
            }

            if (txtMedida.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "La medida es obligatoria");
                return;
            }

            double precio;
            double medida;

            try {
            	precio = Double.parseDouble(txtPrecio.getText());
            	medida = Double.parseDouble(txtMedida.getText());

            	if (precio <= 0) {
            	    JOptionPane.showMessageDialog(null, "El precio debe ser mayor que 0");
            	    return;
            	}

            	if (medida <= 0) {
            	    JOptionPane.showMessageDialog(null, "La medida debe ser mayor que 0");
            	    return;
            	}
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Precio o medida inválidos");
                return;
            }

            Joya j = new Joya();
            j.setTipo(txtTipo.getText().trim());
            j.setMaterial(comboMaterial.getSelectedItem().toString());
            j.setAleacion(txtAleacion.getText().trim());
            j.setPrecio(precio);
            j.setMedida(medida);

            if (dao.existeJoya(j)) {
                JOptionPane.showMessageDialog(null, "Esa joya ya existe");
                return;
            }

            dao.insertarJoya(j);

            JOptionPane.showMessageDialog(null, "Joya insertada");

            cargarTabla();
        });
        
        //EDITAR
        btnEdit.addActionListener(e -> {
        	
        	if (txtTipo.getText().trim().isEmpty()) {
        	    JOptionPane.showMessageDialog(null, "El tipo es obligatorio");
        	    return;
        	}
        	if (txtTipo.getText().length() > 50) {
        	    JOptionPane.showMessageDialog(null, "Máximo 50 caracteres en tipo");
        	    return;
        	}

        	if (txtAleacion.getText().trim().isEmpty()) {
        	    JOptionPane.showMessageDialog(null, "La aleación es obligatoria");
        	    return;
        	}
        	if (txtAleacion.getText().length() > 50) {
        	    JOptionPane.showMessageDialog(null, "Máximo 50 caracteres en Aleacion");
        	    return;
        	}

            if (txtPrecio.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El precio es obligatorio");
                return;
            }

            if (txtMedida.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "La medida es obligatoria");
                return;
            }

            int fila = table.getSelectedRow();

            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Selecciona una fila");
                return;
            }

            int id = (int) table.getValueAt(fila, 0);

            double precio;
            double medida;

            try {
            	precio = Double.parseDouble(txtPrecio.getText());
            	medida = Double.parseDouble(txtMedida.getText());

            	if (precio <= 0) {
            	    JOptionPane.showMessageDialog(null, "El precio debe ser mayor que 0");
            	    return;
            	}

            	if (medida <= 0) {
            	    JOptionPane.showMessageDialog(null, "La medida debe ser mayor que 0");
            	    return;
            	}
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Precio o medida inválidos");
                return;
            }

            Joya j = new Joya();
            j.setId(id);
            j.setTipo(txtTipo.getText().trim());
            j.setMaterial(comboMaterial.getSelectedItem().toString());
            j.setAleacion(txtAleacion.getText());
            j.setPrecio(precio);
            j.setMedida(medida);

            dao.modificarJoya(j);

            JOptionPane.showMessageDialog(null, "Joya modificada");

            cargarTabla();
        });
        
        //ELIMINAR
        btnDelete.addActionListener(e -> {
            int fila = table.getSelectedRow();

            if (fila == -1) {
                javax.swing.JOptionPane.showMessageDialog(null, "Selecciona una fila");
                return;
            }

            int id = (int) table.getValueAt(fila, 0);

            Joya j = new Joya();
            j.setId(id);
            int confirm = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres borrar?");
            if (confirm != JOptionPane.YES_OPTION) return;
            dao.eliminarJoya(j); 

            cargarTabla();
        });
        
        // ===== BUSCAR =====
        btnBuscar.addActionListener(e -> {
            String filtro = txtBuscar.getText().toLowerCase();

            List<Joya> lista = dao.listarJoyas();
            List<Joya> filtrada = new java.util.ArrayList<>();

            for (Joya j : lista) {
                if (j.getTipo().toLowerCase().contains(filtro)) {
                    filtrada.add(j);
                }
            }

            actualizarTabla(filtrada);
            centrarTabla();
        });

        btnReset.addActionListener(e -> {
            txtBuscar.setText("");
            cargarTabla();
        });
        cargarTabla();
        
     // VALIDACION TIEMPO REAL PRECIO
        txtPrecio.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { validar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { validar(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { validar(); }

            private void validar() {
                try {
                    Double.parseDouble(txtPrecio.getText());
                    txtPrecio.setBackground(Color.WHITE);
                } catch (Exception ex) {
                    txtPrecio.setBackground(new Color(255, 200, 200));
                }
            }
        });
        
        txtMedida.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { validar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { validar(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { validar(); }

            private void validar() {
                try {
                    Double.parseDouble(txtMedida.getText());
                    txtMedida.setBackground(Color.WHITE);
                } catch (Exception ex) {
                    txtMedida.setBackground(new Color(255, 200, 200));
                }
            }
        });
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.out.println("Cerrando aplicación...");
                // Si usas Hikari:
                // DataBase.close();  <-- si tienes método
            }
        });
    }
   
    //METODOS
    private void cargarTabla() {
        List<Joya> lista = dao.listarJoyas();
        table.setModel(new JoyaTableModel(lista));
        
    }

    private void actualizarTabla(List<Joya> lista) {
        table.setModel(new JoyaTableModel(lista));
        
    }

    private void centrarTabla() {
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }
    }
    
    private void validarNumero(JTextField campo) {
        try {
            if (!campo.getText().isEmpty()) {
                Double.parseDouble(campo.getText());
                campo.setBackground(Color.WHITE);
            } else {
                campo.setBackground(Color.WHITE);
            }
        } catch (NumberFormatException e) {
            campo.setBackground(new Color(255, 200, 200)); // rojo suave
        }
    }
}
