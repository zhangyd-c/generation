package com.generation.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.generation.entity.TableForm;
import com.generation.service.IGenerationMainService;
import com.generation.service.impl.GenerationMainServiceImpl;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class GenerationOfTablesServlet
 */
@WebServlet("/GenerationOfTablesServlet")
public class GenerationOfTablesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IGenerationMainService generationMainService = new GenerationMainServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerationOfTablesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TableForm sqlFrom = generationMainService.getTableForm(request);
		List<String> list = generationMainService.getTableNameList(sqlFrom);
		System.out.println(list);
		JSONArray json = JSONArray.fromObject(list);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json.toString());
	}

}
