package controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.ErrorConexionException;
import model.Libro;
import service.LibrosService;
import service.TemasService;


/**
 * Servlet implementation class LibrosAction
 */
@WebServlet("/LibrosAction")
public class LibrosAction extends HttpServlet {
	@Inject
	LibrosService glibros;
	@Inject
	TemasService gtemas;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idTema=Integer.parseInt(request.getParameter("tema"));
		List<Libro> libros;
		try {
			//si idTema es 0 es que ha elegido todos
			if(idTema==0){
				libros=glibros.recuperarLibros();
			}
			else{
				libros=glibros.recuperarLibros(idTema);
			}
			request.setAttribute("libros", libros);
			//esto se hace para que en la p�gina de libros se pueda saber que 
			//nombre de tema se seleccion�
			request.setAttribute("tema", idTema>0?gtemas.recuperarTemaPorId(idTema).getTema():"Todos");
		}
		catch(ErrorConexionException ex) {
			request.setAttribute("error", true);
			request.setAttribute("mensajeError", ex.getMessage());
		}
	}

}
