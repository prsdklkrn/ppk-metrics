package com.ppk.metrics.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;

@Configuration
@EnableMetrics
public class SpringMetricsConfig extends MetricsConfigurerAdapter {

	@Override
	public void configureReporters(MetricRegistry metricRegistry) {
		registerReporter(getConsoleReporter(metricRegistry));
		registerReporter(getJMXReporter(metricRegistry));
	}

	private ConsoleReporter getConsoleReporter(MetricRegistry metricRegistry) {
		ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry).convertRatesTo(TimeUnit.SECONDS)
				.convertDurationsTo(TimeUnit.MILLISECONDS).build();
		reporter.start(1, TimeUnit.MINUTES);
		return reporter;
	}

	private JmxReporter getJMXReporter(MetricRegistry metricRegistry) {
		JmxReporter reporter = JmxReporter.forRegistry(metricRegistry).build();
		reporter.start();
		return reporter;
	}
}