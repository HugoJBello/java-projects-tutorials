package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.GetOutputRequest;
import io.spring.guides.gs_producing_web_service.GetOutputResponse;
import io.spring.guides.gs_producing_web_service.Output;

@Endpoint
public class OutputEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	private OutputRepository countryRepository;

	@Autowired
	public OutputEndpoint(OutputRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOutputRequest")
	@ResponsePayload
	public GetOutputResponse getOutput(@RequestPayload GetOutputRequest request) {
		GetOutputResponse response = new GetOutputResponse();
//		Output out = new Output();
//		out.setOutputString("bla bla " + request.getInputString());
//		response.setOutput(out);

		response.setOutput(countryRepository.findOutput(request.getInputString()));
		return response;
	}
}
