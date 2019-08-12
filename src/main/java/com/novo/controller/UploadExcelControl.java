package com.novo.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.novo.entity.BuyPlanEntity;
import com.novo.entity.BuySchemeEntity;
import com.novo.entity.CloudGoodsEntity;
import com.novo.entity.UserBuyPlanEntity;
import com.novo.entity.UserEntity;
import com.novo.entity.UserProductEntity;
import com.novo.service.IBuyPlanService;
import com.novo.service.IBuySchemeService;
import com.novo.service.ICloudGoodsService;
import com.novo.service.IUserBuyPlanService;
import com.novo.service.IUserProductService;
import com.novo.util.DateToString;
import com.novo.util.ImportExcelUtil;
import com.novo.util.ToExcelUtil;

@Controller

public class UploadExcelControl {

	@Autowired
	private IUserProductService userProductService;
	@Autowired
	private IUserBuyPlanService userBuyPlanService;
	@Autowired
	private IBuyPlanService buyPlanService;
	@Autowired
	private ICloudGoodsService cloudGoodsService;
	@Autowired
	private IBuySchemeService buySchemeService;
	private String percent1;

	/*
	 * public void test1(List<List<Object>> listob,HttpServletRequest request) {
	 * final List<List<Object>> listob0=listob; Thread t=new Thread(new Runnable() {
	 * public void run() { try {
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } } }); t.start(); Abc.t1 = t; }
	 * 
	 * @RequestMapping("testUp1.novo")
	 * 
	 * @ResponseBody public String testUp1() { String result = "";
	 * 
	 * if(Abc.t1 == null) { result = "null"; } else { result = "" +
	 * Abc.t1.isAlive(); }
	 * 
	 * return result; }
	 */

	@RequestMapping("localGoodsExcel.novo")
	public String localGoodsExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request)
			throws Exception {
		if (!file.isEmpty()) {
			try {
				// 这里将上传得到的文件保存指定目录下
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File("D:\\upload\\localgoodsfile\\",
						System.currentTimeMillis() + file.getOriginalFilename()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		InputStream in = null;

		List<List<Object>> listob = null;

		in = file.getInputStream();

		listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());

		// 该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
		HttpSession session = request.getSession(false);
		UserEntity user = (UserEntity) session.getAttribute("user");
		DateToString d = new DateToString();
		UserProductEntity upe = null;
		CloudGoodsEntity upe1 = null;
		for (int i = 0; i < listob.size(); i++) {

			List<Object> lo = listob.get(i);

			upe = new UserProductEntity();
			upe.setErpNo(String.valueOf(lo.get(0)));

			if (userProductService.exist(upe.getErpNo(), user) == null) {
				upe.setComName(String.valueOf(lo.get(1)));
				upe.setSpec(String.valueOf(lo.get(2)));
				upe.setDrug(String.valueOf(lo.get(3)));
				upe.setUnit(String.valueOf(lo.get(4)));
				upe.setProduceFact(String.valueOf(lo.get(5)));
				upe.setBarCode(String.valueOf(lo.get(6)));
				upe.setLicenseNo(String.valueOf(lo.get(7)));

				upe.setUser(user);
				// 接下来与平台商品进行关联后存入本地数据库
				upe.setRelaState("0");
				upe.setSignError("0");
				// 判断
				
				if (upe.getLicenseNo().contains("国药准字")) {
					upe1 = cloudGoodsService.relevance(upe);
					if (upe1 != null) {
						upe.setRelaState("1");
						upe.setGoods(upe1);
						upe.setRelaTime(d.getString(new Date()));
						userProductService.add(upe);
					} else {
						userProductService.add(upe);
					}
				} else {
					upe1 = cloudGoodsService.relevanceByQuanZhi(upe);
					if (upe1 != null) {
						upe.setRelaState("1");
						upe.setGoods(upe1);
						upe.setRelaTime(d.getString(new Date()));
						userProductService.add(upe);
					} else {
						upe.setRelaState("0");
						userProductService.add(upe);
					}
				}
				upe1 = null;
			} else {
				continue;
			}

		}

		return "1";
	}

	@RequestMapping("buyPlan.novo")
	@ResponseBody
	public String buyPlan(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {

		if (!file.isEmpty()) {

			try {

				// 这里将上传得到的文件保存指定目录下

				FileUtils.copyInputStreamToFile(file.getInputStream(),

						new File("D:\\upload\\buyplanfile\\", System.currentTimeMillis() + file.getOriginalFilename()));

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

		InputStream in = null;

		List<List<Object>> listob = null;

		in = file.getInputStream();

		listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
		try {
			// 该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
			HttpSession session = request.getSession(false);
			UserEntity user = (UserEntity) session.getAttribute("user");
			UserBuyPlanEntity ubp = new UserBuyPlanEntity();
			Date date = new Date();
			DateToString d = new DateToString();
			ubp.setPlanTime(d.getStringTime(date));
			ubp.setUser(user);
			ubp.setState("0");
			ubp.setName(file.getOriginalFilename());
			userBuyPlanService.save(ubp);
			UserBuyPlanEntity userBuyPlanEntity = userBuyPlanService.query(d.getStringTime(date), user);
			int tolNum = 0;
			for (int i = 0; i < listob.size(); i++) {

				List<Object> lo = listob.get(i);
				
				BuyPlanEntity bpe = new BuyPlanEntity();
				bpe.setComName(String.valueOf(lo.get(0)));
				bpe.setSpec(String.valueOf(lo.get(1)));
				bpe.setProduceFact(String.valueOf(lo.get(2)));
				bpe.setLicenseNo(String.valueOf(lo.get(3)));
				bpe.setBarCode(String.valueOf(lo.get(4)));
				bpe.setDrug(String.valueOf(lo.get(5)));
				bpe.setUnit(String.valueOf(lo.get(6)));
				bpe.setErpNo(String.valueOf(lo.get(7)));
				if (String.valueOf(lo.get(8)) != null && !"".equals(String.valueOf(lo.get(8)))) {
					bpe.setEvaluate(Float.parseFloat(String.valueOf(lo.get(8))));
				}
				if (String.valueOf(lo.get(9)) != null && !"".equals(String.valueOf(lo.get(9)))) {
					bpe.setBuyNum(Integer.parseInt(String.valueOf(lo.get(9))));
				}

				bpe.setUserBuyPlan(userBuyPlanEntity);

				// 接下来与本地商品库进行关联后存入本地数据库
				UserProductEntity upe = userProductService.query(String.valueOf(lo.get(7)), user);
				if (upe != null) {
					bpe.setUserProduce(upe);
					bpe.setState("1");
				} else {
					bpe.setState("0");
				}
				buyPlanService.save(bpe);
				tolNum += 1;
			}
			userBuyPlanEntity.setTotalGoods(tolNum);
			userBuyPlanService.update(userBuyPlanEntity);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "1";
	}

	@RequestMapping("downloadBuyPlan.novo")
	@ResponseBody
	public String downloadBuyPlan(int id) throws Exception {
		List<BuyPlanEntity> list = buyPlanService.getListOfNoRe(id);
		ToExcelUtil te = new ToExcelUtil();
		String name = userBuyPlanService.getById(id).getName();
		te.toEx1(list, name);

		return "success";
	}

	@RequestMapping("downloadBuyScheme.novo")
	@ResponseBody
	public String downloadBuyScheme(int id) throws Exception {
		List<BuySchemeEntity> list = buySchemeService.getUnpurchased(id);
		ToExcelUtil te = new ToExcelUtil();
		String name = userBuyPlanService.getById(id).getName();
		te.toEx2(list, name);

		return "success";
	}

}