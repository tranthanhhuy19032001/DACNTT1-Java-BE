package com.laptrinhjavaweb.controller.admin.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.pageable.Pageable;
import com.laptrinhjavaweb.pageable.PageableRequest;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.HttpUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = { "/api/admin-news" })
public class NewAPI extends HttpServlet {

	@Inject
	private INewService newService;

	private static final long serialVersionUID = 1L;

	private Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		NewModel newModel = FormUtil.toModel(NewModel.class, request);

		Pageable pageable = new PageableRequest();
//		newModel.setListResult(newService.findAll(pageable));
		List<NewModel> newsList = newService.findAll(pageable);
		
		String newsJsonString = this.gson.toJson(newsList);

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(newsJsonString);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		// Convert json into model
		NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);

//		System.out.println(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		if(newModel.getCreatedBy() == null) {
			newModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		}

		// save data into db
		newModel = newService.save(newModel);
		// response data json to client
		objectMapper.writeValue(response.getOutputStream(), newModel);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// Convert json into model
		NewModel updateNew = HttpUtil.of(request.getReader()).toModel(NewModel.class);

		if(updateNew.getModifiedBy() == null) {
			updateNew.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		}
		// update data into db
		updateNew = newService.update(updateNew);

		objectMapper.writeValue(response.getOutputStream(), updateNew);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// Convert json into model
		NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);
//		System.out.println(newModel);
		// handle in business layer
		newService.delete(newModel.getIds());
		// response data
		objectMapper.writeValue(response.getOutputStream(), "{}");
	}

//	private void saveOrUpdate() {
//		
//	}

}
