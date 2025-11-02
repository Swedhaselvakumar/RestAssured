package restAssured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.OrderDetail;
import pojo.Orders;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

public class ECommerceAPITest {

	public static void main(String[] args) {

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();

		LoginRequest loginreq = new LoginRequest();
		loginreq.setUserEmail("swedhakabi@gmail.com");
		loginreq.setUserPassword("*Hansika12*");

		RequestSpecification res = given().log().all().spec(req).body(loginreq);

		ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();

		LoginResponse loginresponse = res.when().post("/api/ecom/auth/login").then().spec(resspec).extract().response()
				.as(LoginResponse.class);

		String Token = loginresponse.getToken();
		String Userid = loginresponse.getUserId();

		System.out.println("Token: " + Token);

		// Add Product------------------

		RequestSpecification addProduct = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", Token).build();

		RequestSpecification resaddproduct = given().log().all().spec(addProduct).param("productName", "Laptop")
				.param("productAddedBy", Userid).param("productCategory", "fashion")
				.param("productSubCategory", "shirts").param("productPrice", "11500")
				.param("productDescription", "Addias originals").param("productFor", "women")
				.multiPart("productImage", new File("C://Users//Swedha//Downloads//ElegantAmberPerfumeBottl.jpeg"));

		String responseAddProduct = resaddproduct.when().post("api/ecom/product/add-product").then().log().all()
				.extract().response().asString();

		JsonPath js = new JsonPath(responseAddProduct);
		String productId = js.get("productId");

		System.out.println("productId: " + productId);

		// Create Order------------------------

		RequestSpecification createOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", Token).setContentType(ContentType.JSON).build();

		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setCountry("India");
		orderDetail.setProductOrderedId(productId);

		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		orderDetailList.add(orderDetail);

		Orders orders = new Orders();
		orders.setOrders(orderDetailList);

		RequestSpecification createOrderReq = given().log().all().spec(createOrderBaseReq).body(orders);

		String responseAddOrder = createOrderReq.when().post("/api/ecom/order/create-order").then().log().all()
				.extract().response().asString();
		System.out.println(responseAddOrder);
		
		//DELETE the product-----------------
		RequestSpecification deleteOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", Token).setContentType(ContentType.JSON).build();
		
		RequestSpecification deleteOrderReq = given().log().all().spec(deleteOrderBaseReq).pathParam("productId", productId);
		
		
		//for using pathparam use curly{} braces for sending it in runtime.....
		String deleteOrderresponse = deleteOrderReq.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all()
		.extract().response().asString();
		
		JsonPath js1 = new JsonPath(deleteOrderresponse);
		String message = js1.get("message");
		
		Assert.assertEquals("Product Deleted Successfully", message);
		//System.out.println(deleteOrderresponse);

	}

}
