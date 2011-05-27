package pts.model.network.properties;

import thewebsemantic.Namespace;

@Namespace(value="http://jscc.ru/pts#")
public class ElementProperty<T> 
{
	private String name;
	private T value;
	
	public ElementProperty()
	{
	}
	
	public ElementProperty(String name, T value)
	{
		this.name = name;
		this.value = value;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public T getValue() 
	{
		return value;
	}
	
	public void setValue(T value) 
	{
		this.value = value;
	}
	
	
}
