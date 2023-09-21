
package model.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Conectionn;

public class UserDAO {

    /* Atributos para realizar operaciones sobre la base de datos */
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql = null;
    int r;



// INICIAR SESION --------------------------------------------------------------------------------------------------------------------------
    public UserVO login(String username) throws SQLException {
        UserVO newUserVO = null;

        try{
            sql = "SELECT `usernameUsuario`, `contrasenaUsuario`, `estadoUsuario`, `idFKRol` FROM `usuarios` WHERE `usernameUsuario`='" + username + "';";
            System.out.println("Consulta sql exitosa...");

            con = Conectionn.conectTo();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("Atributos de operación listos...");

            if(rs.next()){
                newUserVO = new UserVO();
                newUserVO.setStateUser(rs.getString("estadoUsuario"));
                newUserVO.setIdfkRolUser(rs.getString("idFKRol"));
                newUserVO.setPasswordUser(rs.getString("contrasenaUsuario"));
                newUserVO.setUsernameUser(rs.getString("usernameUsuario"));    
            }

            ps.close();
            System.out.println("Consulta de login exitosa.");

        }catch(Exception x){ 
            System.out.println("(DAO) La consulta no pudo ser ejecutada: " + x.getMessage().toString()); 
        } finally{
            con.close();
        }

        return newUserVO;
    }




// REGISTRARSE --------------------------------------------------------------------------------------------------------------------------
    public void register(UserVO newUserVO) throws SQLException {
        try {
            System.out.println("Registrando usuario...");
            sql = "INSERT INTO usuarios(`idUsuario`, `prioridadUsuario`, `nombreUsuario`, `apellidoUsuario`, `emailUsuario`, `telefonoUsuario`, `tipoDocUsuario`, `documentoUsuario`, `usernameUsuario`, `contrasenaUsuario`, `idFKRol`, `estadoUsuario`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            con = Conectionn.conectTo();
            ps = con.prepareStatement(sql);

            ps.setString( 1, newUserVO.getIdUser());
            ps.setString( 2, "Normal");
            ps.setString( 3, newUserVO.getNameUser());
            ps.setString(4, newUserVO.getLastNameUser());
            ps.setString(5, newUserVO.getEmailUser());
            ps.setString( 6, newUserVO.getCelphoneUser());//
            ps.setString(7, newUserVO.getTypeDocumentUser());
            ps.setString(8, newUserVO.getDocumentNumberUser());
            ps.setString(9, newUserVO.getUsernameUser());
            ps.setString(10, newUserVO.getPasswordUser());
            ps.setString(11, newUserVO.getIdfkRolUser());
            ps.setString(12, newUserVO.getStateUser());

            ps.executeUpdate();
            ps.close();

            System.out.println("Se registró el usuario correctamente.");
            
        } catch (Exception x) {
            System.out.println("(DAO) La consulta no pudo ser ejecutada: " + x.getMessage().toString()); 
        } finally{
            con.close();
        }
    }



// LISTAR TODOS LOS USUARIOS --------------------------------------------------------------------------------------------------------------------------
    public List<UserVO> listsUsers() throws SQLException{
        List<UserVO> arrayUser=new ArrayList<>();
        sql="SELECT usu.idUsuario, usu.prioridadUsuario, usu.nombreUsuario, usu.apellidoUsuario, usu.emailUsuario, usu.telefonoUsuario, usu.usernameUsuario, usu.estadoUsuario FROM `usuarios` AS usu;";
        try {
            con=Conectionn.conectTo();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);

            while(rs.next()){
                UserVO r=new UserVO();
                r.setIdUser(rs.getString("IdUsuario"));
                r.setPriorityUser(rs.getString("prioridadUsuario"));
                r.setNameUser(rs.getString("nombreUsuario"));
                r.setLastNameUser(rs.getString("apellidoUsuario"));
                r.setEmailUser(rs.getString("emailUsuario"));
                r.setCelphoneUser(rs.getString("telefonoUsuario"));
                r.setUsernameUser(rs.getString("usernameUsuario"));
                r.setStateUser(rs.getString("estadoUsuario"));

                arrayUser.add(r);
            }
            
            ps.close();
            System.out.println("Consulta exitosa.");
        } catch (Exception e) {
            System.out.println("(DAO) La consulta no pudo ser ejecutada "+e.getMessage().toString());
        } finally{ 
            con.close();
        }
        
        return arrayUser;
    }



// BUSCAR USUARIO --------------------------------------------------------------------------------------------------------------------------
    public List<UserVO> searchUser(String dataForSearch, String typeDataSearchFor) throws SQLException{
        List<UserVO> arrayUserSearch=new ArrayList<>();

        System.out.println("Guardando parametros de busqueda...");

        if(typeDataSearchFor.equals("idFKRol")){
            sql = "SELECT usu.idUsuario, usu.prioridadUsuario, usu.nombreUsuario, usu.apellidoUsuario, usu.emailUsuario, usu.telefonoUsuario, usu.usernameUsuario, usu.estadoUsuario FROM `usuarios` AS usu INNER JOIN roles AS rol ON usu.idFKRol=rol.id_Rol WHERE rol.nombre_Rol = '" + dataForSearch + "';";
        }else{
            sql = "SELECT idUsuario, prioridadUsuario, nombreUsuario, apellidoUsuario, emailUsuario, telefonoUsuario, usernameUsuario, estadoUsuario FROM `usuarios` WHERE " + typeDataSearchFor + "='" + dataForSearch + "';";
        }

        try {
            System.out.println("Buscando usuario...");

            con=Conectionn.conectTo();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);

            while(rs.next()){
                UserVO r=new UserVO();
                r.setIdUser(rs.getString("IdUsuario"));
                r.setPriorityUser(rs.getString("prioridadUsuario"));
                r.setNameUser(rs.getString("nombreUsuario"));
                r.setLastNameUser(rs.getString("apellidoUsuario"));
                r.setEmailUser(rs.getString("emailUsuario"));
                r.setCelphoneUser(rs.getString("telefonoUsuario"));
                r.setUsernameUser(rs.getString("usernameUsuario"));
                r.setStateUser(rs.getString("estadoUsuario"));

                arrayUserSearch.add(r);
            }
            
            ps.close();
            System.out.println("Busqueda exitosa.");
        } catch (Exception e) {
            System.out.println("(DAO) La busqueda no pudo ser ejecutada: "+e.getMessage().toString());
        } finally{ 
            con.close();
        }

        return arrayUserSearch;
    }



// DEFINIR ESTADO DE USUARIO --------------------------------------------------------------------------------------------------------------------------
    //INACTIVAR
    public void inactivateUser(UserVO newUserVO) throws SQLException {
        try{
            System.out.println("Inactivando usuario...");

            sql="UPDATE `usuarios` SET `estadoUsuario` = '" + newUserVO.getStateUser() + "' WHERE `idUsuario` = '" + newUserVO.getIdUser() + "';";
            con = Conectionn.conectTo();
            ps = con.prepareStatement(sql);

            ps.executeUpdate();
            ps.close();

        } catch(Exception x){
             System.out.println("(DAO) La actualización de estado no pudo ser ejecutada: " + x.getMessage().toString());
        } finally{ 
            con.close();
        }

    }

    //ACTIVAR
    public void activateUser(UserVO newUserVO) throws SQLException {
        try{
            System.out.println("Activando usuario...");

            sql="UPDATE `usuarios` SET `estadoUsuario` = '" + newUserVO.getStateUser() + "' WHERE `idUsuario` = '" + newUserVO.getIdUser() + "';";
            con = Conectionn.conectTo();
            ps = con.prepareStatement(sql);

            ps.executeUpdate();
            ps.close();

        } catch(Exception x){
             System.out.println("(DAO) La actualización de estado no pudo ser ejecutada: " + x.getMessage().toString());
        } finally{ 
            con.close();
        }

    }



// BUSCAR USUARIO A ACTUALIZAR --------------------------------------------------------------------------------------------------------------------------
    public List<UserVO> listOneUser(UserVO newUserVO) throws SQLException{
        List<UserVO> arrayOneUser=new ArrayList<>();

        sql="SELECT * FROM `usuarios` WHERE idUsuario='" + newUserVO.getIdUser() + "';";
        
        try {
            con=Conectionn.conectTo();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(sql);

            while(rs.next()){
                UserVO r=new UserVO();
                r.setIdUser(rs.getString("IdUsuario"));
                r.setPriorityUser(rs.getString("prioridadUsuario"));
                r.setNameUser(rs.getString("nombreUsuario"));
                r.setLastNameUser(rs.getString("apellidoUsuario"));
                r.setEmailUser(rs.getString("emailUsuario"));
                r.setCelphoneUser(rs.getString("telefonoUsuario"));
                r.setTypeDocumentUser(rs.getString("tipoDocUsuario"));
                r.setDocumentNumberUser(rs.getString("documentoUsuario"));
                r.setUsernameUser(rs.getString("usernameUsuario"));
                r.setPasswordUser(rs.getString("contrasenaUsuario"));
                r.setIdfkRolUser(rs.getString("idFKRol"));
                r.setStateUser(rs.getString("estadoUsuario"));

                arrayOneUser.add(r);
            }
            
            ps.close();
            System.out.println("Consulta exitosa.");
        } catch (Exception e) {
            System.out.println("(DAO) La consulta no pudo ser ejecutada "+e.getMessage().toString());
        } finally{ 
            con.close();
        }
        
        return arrayOneUser;
    }



// ACTUALIZAR USUARIO --------------------------------------------------------------------------------------------------------------------------
    public void updateUser(UserVO newUserVO) throws SQLException {
        try{
            System.out.println("Actualizando usuario...");
            sql = "UPDATE usuarios SET prioridadUsuario='" + newUserVO.getPriorityUser() + "', nombreUsuario='" + newUserVO.getNameUser() + "', apellidoUsuario='" + newUserVO.getLastNameUser() + "', emailUsuario='" + newUserVO.getEmailUser() + "', telefonoUsuario='" + newUserVO.getCelphoneUser() + "', tipoDocUsuario='" + newUserVO.getTypeDocumentUser() + "', documentoUsuario='" + newUserVO.getDocumentNumberUser() + "', usernameUsuario='" + newUserVO.getUsernameUser() + "', contrasenaUsuario='" + newUserVO.getPasswordUser() + "', idFKRol='" + newUserVO.getIdfkRolUser() + "', estadoUsuario='" + newUserVO.getStateUser() + "' WHERE idUsuario='" + newUserVO.getIdUser() + "';";

            con = Conectionn.conectTo();
            ps = con.prepareStatement(sql);

            ps.executeUpdate();
            ps.close();

        } catch(Exception x){
            System.out.println("(DAO) La consulta no pudo ser ejecutada " + x.getMessage().toString());
        } finally{ 
            con.close();
        }
        
    }


}
