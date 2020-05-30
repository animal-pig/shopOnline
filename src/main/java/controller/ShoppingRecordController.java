package controller;

import com.alibaba.fastjson.JSONArray;
import dao.ProductDao;
import entity.Product;
import entity.ShoppingRecord;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ProductService;
import service.ShoppingRecordService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShoppingRecordController {
    @Resource
    private ProductService productService;
    @Resource
    private ShoppingRecordService shoppingRecordService;

    @Autowired
    HttpSession httpSession;
//    转单shopping_record界面
    @RequestMapping(value = "/shopping_record")
    public String shopping_record(){
        return "shopping_record";
    }

//    转到shopping_handle订单处理页面
    @RequestMapping(value = "/shopping_handle")
    public String shopping_handle(){
        return "shopping_handle";
    }


//    根据客户id，商品id，购买数量更新订单，用于下单
    @RequestMapping(value = "/addShoppingRecord",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addShoppingRecord(int userId,int productId,int counts){
        System.out.println("我添加了"+userId+" "+productId);
        String result = null;
        Product product = productService.getProductById(productId);
//        根据product查找商家id，然后注入订单；
//        int shangjiaId=productService.getSJid(productId);

        if(counts<=product.getCounts()) {
            ShoppingRecord shoppingRecord = new ShoppingRecord();
            shoppingRecord.setUserId(userId);
            shoppingRecord.setProductId(productId);
            shoppingRecord.setProductPrice(product.getPrice() * counts);
            shoppingRecord.setCounts(counts);
            shoppingRecord.setOrderStatus(0);
            shoppingRecord.setShangjiaId(product.getUserId());
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            shoppingRecord.setTime(sf.format(date));
            product.setCounts(product.getCounts()-counts);
            productService.updateProduct(product);
            shoppingRecordService.addShoppingRecord(shoppingRecord);
            result = "success";
        }
        else{
            result = "unEnough";
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }


//    根据客户id，商品id，订单时间查到特定订单，根据获取接收到的订单状态更改订单状态
    @RequestMapping(value = "/changeShoppingRecord",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> changeShoppingRecord(int userId,int productId,String time,int orderStatus){
        System.out.println("我接收了"+userId+" "+productId+" "+time+" "+orderStatus);
        ShoppingRecord shoppingRecord = shoppingRecordService.getShoppingRecord(userId,productId,time);
        System.out.println("我获取到了了"+shoppingRecord.getTime());
        shoppingRecord.setOrderStatus(orderStatus);
        shoppingRecordService.updateShoppingRecord(shoppingRecord);

        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        System.out.println("我成功fanhui了");
        return resultMap;
    }


//    查询特定用户的所有订单
    @RequestMapping(value = "/getShoppingRecords",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getShoppingRecords(int userId){
        List<ShoppingRecord> shoppingRecordList = shoppingRecordService.getShoppingRecords(userId);
        String shoppingRecords = JSONArray.toJSONString(shoppingRecordList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",shoppingRecords);
        return resultMap;
    }


//    根据订单状态OrderStatus情况获取订单
    @RequestMapping(value = "/getShoppingRecordsByOrderStatus",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getShoppingRecordsByOrderStatus(int orderStatus){
        List<ShoppingRecord> shoppingRecordList = shoppingRecordService.getShoppingRecordsByOrderStatus(orderStatus);
        String shoppingRecords = JSONArray.toJSONString(shoppingRecordList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",shoppingRecords);
        return resultMap;
    }


//    获取全部订单信息
    @RequestMapping(value = "/getAllShoppingRecords",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getAllShoppingRecords(){
        User u=(User)httpSession.getAttribute("currentUser");
        List<ShoppingRecord> shoppingRecordList = shoppingRecordService.getAllShoppingRecords(u.getId());
        String shoppingRecords = JSONArray.toJSONString(shoppingRecordList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",shoppingRecords);
//        System.out.println("我反悔了"+shoppingRecords);
        return resultMap;
    }


//    查看某一购物者对于某一商品的订单情况
    @RequestMapping(value = "/getUserProductRecord",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getUserProductRecord(int userId,int productId){
        String result = "false";
        if(shoppingRecordService.getUserProductRecord(userId,productId)){
            result = "true";
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }
}
