package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option=request.getParameter("option");
		String url="login.html";
		switch(option) {
			case "doRegistrar": //registro de un cliente
				request.getRequestDispatcher("RegistrarAction").include(request, response);
				if(request.getAttribute("error")!=null) {
					url="errorConexion.jsp";
					break;
				}
				url="login.html";
				break;
			case "doLogin": //hacer un login 
				request.getRequestDispatcher("LoginAction").include(request, response);
				if(request.getAttribute("error")!=null) {
					url="errorConexion.jsp";
					break;
				}
				//para recuperar la lista de temas y pasarla a la .jsp
				request.getRequestDispatcher("TemasAction").include(request, response);
				if(request.getAttribute("error")!=null) {
					url="errorConexion.jsp";
					break;
				}
				url=(Boolean)request.getAttribute("resultado")?"temas.jsp":"error.html";
				break;
			case "doTemas":  //pulsación del enlace otro tema
				request.getRequestDispatcher("TemasAction").include(request, response);
				if(request.getAttribute("error")!=null) {
					url="errorConexion.jsp";
					break;
				}
				url="temas.jsp";
				break;
			case "doLibros": //pulsación del botón ver libros
				request.getRequestDispatcher("LibrosAction").include(request, response);
				if(request.getAttribute("error")!=null) {
					url="errorConexion.jsp";
					break;
				}
				url="libros.jsp";
				break;
			case "doAgregarCarrito":
            	request.getRequestDispatcher("AgregarCarritoAction").include(request, response);
            	if(request.getAttribute("error")!=null) {
					url="errorConexion.jsp";
					break;
				}
            	//volvemos a transferir la petición a este controlador de acción
            	//para que recupere los libros de nuevo y se los pase en un atributo
            	//de petoición al libros.jsp. Como el tema se pasa por el enlace de "Comprar"
            	//seguirá estando disponible como parámetro
            	request.getRequestDispatcher("LibrosAction").include(request, response);
            	if(request.getAttribute("error")!=null) {
					url="errorConexion.jsp";
					break;
				}
                url="libros.jsp";
                break;
            case "doEliminarCarrito":
            	request.getRequestDispatcher("EliminarCarritoAction").include(request, response);
            	request.getRequestDispatcher("LibrosAction").include(request, response);
            	if(request.getAttribute("error")!=null) {
					url="errorConexion.jsp";
					break;
				}
                url="libros.jsp";
                break;
			case "toRegistro":
				url="registro.html";
				break;
			case "toSalir":
				url="login.html";
				break;
			
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
