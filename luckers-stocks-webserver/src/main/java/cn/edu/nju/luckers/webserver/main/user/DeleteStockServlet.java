package cn.edu.nju.luckers.webserver.main.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.NotBoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nju.luckers.database_getter_interface.common.RMIService;
import cn.edu.nju.luckers.database_getter_interface.service.favorites.FavoriteService;
import cn.edu.nju.luckers.database_getter_interface.service.login.po.UserIdentity;
import net.sf.json.JSONObject;

public class DeleteStockServlet extends HttpServlet {
	/**
	 * 删除股票的servlet
	 */
	private static final long serialVersionUID = 1L;
	FavoriteService service;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			service = (FavoriteService) RMIService.getInstance(FavoriteService.class);
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		response.setContentType("text/html;charset=gbk");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String stockID = request.getParameter("stockID");
 
		boolean isOK = service.deleteStock(new UserIdentity(username, "123456"), stockID);

		JSONObject member = new JSONObject();
		member.put("remind", isOK);

		out.println(member);
		out.flush();
		out.close();
	}

}
