package com.springmaven.ipldashbaord.data;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.springmaven.ipldashbaord.model.Match;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    private final String[] FIELD_NAMES = new String[] { "season", "id", "name", "short_name", "description",
            "home_team", "away_team", "toss_won", "decision", "first_inning_score", "second_inning_score", "home_score",
            "away_score", "winner", "result",
            "start_date", "end_date", "venue_id", "venue_name", "home_captain", "away_captain", "pom", "points",
            "super_over", "home_overs", "home_runs", "home_wicket", "home_boundaries", "away_overs", "away_runs",
            "away_wickets", "away_boundaries", "highlights", "home_key_batsman", "home_key_bowler", "home_playx1",
            "away_playx1", "away_key_batsman", "away_key_bowler", "match_days", "umpire1", "umpire2", "tv_umpire",
            "referee", "reserve_umpire" };

    @Bean
    public FlatFileItemReader<MatchInput> reader() {
        return new FlatFileItemReaderBuilder<MatchInput>()
                .name("MatchItemReader")
                .resource(new ClassPathResource("all_season_summary.csv"))
                .delimited()
                .names(FIELD_NAMES)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<MatchInput>() {
                    {
                        setTargetType(MatchInput.class);
                    }
                })
                .build();
    }

    @Bean
    public MatchDataProcessor processor() {
        return new MatchDataProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
        JdbcBatchItemWriter<Match> batchItemWriter = new JdbcBatchItemWriter<>();
        final String QUERY_INSERT = "insert into Match (season, id, description, team1, team2, toss_winner, toss_decision, score1, score2, key_batsmen1, key_batsmen2, key_bowler1, key_bowler2, result, venue_name, pom, date, match_winner) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        batchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        batchItemWriter.setDataSource(dataSource);
        batchItemWriter.setSql(QUERY_INSERT);
        ItemPreparedStatementSetter<Match> valueSetter = new MatchPreparedStatementSetter();
        batchItemWriter.setItemPreparedStatementSetter(valueSetter);
        return batchItemWriter;
    }

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
    public Step step1(JdbcBatchItemWriter<Match> writer) {
        return (Step) stepBuilderFactory
                .get("step1")
                .<MatchInput, Match>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

}
