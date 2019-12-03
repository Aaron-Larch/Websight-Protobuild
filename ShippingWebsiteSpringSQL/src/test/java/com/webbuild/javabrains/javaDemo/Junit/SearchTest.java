package com.webbuild.javabrains.javaDemo.Junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.Test;

import com.webbuild.javabrains.Operations.SimpleSerch;

class SearchTest {
	
	//Test the library to see if it Knows all symbols used for comparison
	@Test
	void dynamicparsetest_AverageSymbol() {
		//Given
		String sample="Average >= 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("Average", is(tstarray[0]));
		assertThat(">=", is(tstarray[1]));
		assertThat("20", is(tstarray[2]));
	}

	@Test
	void dynamicparsetest_MedianSymbol() {
		//Given
		String sample="Median > 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("Median", is(tstarray[0]));
		assertThat(">", is(tstarray[1]));
		assertThat("20", is(tstarray[2]));
	}
	
	@Test
	void dynamicparsetest_MaxSymbol() {
		//Given
		String sample="Max <= 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("Max", is(tstarray[0]));
		assertThat("<=", is(tstarray[1]));
		assertThat("20", is(tstarray[2]));
	}
	
	@Test
	void dynamicparsetest_MinSymbol() {
		//Given
		String sample="Min = 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("Min", is(tstarray[0]));
		assertThat("=", is(tstarray[1]));
		assertThat("20", is(tstarray[2]));
	}
	
	@Test
	void dynamicparsetest_MeanSymbol() {
		//Given
		String sample="Mean < 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("Mean", is(tstarray[0]));
		assertThat("<", is(tstarray[1]));
		assertThat("20", is(tstarray[2]));
	}
	
	@Test
	void dynamicparsetest_Symbol() {
		//Given
		String sample="Mean != 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("Mean", is(tstarray[0]));
		assertThat("!=", is(tstarray[1]));
		assertThat("20", is(tstarray[2]));
	}
	
	//Test the library to see if it Knows all words associated with comparison
	@Test
	void dynamicparsetest_lessthan() {
		//Given
		String sample="Average less than 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("Average", is(tstarray[0]));
		assertThat("less than", is(tstarray[1]));
		assertThat("20", is(tstarray[2]));
	}

	@Test
	void dynamicparsetest_Greaterthan() {
		//Given
		String sample="Median Greater than 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("Median", is(tstarray[0]));
		assertThat("Greater than", is(tstarray[1]));
		assertThat("20", is(tstarray[2]));
	}
	
	@Test
	void dynamicparsetest_equalto() {
		//Given
		String sample="Max equal to 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("Max", is(tstarray[0]));
		assertThat("equal to", is(tstarray[1]));
		assertThat("20", is(tstarray[2]));
	}
	
	@Test
	void dynamicparsetest_lessthanorequalto() {
		//Given
		String sample="Min less than or equal to 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("Min", is(tstarray[0]));
		assertThat("less than or equal to", is(tstarray[1]));
		assertThat("20", is(tstarray[2]));
	}
	
	@Test
	void dynamicparsetest_Greaterthanorequalto() {
		//Given
		String sample="Mean Greater than or equal to 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("Mean", is(tstarray[0]));
		assertThat("Greater than or equal to", is(tstarray[1]));
		assertThat("20", is(tstarray[2]));
	}
	
	@Test
	void dynamicparsetest_equal() {
		//Given
		String sample="Max equal 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("Max", is(tstarray[0]));
		assertThat("equal", is(tstarray[1]));
		assertThat("20", is(tstarray[2]));
	}
	
	@Test
	void dynamicparsetest_equals() {
		//Given
		String sample="Min equals 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("Min", is(tstarray[0]));
		assertThat("equals", is(tstarray[1]));
		assertThat("20", is(tstarray[2]));
	}

	@Test
	void dynamicparsetest_doesnotequal() {
		//Given
		String sample="Mean does not equal 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("Mean", is(tstarray[0]));
		assertThat("does not equal", is(tstarray[1]));
		assertThat("20", is(tstarray[2]));
	}

	//Test Primary key search phrases
	@Test
	void dynamicparsetest_Name() {
		//Given
		String sample="Name contains 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("Name", is(tstarray[0]));
		assertThat("contains", is(tstarray[1]));
		assertThat("20", is(tstarray[2]));
	}

	@Test
	void dynamicparsetest_reportid() {
		//Given
		String sample="report id contains the number 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("report id", is(tstarray[0]));
		assertThat("contains the number", is(tstarray[1]));
		assertThat("20", is(tstarray[2]));
	}

	@Test
	void dynamicparsetest_recordname() {
		//Given
		String sample="record name contains 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("record name", is(tstarray[0]));
		assertThat("contains", is(tstarray[1]));
		assertThat("20", is(tstarray[2]));
	}

	@Test
	void dynamicparsetest_primarykey() {
		//Given
		String sample="primary key contains the number 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("primary key", is(tstarray[0]));
		assertThat("contains the number", is(tstarray[1]));
		assertThat("20", is(tstarray[2]));
	}

	@Test
	void dynamicparsetest_mode() {
		//Given
		String sample="mode of 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("mode", is(tstarray[0]));
		assertThat("of", is(tstarray[1]));
		assertThat("20", is(tstarray[2]));
	}
	
	//Error handling tests
	@Test
	void dynamicparsetest_wrongworderror() {
		//Given
		String sample="wax contains the number 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("ErrCollume", is(tstarray[0]));
	}
	
	@Test
	void dynamicparsetest_wrongnumbererror() {
		//Given
		String sample="Max contains the number pie";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("ErrValue", is(tstarray[0]));
	}
	
	@Test
	void dynamicparsetest_ModeOrdererror() {
		//Given
		String sample="Mode contains 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("ErrOrder", is(tstarray[0]));
	}
	
	@Test
	void dynamicparsetest_outsideOrdererror() {
		//Given
		String sample="Average of 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("ErrOrder", is(tstarray[0]));
	}
	
	@Test
	void dynamicparsetest_wordsoutofOrdererror() {
		//Given
		String sample="key primary of 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("ErrCollume", is(tstarray[0]));
	}
	
	@Test
	void dynamicparsetest_compairisonerror() {
		//Given
		String sample="min pickle 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("ErrCompair", is(tstarray[0]));
	}
	
	@Test
	void dynamicparsetest_sizeerror() {
		//Given
		String sample="min pickle20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("Error", is(tstarray[0]));
	}
	
	@Test
	void dynamicparsetest_modecontainserror() {
		//Given
		String sample="min contains 20";
		//When
		String[] tstarray= SimpleSerch.dynamicparse(sample);
		//Then
		assertThat("ErrOrder", is(tstarray[0]));
	}
}
