package com.stack.platform.services;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;

import com.netflix.hystrix.Hystrix;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({ "javax.management.*", "javax.xml.parsers.*", "com.sun.org.apache.xerces.internal.jaxp.*", 
	"ch.qos.logback.*", "org.slf4j.*" })
public abstract class BaseServiceTests {
	
	@BeforeClass
    public static void beforeClass(){		
		Hystrix.reset();
	}
}