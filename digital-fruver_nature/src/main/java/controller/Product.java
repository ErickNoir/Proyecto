package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.product.ProductVO;
import model.product.ProductDAO;


@MultipartConfig
public class Product extends HttpServlet{
    ProductVO newProductVO = new ProductVO();
    ProductDAO newProductDAO = new ProductDAO();
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String navProduct = req.getParameter("navProduct");
        System.out.println(navProduct);

        if(navProduct!=null){
            switch(navProduct){
            case "listProducts":
                System.out.println("Listando...");
                listProducts(req,resp);
                break;

            case "registProduct":
                RequestDispatcher register = req.getRequestDispatcher("views/product/registProduct.jsp");
                register.forward(req, resp);
                break;
            }
        } else {
            System.out.println("Idk, bro...");
        }
    }



    private void listProducts(HttpServletRequest req, HttpServletResponse resp) {
        try{List<ProductVO> arrayProduct = newProductDAO.listsProducts();
            req.setAttribute("productList", arrayProduct);
            System.out.println("Finalizando...");
                        
            RequestDispatcher showProducts = req.getRequestDispatcher("views/product/listProducts.jsp");
            showProducts.forward(req, resp);

            System.out.println("Se terminó de listar los productos.");
            System.out.println("------------------------------------------------------------------------");
        }catch(Exception e){ System.out.println("Error al listar los productos: "+ e.getMessage().toString()); }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productForm = req.getParameter("productForm");
        System.out.println(productForm);

        switch(productForm){
            case "searchProductFor":
                searchProductFor(req,resp);
                break;

            case "inactivateProduct":
                inactivateProduct(req,resp);
                break;

            case "activateProduct":
                activateProduct(req,resp);
                break;
            
// REGISTRAR PRODUCTO -------------------------------------------------------------------------------------------------------------------------- 
            case "Registrar Producto":
                Part partt = req.getPart("photoNewProduct");
                InputStream inputStream = partt.getInputStream();
                
                String newNameP = req.getParameter("nameNewProduct"); 
                String disponibilidadPoducto = req.getParameter("stockNewProduct"); 
                String detalles = req.getParameter("detailsNewProduct"); 
                String precio = req.getParameter("priceNewProduct"); 
                String empaque = req.getParameter("packageNewProduct"); 
                String estado = req.getParameter("statusNewProduct"); 

                newProductVO.setNameProduct(newNameP);
                newProductVO.setImageProduct(inputStream);
                newProductVO.setAvailabilityProduct(disponibilidadPoducto);
                newProductVO.setDescriptionProduct(detalles);
                newProductVO.setPriceProduct(precio);
                newProductVO.setIdFKpackaging(empaque);
                newProductVO.setStatusProduct(estado);
                
                try {
                    newProductDAO.registProduct(newProductVO);
                    req.getRequestDispatcher("product?productForm=listProducts").forward(req, resp);;
                } catch (Exception e) {
                    System.out.println("Error al listar los productos: "+ e.getMessage().toString());
                }
                break;
                

                case "searchOneProduct":
                    searchOneProduct(req,resp);
                    break;


                case "Actualizar Producto":
                    Part uPartt = req.getPart("updateImageProduct");
                    InputStream uInputStream = uPartt.getInputStream();
                    
                    String uIDP = req.getParameter("updateIDProduct");
                    String uNameP = req.getParameter("updateNameProduct"); 
                    String uDispoP = req.getParameter("updateStockProduct"); 
                    String uDetallesP = req.getParameter("updateDetailsProduct"); 
                    String uPrecioP = req.getParameter("updatePriceProduct"); 
                    String uEmpaqueP = req.getParameter("updatePackageProduct"); 
                    String uEstadop = req.getParameter("updateStatusProduct"); 
                    
                    newProductVO.setIdProduct(uIDP);
                    newProductVO.setImageProduct(uInputStream);
                    newProductVO.setNameProduct(uNameP); 
                    newProductVO.setAvailabilityProduct(uDispoP);
                    newProductVO.setDescriptionProduct(uDetallesP);
                    newProductVO.setPriceProduct(uPrecioP);
                    newProductVO.setIdFKpackaging(uEmpaqueP);
                    newProductVO.setStatusProduct(uEstadop);
                    
                    try {
                        newProductDAO.updateProductt(newProductVO);
                        req.getRequestDispatcher("product?productForm=Gestionar Productos").forward(req, resp);;
                    } catch (Exception e)  {
                        System.out.println("Error al listar los productos: "+ e.getMessage().toString());
                    }
                    break;
        }
    }





// BUSCAR PRODUCTO --------------------------------------------------------------------------------------------------------------------------
    private void searchProductFor(HttpServletRequest req, HttpServletResponse resp) {
        String dataForSearch = null;
        String typeDataSearchFor = null;

        if(req.getParameter("searchData")!= null){ dataForSearch = req.getParameter("searchData"); }
        if(req.getParameter("searchFor")!= null){ typeDataSearchFor = req.getParameter("searchFor"); }

        try{
            List<ProductVO> arrayProductSearch = newProductDAO.searchProduct(dataForSearch, typeDataSearchFor);
            req.setAttribute("product", arrayProductSearch);

            System.out.println("Finalizando...");

            RequestDispatcher showProductsSearch = req.getRequestDispatcher("views/product/listProductForSearch.jsp");
            showProductsSearch.forward(req, resp);

            System.out.println("Se terminó de buscar el producto.");
            System.out.println("------------------------------------------------------------------------");

        } catch(Exception x){
            System.out.println("(Servlet) Error al buscar el producto: "+ x.getMessage().toString());
        }
    }



// DEFINIR ESTADO DE PRODUCTO --------------------------------------------------------------------------------------------------------------------------
    //INACTIVAR
    private void inactivateProduct(HttpServletRequest req, HttpServletResponse resp){
        if(req.getParameter("defineStatusProduct")!= null){ newProductVO.setIdProduct(req.getParameter("defineStatusProduct"));}
        if(req.getParameter("defineStatus")!= null){ newProductVO.setStatusProduct(req.getParameter("defineStatus"));}

        try{
            newProductDAO.inactivateProduct(newProductVO);
            System.out.println("Finalizando...");

            RequestDispatcher showProducts = req.getRequestDispatcher("views/product/listProducts.jsp");
            showProducts.forward(req, resp);

            System.out.println("La inactivación del producto fue exitosa.");
            System.out.println("------------------------------------------------------------------------");
        } catch(Exception x){
            System.out.println("(Servlet) Error al inactivar el productp: "+ x.getMessage().toString());
        }
    }

    //ACTIVAR
    private void activateProduct(HttpServletRequest req, HttpServletResponse resp){
        if(req.getParameter("defineStatusProduct")!= null){ newProductVO.setIdProduct(req.getParameter("defineStatusProduct"));}
        if(req.getParameter("defineStatus")!= null){ newProductVO.setStatusProduct(req.getParameter("defineStatus"));}

        try{
            newProductDAO.activateProduct(newProductVO);
            System.out.println("Finalizando...");

            RequestDispatcher showUsers = req.getRequestDispatcher("views/product/listProducts.jsp");
            showUsers.forward(req, resp);

            System.out.println("La activación del producto fue exitosa.");
            System.out.println("------------------------------------------------------------------------");
        } catch(Exception x){
            System.out.println("(Servlet) Error al activar el producto: "+ x.getMessage().toString());
        }
    }

// BUSCAR UN PRODUCTO --------------------------------------------------------------------------------------------------------------------------
    private void searchOneProduct(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Buscando producto...");
        if(req.getParameter("searchProductToEdit")!= null){ newProductVO.setIdProduct(req.getParameter("searchProductToEdit"));}

        try{
            List<ProductVO> arrayOneProduct = newProductDAO.listOneProduct(newProductVO);
            req.setAttribute("product", arrayOneProduct);

            System.out.println("Finalizando...");
            
            RequestDispatcher formToUpdateUser = req.getRequestDispatcher("views/product/updateProduct.jsp");
            formToUpdateUser.forward(req, resp);

            System.out.println("Se terminó de listar los datos del producto buscado.");
            System.out.println("------------------------------------------------------------------------");
        }catch(Exception e){
            System.out.println("(Servlet) Error al listar los datos del producto buscado: "+ e.getMessage().toString());
        }
    }
}
