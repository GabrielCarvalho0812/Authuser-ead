package com.ead.authuser.clients;

import com.ead.authuser.dtos.CourseRecordDto;
import com.ead.authuser.dtos.ResponsePageDto;
import io.github.resilience4j.retry.annotation.Retry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CourseClient {

    Logger logger = LogManager.getLogger(CourseClient.class);

    @Value("${ead.api.url.course}")
    String baseUrlCourse;

    final RestClient restClient;

    public CourseClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.build();
    }

    @Retry(name = "retryInstance", fallbackMethod = "retryfallback")
    public Page<CourseRecordDto> getAllCoursesByUser(UUID userId, Pageable pageable) {
        String url = baseUrlCourse + "/courses?userId/" + userId + "/&pageable=" + pageable.getPageNumber() + "&size=" + pageable.getPageSize()
                + pageable.getPageSize() + "&sort=" + pageable.getSort().toString().replaceAll(": ", ",");
            logger.debug("Resquest Url: {}", url);

        try {
            //buscar uma listagem de recursos no outro microsservice
            return restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(new ParameterizedTypeReference<ResponsePageDto<CourseRecordDto>>() {});


        }catch (RestClientException e){
            logger.error("Error Request RestClient with cause: {} ", e.getMessage());
            throw new RuntimeException("Error Request RestClient", e);
        }
    }

    public Page<CourseRecordDto> retryfallback(UUID userId, Pageable pageable, Throwable t) {
        logger.error("Inside retry retryfallback, cause - {}",t.toString());
        List<CourseRecordDto> searchResult = new ArrayList<>();
        return new PageImpl<>(searchResult);
    }

}



