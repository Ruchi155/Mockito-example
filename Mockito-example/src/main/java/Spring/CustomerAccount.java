
package Spring;
import java.sql.SQLException;



// Uses the CustomerAccountDAO to save to the database
public class CustomerAccount 
{
	static CustomerAccount instance=null;
	private String custName;
	private String custPhone;
	private String custAcctNumber;
	public CustomerAccount() 
	{
		// create empty CustomerAccount
		}
	/** public static CustomerAccount getInstance()
	{
		// TODO Auto-generated method stub
		if(instance==null)
		{
			instance=new CustomerAccount();
		}
		return instance;
	}*/
public CustomerAccount createNewAccount(String name, String phone)throws SQLException, NoAccountCreatedException 
{
		CustomerAccount newAcct = null;
		String acctNum = "";
		CustomerAccountDAO cad = new CustomerAccountDAO();
	try 
	{
		acctNum = cad.newAcctNumber(name, phone);
	} 
	catch (SQLException se) 
	{
		acctNum  = cad.newAcctNumber(name, phone);
	
		
	catch (NoAccountCreatedException se1) 
	{
		throw new NoAccountCreatedException(String.format("Account for %s at %s not created", name, phone));
	}

	
	custName = name;
	custPhone = phone;
	custAcctNumber = acctNum;
	try 
	{
		cad.saveAccount(this);
	} 
	catch (SQLException se2) 
	{
		if (se2.getErrorCode() != 803) throw new NoAccountCreatedException
		(String.format("Account for %s at %s not created with account number %s", name, phone, acctNum));
		}
	return newAcct;
}

public CustomerAccount updateCustomerName(String acctNum, String name) throws NoSuchCustomerAccountException
{
	CustomerAccountDAO cad = new CustomerAccountDAO();
	try 
	{
		custName = name;
		cad.updateAccount(this);
	} 
	catch (SQLException se) 
	{ // unable to find the record to be updated throws 
		new NoSuchCustomerAccountException(String.format("No customer record with acctount number %s ", acctNum));
	}
	return null;
	}

}

