/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package procesoreportedaños;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class ProcesoReporteDaños {

    public static void main(String[] args) throws SQLException {

        String usuario = "root";
        String password = "031208";
        String url = "jdbc:mysql://localhost:3306/condominio";

        while (true) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conexion = DriverManager.getConnection(url, usuario, password);
                Statement st = conexion.createStatement();

                System.out.println("\nPERSONAS");
                ResultSet resultadoPersonas = st.executeQuery("select * from personas");
                System.out.println("\nID\tNOMBRE\tAPELLIDO\tEMAIL");
                while (resultadoPersonas.next()) {
                    System.out.println(resultadoPersonas.getInt("personas_id")
                            + "\t" + resultadoPersonas.getString("personas_nombre")
                            + "\t" + resultadoPersonas.getString("personas_apellido")
                            + "\t" + resultadoPersonas.getString("personas_email"));
                }

                System.out.println("\nREPORTE DAÑOS");
                ResultSet resultadoReportesDaños = st.executeQuery("select * from reportes_daños");
                System.out.println("\nID\tFECHA\tMOTIVO\tCONDOMINIO");
                while (resultadoReportesDaños.next()) {
                    System.out.println(resultadoReportesDaños.getInt("reportes_daños_id")
                            + "\t" + resultadoReportesDaños.getString("reportes_daños_fecha")
                            + "\t" + resultadoReportesDaños.getString("reportes_daños_motivo")
                            + "\t" + resultadoReportesDaños.getString("condominios_condominios_id"));
                }

                System.out.println("\nCONDOMINIO");
                ResultSet resultadoCondominios = st.executeQuery("select * from condominios");
                System.out.println("\nID\tNOMBRE");
                while (resultadoCondominios.next()) {
                    System.out.println(resultadoCondominios.getInt("condominios_id")
                            + "\t" + resultadoCondominios.getString("condominios_nombre"));
                }

                System.out.println("\n1. Insertar reporte daño");
                System.out.println("2. Editar reporte daño");
                System.out.println("3. Eliminar reporte daño");
                System.out.println("4. Consultar personas");
                System.out.println("5. Consultar condominio");
                System.out.println("6. Consultar reporte daño");
                System.out.println("7. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = Integer.parseInt(JOptionPane.showInputDialog("Seleccione una opción:"));

                switch (opcion) {
                    case 1 -> {                        
                        String fecha = JOptionPane.showInputDialog("Introduzca la fecha del reporte daño: ");
                        String contenido = JOptionPane.showInputDialog("Introduzca el motivo del reporte daño: ");
                        String CondominioID = JOptionPane.showInputDialog("Introduzca el id del Condominio: ");
                        st.executeUpdate("Insert into reportes_daños (reportes_daños_fecha, reportes_daños_motivo, condominios_condominios_id) values('" + fecha + "','" + contenido + "'," + CondominioID + ")");
                        JOptionPane.showMessageDialog(null, "Reporte de Daño registrado correctamente");
                    }
                    case 2 -> {
                        String reportes_daños_id = JOptionPane.showInputDialog("Introduzca el ID del Reporte de Daño a editar: ");
                        String Newcontenido = JOptionPane.showInputDialog("Introduzca el motivo del reporte daño: ");
                        st.executeUpdate("Update reportes_daños set reportes_daños_motivo = '" + Newcontenido + "' where reportes_daños_id = " + reportes_daños_id);
                        JOptionPane.showMessageDialog(null, "Reporte de Daño editado correctamente");
                    }
                    case 3 -> {
                        String reportes_daños_id = JOptionPane.showInputDialog("Introduzca el ID del Reporte de Daño a eliminar: ");
                        st.executeUpdate("Delete from reportes_daños where reportes_daños_id = " + reportes_daños_id);
                        JOptionPane.showMessageDialog(null, "Reporte de Daño eliminado correctamente");
                    }
                    case 4 -> {
                        String personas_id = JOptionPane.showInputDialog("Introduzca el ID de la persona a consultar: ");
                        ResultSet resultadoPersona = st.executeQuery("select * from personas where personas_id = " + personas_id);
                        while (resultadoPersona.next()) {
                            System.out.println("\nID\tNOMBRE\tAPELLIDO\tEMAIL");
                            System.out.println(resultadoPersona.getInt("personas_id")
                                    + "\t" + resultadoPersona.getString("personas_nombre")
                                    + "\t" + resultadoPersona.getString("personas_apellido")
                                    + "\t" + resultadoPersona.getString("personas_email"));
                        }
                    }

                    case 5 -> {
                        String condominios_id = JOptionPane.showInputDialog("Introduzca el ID del Condominio a consultar: ");
                        ResultSet resultadoCondominio = st.executeQuery("select * from condominios where condominios_id = " + condominios_id);
                        while (resultadoCondominio.next()) {
                            System.out.println("\nID\tNOMBRE");
                            System.out.println(resultadoCondominio.getInt("condominios_id")
                                    + "\t" + resultadoCondominio.getString("condominios_nombre"));
                        }
                    }

                    case 6 -> {
                        String reportes_daños_id = JOptionPane.showInputDialog("Introduzca el ID del contratista a consultar: ");
                        ResultSet resultadoReportesDaño = st.executeQuery("select * from reportes_daños where reportes_daños_id = " + reportes_daños_id);
                        while (resultadoReportesDaño.next()) {
                            System.out.println(resultadoReportesDaño.getInt("reportes_daños_id")
                                    + "\t" + resultadoReportesDaño.getString("reportes_daños_fecha")
                                    + "\t" + resultadoReportesDaño.getString("reportes_daños_motivo")
                                    + "\t" + resultadoReportesDaño.getString("condominios_condominios_id"));
                        }
                    }

                    case 7 ->
                        System.exit(0);

                    default ->
                        JOptionPane.showMessageDialog(null, "Opción no válida");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProcesoReporteDaños.class.getName());
            }
        }
    }

}
