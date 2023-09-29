import java.sql.*;
import java.util.Scanner;

public class Metodo_Meli {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("*****GESTION DE REGISTRO*****");

        System.out.println("Deseas registrar usuario, editar usuario, consultar usuario, o eliminar usuario?: ");
        String result = scanner.nextLine();

        if (result.equals("registrar usuario")) {

            System.out.println("Ingrese username: ");
            String email = scanner.nextLine();

            System.out.println("Ingrese password: ");
            String pass = scanner.nextLine();

            String newusu = Select(email, pass);
            if (newusu.equalsIgnoreCase("Secops21_MercL") || newusu.equalsIgnoreCase("ME2JK")) {

                System.out.println("Ingrese el username a registrar: ");
                String usernamerecord = scanner.nextLine();

                System.out.print("Ingrese password del username: ");
                String passwordrecord = scanner.nextLine();

                System.out.println("Ingrese los permisos que se otorgan: ");
                String permissions = scanner.nextLine();

                SecMercl_register(usernamerecord, passwordrecord, permissions);
            } else {
                System.out.println("tu usuario: " + newusu + " no tiene el permiso de registrar");
            }
    } if (result.equals("editar usuario")) {

            System.out.println("Ingrese username: ");
            String email = scanner.nextLine();

            System.out.println("Ingrese password: ");
            String pass = scanner.nextLine();

            String newusu = Select(email, pass);
            if (newusu.equalsIgnoreCase("Secops21_MercL")) {

                System.out.println("Ingrese el username a editar: ");
                String usernamerecord = scanner.nextLine();

                System.out.print("Ingrese password actualizado del username: ");
                String passwordrecord = scanner.nextLine();

                System.out.println("Ingrese los nuevos permisos que se otorgan: ");
                String permissions = scanner.nextLine();

                SecMercl_Edit(usernamerecord, passwordrecord, permissions);

            }else if (newusu.equalsIgnoreCase("ME2JK")) {

                System.out.println("Actualice o edite su password: ");
                pass = scanner.nextLine();

                System.out.println("Actualice o edite sus permisos: ");
                String permissions = scanner.nextLine();

                SecMercl_Edit(newusu, pass, permissions);
            }else {
                System.out.println("tu usuario: " + newusu + " no tiene el permiso de editar");
            }

        } if (result.equals("consultar usuario")){

            System.out.println("Ingrese username: ");
            String email = scanner.nextLine();

            System.out.println("Ingrese password: ");
            String pass = scanner.nextLine();

            String newusu = Select(email, pass);
            if (newusu.equalsIgnoreCase("Secops21_MercL")){

                SecMercl_Selectconsul();

            }else if (newusu.equalsIgnoreCase("MerCSL")){

                SecMercl_Select_One(newusu);
            } else {
                System.out.println("tu usuario: " + newusu + " no tiene el permiso de realizar consultas");
            }

        } if (result.equals("eliminar usuario")){

            System.out.println("Ingrese username: ");
            String email = scanner.nextLine();

            System.out.println("Ingrese password: ");
            String pass = scanner.nextLine();

            String newusu = Select(email, pass);
            if (newusu.equalsIgnoreCase("Secops21_MercL") || newusu.equalsIgnoreCase("MEJS75")) {

                System.out.println("Ingrese el username que deseas eliminar: ");
                email = scanner.nextLine();

                SecMercl_Delete(email);
            }
            else {
                System.out.println("tu usuario: " + newusu + " no tiene el permiso de eliminar");
            }
        }
}

    public static void SecMercl_Select_One(String newusu) throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/usuarios-meli";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consultaSQL = "SELECT * FROM usu_meli WHERE username = ?";

        PreparedStatement statement = connection.prepareStatement(consultaSQL);
        statement.setString(1, newusu); // Establecer el valor del parámetro

        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();

        // Procesar el resultado si existe
        if (resultSet.next()) {
            String usernameone = resultSet.getString("username");
            String passwordone = resultSet.getString("password");
            String permissions = resultSet.getString("permissions");

            System.out.println("Esta es su informacion, " + "Username: " + usernameone + " password: " + passwordone + "Y sus permisos otorgados son: " + permissions);

        } else {
            System.out.println("No se encontró un registro con el username ingresado.");
        }

        // Cerrar recursos
        resultSet.close();
        statement.close();
        connection.close();

    }

    private static void SecMercl_Delete(String email) throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/usuarios-meli";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        String sentenciaSQL = "DELETE FROM usu_meli WHERE codigo = ?";
        PreparedStatement prepareStatement = connection2.prepareStatement(sentenciaSQL);
        prepareStatement.setString(1, email);
        prepareStatement.executeUpdate();

        System.out.println("Usuario eliminado de manera exitosa");

    }

    private static void SecMercl_Selectconsul() throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/usuarios-meli";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM usu_meli");

        while(resultSet2.next()){
            String username = resultSet2.getString("username");
            String password = resultSet2.getString("password");
            String permissions = resultSet2.getString("permissions");

            System.out.println("Este es el username " + username +  " su password: " + password + " y los permisos otorgados son: " + permissions);
        }
    }

    private static void SecMercl_Edit(String usernamerecord, String passwordrecord, String permissions) throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/usuarios-meli";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        String consulta = "UPDATE usu_meli SET password = ?, permissions = ? WHERE username = ?";
        PreparedStatement preparedStatement = connection2.prepareStatement(consulta);
        preparedStatement.setString(1, passwordrecord);
        preparedStatement.setString(2, permissions);
        preparedStatement.setString(3, usernamerecord);

        int filasActualizadas = preparedStatement.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Sus credenciales se han actualizado exitosamente");
        } else {
            System.out.println("No se encontro un username registrado con estas credenciales para actualizar");
        }

        preparedStatement.close();
        connection2.close();
    }

    private static void SecMercl_register (String usernamerecord, String passwordrecord, String permissions) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/usuarios-meli";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM usu_meli");


            // Sentencia INSERT
            String sql = "INSERT INTO usu_meli (username, password, permissions) VALUES (?, ?, ?)";

            // Preparar la sentencia
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usernamerecord);
            preparedStatement.setString(2, passwordrecord);
            preparedStatement.setString(3, permissions);


            // Ejecutar la sentencia
            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Usuario registrado de manera exitosa.");
            } else {
                System.out.println("No se pudo registrar el usuario");
            }

            preparedStatement.close();
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String Select (String email, String pass) throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/usuarios-meli";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consultaSQL = "SELECT * FROM usu_meli WHERE username = ? AND password = ?";

        PreparedStatement statement = connection.prepareStatement(consultaSQL);
        statement.setString(1, email); // Establecer el valor del parámetro
        statement.setString(2, pass);

        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();

        // Procesar el resultado si existe
        if (resultSet.next()) {
            String correo = resultSet.getString("username");
            correo.equalsIgnoreCase(email);
            String password_funcionary = resultSet.getString("password");
            String permisos = resultSet.getString("permissions");

            // Cerrar recursos
            resultSet.close();
            statement.close();
            connection.close();

            return correo;

        }else{
            System.out.println("Credenciales invalidas");
        }

        return "";
    }
    }
