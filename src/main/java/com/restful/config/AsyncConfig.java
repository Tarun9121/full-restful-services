package com.restful.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * When you mark a class with @Configuration, Spring treats it as a source of bean definitions and configuration. During the startup process, Spring scans the class for any configurations or beans that need to be created or managed.
 *
 * <h3>Bean_Registration:</h3>
 * <p>Spring will automatically register the beans defined in the class. For example, if you define a bean with @Bean inside a @Configuration class, Spring will create and manage that bean throughout the application lifecycle.</p>
 *
 * <h3>Configuration Checks:</h3>
 * <p>Spring will read the configuration class during application startup, checking for things like:</p>
 *
 * <li>Custom bean definitions using @Bean.</li>
 * <li>Enabling certain features, like @EnableAsync, @EnableScheduling, or @EnableWebSecurity, etc.</li>
 * <li>Any properties or external configurations that need to be injected.</li>
 *
 * <h3>Enable Additional Features:</h3>
 * When you use @EnableAsync in a @Configuration class, Spring will look for this during the startup process. It will set up an Executor (the thread pool) and other necessary components to support asynchronous processing.
 */

@Configuration
@EnableAsync
public class AsyncConfig {

}
