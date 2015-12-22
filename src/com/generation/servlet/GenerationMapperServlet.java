package com.generation.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.generation.entity.TableForm;
import com.generation.service.IGenerationMainService;
import com.generation.service.IGenerationMapperService;
import com.generation.service.impl.GenerationMainServiceImpl;
import com.generation.service.impl.GenerationMapperServiceImpl;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GenerationMainServlet
 */
@WebServlet("/GenerationMapperServlet")
public class GenerationMapperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IGenerationMapperService generationMapperService = new GenerationMapperServiceImpl();
	private IGenerationMainService generationMainService = new GenerationMainServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerationMapperServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TableForm sqlForm = generationMainService.getTableForm(request);
		Map<String, Object> map = generationMapperService.getMybatisSql(sqlForm);
		// 页面用，保证队形
		String sql = (String) map.get("list");
		map.put("list", sql.replace("	", " "));
		JSONObject json = JSONObject.fromObject(map);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
