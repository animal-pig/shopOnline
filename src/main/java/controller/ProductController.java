package controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import entity.Product;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import reply.Response;
import service.ProductService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    @Resource
    private ProductService productService;

    @Autowired
    HttpSession httpSession;
//    查询所有产品
    @RequestMapping(value = "/getAllProducts")
    @ResponseBody
    public Map<String,Object> getAllProducts(){
        List<Product> productList = new ArrayList<>();
        productList = productService.getAllProduct();
//        将对象转成字符通过@ResponseBody写入到response 的body内
        String allProducts = JSONArray.toJSONString(productList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("allProducts",allProducts);
        return resultMap;
    }

    @RequestMapping(value = "/getVndorProducts")
    @ResponseBody
    public Map<String,Object> getVndorProducts(){
        List<Product> productList = new ArrayList<>();
        User user = (User)httpSession.getAttribute("currentUser");
        productList = productService.getProductByUserId(user.getId());
//        将对象转成字符通过@ResponseBody写入到response 的body内
        String allProducts = JSONArray.toJSONString(productList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("allProducts",allProducts);
        return resultMap;
    }

    //删除特定id商品
    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    @ResponseBody
    public Response deleteProduct(int id) {
        return productService.deleteProduct(id);
    }

//    添加商品
    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addProduct(String name,String description,String keyWord,int price,int counts,int type) {
        System.out.println("添加了商品："+name);
        String result ="fail";
        User user = (User)httpSession.getAttribute("currentUser");
        Product product = new Product();
        product.setUserId(user.getId());
        product.setName(name);
        product.setDescription(description);
        product.setKeyWord(keyWord);
        product.setPrice(price);
        product.setCounts(counts);
        product.setType(type);
        productService.addProduct(product);
        result = "success";
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

//    通过商品id查找商品，商品信息存入session
    @RequestMapping(value = "/productDetail", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> productDetail(int id, HttpSession httpSession) {
        System.out.println("I am here!"+id);
        Product product = productService.getProductById(id);
        httpSession.setAttribute("productDetail",product);
        System.out.print("I am here"+product.getName());
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        return resultMap;
    }

//    转到product_detail页面
    @RequestMapping(value = "/product_detail")
    public String product_detail() {
        return "product_detail";
    }


//     关键字保存到session中
    @RequestMapping(value = "/searchPre", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> searchPre(String searchKeyWord,HttpSession httpSession) {
        httpSession.setAttribute("searchKeyWord",searchKeyWord);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        return resultMap;
    }


//    转到search页面
    @RequestMapping(value = "/search")
    public String search() {
        return "search";
    }


//    根据关键词查找商品信息
    @RequestMapping(value = "/searchProduct", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> searchProduct(String searchKeyWord){
        System.out.println("我到了SearchProduct"+searchKeyWord);
        List<Product> productList = new ArrayList<Product>();
        productList = productService.getProductsByKeyWord(searchKeyWord);
        String searchResult = JSONArray.toJSONString(productList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",searchResult);
        System.out.println("我返回了"+searchResult);
        return resultMap;
    }


//    根据商品id查找详细信息
    @RequestMapping(value = "/getProductById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getProductById(int id) {
        Product product = productService.getProductById(id);
        String result = JSON.toJSONString(product);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

//    上传图片
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam MultipartFile productImgUpload, String name, HttpServletRequest request) {
        String result = "fail";
        try{
            if(productImgUpload != null && !productImgUpload.isEmpty()) {
                String fileRealPath = request.getSession().getServletContext().getRealPath("/static/img");
                int id = productService.getProductByName(name).getId();
                String fileName = String.valueOf(id)+".jpg";
                System.out.println("图片名字"+fileName);
                File fileFolder = new File(fileRealPath);
                System.out.println("fileRealPath=" + fileRealPath+"/"+fileName);
                if(!fileFolder.exists()){
                    fileFolder.mkdirs();
                    System.out.println("创建文件成功");
                }
//                File file = new File(fileFolder,fileName);
                productImgUpload.transferTo(new File(fileRealPath,fileName));
                result = "success";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }
}
