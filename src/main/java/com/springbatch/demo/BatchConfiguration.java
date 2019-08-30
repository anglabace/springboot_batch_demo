package com.springbatch.demo;

/**
 * @author xiongchenyang
 * @Date 2019/8/23
 **/

import ch.qos.logback.core.net.server.Client;
import com.springbatch.po.ClientInfo;
import com.springbatch.po.Person;
import com.springbatch.service.ClientInfoBillFieldSetMapper;
import com.springbatch.service.ClientInfoItemProcessor;
import com.springbatch.service.ClientInfoItemProcessor2;
import com.springbatch.service.PersonItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.configuration.ListableJobLocator;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.explore.support.MapJobExplorerFactoryBean;
import org.springframework.batch.core.explore.support.SimpleJobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.TaskletStepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.*;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.orm.JpaNativeQueryProvider;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.support.DatabaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Resource
    private ClientInfoBillFieldSetMapper clientInfoBillFieldSetMapper;
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private JobExplorer jobExplorer;
    @Resource
    private EntityManagerFactory entityManagerFactory;

    // tag::readerwriterprocessor[]
    @Bean
    public FlatFileItemReader<Person> personReader() {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new ClassPathResource("sample-data.csv"))
                .delimited()
                .names(new String[]{"firstName", "lastName"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                    setTargetType(Person.class);
                }})
                .build();
    }
    @Bean
    public LineMapper<ClientInfo> clientInfoLineMapper(){
        DefaultLineMapper<ClientInfo> clientInfoDefaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(",");
//        delimitedLineTokenizer.setNames("clientNo","clientName","sex","idType","idNo","birthDate","country","nationality",
//                "marriage","localHouseFlag","nativePlaceFlag","idnoValidDate","nativeAddress","education","degree",
//                "schoolCode","graduationTime","employeeType","pricingType","civilServantType","occupationType");
        clientInfoDefaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        clientInfoDefaultLineMapper.setFieldSetMapper(clientInfoBillFieldSetMapper);
        return clientInfoDefaultLineMapper;

    }

    @Bean
    public FlatFileItemReader<ClientInfo> clientInfoReader(){
        FlatFileItemReader<ClientInfo> clientInfoFlatFileItemReader = new FlatFileItemReader<>();
        clientInfoFlatFileItemReader.setResource(new ClassPathResource("client_info.txt"));
        clientInfoFlatFileItemReader.setLineMapper(new DefaultLineMapper<ClientInfo>(){{
            setLineTokenizer(new DelimitedLineTokenizer(){{
                setNames(new String[]{"clientNo","clientName","sex","idType","idNo","birthDate","country","nationality",
                        "marriage","localHouseFlag","nativePlaceFlag","idnoValidDate","nativeAddress","education","degree",
                        "schoolCode","graduationTime","employeeType","pricingType","civilServantType","occupationType","createdDate","createdBy","updatedDate","updatedBy"});
//                setDelimiter(",");
            }});
            setFieldSetMapper(clientInfoBillFieldSetMapper);
//            setFieldSetMapper(new BeanWrapperFieldSetMapper<ClientInfo>() {{
//                setTargetType(ClientInfo.class);
//            }});
        }});
        return clientInfoFlatFileItemReader;
    }

    @Bean
    public JpaPagingItemReader<ClientInfo> clientInfoReader2(){
        JpaPagingItemReader<ClientInfo> jpaPagingItemReader = new JpaPagingItemReader<>();
        JpaNativeQueryProvider<ClientInfo> jpaNativeQueryProvider = new JpaNativeQueryProvider<>();
        jpaPagingItemReader.setEntityManagerFactory(entityManagerFactory);
        jpaNativeQueryProvider.setSqlQuery("select * from client_info_temp");
        jpaNativeQueryProvider.setEntityClass(ClientInfo.class);
        try {
            jpaNativeQueryProvider.afterPropertiesSet();

            jpaPagingItemReader.setPageSize(3);
            jpaPagingItemReader.setQueryProvider(jpaNativeQueryProvider);
            jpaPagingItemReader.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //所有ItemReader和ItemWriter实现都会在ExecutionContext提交之前将其当前状态存储在其中,如果不希望这样做,可以设置setSaveState(false)
        jpaPagingItemReader.setSaveState(true);
        return jpaPagingItemReader;
    }

    @Bean
    public PersonItemProcessor personItemProcessor() {
        return new PersonItemProcessor();
    }

    @Bean
    public ClientInfoItemProcessor clientInfoItemProcessor(){
        return new ClientInfoItemProcessor();
    }

    @Bean(name = "clientInfoItemProcessor2")
    public ClientInfoItemProcessor2 clientInfoItemProcessor2(){
        return new ClientInfoItemProcessor2();
    }

    @Bean
    public JdbcBatchItemWriter<Person> personWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Person>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<ClientInfo> clientInfoWriter(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<ClientInfo>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO client_info_temp(client_no,client_name,sex,id_type,id_no,birth_date,country,nationality,marriage,local_house_flag,native_place_flag,idno_valid_date," +
                        "native_address,education,degree,school_code,graduation_time,employee_type,pricing_type,civil_servant_type,occupation_type,created_by,created_date,updated_by,updated_date) " +
                        "VALUES (:clientNo,:clientName,:sex,:idType,:idNo,:birthDate,:country,:nationality,:marriage,:localHouseFlag,:nativePlaceFlag," +
                        ":idnoValidDate,:nativeAddress,:education,:degree,:schoolCode,:graduationTime,:employeeType,:pricingType,:civilServantType,:occupationType,:createdBy,:createdDate,:updatedBy,:updatedDate)")
                .dataSource(dataSource)
                .build();
    }


    @Bean
    public JpaItemWriter<ClientInfo> clientInfoWriter2(){
        JpaItemWriter<ClientInfo> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemWriter;
    }
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Job importClientInfoJob(ClientInfoNotificationListener clientInfoNotificationListener , Step clientInfoStep1,Step clientInfoStep2){
        return jobBuilderFactory.get("importClientInfoJob")
                .incrementer(new RunIdIncrementer())
                .listener(clientInfoNotificationListener)
                .flow(clientInfoStep1)
                .next(clientInfoStep2)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Person> personWriter) {
        return stepBuilderFactory.get("step1")
                .<Person, Person> chunk(2)
                .reader(personReader())
                .processor(personItemProcessor())
                .writer(personWriter)
                .build();
    }

    @Bean
    public Step clientInfoStep1(JdbcBatchItemWriter<ClientInfo> clientInfoWriter) {
        return stepBuilderFactory.get("clientInfoStep1")
                .<ClientInfo, ClientInfo> chunk(Integer.MAX_VALUE)
                .reader(clientInfoReader())
                .processor(clientInfoItemProcessor())
                .writer(clientInfoWriter)
                .build();
    }

    @Bean
    public Step clientInfoStep2(JpaItemWriter<ClientInfo> clientInfoWriter2){
        return  stepBuilderFactory.get("clientInfoStep2")
                .<ClientInfo, ClientInfo> chunk(Integer.MAX_VALUE)
                .reader(clientInfoReader2())
                .processor(clientInfoItemProcessor2())
                .writer(clientInfoWriter2)
                .build();
    }
    

    @Bean
    public JobRepository jobRepository(@Qualifier("dataSource") DataSource dataSource, PlatformTransactionManager transactionManager)throws Exception{

        JobRepositoryFactoryBean jobRepositoryFactoryBean =
                new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDatabaseType(DatabaseType.MYSQL.name());
        return jobRepositoryFactoryBean.getObject();
    }


    /**
     * JobLauncher定义，用来启动Job的接口
     * @param dataSource
     * @param transactionManager
     * @return
     * @throws Exception
     */
    @Bean
    public SimpleJobLauncher jobLauncher(@Qualifier("dataSource") DataSource dataSource, PlatformTransactionManager transactionManager)throws Exception{
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository(dataSource, transactionManager));
        return jobLauncher;
    }

    @Bean
    public SimpleJobOperator jobOperator(@Qualifier("dataSource") DataSource dataSource,@Qualifier("jobRepository") JobRepository jobRepository, @Qualifier("jobLauncher")JobLauncher jobLauncher) throws Exception {
        SimpleJobOperator jobOperator = new SimpleJobOperator();
        jobOperator.setJobRepository(jobRepository);
        jobOperator.setJobLauncher(jobLauncher);
        jobOperator.setJobRegistry(new MapJobRegistry());
//        JobExplorerFactoryBean jobExplorerFactoryBean = new JobExplorerFactoryBean();
//        MapJobExplorerFactoryBean mapJobExplorerFactoryBean = new MapJobExplorerFactoryBean();
//        mapJobExplorerFactoryBean.setRepositoryFactory();
//        jobExplorerFactoryBean.setDataSource(dataSource);
//        jobExplorerFactoryBean.setJdbcOperations(jdbcTemplate);
//        JobExplorer jobExplorer = mapJobExplorerFactoryBean.getObject();
        jobOperator.setJobExplorer(jobExplorer);
        return jobOperator;
    }

    // end::jobstep[]
}
