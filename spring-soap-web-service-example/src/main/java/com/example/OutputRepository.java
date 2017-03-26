package com.example;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

 import io.spring.guides.gs_producing_web_service.Output;

import org.springframework.stereotype.Component;
 
@Component
public class OutputRepository {
	private static final List<Output> outList = new ArrayList<Output>();

	@PostConstruct
	public void initData() {
		Output out1 = new Output();
		out1.setOutputString("output");

		outList.add(out1);
 
	}
	
	public Output findOutput(String input){
		Output result = new Output();
		result.setOutputString("-----");

		for (Output country : outList) {
			if (input.contains(country.getOutputString())) {
				result = country;
			}
		}
		return result;
	}
	 
}
