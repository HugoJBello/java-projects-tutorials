package com.example;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import io.spring.guides.gs_producing_web_service.GetOutput;

import org.springframework.stereotype.Component;
 
@Component
public class OutputRepository {
	private static final List<GetOutput> outList = new ArrayList<GetOutput>();

	@PostConstruct
	public void initData() {
		GetOutput out1 = new GetOutput();
		out1.setGetOutputString("output");

		outList.add(out1);
 
	}
	
	public GetOutput findOutput(String input){
		GetOutput result = new GetOutput();
		result.setGetOutputString("-----");

		for (GetOutput getOutput : outList) {
			if (input.contains(getOutput.getGetOutputString())) {
				result = getOutput;
			}
		}
		return result;
	}
	 
}
