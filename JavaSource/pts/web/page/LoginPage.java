package pts.web.page;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;

import pts.core.LoginBean;

@ManagedBean(name="loginPage")
@RequestScoped
public class LoginPage
{
	public static final String BEAN_NAME = "loginPage";
	
	public static LoginPage getInstance()
	{
		return (LoginPage)FacesContext.getCurrentInstance().
			getExternalContext().getRequestMap().get(BEAN_NAME);
	}
	
	private String user;
	private String password;

	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getUser()
	{
		return user;
	}
	
	public void setUser(String user)
	{
		this.user = user;
	}
	
	public String doLogin() throws IOException, ServletException
    {
		boolean loggedIn = LoginBean.getInstance().login(user, password);
		
		if(loggedIn)
		{
			return "success";
		}
		else
		{
			return "fail";
		}
    }
}
