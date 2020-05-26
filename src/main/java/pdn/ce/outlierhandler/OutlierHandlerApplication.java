package pdn.ce.outlierhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import pdn.ce.outlierhandler.modules.coremodule.sevice.ProcessSchedulingService;

@SpringBootApplication
@EnableScheduling
public class OutlierHandlerApplication {
	@Autowired
	ProcessSchedulingService processSchedulingService;

	public static void main(String[] args) {
		SpringApplication.run(OutlierHandlerApplication.class, args);
	}

	@Scheduled(cron = "15 * * * * ?")
	public void processSchedulerCronJob() {
		// processSchedulingService.runUnfinishedTask();
	}
}
