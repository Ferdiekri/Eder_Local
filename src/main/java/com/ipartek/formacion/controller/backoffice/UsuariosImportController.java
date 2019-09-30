package com.ipartek.formacion.controller.backoffice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ipartek.formacion.model.ConnectionManager;
import com.ipartek.formacion.model.dao.UsuarioDAO;
import com.ipartek.formacion.model.pojo.Usuario;

/**
 * Servlet implementation class UsuariosImportController
 */
@WebServlet("/backoffice/migracion")
public class UsuariosImportController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final static Log LOG = LogFactory.getLog(UsuariosImportController.class);
	
	UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
	
	
	private static final String FICHERO = "personas.txt";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuariosImportController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int totalLineas = 0;
		int totalInsertadas = 0;
		int totalErroneas = 0;
		long tiempoInicio = System.currentTimeMillis();
		
		Usuario u = new Usuario();
		String[] campos;
		String registro = "";
		String nombre = "";
		
		try( BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/" + FICHERO )));
				Connection con = ConnectionManager.getConnection()){
			
			con.setAutoCommit(false);
			
			while ((registro = br.readLine()) != null) {

				totalLineas++;
				LOG.debug("Linea " + totalLineas);
				
				try {
					campos = registro.split(",");
					
					if (campos.length == 7) {
												
						nombre = campos[0] + " " +campos[1] + " " + campos[2];
						
						//u = new Usuario();
						u.setNombre(nombre);
						u.setContrasenya(campos[5]);

						usuarioDAO.migrar(u, con);
						
						totalInsertadas++;
						
					}else {
						totalErroneas++;
						LOG.warn("**** ERROR ****" + registro);
					}
					
				} catch (Exception e) {
					totalErroneas++;
					LOG.fatal("**** ERROR ****" + registro);
				}
				
			} //while
			
			con.commit();
			
			long tiempoFin = System.currentTimeMillis();
			
			request.setAttribute("lineasLeidas", totalLineas);
			request.setAttribute("lineasInsertadas", totalInsertadas);
			request.setAttribute("lineasErroneas", totalErroneas);
			request.setAttribute("tiempo", tiempoFin-tiempoInicio);
			
			LOG.info("---------------------------------------");
			LOG.info("--------- MIGRACIÓN TERMINADA ---------");
			LOG.info("---------------------------------------");
			LOG.info("");
			LOG.info("Leidas: " + totalLineas);
			LOG.info("Insertadas: " + totalInsertadas);
			LOG.info("Erroneas: " + totalErroneas);
			LOG.info("Tiempo: " + (tiempoFin-tiempoInicio) + " ms");
			LOG.info("Tiempo: " + ((tiempoFin-tiempoInicio)/1000) + " s");
			LOG.info("Tiempo: " + ((tiempoFin-tiempoInicio)/60000) + " min");
			
			request.getRequestDispatcher("migracion.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
