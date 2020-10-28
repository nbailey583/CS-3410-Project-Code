package seniority;

public class Employee implements Comparable<Employee>
{
	private String name, dateHired, firstName, lastName;
	private int day, month, year;
	private String department;
	public Employee(String name, String dateHired, String department)
	{
		this.name = name;
		String[] nameTokens = name.split(" ");
		this.firstName = nameTokens[0];
		this.lastName = nameTokens[1];
		this.dateHired = dateHired;
		String[] tokens = dateHired.split("/");
		this.month = Integer.parseInt(tokens[0]);
		this.day = Integer.parseInt(tokens[1]);
		this.year = Integer.parseInt(tokens[2]);
		this.department = department.toUpperCase();
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDateHired()
	{
		return dateHired;
	}
	
	int getMonth()
	{
		return month;
	}
	
	int getDay()
	{
		return day;
	}
	
	int getYear()
	{
		return year;
	}
	
	String getDepartment()
	{
		return department;
	}
	
	public int compareTo(Employee e)
	{
		if(getYear() < e.getYear())
		{
			return -1;
		}
		
		else if(getYear() > e.getYear())
		{
			return 1;
		}
		
		else if(getYear() == (e.getYear()))
		{
			if(getMonth() < e.getMonth())
			{
				return -1;
			}
			
			else if(getMonth() > e.getMonth())
			{
				return 1;
			}
			
			else if(getMonth() == (e.getMonth()))
			{
				if(getDay() < e.getDay())
				{
					return -1;
				}
				
				else if(getDay() > e.getDay())
				{
					return 1;
				}
				
				else if(getDay() == (e.getDay()))
				{
					if(lastName.compareTo(e.lastName) == 0)
					{
						return firstName.compareTo(e.firstName);
					}
					else
					{ 
						return lastName.compareTo(e.lastName);
					}
				}
			}
		}
		return 0;
	}
}
