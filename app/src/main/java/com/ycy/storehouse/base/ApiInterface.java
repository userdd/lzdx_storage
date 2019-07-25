package com.ycy.storehouse.base;


import com.ycy.storehouse.utils.RestApi;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {

    //服务器地址
    String BASE_URL = "http://store-service-landa.lz-cc.com/";

    class ApiFactory {
        private volatile static Object monitor = new Object();
        private static ApiInterface apiSingleton;

        public static ApiInterface createApi() {
            if (apiSingleton == null) {
                synchronized (monitor) {
                    if (apiSingleton == null) {
                        apiSingleton = RestApi.getInstance().create(BASE_URL, ApiInterface.class);
                    }
                }
            }
            return apiSingleton;
        }
    }

    /**
     * 管理员登录
     */
    @FormUrlEncoded
    @POST("admin/login")
    Call<ResponseBody> login(@Field("accountName") String account, @Field("accountPass") String password);

    /**
     * 物品类别
     */
    @GET("category/goodsCategory")
    Call<ResponseBody> goodsCategory();

    /**
     * 物品子类
     */
    @GET("category/categoryList")
    Call<ResponseBody> goodsCategoryAll();

    /**
     * 调研信息
     */
    @FormUrlEncoded
    @POST("surveyInfo/selectSurveyInfo")
    Call<ResponseBody> selectSurveyInfo(@Field("page") int page, @Field("size") int size, @Field("name") String name,
                                        @Field("categoryId") Integer categoryId,@Field("startTime") String startTime,
                                        @Field("endTime") String endTime);

    /**
     * 添加调研信息
     */
    @FormUrlEncoded
    @POST("surveyInfo/addSurveyInfo")
    Call<ResponseBody> addSurveyInfo(@Field("name") String name, @Field("address") String address,
                                     @Field("remarks") String remarks, @Field("price") int price,
                                     @Field("unitId") int unitId, @Field("categoryId") int categoryId,
                                     @Field("goodsName") String goodsName,@Field("shopName") String shopName);

    /**
     * 查看调研信息
     */
    @FormUrlEncoded
    @POST("surveyInfo/surveyInfo")
    Call<ResponseBody> selectInfo(@Field("id") int id);


    /**
     * 查询物品列表
     */
    @FormUrlEncoded
    @POST("admin/goodsInfo")
    Call<ResponseBody> goodsInfoList(@Field("page") int page, @Field("size") int size, @Field("name") String name,
                                     @Field("cateId") Integer cateId, @Field("storeId") Integer storeId);

    /**
     * 查询仓库列表
     */
    @GET("store/store/use")
    Call<ResponseBody> goodsStore();

    /**
     * 单位列表
     */
    @GET("unit/selectUnit")
    Call<ResponseBody> goodsUnit();

    /**
     * 查询商品出入库及库存
     */
    @FormUrlEncoded
    @POST("admin/selectGoodsInfoAll")
    Call<ResponseBody> goodsInfo(@Field("id") int id);

    /**
     * 盘点
     */
    @FormUrlEncoded
    @POST("inventory/addGoodsLoos")
    Call<ResponseBody> addGoodsLoos(@Field("goodsId") int goodsId,@Field("name") String name,@Field("number") int number,
                                    @Field("price") int price,@Field("position") String position,
                                    @Field("lRemarks") String lRemarks,@Field("state") int state,
                                    @Field("unitId") int unitId,@Field("providerId") int providerId,
                                    @Field("lSpecs") String lSpecs,@Field("storeId") int storeId);

    /**
     * 入库记录列表
     */
    @FormUrlEncoded
    @POST("enter/recordList")
    Call<ResponseBody> enterInfoList(@Field("goodsId") int id,@Field("page") int page, @Field("size") int size);

    /**
     * 入库记录列表
     */
    @FormUrlEncoded
    @POST("enter/recordList")
    Call<ResponseBody> enterInfoList(@Field("page") int page, @Field("size") int size);

    /**
     * 出库记录列表
     */
    @FormUrlEncoded
    @POST("out/recordList")
    Call<ResponseBody> outInfoList(@Field("goodsId") int id,@Field("page") int page, @Field("size") int size);

    /**
     * 出库记录列表
     */
    @FormUrlEncoded
    @POST("out/recordList")
    Call<ResponseBody> outInfoList(@Field("page") int page, @Field("size") int size);

    /**
     * 添加商品
     */
    @FormUrlEncoded
    @POST("enter/addRecord")
    Call<ResponseBody> addGoods(@Field("name") String name,@Field("pinYin") String pinYin,
                                    @Field("stock") double stock,@Field("price") int price,
                                @Field("cateId") int cateId, @Field("unitId") int unitId,
                                @Field("providerId") int providerId, @Field("storeId") int storeId,
                                @Field("minWarn") int minWarn, @Field("position") String position,
                                @Field("gSpecs") String gSpecs, @Field("gRemarks") String gRemarks,
                                @Field("address") String address);

    /**
     * 添加出库
     */
    @FormUrlEncoded
    @POST("out/add")
    Call<ResponseBody> addOutRecords(@Field("goodsId") int goodsId,@Field("person") String person,
                                @Field("cateId") int cateId, @Field("unitId") int unitId,
                                @Field("number") double number, @Field("address") String address,
                                @Field("accountId") int accountId,@Field("storeId") int storeId);

    /**
     * 添加入库
     */
    @FormUrlEncoded
    @POST("enter/addStock")
    Call<ResponseBody> addEnterRecords(@Field("goodsId") int goodsId,@Field("number") double number,
                                @Field("enterPrice") int enterPrice, @Field("address") String address);

    /**
     * 仓库出库人员列表
     */
    @FormUrlEncoded
    @POST("storeAccount/selectStoreAccount")
    Call<ResponseBody> outStoreList(@Field("storeId") int storeId);


    /**
     * 供应商
     */
    @POST("provider/getAllProvider")
    Call<ResponseBody> getAllProvider();


    /**
     * 上传图片
     */
    @Multipart
    @POST("admin/uploadFile")
    Call<ResponseBody> uploadFile(@PartMap Map<String, RequestBody> maps);

    /**
     * 调研添加图片
     */
    @FormUrlEncoded
    @POST("surveyInfo/updateSurveyInfo")
    Call<ResponseBody> updateSurveyInfo(@Field("sUrl") String sUrl,@Field("id")int id);


}
