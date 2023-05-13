package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.pageable.Pageable;
import com.laptrinhjavaweb.pageable.PageableRequest;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.MessageUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = { "/admin-new" })
public class NewController extends HttpServlet {

	private static final long serialVersionUID = 2686801510274002166L;
	
	@Inject
	private INewService newService;
	
	@Inject
	private ICategoryService categoryService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Map request to Model
		NewModel newModel = FormUtil.toModel(NewModel.class, request);
		String view = "";
		if(newModel.getType().equals(SystemConstant.LIST)) {
//		Integer offset = (newModel.getPage()-1) * newModel.getMaxPageItem();
			Pageable pageable = new PageableRequest(newModel.getPage(), newModel.getMaxPageItem(), 
					new Sorter(newModel.getSortName(), newModel.getSortBy()));
			
			newModel.setListResult(newService.findAll(pageable));
			newModel.setTotalItem(newService.getTotalItem());
			newModel.setTotalPage((int) Math.ceil((double) newModel.getTotalItem() / newModel.getMaxPageItem()));
			
			view = "/views/admin/new/list.jsp";
		} else if (newModel.getType().equals(SystemConstant.EDIT)) {
			if (newModel.getId() != null) {
				newModel = newService.findOne(newModel.getId());
			}
			request.setAttribute("categories", categoryService.findAll());
			view = "/views/admin/new/edit.jsp";
		}
		MessageUtil.showMessage(request);
		request.setAttribute(SystemConstant.MODEL, newModel);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);	

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	} 

}
