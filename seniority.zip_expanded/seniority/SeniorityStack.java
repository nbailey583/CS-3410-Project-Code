package seniority;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class SeniorityStack
{
	Stack<Employee> emp = new Stack<Employee>();
	Stack<Employee> tempStAll = new Stack<Employee>();
	Stack<Employee> empTech = new Stack<Employee>();
	Stack<Employee> tempStTech = new Stack<Employee>();
	Stack<Employee> empFin = new Stack<Employee>();
	Stack<Employee> tempStFin = new Stack<Employee>();
	Stack<Employee> empMark = new Stack<Employee>();
	Stack<Employee> tempStMark = new Stack<Employee>();
	Stack<Employee> empSupp = new Stack<Employee>();
	Stack<Employee> tempStSupp = new Stack<Employee>();
	
	public SeniorityStack(String filename) throws FileNotFoundException
	{
		//Read and parse from file
		File file = new File(filename);
		Scanner input = new Scanner(file);
		while(input.hasNextLine())
		{
			String str = input.nextLine();
			String[] tokens = str.split(", ");
			//Assuming "FirstName LastName, DateHired(MM/DD/YYYY), Department (ie. Technical, FInancial, Marketing, Support" format
			String name = tokens[0];
			String dateHired = tokens[1];
			String department = tokens[2];
			addEmp(new Employee(name,dateHired, department));//Create stack of all Employees
			addDeptEmp(new Employee(name, dateHired, department));//Creates stacks of grouped employees by dept.
			
		}
		input.close();
	}
	
	private void addEmp(Employee e)//All Employees
	{
		if(emp.isEmpty())
		{
			emp.push(e);
		}
		
		if(!emp.isEmpty())
		{
			while(!emp.contains(e) && !emp.isEmpty())
			{
				Employee tempEmp = emp.peek();
				if(tempEmp.compareTo(e) == 1)
				{
					tempStAll.push(emp.pop());
				}
				else break;
			}
			if(!emp.contains(e))
			{
				emp.push(e);
			}
			while(!tempStAll.isEmpty()) 
			{
				addEmp(tempStAll.pop());
			}
		}
	}
	
	private void addDeptEmp(Employee e)//By Department, hopefully; O(2n) as each dept is only interacted with separately;
	{
		
		switch (e.getDepartment())
		{
			case "TECHNICAL":
			{
				if(empTech.isEmpty()) //O(1)
				{
					empTech.push(e);
				}
				
				while(!empTech.contains(e) && !empTech.isEmpty()) //O(n)
				{
					Employee tempEmp = empTech.peek();
					if(tempEmp.compareTo(e) == -1)
					{
						tempStTech.push(empTech.pop());
					}
					else break;
				}
				if(!empTech.contains(e)) //O(1), I know it is rendered useless by the above while loop but if I remove it the program fails
				{
					empTech.push(e);
				}
				empTech.addAll(tempStTech); //O(n)
				break;
			}
			
			case "FINANCIAL":
			{
				if(empFin.isEmpty())
				{
					empFin.push(e);
				}
				
				while(!empFin.contains(e) && !empFin.isEmpty())
				{
					Employee tempEmp = empFin.peek();
					if(tempEmp.compareTo(e) == -1)
					{
						tempStFin.push(empFin.pop());
					}
					else break;
				}
				if(!empFin.contains(e))
				{
					empFin.push(e);
				}
				empFin.addAll(tempStFin);
				break;
			}
			
			case "MARKETING":
			{
				if(empMark.isEmpty())
				{
					empMark.push(e);
				}
				
				while(!empMark.contains(e) && !empMark.isEmpty())
				{
					Employee tempEmp = empMark.peek();
					if(tempEmp.compareTo(e) == -1)
					{
						tempStMark.push(empMark.pop());
					}
					else break;
				}
				if(!empMark.contains(e))
				{
					empMark.push(e);
				}
				empMark.addAll(tempStMark);
				break;
			}
			
			case "SUPPORT":
			{
				if(empSupp.isEmpty())
				{
					empSupp.push(e);
				}
				
				while(!empSupp.contains(e) && !empSupp.isEmpty())
				{
					Employee tempEmp = empSupp.peek();
					if(tempEmp.compareTo(e) == -1)
					{
						tempStSupp.push(empSupp.pop());
					}
					else break;
				}
				if(!empSupp.contains(e))
				{
					empSupp.push(e);
				}
				empSupp.addAll(tempStSupp);
				break;
			}
				default:
					break;
		}
	}
	
	public  void layoffAll(String date)//(MM/DD/YYYY) O(3n)
	{
		Employee layoff = new Employee("Null Null", date, "Null");
		System.out.println(layoff.getDateHired());
		Stack<Employee> fired = new Stack<Employee>();
		Stack<Employee> temp = new Stack<Employee>();
		int i = 0;
		int j = emp.size();
		while(i<j)
		{
			if(layoff.compareTo(emp.peek()) == -1)
			{
				fired.push(emp.pop());
			}
			else 
			{
				temp.push(emp.pop());
			}
			i++;
		}
		while(!temp.isEmpty()) //O(n)
		{
			emp.add(temp.pop());
		}
		if(!fired.isEmpty())
		{
			System.out.println("Employees fired:");
			for(Employee f: fired) //O(n)
			{
				System.out.println(f.getName());
			}
		}
	}
	
	public void printEmpStackAll()//Clears for now, probably should use a copy for best practice after cleaning up
	{
		System.out.println("All Employees:");
		if(emp.isEmpty()) System.out.println("No employees");
		while(!emp.isEmpty()) //O(n)
		{
			Employee e = emp.pop();
			System.out.println(e.getName());
		}
	}
	
	public void printDepartmentStacks() //O(4n)
	{
		System.out.println("List of Employees in Technical:");
		if(empTech.isEmpty()) System.out.println("No employees");
		for(Employee e : empTech)
		{
			System.out.println(e.getName());
		}
		System.out.println("\nList of Employees in Support:");
		if(empSupp.isEmpty()) System.out.println("No employees");
		for(Employee e: empSupp)
		{
			System.out.println(e.getName());
		}
		System.out.println("\nList of Employees in Marketing:");
		if(empMark.isEmpty()) System.out.println("No employees");
		for(Employee e : empMark) 
		{
			System.out.println(e.getName());
		}
		System.out.println("\nList of Employee in Financial:");
		if(empFin.isEmpty()) System.out.println("No employess");
		for(Employee e : empFin)
		{
			System.out.println(e.getName());
		}
	}
	
	public void layoffDept(String date, String dept)//(MM/DD/YYYY, Financial/Support/Technical/Marketing) //O(n), one dept at a time.
	{
		Employee layoff = new Employee("Null Null", date, dept);
		Stack<Employee> fired = new Stack<Employee>();
		Stack<Employee> temp = new Stack<Employee>();
		switch (layoff.getDepartment())
		{
			case "TECHNICAL":
			{
				int i = 0;
				int j = empTech.size();
				while(i<j)
				{
					if(layoff.compareTo(empTech.peek()) == -1)
					{
						fired.push(empTech.pop());
					}
					else
					{
						temp.push(empTech.pop());
					}
					i++;
				}
				break;
			}
			
			case "FINANCIAL":
			{
				int i = 0;
				int j = empFin.size();
				while(i<j)
				{
					if(layoff.compareTo(empFin.peek()) == -1)
					{
						fired.push(empFin.pop());
					}
					else
					{
						temp.push(empFin.pop());
					}
					i++;
				}
				break;
			}
			
			case "MARKETING":
			{
				int i = 0;
				int j = empMark.size();
				while(i<j)
				{
					if(layoff.compareTo(empMark.peek()) == -1)
					{
						fired.push(empMark.pop());
					}
					else
					{
						temp.push(empMark.pop());
					}
					i++;
				}
				break;
			}
			
			case "SUPPORT":
			{
				int i = 0;
				int j = empSupp.size();
				while(i<j)
				{
					if(layoff.compareTo(empSupp.peek()) == -1)
					{
						fired.push(empSupp.pop());
					}
					else
					{
						temp.push(empSupp.pop());
					}
					i++;
				}
				break;
			}
		}
		
		while(!temp.isEmpty())
		{
			addDeptEmp(temp.pop());
		}
		System.out.println("Employees fired from " + dept + ":");
		for(Employee f: fired)
		{
			System.out.println(f.getName());
		}
	}
}