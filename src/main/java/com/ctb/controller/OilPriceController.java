package com.ctb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ctb.mapper.OilPriceCityMapper;
import com.ctb.mapper.OilPriceMapper;
import com.ctb.model.OilPriceCity;
import com.ctb.util.DateUtils;
import com.ctb.util.JsonUtils;
import com.ctb.util.MapUtils;
import com.ctb.util.RedisUtil;
import com.ctb.vo.CityCode;
import com.ctb.vo.OilCity;
import com.ctb.vo.OilPrice;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.*;


@Controller
public class OilPriceController {
    private Logger logger = Logger.getLogger(OilPriceController.class);
    @Autowired
    private OilPriceCityMapper oilPriceCityMapper;
    @Autowired
    private OilPriceMapper oilPriceMapper;
//    @Autowired
//    private JedisPool jedisPool;
    @RequestMapping("/oilPrice")
    @ResponseBody
    public String getOilPrice( String code) throws UnsupportedEncodingException {
//        Jedis redis= jedisPool.getResource();
        String oilPice=RedisUtil.get("oil_price_"+code);
//        redis.close();
        OilPrice op = new OilPrice();
        if(!StringUtils.isEmpty(oilPice)){
            String returnStr=new String(oilPice.getBytes(),"UTF-8");
            List priceList= JsonUtils.parseObject(returnStr,List.class);
            JSONObject map= (JSONObject) priceList.get(0);
            op.setOil90(map.get("price_ninety_gasoline")+"");
            op.setOil93(map.get("price_ninety_three_gasoline")+"");
            op.setOil97(map.get("price_ninety_seven_gasoline")+"");
            op.setOilZero(map.get("price_zero_diesel")+"");
            op.setOilTrend(map.get("price_trend")+"");
        }else{
            String dstr=DateUtils.formatDate(new Date(),"yyyy-MM-dd");
            Date date=DateUtils.parseDate(dstr);
            com.ctb.model.OilPrice price= oilPriceMapper.getNowPriceByCityCode(code,date);
            if(price!=null){
                op.setOil90(price.getPriceNinetyGasoline());
                op.setOil93(price.getPriceNinetyThreeGasoline());
                op.setOil97(price.getPriceNinetySevenGasoline());
                op.setOilZero(price.getPriceZeroDiesel());
                op.setOilTrend(price.getPriceTrend());
            }
        }

        return JsonUtils.toJSONString(op);
    }
    @RequestMapping("/oilcity")
    @ResponseBody
    public String oilcity() throws UnsupportedEncodingException {
//        Jedis redis= RedisUtil.getJedis(jedisPool);
        String city=RedisUtil.get("oil_price_city");
        List<OilCity> reCity=new ArrayList<>();
        if(!StringUtils.isEmpty(city)){
            String returnStr=new String(city.getBytes(),"UTF-8");
            Map citys= MapUtils.parseJsonString(returnStr);
            Set<String> keys=citys.keySet();
            keys.remove("insert_time");
            logger.info("============================redisData");
            for(String str :keys){
                Object obj= citys.get(str);
                OilCity oilCity= JsonUtils.parseObject(obj.toString(),OilCity.class);
                oilCity.setInitial(oilCity.getInitial().toUpperCase());
                reCity.add(oilCity);
            }

        }else{

            logger.info("============================mysqlData");

            List<OilPriceCity> citys=oilPriceCityMapper.getAllCitys();
            for(OilPriceCity c:citys){
                OilCity vo=new OilCity();
                vo.setInitial(c.getPriceCityNameFirstLetter().toUpperCase());
                vo.setCode(c.getPriceCityId()+"");
                vo.setCity(c.getPriceCityName());
                reCity.add(vo);
            }
        }
        Comparator<OilCity> sort=(o, p)->o.getInitial().compareTo(p.getInitial());
        reCity.sort(sort);
//        logger.info("==================PoolNum; "+jedisPool.getNumActive());
        return JsonUtils.toJSONString(reCity);

    }
    @RequestMapping("/getMapCity")
    @ResponseBody
    public String getMapCity()throws UnsupportedEncodingException {
        List<OilPriceCity> citys = oilPriceCityMapper.getAllCitys();
        List<CityCode> reList = new ArrayList<>();
        Map<String, String> reMap = new HashMap<>();
        for (OilPriceCity c : citys) {
            reMap.put(c.getPriceCityName(), c.getPriceCityId() + "");
        }
        return JsonUtils.toJSONString(reMap);
    }

}
