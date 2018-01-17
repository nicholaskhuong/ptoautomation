package com.enclave.pto.dashboard;

import org.testng.annotations.Test;

import com.enclave.pto.utilities.BaseTestCase;
import com.enclave.pto.utilities.Credentials;
import com.enclave.pto.utilities.TestData;
@Credentials(user = "axis_support@abb.com", password = "Testuser1")
@TestData(fileName = "login.xls", startRow =1, endRow=2)
public class PTO_Login_DataDriven extends BaseTestCase{

  @Test(dataProvider="ExcelDataProvider")
  public void Login(String UserName, String Password) throws Exception {
//	  	driver.navigate().to(getServerURL() + "/SupplierPortal/#!CustomerAdminDashboard");
	  	System.out.println(UserName +" " +Password);
  }
}
