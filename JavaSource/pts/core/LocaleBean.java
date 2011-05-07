package pts.core;

import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="localeBean")
@SessionScoped
public class LocaleBean {

    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public Locale getLocale() 
    {
        return locale;
    }

    public String getLanguage() 
    {
        return locale.getLanguage();
    }

    public void setLanguage(String language) 
    {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

}

