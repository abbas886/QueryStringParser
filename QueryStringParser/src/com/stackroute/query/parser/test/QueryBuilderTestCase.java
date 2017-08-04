package com.stackroute.query.parser.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.stackroute.query.parser.AggregateFunction;
import com.stackroute.query.parser.QueryParameter;
import com.stackroute.query.parser.QueryParser;
import com.stackroute.query.parser.Restriction;

public class QueryBuilderTestCase {

	private static QueryParser queryParser;
	private static QueryParameter queryParameter;

	private String queryString;

	@BeforeClass
	public static void init() {
		queryParser = new QueryParser();
	}

	@Test
	public void getFileNameTestCase() {
		queryString = "select * from employe.csv";
		queryParameter = queryParser.parseQuery(queryString);
		assertEquals("employe.csv", queryParameter.getFile());
		display(queryString, queryParameter);
	}

	@Test
	public void getFieldsTestCase() {
		queryString = "select id, name, salary from employe.csv";
		queryParameter = queryParser.parseQuery(queryString);
		List<String> expectedFields = new ArrayList<>();
		expectedFields.add("id");
		expectedFields.add("name");
		expectedFields.add("salary");
		assertArrayEquals(expectedFields.toArray(), queryParameter.getFields().toArray());
		display(queryString, queryParameter);
	}
	
	@Test
	public void getFieldsAndRestrictionsTestCase() {
		queryString = "select id, name, salary from employe.csv where salary>10000 and department = 'HR'";
		queryParameter = queryParser.parseQuery(queryString);
		List<Restriction> restrictions = queryParameter.getRestrictions();
		assertNotNull(restrictions);;
		
		display(queryString, queryParameter);
	}
	
	@Test
	public void getRestrictionsAndAggregateFunctionsTestCase() {
		queryString = "select count(*), avg(salary), min(basic), max(age) from employe.csv where salary>10000 and department = 'HR'";
		queryParameter = queryParser.parseQuery(queryString);
		List<Restriction> restrictions = queryParameter.getRestrictions();
		assertNotNull(restrictions);;
		
		display(queryString, queryParameter);
	}
	
	@Test
	public void getGrouByAndOrderByTestCase() {
		queryString = "select id,name,salary, department from employe.csv where salary>10000 and department = 'HR' group by department order by salary";
		queryParameter = queryParser.parseQuery(queryString);
		List<Restriction> restrictions = queryParameter.getRestrictions();
		assertNotNull(restrictions);;
		
		display(queryString, queryParameter);
	}
	@Test
	public void getGrouByTestCase() {
		queryString = "select  department, count(*) from employe.csv where salary>10000 group by department";
		queryParameter = queryParser.parseQuery(queryString);
		List<Restriction> restrictions = queryParameter.getRestrictions();
		assertNotNull(restrictions);;
		
		display(queryString, queryParameter);
	}

	@Test
	public void getOrderByAndWhereConditionTestCase() {
		queryString = "select id,name,salary, department from employe.csv where salary>10000 and department = 'HR' order by salary";
		queryParameter = queryParser.parseQuery(queryString);
		List<Restriction> restrictions = queryParameter.getRestrictions();
		assertNotNull(restrictions);;
		
		display(queryString, queryParameter);
	}
	
		
	@Test
	public void getOrderByTestCase() {
		queryString = "select city,name,salary from d:/emp.csv where city=Bangalore order by salary";
		queryParameter = queryParser.parseQuery(queryString);
		List<Restriction> restrictions = queryParameter.getRestrictions();
		assertNotNull(restrictions);;
		
		display(queryString, queryParameter);
	}
	@Test
	public void getOrderByWihtoutWhereTestCase() {
		queryString = "select city,name,salary from d:/emp.csv  order by salary";
		queryParameter = queryParser.parseQuery(queryString);
		List<String> orderByFields = queryParameter.getOrderByFields();
		assertNotNull(orderByFields);;
		
		display(queryString, queryParameter);
	}
	
	@Test
	public void getGroupeByWithoutWhereTestCase() {
		queryString = "select department, count(*) from d:/emp.csv group by department";
		queryParameter = queryParser.parseQuery(queryString);
		List<String> groupByFields = queryParameter.getGroupByFields();
		assertNotNull(groupByFields);;
		
		display(queryString, queryParameter);
	}



	private void display(String queryString, QueryParameter queryParameter) {
		System.out.println("\nQuery : " + queryString);
		System.out.println("--------------------------------------------------");
		System.out.println("Base Query:" + queryParameter.getBaseQuery());
		System.out.println("File:" + queryParameter.getFile());
		System.out.println("Query Type:" + queryParameter.getQUERY_TYPE());
		List<String> fields = queryParameter.getFields();

		System.out.println("Selected field(s):");
		if (fields == null || fields.isEmpty()) {
			System.out.println("*");
		} else {
			for (String field : fields) {
				System.out.println("\t" + field);
			}
		}
		
		List<Restriction> restrictions = queryParameter.getRestrictions();
		
		if(restrictions!=null && !restrictions.isEmpty())
		{
			System.out.println("Where Conditions : ");
			int conditionCount=1;
			for(Restriction restriction :restrictions )
			{
				System.out.println("\tCondition : " + conditionCount++);
				System.out.println("\t\tName : "+restriction.getPropertyName());
				System.out.println("\t\tCondition : "+restriction.getCondition());
				System.out.println("\t\tValue : "+restriction.getPropertyValue());
			}
		}

		List<AggregateFunction>  aggregateFunctions = queryParameter.getAggregateFunctions();
		if(aggregateFunctions!=null && !aggregateFunctions.isEmpty()){
			
			System.out.println("Aggregate Functions : ");
			int funtionCount=1;
			for(AggregateFunction aggregateFunction :aggregateFunctions )
			{
				System.out.println("\t Aggregate Function : " + funtionCount++);
				System.out.println("\t\t function : "+aggregateFunction.getFunction());
				System.out.println("\t\t  field : "+aggregateFunction.getField());
				System.out.println("\t\t  index : "+aggregateFunction.getAggregateFieldIndex());
			}
			
		}
		
		List<String>  orderByFields = queryParameter.getOrderByFields();
		if(orderByFields!=null && !orderByFields.isEmpty()){
			
			System.out.println(" Order by fields : ");
			for(String orderByField :orderByFields )
			{
				System.out.println("\t "+orderByField);
				
			}
			
		}
		
		List<String>  groupByFields = queryParameter.getGroupByFields();
		if(groupByFields!=null && !groupByFields.isEmpty()){
			
			System.out.println(" Group by fields : ");
			for(String groupByField :groupByFields )
			{
				System.out.println("\t "+groupByField);
				
			}
			
		}

	}
}

















