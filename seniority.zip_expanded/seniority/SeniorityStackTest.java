package seniority;

import java.io.FileNotFoundException;

public class SeniorityStackTest 
{

	public static void main(String[] args) 
	{
		stackCreationFromFileTest();//Passed
		fireAlltest();//Passed
		fireDept_Support();//Passed
		githubEditTest();
	}

	private static void githubEditTest();
	{
		break;
	}
	
	private static void fireDept_Support() 
	{
		try
		{
			SeniorityStack ss = new SeniorityStack("seniority/test1.txt");
			System.out.println("Firing via department test:");
			ss.layoffDept("05/05/1919", "Support");
			ss.printDepartmentStacks();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		}
	}

	private static void fireAlltest()
	{
		try 
		{
			SeniorityStack ss = new SeniorityStack("seniority/test1.txt");
			System.out.println("Firing from entire company roster test:");
			ss.layoffAll("05/05/1969");
			ss.printEmpStackAll();
		} 		
		catch (FileNotFoundException e) 
		{
			System.out.println("File not Found");
		}
	}

	private static void stackCreationFromFileTest() 
	{
		try 
		{
			SeniorityStack ss = new SeniorityStack("seniority/test1.txt");
			System.out.println(".Read from file test:");
			ss.printEmpStackAll();
			ss.printDepartmentStacks();
		} catch (FileNotFoundException e) 
		{
			System.out.println("File not Found");
		}	
	}

}
