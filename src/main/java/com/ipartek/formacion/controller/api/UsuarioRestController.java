package com.ipartek.formacion.controller.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ipartek.formacion.model.dao.UsuarioDAO;
import com.ipartek.formacion.model.pojo.Usuario;

/**
 * Servlet implementation class UsuarioRestController
 */
@WebServlet("/api/usuarios/")
public class UsuarioRestController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// cambiar el content type de text/html a application/json
		response.setContentType("application/json");
		response.setCharacterEncoding("uft8");
		
		String filtro = request.getParameter("filtro");
		String orden = request.getParameter("orden");
		
		//conseguir datos desde del DAO
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		
		
		if (filtro != null && orden != null && !filtro.isEmpty() && !orden.isEmpty()) {
			lista = usuarioDAO.getAllByNombre(filtro);
		}else {
			lista = usuarioDAO.getAll();
		}
		
		
		// capar campos que no queremos mostrar
		for( Usuario u: lista) {
			u.setContrasenya(null);
			u.setFechaCreacion(null);
			u.setFechaEliminacion(null);
			u.setRol(null);
		}
		
		
		// convertir POJOs a (String)JSON
		Gson gson = new Gson();
		String responseBody = gson.toJson(lista);
		
				
		// pintar datos por la salida
		ServletOutputStream out = response.getOutputStream();
		out.print(responseBody);
		out.flush(); // si la respuesta es muy grande conviene hacer esto
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(501);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(501);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(501);
	}

}
