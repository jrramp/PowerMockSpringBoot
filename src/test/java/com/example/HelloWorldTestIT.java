package com.example;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



@RunWith(PowerMockRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = HelloWorldTestIT.SpringTestConfig.class)
//@SpringApplicationConfiguration(classes = HelloWorldTestIT.SpringTestConfig.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PrepareForTest(CustomIDGenerator.class)
public class HelloWorldTestIT
{

    @Configuration
    @EnableAutoConfiguration
    @ComponentScan("com.example")
    public static class SpringTestConfig
    {
    }
    
    private MockMvc mockMvc;
    
    @Before
    public void setUp() throws Exception
    {
        PowerMockito.mockStatic(CustomIDGenerator.class);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Assert.assertNotNull("WebApplicationContext setup failed.", mockMvc);
        Mockito.when(CustomIDGenerator.getId()).then(new Answer<String>() {

			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				return "ABCDEFGH";
			}
		});
    }
  

    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Test
    public void testRest() throws Exception{
    	final String url = "/hello";
    	final String contentAsString = mockMvc.perform(get(url))
                // then check if returns success status code
                .andExpect(status().isOk())
                // and check if it has JSON contents
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                // and check if we get UUID
       
                .andReturn().getResponse().getContentAsString();
    	System.out.println(contentAsString);
    }

}