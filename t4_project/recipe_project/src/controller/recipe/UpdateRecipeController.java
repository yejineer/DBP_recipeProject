package controller.recipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.Recipe;
import model.Procedure;
import model.Ingredient;
import model.service.IngredientManager;
import model.service.MemberManager;
import model.service.RecipeManager;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class UpdateRecipeController implements Controller {
	
	private static final Logger log = LoggerFactory.getLogger(UpdateRecipeController.class);
//	private static final int List = 0;
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		if (request.getMethod().equals("GET")) {	
    		// GET request: 레시피 수정 form (초기 값)요청	
    		// 원래는 UpdateRecipeFormController가 처리하던 작업을 여기서 수행
    		int recipe_id = Integer.parseInt(request.getParameter("recipe_id"));

    		log.debug("UpdateForm Request : {}", recipe_id);
    		
    		RecipeManager manager = RecipeManager.getInstance();
			Recipe recipe= manager.findRecipe(recipe_id);	// 수정하려는 사용자 정보 검색
			request.setAttribute("recipe", recipe);			

			request.setAttribute("curMemberId", 
					MemberSessionUtils.getLoginMemberId(request.getSession()));		
			return "/recipe/updateForm.jsp";
	    }	
		
		// POST request
    	
		/* writer 설정 위해 */
		MemberManager mManager = MemberManager.getInstance();
		String email_id = MemberSessionUtils.getLoginMemberId(request.getSession());
		String writer = mManager.findMember(email_id).getMname();
		Date nowTime = new Date();
		
		/* 사용자로부터 입력받아온 재료 정보와 조리 과정을 recipe객체의 멤버변수에 맞게 */
		String[] iname = request.getParameterValues("iname");
		String[] quantity = request.getParameterValues("quantity");
		String[] procText = request.getParameterValues("proc_text");
		String[] procId = request.getParameterValues("proc_id");
		
		IngredientManager imanager = IngredientManager.getInstance();
		List<Ingredient> iList = new ArrayList<>();
		for (int i = 0; i < iname.length; i++) {
			Ingredient ingredient = new Ingredient();
			if (iname[i] == null || iname[i].trim().equals("")) {	// ""만 들어올 경우를 방지
				continue;
			}
			ingredient.setIngredient_id(imanager.findIdByName(iname[i]));
			ingredient.setQuantity(quantity[i]);
			iList.add(ingredient);
		}

		/* 조리 과정들의 배열 */
		List<Procedure> pList = new ArrayList<>();
		for (int i = 0; i < procText.length; i++) {
			Procedure proc = new Procedure(); 
			if (procId[i] == null || procId[i].trim().equals("")) {	// ""만 들어올 경우를 방지
				continue;
			}
			proc.setProc_Id(Integer.valueOf(procId[i]));
			proc.setText(procText[i]);
			proc.setImg_url(null);
			pList.add(proc);
		}
		/* 조리 과정을 proc_id를 기준으로 오름차순으로 정렬*/
		pList.sort(new Comparator<Procedure>() {

			@Override
			public int compare(Procedure arg0, Procedure arg1) {
				// TODO Auto-generated method stub
				 int age0 = arg0.getProc_Id();
                 int age1 = arg1.getProc_Id();
                 if (age0 == age1)
                       return 0;
                 else if (age0 > age1)
                       return 1;
                 else
                       return -1;
			}
			
		});
		/* result_img */
		RecipeManager manager = RecipeManager.getInstance();
		String result_img = manager.findRecipe(Integer.parseInt(request.getParameter("recipe_id"))).getResult_img();
		
		/* request로 받아온 parameter들로 recipe 객체 생성*/
		Recipe updateRecipe = new Recipe(
				Integer.parseInt(request.getParameter("recipe_id")), //recipe_id는 DAO에서 시퀀스로 설정. 그래서 필요 X.
				30,
				request.getParameter("rname"),
				Integer.parseInt(request.getParameter("time")),
				result_img,
				0,
				pList,
				iList,
				null,
				writer,
				nowTime
		);
		
		log.debug("Update Recipe : {}", updateRecipe);
    	
		manager.update(updateRecipe);
		Recipe recipe = manager.findRecipe(Integer.parseInt(request.getParameter("recipe_id")));
		request.setAttribute("recipe", recipe);
		request.setAttribute("memberName", writer);
		request.setAttribute("curMemberId", 
				MemberSessionUtils.getLoginMemberId(request.getSession()));		
		return "/recipe/view.jsp";
	}

}
