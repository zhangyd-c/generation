package com.generation.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.generation.entity.TableForm;
import com.generation.service.IGenerationClassService;
import com.generation.service.IGenerationMainService;
import com.generation.service.impl.GenerationClassServiceImpl;
import com.generation.service.impl.GenerationMainServiceImpl;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GenerationMainServlet
 */
@WebServlet("/GenerationClassServlet")
public class GenerationClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IGenerationClassService generationClassService = new GenerationClassServiceImpl();
	private IGenerationMainService generationMainService = new GenerationMainServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerationClassServlet() {
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
		
		TableForm sqlForm = generationMainService.getTableForm(request);
		Map<String, Object> map = generationClassService.getJava(sqlForm);
		// 页面用，保证队形
		String sql = (String) map.get("list");
		map.put("list", sql.replace("	", " "));
		JSONObject json = JSONObject.fromObject(map);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json.toString());
	}

}
