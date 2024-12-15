package io.github.harryjhin

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameter
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class TestJobConfiguration(
    private val jobLauncher: JobLauncher,
    private val jobRepository: JobRepository,
    private val platformTransactionManager: PlatformTransactionManager,
) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Scheduled(cron = "0 0/1 * * * *")
    fun testJobLauncher() {
        jobLauncher.run(
            testJob(),
            JobParametersBuilder()
                .addJobParameter("executedTime", JobParameter(System.currentTimeMillis(), Long::class.java))
                .toJobParameters()
        )
    }

    @Bean
    fun testJob(): Job = JobBuilder("testJob", jobRepository)
        .incrementer(RunIdIncrementer())
        .start(step1())
        .build()

    @Bean
    fun step1(): Step = StepBuilder("step1", jobRepository)
        .tasklet(
            { contribution, chunkContext ->
                logger.info("Hello, World!")
                return@tasklet RepeatStatus.FINISHED
            },
            platformTransactionManager
        )
        .build()
}