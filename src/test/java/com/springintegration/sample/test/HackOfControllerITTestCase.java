package com.springintegration.sample.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "classpath:test-context.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class HackOfControllerITTestCase {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	@Autowired
	WebApplicationContext wac;

	@Autowired
	Filter testFilterChain;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(testFilterChain).build();
	}

	@Test
	public void switchTest() throws Exception {
		mockMvc.perform(post("/").with(new RequestPostProcessor() {

			@Override
			public MockHttpServletRequest postProcessRequest(MockHttpServletRequest _request) {
				System.err.println("Request in test: " + _request.toString());
				ServletWebRequest servletWebRequest = new ServletWebRequest(_request);
				RequestContextHolder.setRequestAttributes(servletWebRequest);

				return _request;
			}
		})

		).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());

	}

}
