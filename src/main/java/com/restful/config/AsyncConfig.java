package com.restful.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * When you mark a class with @Configuration, Spring treats it as a source of bean definitions and configuration.
 * During the startup process, Spring scans the class for any configurations or beans that need to be created or managed.
 *
 * Bean_Registration:
 * Spring will automatically register the beans defined in the class. For example, if you define a bean with @Bean inside
 * a @Configuration class, Spring will create and manage that bean throughout the application lifecycle.
 *
 * Configuration Checks:
 * Spring will read the configuration class during application startup, checking for things like:
 *
 * Custom bean definitions using @Bean.
 * Enabling certain features, like @EnableAsync, @EnableScheduling, or @EnableWebSecurity, etc.
 * Any properties or external configurations that need to be injected.
 *
 * Enable Additional Features:
 * When you use @EnableAsync in a @Configuration class, Spring will look for this during the startup process.
 * It will set up an Executor (the thread pool) and other necessary components to support asynchronous processing.
 *
 * @EnableAsync annotation:
 * Purpose: The annotation attribute allows us to specify the types of annotations that the framework should recognize as
 * markers for asynchronous execution.
 *
 * Default Behavior: By default, @EnableAsync detects Spring's @Async annotation as well as the EJB 3.1 javax.ejb.Asynchronous annotation.
 *
 * Customization: You can configure this option to detect other, custom annotations defined by the user to mark methods for asynchronous execution.
 * This adds flexibility, allowing different types of annotations to trigger async behavior.
 */

@Configuration
@EnableAsync
public class AsyncConfig {

}
