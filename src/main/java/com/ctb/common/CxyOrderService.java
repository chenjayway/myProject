package com.ctb.common;

import com.ctb.util.EncryptUtil;
import com.ctb.util.JsonUtils;
import com.ctb.util.MapUtils;
import com.ctb.util.httpclient.HttpHelper;
import com.ctb.util.httpclient.ResponseContent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**车行易订单提交/查询公共方法
 * Created by jayway on 2017/6/27.
 * return 生成订单返回如下 Json
 *   "code": 0,
     "errormsg": "下单成功",
     "successFlag": true,
     "data": {
         "orderId": "TEST07170320000012",
         "exOrderId": "2017032014055658",
         "userId": null,
         "userPhone": "13168681889",
         "createTime": "2017-03-20 14:05:56",
         "orderStatus": 2,
         "orderAmount": 100,
         "discountAmount": 1,
         "payAmount": 99,
         "payNo": "20170320459380642002"
 }
 */
@Component
public class CxyOrderService {
    //不同参数，和url调用同一方法
    public Map ctyOrder(Map paramMap, String url) throws Exception {
        String paramStr=JsonUtils.toJSONString(paramMap);
        String content=EncryptUtil.encrypt(paramStr);
        Map<String,String> requestMap=new HashMap<>();
        requestMap.put("content",content);
        requestMap.put("key",EncryptUtil.aeskey);
        String requestStr=JsonUtils.toJSONString(requestMap);
        ResponseContent responseContent= HttpHelper.postJsonEntity(url,requestStr);
        String resultStr=responseContent.getUTFContent();
        //返回结果toMap
        if(StringUtils.isEmpty(resultStr)){
            return new HashMap();
        }else{
            //先解密
            resultStr= EncryptUtil.aesDecrypt(resultStr);
            //转map
            Map reMap=MapUtils.stringToMap(resultStr);
            return reMap;
        }
    }

}
