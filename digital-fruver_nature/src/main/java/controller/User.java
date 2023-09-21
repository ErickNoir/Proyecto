package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.user.UserVO;
import model.user.UserDAO;


public class User extends HttpServlet{
    UserVO newUserVO = new UserVO();
    UserDAO newUserDAO = new UserDAO();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String navUser = req.getParameter("navUser");
        System.out.println(navUser);

        switch(navUser){
            case "index":
                RequestDispatcher index = req.getRequestDispatcher("index.jsp");
                index.forward(req, resp);
                break;

            case "indexAdmin":
                RequestDispatcher indexAdmin = req.getRequestDispatcher("views/user/adminIndex.jsp");
                indexAdmin.forward(req, resp);
                break;

            case "indexClient":
                RequestDispatcher indexClient = req.getRequestDispatcher("views/user/clientIndex.jsp");
                indexClient.forward(req, resp);
                break;

            case "indexEmpl":
                RequestDispatcher emplAdmin = req.getRequestDispatcher("views/user/emplIndex.jsp");
                emplAdmin.forward(req, resp);
                break;

            case "login":
                RequestDispatcher login = req.getRequestDispatcher("views/user/login.jsp");
                login.forward(req, resp);
                break;

            case "register":
                RequestDispatcher register = req.getRequestDispatcher("views/user/register.jsp");
                register.forward(req, resp);
                break;
            
            case "listUsers":
                System.out.println("Listando...");
                listUsers(req,resp);
                break;
        }

    }




    private void listUsers(HttpServletRequest req, HttpServletResponse resp) {
        try{
            List<UserVO> arrayUser = newUserDAO.listsUsers();
            req.setAttribute("user", arrayUser);

            System.out.println("Finalizando...");
            
            RequestDispatcher showUsers = req.getRequestDispatcher("views/user/listUsers.jsp");
            showUsers.forward(req, resp);

            System.out.println("Se terminó de listar los usuarios.");
            System.out.println("------------------------------------------------------------------------");
        }catch(Exception e){
            System.out.println("Error al listar los usuarios: "+ e.getMessage().toString());
        }
    }




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userForm = req.getParameter("userForm");
        System.out.println(userForm);

        switch(userForm){
            case "validateFormLogin":
                login(req,resp);
                break;

            case "registNewUser":
                register(req,resp);
                break;
            
            case "searchUserFor":
                searchUserFor(req,resp);
                break;

            case "inactivateUser":
                inactivateUser(req,resp);
                break;

            case "activateUser":
                activateUser(req,resp);
                break;

            case "searchOneUser":
                searchOneUser(req,resp);
                break;

            case "updateUser":
                updateUser(req,resp);
                break;
        }

    }



// INICIAR SESION --------------------------------------------------------------------------------------------------------------------------
    private void login(HttpServletRequest req, HttpServletResponse resp) {
        String usernameLogin = req.getParameter("enteredLogUsername");
        String passwordLogin = req.getParameter("enteredLogPassword");

        try {
            UserVO newUserVO = newUserDAO.login(usernameLogin);

            if (newUserVO != null) {
                String usernameUser = newUserVO.getUsernameUser();
                String passwordUser = newUserVO.getPasswordUser();
                String stateUser = newUserVO.getStateUser();
                String rolUser = newUserVO.getIdfkRolUser();

                if (usernameLogin.equals(usernameUser) && passwordLogin.equals(passwordUser) && "Activo".equals(stateUser)) {

                    if("1".equals(rolUser)){
                        System.out.println("Bienvenido al sistema, administrador.");
                        System.out.println("------------------------------------");
                        RequestDispatcher indexAdmin = req.getRequestDispatcher("views/user/adminIndex.jsp");
                        indexAdmin.forward(req, resp);

                    } else if("2".equals(rolUser)){
                        System.out.println("Bienvenido al sistema, empleado.");
                        System.out.println("------------------------------------");
                        RequestDispatcher emplAdmin = req.getRequestDispatcher("views/user/emplIndex.jsp");
                        emplAdmin.forward(req, resp);
                    } else {
                        System.out.println("Bienvenido al sistema, cliente.");
                        System.out.println("------------------------------------");
                        RequestDispatcher indexClient = req.getRequestDispatcher("views/user/clientIndex.jsp");
                        indexClient.forward(req, resp);
                    }
                    
                } else {
                    System.out.println("Acceso denegado.");
                    System.out.println("-------------------------------------");
                    RequestDispatcher login = req.getRequestDispatcher("views/user/login.jsp");
                    login.forward(req, resp);
                }
                
            } else {
                System.out.println("Usuario no encontrado.");
                System.out.println("------------------------------------");
                RequestDispatcher login = req.getRequestDispatcher("views/user/login.jsp");
                login.forward(req, resp);
            }

        } catch(Exception x){
            System.out.println("(Servlet) La consulta no pudo ser ejecutada" + x.getMessage().toString()); 
        }
    }



// REGISTRARSE --------------------------------------------------------------------------------------------------------------------------
    private void register(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter("nameUser")!=null) { newUserVO.setNameUser((req.getParameter("nameUser"))); }
        if (req.getParameter("lastnameUser")!=null) { newUserVO.setLastNameUser((req.getParameter("lastnameUser"))); }
        if (req.getParameter("emailUser")!=null) { newUserVO.setEmailUser((req.getParameter("emailUser"))); }
        if (req.getParameter("phoneUser")!=null) { newUserVO.setCelphoneUser((req.getParameter("phoneUser"))); }
        if (req.getParameter("typeDocUser")!=null) { newUserVO.setTypeDocumentUser((req.getParameter("typeDocUser"))); }
        if (req.getParameter("documentUser")!=null) { newUserVO.setDocumentNumberUser((req.getParameter("documentUser"))); }
        if (req.getParameter("userUser")!=null) { newUserVO.setUsernameUser((req.getParameter("userUser"))); }
        if (req.getParameter("passwordUser")!=null) { newUserVO.setPasswordUser((req.getParameter("passwordUser"))); }
        if (req.getParameter("typeUser")!=null) { newUserVO.setIdfkRolUser((req.getParameter("typeUser"))); }
    

        String typeUser = req.getParameter("typeUser");
        System.out.println("Validando estado..." + typeUser); 
            if("2".equals(typeUser)){
                System.out.println("Definendo estado como inactivo..."); 
                newUserVO.setStateUser("Inactivo");
            }else if("3".equals(typeUser)){
                System.out.println("Definendo estado como activo..."); 
                newUserVO.setStateUser("Activo");
            }else{
                System.out.println("Error definiendo el estado.");
            }
        
        
        try {
            newUserDAO.register(newUserVO);
            System.out.println("El registro fue exitoso.");

            RequestDispatcher login = req.getRequestDispatcher("views/user/login.jsp");
            login.forward(req, resp);
        } catch (Exception x) {
            System.out.println("(Servlet) La consulta no pudo ser ejecutada" + x.getMessage().toString()); 
        }
    }



// BUSCAR USUARIO --------------------------------------------------------------------------------------------------------------------------
    private void searchUserFor(HttpServletRequest req, HttpServletResponse resp){
        String dataForSearch = null;
        String typeDataSearchFor = null;

        if(req.getParameter("searchData")!= null){ dataForSearch = req.getParameter("searchData"); }
        if(req.getParameter("searchFor")!= null){ typeDataSearchFor = req.getParameter("searchFor"); }

        try{
            List<UserVO> arrayUserSearch = newUserDAO.searchUser(dataForSearch, typeDataSearchFor);
            req.setAttribute("user", arrayUserSearch);

            System.out.println("Finalizando...");

            RequestDispatcher showUsersSearch = req.getRequestDispatcher("views/user/listUserForSearch.jsp");
            showUsersSearch.forward(req, resp);

            System.out.println("Se terminó de buscar el usuario.");
            System.out.println("------------------------------------------------------------------------");

        } catch(Exception x){
            System.out.println("(Servlet) Error al buscar el usuario: "+ x.getMessage().toString());
        }
    }



// DEFINIR ESTADO DE USUARIO --------------------------------------------------------------------------------------------------------------------------
    //INACTIVAR
    private void inactivateUser(HttpServletRequest req, HttpServletResponse resp){
        if(req.getParameter("defineStatusUser")!= null){ newUserVO.setIdUser(req.getParameter("defineStatusUser"));}
        if(req.getParameter("defineStatus")!= null){ newUserVO.setStateUser(req.getParameter("defineStatus"));}

        try{
            newUserDAO.inactivateUser(newUserVO);
            System.out.println("Finalizando...");

            RequestDispatcher showUsers = req.getRequestDispatcher("views/user/listUsers.jsp");
            showUsers.forward(req, resp);

            System.out.println("La inactivación del usuario fue exitosa.");
            System.out.println("------------------------------------------------------------------------");
        } catch(Exception x){
            System.out.println("(Servlet) Error al inactivar el usuario: "+ x.getMessage().toString());
        }
    }

    //ACTIVAR
    private void activateUser(HttpServletRequest req, HttpServletResponse resp){
        if(req.getParameter("defineStatusUser")!= null){ newUserVO.setIdUser(req.getParameter("defineStatusUser"));}
        if(req.getParameter("defineStatus")!= null){ newUserVO.setStateUser(req.getParameter("defineStatus"));}

        try{
            newUserDAO.activateUser(newUserVO);
            System.out.println("Finalizando...");

            RequestDispatcher showUsers = req.getRequestDispatcher("views/user/listUsers.jsp");
            showUsers.forward(req, resp);

            System.out.println("La activación del usuario fue exitosa.");
            System.out.println("------------------------------------------------------------------------");
        } catch(Exception x){
            System.out.println("(Servlet) Error al activar el usuario: "+ x.getMessage().toString());
        }
    }



// BUSCAR UN USUARIO --------------------------------------------------------------------------------------------------------------------------
    private void searchOneUser(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Buscando usuario...");

        if(req.getParameter("searchUserToEdit")!= null){ newUserVO.setIdUser(req.getParameter("searchUserToEdit"));}

        try{
            List<UserVO> arrayOneUser = newUserDAO.listOneUser(newUserVO);
            req.setAttribute("user", arrayOneUser);

            System.out.println("Finalizando...");
            
            RequestDispatcher formToUpdateUser = req.getRequestDispatcher("views/user/updateUser.jsp");
            formToUpdateUser.forward(req, resp);

            System.out.println("Se terminó de listar los datos del usuario buscado.");
            System.out.println("------------------------------------------------------------------------");
        }catch(Exception e){
            System.out.println("(Servlet) Error al listar los datos del usuario buscado: "+ e.getMessage().toString());
        }
    }



// ACTUALIZAR USUARIO --------------------------------------------------------------------------------------------------------------------------
    private void updateUser(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter("updateIDUser")!=null) { newUserVO.setIdUser(req.getParameter("updateIDUser")); }
        if (req.getParameter("updatePriorityUser")!=null) { newUserVO.setPriorityUser(req.getParameter("updatePriorityUser")); }
        if (req.getParameter("updateNameUser")!=null) { newUserVO.setNameUser(req.getParameter("updateNameUser")); }
        if (req.getParameter("updateLastnameUser")!=null) { newUserVO.setLastNameUser(req.getParameter("updateLastnameUser")); }
        if (req.getParameter("updateEmailUser")!=null) { newUserVO.setEmailUser(req.getParameter("updateEmailUser")); }
        if (req.getParameter("updatePhoneUser")!=null) { newUserVO.setCelphoneUser(req.getParameter("updatePhoneUser")); }
        if (req.getParameter("updateTypeDocUser")!=null) { newUserVO.setTypeDocumentUser(req.getParameter("updateTypeDocUser")); }
        if (req.getParameter("updateDocumentUser")!=null) { newUserVO.setDocumentNumberUser(req.getParameter("updateDocumentUser")); }
        if (req.getParameter("updateUserUser")!=null) { newUserVO.setUsernameUser(req.getParameter("updateUserUser")); }
        if (req.getParameter("updatePasswordUser")!=null) { newUserVO.setPasswordUser(req.getParameter("updatePasswordUser")); }
        if (req.getParameter("updateTypeUser")!=null) { newUserVO.setIdfkRolUser(req.getParameter("updateTypeUser")); }
        if (req.getParameter("updateStateUser")!=null) { newUserVO.setStateUser(req.getParameter("updateStateUser")); }

        try {
            newUserDAO.updateUser(newUserVO);
            System.out.println("La actualización del usuario fue exitosa.");

            RequestDispatcher showUsers = req.getRequestDispatcher("views/user/listUsers.jsp");
            showUsers.forward(req, resp);
        } catch (Exception x) {
            System.out.println("(Servlet) La consulta de actualización no pudo ser ejecutada" + x.getMessage().toString()); 
        }
    }



}

