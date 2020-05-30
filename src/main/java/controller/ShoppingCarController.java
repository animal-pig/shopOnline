package controller;

import com.alibaba.fastjson.JSONArray;
import entity.ShoppingCar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ProductService;
import service.ShoppingCarService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ShoppingCarController {
    @Resource
    private ProductService productService;
    @Resource
    private ShoppingCarService shoppingCarService;

//    转到购物车界面
    @RequestMapping(value = "/shopping_car")
    public String shopping_car(){
        return "shopping_car";
    }

//      添加购物车记录
    @RequestMapping(value = "/addShoppingCar",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addShoppingCar(int userId,int productId,int counts){
        System.out.println("数量为"+counts);
        ShoppingCar shoppingCar = shoppingCarService.getShoppingCar(userId,productId);
        if(shoppingCar == null){
            ShoppingCar shoppingCar1 = new ShoppingCar();
            shoppingCar1.setUserId(userId);
            shoppingCar1.setProductId(productId);
            shoppingCar1.setCounts(counts);
            shoppingCar1.setProductPrice(productService.getProductById(productId).getPrice()*counts);
            shoppingCarService.addShoppingCar(shoppingCar1);
        }
        else{
            shoppingCar.setCounts(shoppingCar.getCounts()+counts);
            shoppingCar.setProductPrice(productService.getProductById(productId).getPrice()*shoppingCar.getCounts());
            shoppingCarService.updateShoppingCar(shoppingCar);
        }
        Map<String, Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        System.out.println("我返回了");
        return resultMap;
    }

//    查询特定客户的购物车记录，录入Body内
    @RequestMapping(value = "/getShoppingCars",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getShoppingCars(int userId){
        List<ShoppingCar> shoppingCarList = shoppingCarService.getShoppingCars(userId);
        String shoppingCars = JSONArray.toJSONString(shoppingCarList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",shoppingCars);
        return resultMap;
    }

//    删除购物车记录
    @RequestMapping(value = "/deleteShoppingCar",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteShoppingCar(int userId,int productId){
        shoppingCarService.deleteShoppingCar(userId,productId);
        Map<String, Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        System.out.println("我返回了");
        return resultMap;
    }
}
