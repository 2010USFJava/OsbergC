package com.revature.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.assignment.MenuItem;
import com.revature.assignment.Driver;

public class AddItemsTest {
	static MenuItem a = new MenuItem("Hot dog", 2.50);
	static MenuItem b = new MenuItem("Grilled Cheese", 3.00);
	
	@BeforeClass
	
		
	
	
	@Test
	public void test() {
		double expected = 3.0 + 2.5;
		MenuItem[] items = {a, b};
		double result = Driver.addItems(items);
		assertEquals(expected, result, 0.001);
	}

}
