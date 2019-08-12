package com.novo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.novo.dto.NoBuyDto;
import com.novo.dto.UserDataDto;
import com.novo.entity.BuyPlanEntity;
import com.novo.entity.BuySchemeEntity;
import com.novo.entity.ChildSchemeEntity;
import com.novo.entity.CloudGoodsEntity;
import com.novo.entity.CloudGoodsSupplierEntity;
import com.novo.entity.PostBuyScheme;
import com.novo.entity.SupplierEntity;
import com.novo.entity.UserBuyPlanEntity;
import com.novo.entity.UserBuySchemeEntity;
import com.novo.entity.UserEntity;
import com.novo.entity.UserSupplierEntity;
import com.novo.service.IBuyPlanService;
import com.novo.service.IBuySchemeService;
import com.novo.service.IChildSchemeService;
import com.novo.service.ICloudGoodsService;
import com.novo.service.ISupplierService;
import com.novo.service.IUserBuySchemeService;
import com.novo.util.DateToString;
import com.novo.util.PageBean;

import net.sf.json.JSONArray;

@Controller
public class UserBuySchemeController {
	
	@Autowired
	private IUserBuySchemeService userBuySchemeService;
	@Autowired
	private IBuyPlanService buyPlanService;
	@Autowired
	private ISupplierService supplierService;
	@Autowired
	private ICloudGoodsService cloudGoodsService;
	@Autowired
	private IBuySchemeService buySchemeService;
	@Autowired
	private IChildSchemeService childSchemeService;
	private DateToString d;
	
	@RequestMapping("projectManage.novo")
	@ResponseBody
	public ModelAndView projectManage() {
		ModelAndView mv = new ModelAndView("projectManage");
		return mv;
	}
	
	@RequestMapping("getUserBuyScheme.novo")
	@ResponseBody
	public PageBean getUserBuyScheme(String subTime,String addTime,String state,int pageNo,int pageSize,HttpSession session) {
		
		UserEntity user = (UserEntity) session.getAttribute("user");
		return userBuySchemeService.getList(subTime, addTime, state, pageNo, pageSize,user);
		
	}
	
	@RequestMapping("addScheme.novo")
	@ResponseBody
	public List<UserBuyPlanEntity> addScheme(String planTime,String manageTime,HttpSession session){
		
		UserEntity user = (UserEntity) session.getAttribute("user");
		
		return userBuySchemeService.getUserBuyPlan(planTime,manageTime,user);
	}
	
	@RequestMapping("getDataOfId.novo")
	@ResponseBody
	public UserDataDto getDataOfId(int id,HttpServletRequest request) {
		UserEntity user = (UserEntity) request.getSession().getAttribute("user");
		List<UserSupplierEntity> userSuppList = supplierService.getListOfRe(user);
		List<SupplierEntity> suppList = new ArrayList<SupplierEntity>();
		for (UserSupplierEntity userSupplierEntity : userSuppList) {
			suppList.add(userSupplierEntity.getSupplier());
		}
		List<BuyPlanEntity> buyPlanList = buyPlanService.getListOfRe(id);
		List<CloudGoodsEntity> cloudGoodsList = cloudGoodsService.getList(buyPlanList);
		List<List<CloudGoodsSupplierEntity>> cloudGoodsSupplierList = userBuySchemeService.getListOfCloudGoodsSupplier(cloudGoodsList, suppList,user);
		UserDataDto userDate = new UserDataDto(cloudGoodsSupplierList, buyPlanList, suppList);
		return userDate;
	}
	
	@RequestMapping("determineScheme.novo")
	@ResponseBody
	public String determineScheme(int id,String[] goodsId,String[] supplierId,String[] buyNum,HttpSession session) {
		UserEntity user = (UserEntity) session.getAttribute("user");
		userBuySchemeService.determineScheme(id, goodsId, supplierId, buyNum, user);
		return "success";
	}
	
	@RequestMapping("jumpSchemeDetails.novo")
	@ResponseBody
	public ModelAndView jumpSchemeDetails(int id,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("schemeDetails");
		request.setAttribute("id", id);
		return mv;
	}
	
	@RequestMapping("schemeDetails.novo")
	@ResponseBody
	public PageBean schemeDetails(int id,int pageNo,int pageSize,HttpServletRequest request) {
		PageBean pb = userBuySchemeService.getListOfChildScheme(id, pageNo, pageSize);
		return pb;
	}
	
	@RequestMapping("jumpChildSchemeDetails.novo")
	@ResponseBody
	public ModelAndView childSchemeDetails(int id,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("childSchemeDetails");
		request.setAttribute("id", id);
		return mv;
	}
	
	@RequestMapping("childSchemeDetails.novo")
	@ResponseBody
	public PageBean childSchemeDetails(int id,String comName,int pageNo,int pageSize) {
		return buySchemeService.getList(id, comName, pageNo, pageSize);
	}
	
	@RequestMapping("showNoBuy.novo")
	@ResponseBody
	public NoBuyDto showNoBuy(int id) {
		UserBuySchemeEntity ubs = userBuySchemeService.getById(id);
		return buySchemeService.getNoBuyGoods(buyPlanService.getListOfRe(ubs.getUbp().getId()), buySchemeService.getListById(id));
	}
	
	@RequestMapping("delUserBuyScheme.novo")
	@ResponseBody
	public String delUserBuyScheme(int id) {
		
		userBuySchemeService.delById(id);
		
		return "success";
	}
	
	@RequestMapping("submitScheme.novo")
	@ResponseBody
	public String submitScheme(int id,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpSession session = request.getSession();
		UserEntity user = (UserEntity) session.getAttribute("user");
		String url = "https://www.novochina.com/api.php?app_key=5D4DB8AD-F36E-4C38-BB53-80F37655CBBE&method=dsc.cart.insert.post&format=json&user_name="+user.getName()+"&password="+user.getPassword();
		List<PostBuyScheme> list = buySchemeService.getAllBuyScheme(id);
		JSONArray subMsgs = JSONArray.fromObject(list);
		String jsonString = subMsgs.toString();
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("data", jsonString);
		HttpEntity<MultiValueMap<String, String>> request1 = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> re = rt.postForEntity(url, request1, String.class);
		String str = re.getBody();	
		JSONObject json = JSONObject.parseObject(str);
		String result = json.getString("result");
		String error = json.getString("error");
		if("success".equals(result)) {
			if("0".equals(error)) {
				UserBuySchemeEntity ubs = userBuySchemeService.getById(id);
				ubs.setSubTime(d.getStringTime(new Date()));
				ubs.setState("1");
				userBuySchemeService.updata(ubs);
				return "成功加入购物车";
			}else {
				UserBuySchemeEntity ubs = userBuySchemeService.getById(id);
				ubs.setSubTime(d.getStringTime(new Date()));
				ubs.setState("1");
				userBuySchemeService.updata(ubs);
				String value = "";
				List<String> list0 = new ArrayList<String>();
				String[] goodsSn = error.split("\\\\");
				for(int i=0;i<goodsSn.length;i++) {
					if(i>0) {
						BuySchemeEntity bs = buySchemeService.getByGoodsSn(Integer.parseInt(goodsSn[i]));
						ChildSchemeEntity cs = childSchemeService.getById(bs.getChildScheme().getId());
						UserBuySchemeEntity ubs0 = userBuySchemeService.getById(cs.getUbs().getId());
						cs.setPrice(cs.getPrice()-bs.getReBuyNum()*bs.getPrice());
						cs.setBuyNum(cs.getBuyNum()-1);
						childSchemeService.updata(cs);
						ubs0.setTotalPrice(ubs0.getTotalPrice()-bs.getReBuyNum()*bs.getPrice());
						ubs0.setReBuyNum(ubs0.getReBuyNum()-1);
						userBuySchemeService.updata(ubs0);
						bs.setReBuyNum(0);
						bs.setPrice(0);
						bs.setChildScheme(null);
						buySchemeService.updata(bs);
						list0.add(bs.getComName());
					}
				}
				if(list0.size()==1) {
					return "成功加入购物车,但"+list0.get(0)+"库存、价格不满足或者未上架，并未加入购物车!";
				}else {
					for (int i=0;i<list0.size();i++) {
						if(i!=list0.size()-1) {
							value += list0.get(i)+"、";
						}else {
							value += list0.get(i)+"库存、价格不满足或者未上架，并未加入购物车!";
						}
					}
					return "成功加入购物车,但"+value;
				}
			}
			
		}
		return error;
	}
	
	 private String cnToUnicode(String cn) {
	        char[] chars = cn.toCharArray();
	        String returnStr = "";
	        for (int i = 0; i < chars.length; i++) {
	          returnStr += "\\u" + Integer.toString(chars[i], 16);
	        }
	     
	        return returnStr;
	    }
}
