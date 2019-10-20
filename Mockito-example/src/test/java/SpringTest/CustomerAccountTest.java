package SpringTest;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.sql.SQLException;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Spring.CustomerAccount;
import Spring.CustomerAccountDAO;
import Spring.NoAccountCreatedException;
import Spring.NoSuchCustomerAccountException;

public class CustomerAccountTest 
{
	static CustomerAccountDAO Accountobj;
	static CustomerAccount ca;
	

	@Before
	public void create() throws Exception 
	{
		ca=mock(CustomerAccount.class);
		Accountobj=mock(CustomerAccountDAO.class);
		
		ca.createNewAccount("ABC","67836813");
		//when(Accountobj.newAcctNumber("John","78786767")).thenReturn("1111222233334444");
		Accountobj.saveAccount(ca);
		when(Accountobj.newAcctNumber("ABC","67836813")).thenReturn("1111222233334444");
		
		ca.updateCustomerName("67676767","DEF");
		when(Accountobj.updateAccount(ca)).thenReturn(true);
		Accountobj.saveAccount(ca);
		
		when(Accountobj.getAccount("5656565656")).thenReturn(null);
		
		when(Accountobj.deleteAccount(ca)).thenReturn(true);
	}
	@Test
	public void createnewaccounttest() throws SQLException, NoAccountCreatedException 
	
	{
		assertSame("1111222233334444",Accountobj.newAcctNumber("ABC","67836813"));
	}
	
	@Test
	public void updateaccounttest() throws SQLException, NoSuchCustomerAccountException
	{
		assertSame(true,Accountobj.updateAccount(ca));
		
	}
	@Test
	public void getNumbertest() throws SQLException
	{
		assertEquals(null,Accountobj.getAccount("5656565656"));
		
	}
	
	@Test
	public void getdeleteAccount() throws SQLException 
	{
		assertEquals(true,Accountobj.deleteAccount(ca));
		
	}
	
}
