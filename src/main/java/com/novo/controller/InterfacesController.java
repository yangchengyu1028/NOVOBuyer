package com.novo.controller;


import net.sf.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/interface")
public class InterfacesController {

    @RequestMapping(value="/saveSupplierGoods.novo",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ResponseBody
    public String saveSupplierGoods(@RequestBody String json){
        JSONObject jsonObject =  JSONObject.fromObject(json);
        String comName = jsonObject.getString("comName");
        String spec = jsonObject.getString("spec");
        String produceFact = jsonObject.getString("produceFact");
        String licenseNo = jsonObject.getString("licenseNo");
        String barCode = jsonObject.getString("barCode");
        int stock = jsonObject.getInt("stock");
        String price = jsonObject.getString("price");
        String expiryDate = jsonObject.getString("expiryDate");
        String expiration_date = jsonObject.getString("expiration_date");
        String productiontime = jsonObject.getString("productiontime");
        String sale = jsonObject.getString("sale");
        int supplierId = jsonObject.getInt("supplierId");
        
        if(licenseNo.equals("国药准字")) {
        	
        }

        return "{\"code\":\""+0+"\",\"msg\":\"请求成功\"}";
    }
}
