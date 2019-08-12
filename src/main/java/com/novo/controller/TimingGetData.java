package com.novo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.novo.entity.CloudGoodsEntity;
import com.novo.entity.CloudGoodsSupplierEntity;
import com.novo.entity.SupplierEntity;
import com.novo.entity.UserEntity;
import com.novo.entity.UserSupplierEntity;
import com.novo.service.ICloudGoodsService;
import com.novo.service.ICloudGoodsSupplierService;
import com.novo.service.ISupplierService;
import com.novo.service.IUserService;
import com.novo.service.IUserSupplierService;
import com.novo.util.DateToString;
import com.novo.util.GetNumByString;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class TimingGetData {
	@Autowired
	private IUserService userService;
	@Autowired
	private ISupplierService supplierService;
	@Autowired
	private ICloudGoodsService cloudGoodsService;
	@Autowired
	private ICloudGoodsSupplierService cloudGoodsSupplierService;
	@Autowired
	private IUserSupplierService userSupplierService;
	private GetNumByString getNumByString = new GetNumByString();
	// public static boolean isexecuted1 = false;
	//
	// public static boolean isexecuted2 = false;
	//
	// public static synchronized boolean drawMoney1() {
	//
	// return isexecuted1 ? false : (isexecuted1 = true);
	// }
	//
	// public static synchronized boolean drawMoney2() {
	// return isexecuted2 ? false : (isexecuted2 = true);
	// }

	// 定时执行的方法
	
	/**
	 * 定时更新商家信息
	 */
	public synchronized void execute1() {
		// System.out.println( java.util.UUID.randomUUID());
		// if (!drawMoney1()) {
		// return;
		// }
		// System.out.println("开始更新商品");
		try {
			String url = "https://www.novochina.com/api.php?app_key=5D4DB8AD-F36E-4C38-BB53-80F37655CBBE&method=dsc.shops.select.post&format=json";
			String json = getData(url);
			JSONArray myJson = JSONArray.fromObject(json);// 先将对象转成json数组
			for (int i = 0; i < myJson.size(); i++) {
				JSONObject job = myJson.getJSONObject(i);
				int userId = job.getInt("user_id");
				String suppName = job.getString("rz_shopName");
				SupplierEntity supp = new SupplierEntity();
				supp.setId(userId);
				supp.setName(suppName);
				if (supplierService.exist(userId)) {
					supplierService.updata(supp);
				} else {
					try {
						supplierService.save(supp);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			// } finally {
			// isexecuted1 = false;
		}

	}
	/**
	 * 定时更新诺耳本地库
	 */
	public synchronized void execute2() {

		try {
			Object num = cloudGoodsService.getLastTime();
			if (null == num) {
				num = -1;
			}
			int page = 0;
			while (true) {
				page++;
				String url = "http://api.novochina.com/api.php?app_key=5D4DB8AD-F36E-4C38-BB53-80F37655CBBE&method=dsc.selectlib.select.post&start="
						+ page + "&uptime=" + num;
				String json = getData(url);
				JSONArray myJson = JSONArray.fromObject(json);// 先将对象转成json数组
				if (!myJson.isEmpty()) {
					for (int i = 0; i < myJson.size(); i++) {
						JSONObject job = myJson.getJSONObject(i);
						int goodsId = job.getInt("goods_id");
						String comName = job.getString("common_name");

						String productFact = job.getString("manufacturer");
						String licenseNum = job.getString("license_number");
						String spec = job.getString("goods_attr");
						if (comName.equals("") || productFact.equals("") || licenseNum.equals("") || spec.equals("")) {
							continue;
						}
						String barCode = job.getString("bar_code");
						int lastUpdate = job.getInt("last_update");
						CloudGoodsEntity cg1 = new CloudGoodsEntity();
						cg1.setId(goodsId);
						cg1.setComName(comName);
						cg1.setProduceFact(productFact);
						cg1.setLicenseNo(licenseNum);
						cg1.setSpec(spec);
						cg1.setBarCode(barCode);
						cg1.setUptime(lastUpdate);
						try {
							cg1.setSpecDroduct(getNumByString.getNum(spec));
						} catch (Exception e) {
							e.printStackTrace();
						}
						CloudGoodsEntity cg0 = cloudGoodsService.getByID(goodsId);
						if (cg0 != null) {
							if (!cg0.getComName().equals(comName) || !cg0.getLicenseNo().equals(licenseNum)
									|| !cg0.getProduceFact().equals(productFact) || !cg0.getSpec().equals(spec)) {
								cg0.setBarCode(barCode);
								cg0.setUptime(lastUpdate);
								cg0.setComName(comName);
								cg0.setLicenseNo(licenseNum);
								cg0.setProduceFact(productFact);
								cg0.setSpec(spec);
								cg0.setSpecDroduct(cg1.getSpecDroduct());
								cloudGoodsService.updata(cg0);
							}
						} else {
							try {
								cloudGoodsService.save(cg1);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								// e.printStackTrace();
							}
						}

					}
				} else {
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// } finally {
			// isexecuted2 = false;
		}

	}

	/**
	 * 定时更新每个商家下商品信息
	 */
	public synchronized void execute3() {
		String time = "";// 第一个产品更新的时间
		List<UserEntity> userList = userService.getAllList();
		for (int n = 0; n < userList.size(); n++) {
			List<SupplierEntity> list = supplierService.getAll();
			int page, num;
			
			for (int m = 0; m < list.size(); m++) {
//				if(list.get(m).getId()==15587||list.get(m).getId()==15714) {
				
				String name = "";
				try {
					name = java.net.URLEncoder.encode(list.get(m).getName(), "utf-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				page = 0;
				num = 0;
				while (true) {
					page++;
					String urlGoods = "https://www.novochina.com/api.php?app_key=5D4DB8AD-F36E-4C38-BB53-80F37655CBBE&method=dsc.new_goods_list.select.get&page="
							+ page + "&page_size=100&user_name=" + userList.get(n).getName() + "&ru_name=" + name;
					String jsonGoods = getData(urlGoods);
					
					JSONArray jsonArr = JSONArray.fromObject(jsonGoods);// 先将对象转成json数组
					if (jsonArr.isEmpty()) {
						break;
					}
					
					CloudGoodsEntity cg = null;
					CloudGoodsSupplierEntity cgs = null;
					String promote_price = null;
					int time1 = (int) ((new Date()).getTime()/1000) ;
					for (int i = 0; i < jsonArr.size(); i++) {
						JSONObject job = jsonArr.getJSONObject(i);
						int id = job.getInt("lib_goods_id");
						//System.out.println(job);
						cg = cloudGoodsService.getByID(id);
						if (cg == null) {
							continue;
						}
						String price = job.getString("region_price");
						promote_price = job.getString("promote_price");
						int promote_start_date = Integer.parseInt(job.getString("promote_start_date"));
						int promote_end_date = Integer.parseInt(job.getString("promote_end_date"));
						cgs = new CloudGoodsSupplierEntity();
						if(promote_price!=null&&!"0.00".equals(promote_price)) {
							if(time1>=promote_start_date&&time1<=promote_end_date) {
								cgs.setPrice(promote_price);
							}else {
								if ("null".equals(price)) {
									cgs.setPrice(job.getString("shop_price"));
								} else {
									cgs.setPrice(price);
								}
							}
						}else {
							if ("null".equals(price)) {
								cgs.setPrice(job.getString("shop_price"));
							} else {
								cgs.setPrice(price);
							}
						}
						
						if ("null".equals(price)) {
							cgs.setStock(job.getInt("goods_number"));
						} else {
							cgs.setStock(job.getInt("region_number"));
						}

						if (job.getString("goods_packing").equals("0")) {
							cgs.setGoods_packing("1");
						} else {
							cgs.setGoods_packing(job.getString("goods_packing"));
						}
						

						cgs.setExpiryDate(job.getString("goods_brief"));
						cgs.setGoodsSn(job.getString("goods_sn"));
						cgs.setSale(job.getString("is_on_sale"));
						cgs.setExpiration_date(job.getString("expiration_date"));
						cgs.setProductiontime(job.getString("productiontime"));
						cgs.setSpec(job.getString("goods_attr"));
						cgs.setGoods(cg);
						cgs.setSupp(list.get(m));
						cgs.setUser(userList.get(n));
						cgs.setUpdateTime(new Date().getTime() + "");
						if (m == 0 && n == 0 && page == 1 && i == 0) {
							time = new Date().getTime() + "";
						}

						Object cgs11 = cloudGoodsSupplierService.exist(cgs);
						if (null != cgs11) {
							CloudGoodsSupplierEntity  cgs1 = (CloudGoodsSupplierEntity)cgs11;
							cgs1.setExpiration_date(cgs.getExpiration_date());
							cgs1.setExpiryDate(cgs.getExpiryDate());
							cgs1.setGoods(cgs.getGoods());
							cgs1.setGoods_packing(cgs.getGoods_packing());
							cgs1.setGoodsSn(cgs.getGoodsSn());
							cgs1.setPrice(cgs.getPrice());
							cgs1.setProductiontime(cgs.getProductiontime());
							cgs1.setSale(cgs.getSale());
							cgs1.setStock(cgs.getStock());
							cgs1.setSupp(cgs.getSupp());
							cgs1.setSpec(cgs.getSpec());
							cgs1.setUpdateTime(cgs.getUpdateTime());
							cgs1.setUser(cgs.getUser());
							cloudGoodsSupplierService.update(cgs1);
						

						} else {
							try {
								cloudGoodsSupplierService.save(cgs);
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								 e.printStackTrace();
							}
						}

					}
				}
				UserSupplierEntity us = new UserSupplierEntity();
				SupplierEntity supp = supplierService.getById(list.get(m).getId());
				num = cloudGoodsSupplierService.getAllNum(supp, userList.get(n));
				us.setCount(num);
				us.setSupplier(supp);
				us.setUser(userList.get(n));
				if (userSupplierService.getUS(userList.get(n).getId(), supp.getId()) == null) {
					us.setMarryState("0");
					try {
						userSupplierService.save(us);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
					}
				} else {
					us.setId(userSupplierService.getUS(userList.get(n).getId(), supp.getId()).getId());
					us.setMarryState(userSupplierService.getUS(userList.get(n).getId(), supp.getId()).getMarryState());
					userSupplierService.updataOfNum(us);
				}

			}

		}
		//}
		// 将未更新过的产品变为下架状态
		List<CloudGoodsSupplierEntity> list = cloudGoodsSupplierService.getListByTime(time);
		for (CloudGoodsSupplierEntity cloudGoodsSupplierEntity : list) {
			cloudGoodsSupplierEntity.setSale("0");
			cloudGoodsSupplierService.update(cloudGoodsSupplierEntity);
		}
	}


	public String getData(String addess) {
		URL url = null;
		HttpURLConnection httpConn = null;
		BufferedReader in = null;
		StringBuffer sb = new StringBuffer();
		try {
			url = new URL(addess);
			in = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
			String str = null;
			while ((str = in.readLine()) != null) {
				sb.append(str);
			}
		} catch (Exception ex) {
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
			}
		}
		String data = sb.toString();
		return data;

	}

}
